package de.ts.btin2.intents;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import de.ts.btin2.intents.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    public ActivityResultLauncher<Intent> detailLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        detailLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        //Response auswerten
                        Intent data = result.getData();
                        Bundle bundle = data.getExtras();
                        Log.d("myTag", bundle.getString("RESPONSE_MESSAGE"));
                        binding.txtInput.setText(bundle.getString("RESPONSE_MESSAGE"));
                    }
                }
            }
        );
    }
    public void startDetailActivity(View v){
        Intent i = new Intent(this, DetailActivity.class);
        //i.setAction(Intent.ACTION_VIEW);
        i.putExtra("CALL_MESSAGE",binding.txtInput.getText().toString());
        //startActivity(i);

        detailLauncher.launch(i);
    }

    public void createAlarm(View v){
        try{
            Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM)
                    .putExtra(AlarmClock.EXTRA_HOUR, 11)
                    .putExtra(AlarmClock.EXTRA_MINUTES, 5);
            startActivity(intent);
        }
        catch(ActivityNotFoundException e){
            Toast.makeText(getApplicationContext(),"Keine passende Aktivität!"
                    , Toast.LENGTH_LONG).show();
        }
    }

    public void showUrl(View v){
        try{
            Uri webpage = Uri.parse("google.de");
            Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
            startActivity(intent);
        }
        catch(ActivityNotFoundException e){
            Toast.makeText(getApplicationContext(),"Keine passende Aktivität!"
                    , Toast.LENGTH_LONG).show();
        }
    }

    public void dialPhoneNumber(View v) {
        try{
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:" + "089715936"));
            startActivity(intent);
        }
        catch(ActivityNotFoundException e){
            Toast.makeText(getApplicationContext(),"Keine passende Aktivität!"
                    , Toast.LENGTH_LONG).show();
        }
    }

    public void capturePhoto(View v) {
        Intent i = new Intent(this, SnapshotActivity.class);
        i.setAction(Intent.ACTION_VIEW);
        startActivity(i);
    }

    public void startMapActivity(View v) {
        Intent i = new Intent(this, ViewMapActivity.class);
        i.setAction(Intent.ACTION_VIEW);
        startActivity(i);
    }

}