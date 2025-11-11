package de.btin2.rindenabzugsrechner;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Set;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets)->{
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Spinner spinner = (Spinner) findViewById(R.id.spinnerBaumArt);
        ArrayList<String> options = new ArrayList<>(RindenRechner.BaumKoffizienten.keySet());
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, options);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    public void berechnen(View v){
        Spinner inputBaumArt = findViewById(R.id.spinnerBaumArt);
        TextView inputBaumDM = findViewById(R.id.inputDM);
        TextView outputBaumMM = findViewById(R.id.textRindeMM);
        TextView outputBaumPercent = findViewById(R.id.textRindePercent);

        String baumArt = inputBaumArt.getSelectedItem().toString();
        double dm = Double.valueOf(inputBaumDM.getText().toString());
        Log.d("myTag", baumArt);
        if(RindenRechner.validateBaumart(baumArt)){
            double rindeMM = RindenRechner.calcRindeMM(baumArt, dm);
            double rindePercent = RindenRechner.calcRindePercent(baumArt, dm);

            outputBaumMM.setText(rindeMM + "mm");
            outputBaumPercent.setText(String.valueOf(dm) + "%");
        }else{
            outputBaumMM.setText("Baumart ungueltig");
            outputBaumPercent.setText("Baumart ungueltig");
        }
    }
}