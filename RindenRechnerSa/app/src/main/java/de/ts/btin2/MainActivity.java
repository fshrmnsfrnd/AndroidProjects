package de.ts.btin2;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import de.ts.btin2.databinding.ActivityMainBinding;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Bindng
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        view = binding.getRoot();
        setContentView(view);

        //Spinner füllen
        Spinner baumartSpinner = binding.spinnerBaumart;
        ArrayList<String> baumartList = new ArrayList<>(RindenRechner.getBaumKoffizienten().keySet());
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,android.R.layout.simple_spinner_item,baumartList
        );
        adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
        );
        baumartSpinner.setAdapter(adapter);
    }
}