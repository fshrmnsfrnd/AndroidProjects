package de.ts.btin2.intents;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import de.ts.btin2.intents.databinding.ActivityViewMapBinding;

public class ViewMapActivity  extends AppCompatActivity {
    private ActivityViewMapBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityViewMapBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_view_map);
    }

    public void showMap(View v) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        Uri geoLocation = Uri.parse("geo:0,0?q=" + binding.txtStrasse.getText().toString() + binding.txtOrt.getText().toString());
        intent.setData(geoLocation);
        startActivity(intent);
    }
}