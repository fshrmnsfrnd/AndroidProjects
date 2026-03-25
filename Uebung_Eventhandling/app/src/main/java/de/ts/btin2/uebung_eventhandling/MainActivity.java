package de.ts.btin2.uebung_eventhandling;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import de.ts.btin2.uebung_eventhandling.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    Button btnErreicht;
    Button btnMax;
    TextView erreichtePunkte;
    TextView maxPunkte;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        btnErreicht = binding.btnErreicht;
        btnMax = binding.btnMax;
        erreichtePunkte = binding.erreichtePunkte;
        maxPunkte = binding.maxPunkte;

        btnErreicht.setOnClickListener(new IncreaseHandler(erreichtePunkte));
        btnMax.setOnClickListener(new IncreaseHandler(maxPunkte));

        btnErreicht.setOnLongClickListener(new DecreaseHandler(erreichtePunkte));
        btnMax.setOnLongClickListener(new DecreaseHandler(maxPunkte));

        binding.berechnen.setOnClickListener(view -> {
            int percent = 100 * Integer.parseInt(erreichtePunkte.getText().toString()) / Integer.parseInt(maxPunkte.getText().toString());
            String note;
            if(percent >= 92){
                note = "1";
            }else if(percent >= 81){
                note = "2";
            }else if(percent >= 67) {
                note = "3";
            }else if(percent >= 50){
                note = "4";
            }else if(percent >= 30){
                note = "5";
            }else{
                note = "6";
            }
            binding.note.setText(note);
        });
    }

    private class IncreaseHandler implements Button.OnClickListener{
        private TextView v;
        public IncreaseHandler(TextView v) {
            this.v = v;
        }

        @Override
        public void onClick(View view) {
            v.setText(String.valueOf(Integer.parseInt(v.getText().toString())+1));
        }
    }

    private class DecreaseHandler implements Button.OnLongClickListener{
        private TextView v;
        public DecreaseHandler(TextView v) {
            this.v = v;
        }

        @Override
        public boolean onLongClick(View view) {
            v.setText(String.valueOf(Integer.parseInt(v.getText().toString())-1));
            return true;
        }
    }
}