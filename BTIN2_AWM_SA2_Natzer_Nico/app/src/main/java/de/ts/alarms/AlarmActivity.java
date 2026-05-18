package de.ts.alarms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.view.View;
import android.widget.Toast;

import de.ts.alarms.databinding.ActivityAlarmBinding;

public class AlarmActivity extends AppCompatActivity {
    AlarmRepository repository;
    private ActivityAlarmBinding binding;
    private Alarm alarm = new Alarm();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAlarmBinding.inflate((getLayoutInflater()));
        View view = binding.getRoot();
        setContentView(view);

        repository = new AlarmRepository(getApplication());

        Bundle bundle = getIntent().getExtras();
        this.alarm = new Alarm(bundle.getInt("ALARM_ID"), bundle.getString("ALARM_ORT"), bundle.getString("ALARM_ZEIT"));
        binding.editOrtDetail.setText(alarm.getOrt());
        binding.editZeitDetail.setText(alarm.getZeit());
    }
    public void createAlarm(View v){
        int hour = Integer.parseInt(binding.editZeitDetail.getText().toString().split(":")[0]);
        int minute = Integer.parseInt(binding.editZeitDetail.getText().toString().split(":")[1]);

        try{
            Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM)
                    .putExtra(AlarmClock.EXTRA_HOUR, hour)
                    .putExtra(AlarmClock.EXTRA_MINUTES, minute);
            startActivity(intent);
        }
        catch(ActivityNotFoundException e){
            Toast.makeText(getApplicationContext(),"Keine passende Aktivität!" ,
                    Toast.LENGTH_LONG).show();
        }
    }
    public void startWebsearch(View v){
        try{
            Uri webpage = Uri.parse("https://www.google.com/search?q=" + binding.editOrtDetail.getText().toString());
            Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
            startActivity(intent);
        } catch(ActivityNotFoundException e){
            Toast.makeText(getApplicationContext(),"Keine passende Aktivität!", Toast.LENGTH_LONG).show();
        }
    }

    public void speichernAlarm(View v){
        repository.updateAlarm(new Alarm(alarm.getId(), binding.editOrtDetail.getText().toString(), binding.editZeitDetail.getText().toString()));
    }
    public void loeschenAlarm(View v){
        repository.deleteAlarm(alarm.getId());
        finish();
    }

    public void zuerueck(View v){
        finish();
    }




}