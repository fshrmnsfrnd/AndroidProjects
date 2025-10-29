package de.btin2.tempcalc;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity{

    EditText fieldCelsius;
    EditText fieldFahrenheit;
    EditText fieldKelvin;

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
    }

    public void resetValues(View v){
        fieldCelsius = findViewById(R.id.InputCelsius);
        fieldFahrenheit = findViewById(R.id.InputFahrenheit);
        fieldKelvin = findViewById(R.id.InputKelvin);

        fieldCelsius.setText(String.valueOf(0));
        fieldFahrenheit.setText(String.valueOf(0));
        fieldKelvin.setText(String.valueOf(0));
    }

    public void berechne(View v){


        //Get Values of Fields
        float valueCelsius = Float.valueOf(fieldCelsius.getText().toString());
        float valueFahrenheit = Float.valueOf(fieldFahrenheit.getText().toString());
        float valueKelvin = Float.valueOf(fieldKelvin.getText().toString());

        //Reset wenn schon berechnet
        if(valueKelvin != 0 && valueFahrenheit != 0 && valueCelsius != 0){
            resetValues(v);
        }

        //Berechnen
        //From Kelvin
        if(valueKelvin != 0 && valueFahrenheit == 0 && valueCelsius == 0){
            fieldCelsius.setText(String.valueOf(valueKelvin-273.15));
            fieldFahrenheit.setText(String.valueOf(((valueKelvin-32)*(5.0/9.0))+273.15));
        }
        //From Fahrenheit
        if(valueKelvin == 0 && valueFahrenheit != 0 && valueCelsius == 0){
            fieldCelsius.setText(String.valueOf((valueFahrenheit-32)*(5.0/9.0)));
            fieldKelvin.setText(String.valueOf((valueFahrenheit+459.67)*(5.0/9.0)));
        }
        //From Celsius
        if(valueKelvin == 0 && valueFahrenheit == 0 && valueCelsius != 0){
            fieldKelvin.setText(String.valueOf(valueCelsius+273.15));
            fieldFahrenheit.setText(String.valueOf((valueCelsius*(9.0/5.0))+32));
        }
    }
}