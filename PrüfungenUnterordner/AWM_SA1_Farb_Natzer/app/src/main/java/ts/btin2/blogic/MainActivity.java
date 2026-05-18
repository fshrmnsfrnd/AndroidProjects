package ts.btin2.blogic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Objects;

import ts.btin2.blogic.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    ActivityMainBinding binding;
    int raumIndex = -1;
    ArrayList<Raum> raume = new ArrayList<>();
    String currBerechnungsart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //binding
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //Spinner
        Spinner spinner = binding.spinBerechnungsart;
        ArrayList<String> list = new ArrayList<>();
        list.add("Wand");
        list.add("Decke");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position,long id){
        currBerechnungsart = parent.getItemAtPosition(position).toString();
        Resources res = view.getContext().getResources();
        Drawable img = null;
        switch(currBerechnungsart){
            case "Decke":
                img = ResourcesCompat.getDrawable(res, R.drawable.decke, null);
                break;
            case "Wand":
                default:
                    img = ResourcesCompat.getDrawable(res, R.drawable.wand, null);
        }
        binding.imageView.setImageDrawable(img);
        binding.txtGesamtFlaeche.setText("");

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        //leere Implementierung
    }

    public void fillRaum(Raum raum){
        try{
            binding.editRaumname.setText(raum.getRaumName());
            binding.editBreite.setText(String.valueOf(raum.getBreite()));
            binding.editHoehe.setText(String.valueOf(raum.getHoehe()));
            binding.editLaenge.setText(String.valueOf(raum.getLaenge()));
        }catch(Exception e){
            //Standardwerte
            binding.editRaumname.setText("Raum");
            binding.editBreite.setText(String.valueOf(0.0));
            binding.editHoehe.setText(String.valueOf(0.0));
            binding.editLaenge.setText(String.valueOf(0.0));

            Toast.makeText(getApplicationContext(), "Fehler", Toast.LENGTH_SHORT).show();
            Log.d("myTag", e.getLocalizedMessage());
        }
    }

    public void neuerRaum(View view) {
        raumIndex = -1;
        fillRaum(null);
    }

    public void speichernRaum(View view) {
        try{
            if(!binding.editRaumname.getText().toString().isEmpty()){
                if(raumIndex == -1){
                    Raum raum = new Raum(
                            binding.editRaumname.getText().toString(),
                            Double.parseDouble(binding.editLaenge.getText().toString()),
                            Double.parseDouble(binding.editBreite.getText().toString()),
                            Double.parseDouble(binding.editHoehe.getText().toString())
                    );
                    raume.add(raum);
                    raumIndex = raume.size() - 1;
                }else{
                    raume.get(raumIndex).setRaumName(binding.editRaumname.getText().toString());
                    raume.get(raumIndex).setLaenge(Double.parseDouble(binding.editLaenge.getText().toString()));
                    raume.get(raumIndex).setBreite(Double.parseDouble(binding.editBreite.getText().toString()));
                    raume.get(raumIndex).setHoehe(Double.parseDouble(binding.editHoehe.getText().toString()));
                }
            }else{
                Toast.makeText(getApplicationContext(), "Bitte Namen angeben", Toast.LENGTH_LONG).show();

            }
        }catch(Exception e){
            Toast.makeText(getApplicationContext(), "Fehler", Toast.LENGTH_SHORT).show();
            Log.d("myTag", e.getLocalizedMessage());
        }
    }

    public void resetRaum(View view) {
        Snackbar mySnackbar = Snackbar.make(binding.getRoot(), "Alle Räume löschen", Snackbar.LENGTH_INDEFINITE);
        mySnackbar.setAction("Bestätigen",
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        fillRaum(null);
                        raume.clear();
                        raumIndex = -1;
                    }
                }
        );
        mySnackbar.show();
    }

    public void naechsterRaum(View view){
        try{
            if(!raume.isEmpty()){
                raumIndex += 1;
                if(raumIndex == raume.size()){
                    raumIndex = 0;
                }
                binding.editRaumname.setText(String.valueOf(raume.get(raumIndex).getRaumName()));
                binding.editLaenge.setText(String.valueOf(raume.get(raumIndex).getLaenge()));
                binding.editHoehe.setText(String.valueOf(raume.get(raumIndex).getHoehe()));
                binding.editBreite.setText(String.valueOf(raume.get(raumIndex).getBreite()));
            }
        }catch(Exception e){
            Toast.makeText(getApplicationContext(), "Fehler", Toast.LENGTH_SHORT).show();
            Log.d("myTag", e.getLocalizedMessage());
        }
    }

    public void berechnen(View view){
        try{
            double gesamtflaeche = 0;
            if(currBerechnungsart.equals("Wand")){
                for(Raum r : raume){
                    gesamtflaeche += r.getWandFlaeche();
                }
            }else if(Objects.equals(currBerechnungsart, "Decke")){
                for(Raum r : raume){
                    gesamtflaeche += r.getDeckenFlaeche();
                }
            }
            binding.txtGesamtFlaeche.setText(String.valueOf(gesamtflaeche));
        }catch(Exception e){
            Toast.makeText(getApplicationContext(), "Fehler", Toast.LENGTH_SHORT).show();
            Log.d("myTag", e.getLocalizedMessage());
        }
    }

}