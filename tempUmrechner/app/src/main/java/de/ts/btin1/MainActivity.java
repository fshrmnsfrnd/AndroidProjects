package de.ts.btin1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import de.ts.btin1.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    EditText c;
    EditText k;
    EditText f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        c = binding.inDecCel;
        k = binding.inDecKel;
        f = binding.inDecFahr;
    }



    public void calcFromCel(View v){
        double kv = Double.valueOf(k.getText().toString());
        double fv = Double.valueOf(f.getText().toString());
        double cv = Double.valueOf(c.getText().toString());
        k.setText(String.valueOf(cv * 100));
        f.setText(String.valueOf(cv / 100));
        c.setText(String.valueOf(cv));
    }
}