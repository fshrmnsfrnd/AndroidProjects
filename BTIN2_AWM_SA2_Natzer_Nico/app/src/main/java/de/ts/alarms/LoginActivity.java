package de.ts.alarms;



import static android.text.InputType.TYPE_CLASS_TEXT;
import static android.text.InputType.TYPE_NUMBER_VARIATION_PASSWORD;
import static android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD;
import static android.text.InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

import de.ts.alarms.databinding.ActivityLoginBinding;


public class LoginActivity extends AppCompatActivity {
    ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate((getLayoutInflater()));
        View view = binding.getRoot();
        setContentView(view);
        //NICHT entfernen!!!
        erzeugeBeispielAlarms();
    }

    public void maskPasswort(View v){
        //Toast.makeText(getApplicationContext(), String.valueOf(binding.editPasswort.getInputType()), Toast.LENGTH_SHORT).show();
        if(binding.editPasswort.getInputType() == TYPE_TEXT_VARIATION_PASSWORD){
            binding.editPasswort.setInputType(TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
        }else{
            binding.editPasswort.setInputType(TYPE_TEXT_VARIATION_PASSWORD);
        }
    }

    public void login(View v){
        if(binding.editBenutzername.getText().toString().equals("SA") && binding.editPasswort.getText().toString().equals("geheim")){
            Intent i = new Intent(this,MainActivity.class);
            i.setAction(Intent.ACTION_VIEW);
            startActivity(i);
        }else{
            Toast.makeText(getApplicationContext(), "Wrong User or Password", Toast.LENGTH_SHORT).show();
        }
    }

    public void erzeugeBeispielAlarms(){
        AlarmRepository repository = new AlarmRepository(getApplication());
        Alarm a;

        //Beispielaten erzeugen
        a = new Alarm(1,"Rathaus, München",null);
        repository.insertAlarm(a);

        a = new Alarm(2,"Louvre Museum, Paris",null);
        repository.insertAlarm(a);

        a = new Alarm(3,"Prado Museum, Madrid",null);
        repository.insertAlarm(a);
    }


}