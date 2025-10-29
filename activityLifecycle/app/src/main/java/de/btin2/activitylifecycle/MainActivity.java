package de.btin2.activitylifecycle;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.os.Bundle;
import android.view.View;
import androidx.annotation.NonNull;
import android.util.Log;

public class MainActivity extends AppCompatActivity{

    private final String tag = "StateChange";
    private int clicks = 0;

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

    @Override
    protected void onStart(){
        super.onStart();
        Log.i(tag, "onStart");
    }

    @Override
    protected void onResume(){
        super.onResume();
        Log.i(tag, "onResume");
    }

    @Override
    protected void onPause(){
        super.onPause();
        Log.i(tag, "onPause");
    }

    @Override
    protected void onStop(){
        super.onStop();
        Log.i(tag, "onStop");
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        Log.i(tag, "onRestart");
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.i(tag, "onDestroy");
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(tag, "onSaveInstanceState");
        outState.putInt("savedClicks", clicks);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.i(tag, "onRestoreInstanceState");
        clicks = savedInstanceState.getInt("savedClicks");
    }

    public void inkrementClicks(View v){
        this.clicks++;
        Log.i(tag, "clicked" + this.clicks + "times");
    }
}