package de.ts.btin2.intents;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import de.ts.btin2.intents.databinding.ActivityDetailBinding;

public class DetailActivity extends AppCompatActivity {
    private ActivityDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_detail);
        Bundle bundle = getIntent().getExtras();
        Log.d("myTag", bundle.getString("CALL_MESSAGE"));
        binding.txtInOut.setText(bundle.getString("CALL_MESSAGE"));
        ActivityResultLauncher<Intent> a = new ActivityResultLauncher<Intent>() {
            @Override
            public void launch(Intent intent, @Nullable ActivityOptionsCompat activityOptionsCompat) {}
            @Override
            public void unregister() {}
            @NonNull
            @Override
            public ActivityResultContract<Intent, ?> getContract() {return null;}
        };
    }

    public void backWithData(View v){
        Intent i = new Intent();
        Log.d("myTag", binding.txtInOut.getText().toString());
        i.putExtra("RESPONSE_MESSAGE", binding.txtInOut.getText().toString());
        setResult(RESULT_OK, i);
        super.finish();
    }
}