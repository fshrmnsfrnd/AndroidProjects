package de.ts.btin2.eanapp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.snackbar.Snackbar;

import de.ts.btin2.eanapp.databinding.ActivityMainBinding;
import de.ts.btin2.eanapp.EanSupport;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    EanSupport eanSupport = new EanSupport();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    public void validateHandler(View v){
        String inputEan = binding.txtEanInput.getText().toString();
        int eanLength = inputEan.length();
        try {
            if (eanLength == 13) {

                //Wenn 13 Stellig: nur validieren
                eanSupport.validate(inputEan);
                Snackbar.make(binding.main, "Gültige EAN", Snackbar.LENGTH_LONG).setAction("Nächste",
                    new View.OnClickListener(){
                        @Override
                        public void onClick(View v) {
                            binding.txtEanInput.setText("");
                        }
                    }
                ).show();
            }
            if(eanLength == 12){
                Toast.makeText(getApplicationContext(), String.valueOf(eanLength), Toast.LENGTH_SHORT ).show();
                //Wenn 12 Stellig: 13 Stelle berechnen
                int pz = eanSupport.calcPz(inputEan);
                eanSupport.validate(inputEan+pz);
                binding.txtEanInput.setText(inputEan + pz);
                Snackbar.make(binding.main, "Prüfziffer:"+ pz, Snackbar.LENGTH_LONG).setAction("Nächste",
                        new View.OnClickListener(){
                            @Override
                            public void onClick(View v) {
                                binding.txtEanInput.setText("");
                            }
                        }
                ).show();
            }
        }catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getLocalizedMessage(), Toast.LENGTH_SHORT ).show();
            Log.d("myTag", e.getMessage());
        }finally {
            binding.txtEanInput.setText("");
        }
    }
}