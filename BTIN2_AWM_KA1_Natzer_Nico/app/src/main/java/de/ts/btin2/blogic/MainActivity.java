package de.ts.btin2.blogic;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;


//Databinding
import java.util.List;

import de.ts.btin2.blogic.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {
    private VorwahlRepository repository;
    private VorwahlAdapter adapter;

    private ActivityMainBinding binding;

    private List vorwahlList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View root = binding.getRoot();
        setContentView(root);

        //Repository
        repository = new VorwahlRepository(getApplication());

        //RecyclerView


        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        adapter = new VorwahlAdapter(vorwahlList, listener);
        binding.recyclerView.setAdapter(adapter);

        //mViewModel = new MainViewModel(getApplication());
        repository.getResults().observe(this, vorwahlen -> {
            vorwahlList.clear();
            if (vorwahlen != null) {
                vorwahlList.addAll(vorwahlen);
            }
            adapter.setListe(vorwahlList);
        });
        repository.getResults().observe(this,items -> {
            if (!items.isEmpty())  {
                binding.tvVorwahlTitel.setText(String.valueOf(items.get(0).getVorwahl()));
                binding.tvOrtTitel.setText(items.get(0).getOrt());
            } else {
                binding.editVorwahl.setText("kein Treffer");
            }
        });


    }


    public void neu(View v){
        speichern(v);
        binding.editVorwahl.setText("");
        binding.editOrt.setText("");
    }

    public void speichern(View v){
        String vorwahl = binding.editVorwahl.getText().toString();
        String ort = binding.editOrt.getText().toString();

        Vorwahl vw = new Vorwahl(vorwahl,ort);
        repository.insert(vw);
    }

    public void loeschen(View v){
        String vorwahl = binding.editVorwahl.getText().toString();
        String ort = binding.editOrt.getText().toString();

        Vorwahl vw = new Vorwahl(vorwahl,ort);
        repository.delete(vw);
    }

    public void suchen(View v){
        String suchbegriff = binding.editSuchbegriff.getText().toString();

        repository.find(suchbegriff);

    }
}