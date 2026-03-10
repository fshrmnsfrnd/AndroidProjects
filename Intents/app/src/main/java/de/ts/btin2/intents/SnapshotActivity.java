package de.ts.btin2.intents;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import de.ts.btin2.intents.databinding.ActivitySnapshotBinding;

public class SnapshotActivity extends AppCompatActivity {
    private ActivitySnapshotBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySnapshotBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_snapshot);
    }

    public void takePhoto(View v) {
        Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        detailLauncher.launch(i);
    }

    public ActivityResultLauncher<Intent> detailLauncher = registerForActivityResult(
        new ActivityResultContracts.StartActivityForResult(),
        new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult o) {
                if (o.getResultCode() == RESULT_OK) {
                    assert o.getData() != null;
                    Bitmap thumbnail = o.getData().getParcelableExtra("data");
                    binding.img.setImageBitmap(thumbnail);
                }
            }
        }
    );
}