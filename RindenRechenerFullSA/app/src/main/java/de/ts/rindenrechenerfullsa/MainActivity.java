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
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import de.ts.rindenrechenerfullsa.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    private ActivityMainBinding binding;
    private View view;
    ImageView imgBaumArt;
    String selected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Bindng
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        view = binding.getRoot();
        setContentView(view);

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
        //Bild updaten
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void calc(View view){
        try {
            double dM = Double.valueOf(binding.textDM.getText().toString());
            double percent = RindenRechner.getRindenanteil(selected, dM);
            double staerke = RindenRechner.getRindenstaerke(selected, dM);
            binding.textPercent.setText(String.valueOf(percent));
            binding.textSize.setText(String.valueOf(staerke));

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Check your inputs", Toast.LENGTH_SHORT).show();
            Log.d("myTag", e.getMessage());
        }
    }
}