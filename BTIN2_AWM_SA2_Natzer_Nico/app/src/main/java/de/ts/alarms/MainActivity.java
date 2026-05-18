package de.ts.alarms;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import de.ts.alarms.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {

    private ArrayList<Alarm> alarmList = new ArrayList<>();
    private ActivityMainBinding binding;
    private AlarmRepository repository ;


    int posIndex = 0;
    double startX;
    double startY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate((getLayoutInflater()));
        View view = binding.getRoot();
        setContentView(view);

        // Initialisierungen, nicht entfernen
        repository = new AlarmRepository(getApplication());

        konfiguriereLongClick();
        konfiguriereSwipe();
        konfiguriereObserver();
    }

    public void neuerAlarm(View v){
        Alarm alarm = new Alarm();
        alarm.setOrt("neuer Alarm");
        alarm.setZeit("00:00");
        repository.insertAlarm(alarm);
        refreshWidgets();
    }
    public void startAlarmBearbeiten(){
        if(!alarmList.isEmpty()) {
            Alarm currAlarm = alarmList.get(posIndex);
            Intent i = new Intent(this, AlarmActivity.class);
            i.putExtra("ALARM_ID", currAlarm.getId());
            i.putExtra("ALARM_ORT", currAlarm.getOrt());
            i.putExtra("ALARM_ZEIT", currAlarm.getZeit());
            startActivity(i);
        }
        else{
            Toast.makeText(getApplicationContext(),"Es gibt noch keine Alarm-Objekte.",Toast.LENGTH_SHORT).show();
        }
    }

    private void refreshWidgets(){
        if (alarmList.isEmpty()) {
            binding.lvReminderHeader.setText("Alarm 0 von 0");
            binding.tvOrt.setText("kein Ort");
            binding.tvZeit.setText("00:00");
        }
        else{
            Alarm alarm = alarmList.get(posIndex);
            binding.lvReminderHeader.setText("Alarm " + (posIndex + 1) + " von " + alarmList.size());
            binding.tvOrt.setText(alarm.getOrt());
            binding.tvZeit.setText(alarm.getZeit());
        }
    }

    private void konfiguriereObserver(){
        repository.getAllAlarms().observe(this,alarms ->{
            alarmList.clear();
            alarmList.addAll(alarms);
            posIndex = alarmList.size()-1;
            refreshWidgets();
        });
    }

    private void konfiguriereLongClick(){
        //View.OnLongClickListener listener;
        binding.tvZeit.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                startAlarmBearbeiten();
                return true;
            }
        });

    }
    private void konfiguriereSwipe(){
        View.OnTouchListener listener;
        binding.imgAlarm.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getActionMasked() == MotionEvent.ACTION_DOWN){
                    startX = event.getX();
                    startY = event.getY();
                }else if(event.getActionMasked() == MotionEvent.ACTION_UP){
                    //Maximal 30 y änderung
                    double diffYStartEnd = startY - event.getY();
                    if(diffYStartEnd >= -15 && diffYStartEnd <= 15){
                        //Vorwärts
                        if(startX > event.getX()){
                            if((posIndex + 1) <= (alarmList.size() - 1)){
                                posIndex++;
                            }
                        }
                        //Rückwärts
                        if(startX < event.getX()){
                            if((posIndex - 1) >= 0){
                                posIndex--;
                            }
                        }
                    }
                    refreshWidgets();
                }
                return true;
            }
        });
    }


}