package de.ts.btin2.blogic;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;


//Databinding
import java.util.ArrayList;
import java.util.List;

import de.ts.btin2.blogic.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private VorwahlRepository repository;
    private VorwahlAdapter adapter;

    private ActivityMainBinding binding;

    private final List<Vorwahl> vorwahlList = new ArrayList<>();
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
        adapter = new VorwahlAdapter();
        binding.recyclerView.setAdapter(adapter);

        repository.getResults().observe(this, items -> {
            vorwahlList.clear();

            if (!items.isEmpty()) {
                vorwahlList.addAll(items);
                binding.tvVorwahlTitel.setText(String.valueOf(items.get(0).getVorwahl()));
                binding.tvOrtTitel.setText(items.get(0).getOrt());
            } else {
                binding.editVorwahl.setText("kein Treffer");
            }

            adapter.setListe(vorwahlList);
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

        repository.insert(new Vorwahl(vorwahl,ort));
    }
    public void loeschen(View v){
        String vorwahl = binding.editVorwahl.getText().toString();
        String ort = binding.editOrt.getText().toString();

        repository.delete(new Vorwahl(vorwahl,ort));
    }
    public void suchen(View v){ repository.find(binding.editSuchbegriff.getText().toString()); }
}