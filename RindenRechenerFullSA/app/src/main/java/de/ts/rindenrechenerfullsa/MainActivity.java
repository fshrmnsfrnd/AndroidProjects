package de.ts.rindenrechenerfullsa;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import de.ts.rindenrechenerfullsa.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    private ActivityMainBinding binding;
    ImageView imgBaumArt;
    String selected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Bindng
        binding = ActivityMainBinding.inflate(getLayoutInflater());

        //Spinner füllen
        Spinner baumartSpinner = binding.spinnerBaumart;
        ArrayList<String> baumartList = new ArrayList<>(RindenRechner.getAllBaumKoffizienten().keySet());
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,android.R.layout.simple_spinner_item,baumartList
        );
        adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
        );
        baumartSpinner.setAdapter(adapter);
        baumartSpinner.setOnItemSelectedListener(this);

        //ImageView holen
        imgBaumArt = binding.imgBaumart;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        selected = parent.getItemAtPosition(position).toString();
        Log.d("myTag", "onItemSelected: " + selected);

        int resId = getResources().getIdentifier(selected.toLowerCase().split(" ")[0], "drawable", getPackageName());
        imgBaumArt.setImageResource(resId);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        //empty
    }

    public void calc(View view){
        try {
            double dM = Double.valueOf(binding.textDM.getText().toString());
            double percent = RindenRechner.getRindenanteil(selected, dM);
            double staerke = RindenRechner.getRindenstaerke(selected, dM);

            binding.textSize.setText(String.valueOf(staerke));

            Snackbar snicker = Snackbar.make(view, "Percent:" + percent + "%", Snackbar.LENGTH_INDEFINITE);
            snicker.addCallback(
                new Snackbar.Callback(){
                    @Override
                    public void onShown(Snackbar sb){
                        binding.textPercent.setText(String.valueOf(percent));
                    }

                    @Override
                    public void onDismissed(Snackbar sb, int event){
                        binding.textPercent.setText(String.valueOf(0));
                        Log.d("myTag", "Dismissed");
                    }
                }
            );

            snicker.setAction("Rückgängig", v -> {
                binding.textDM.setText(String.valueOf(0));
                Log.d("myTag", "Back");
            });
            snicker.setAction("Weiter", v -> {
                binding.textDM.setText(String.valueOf(0));
                Log.d("myTag", "weiter");
            });
            snicker.show();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Check your inputs", Toast.LENGTH_SHORT).show();
        }
    }
}