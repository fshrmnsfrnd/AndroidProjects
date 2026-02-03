package de.ts.btin2.notenrechner;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import de.ts.btin2.notenrechner.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private int note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.btnErrPoints.setOnClickListener(new IncDecHandler(binding.txtErrPoints));
        binding.btnMaxPoints.setOnClickListener(new IncDecHandler(binding.txtMaxPoints));
        binding.berechnen.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                double percent = calcPercent(Integer.parseInt(binding.txtErrPoints.getText().toString()), Integer.parseInt(binding.txtMaxPoints.getText().toString()));
                if(percent <= 29){
                    note = 6;
                }else if(percent <= 49){
                    note = 5;
                }else if(percent <= 66){
                    note = 4;
                }else if(percent <= 80){
                    note = 3;
                }else if(percent <= 91){
                    note = 2;
                }else if(percent > 91){
                    note = 1;
                }
                binding.txtNote.setText(String.valueOf(note));
            }
        });
    }

    private class IncDecHandler implements View.OnClickListener, View.OnLongClickListener{
        private final TextView txtToHandle;
        public IncDecHandler(TextView tv){
            txtToHandle = tv;
        }

        @Override
        public void onClick(View v) {
            System.out.println(txtToHandle.getText().toString());
            txtToHandle.setText(String.valueOf(Integer.parseInt(txtToHandle.getText().toString()) + 1));
        }

        @Override
        public boolean onLongClick(View v) {
            txtToHandle.setText(String.valueOf(Integer.parseInt(txtToHandle.getText().toString()) - 1));
            return false;
        }

        @Override
        public boolean onLongClickUseDefaultHapticFeedback(@NonNull View v) {
            return View.OnLongClickListener.super.onLongClickUseDefaultHapticFeedback(v);
        }
    }

    private double calcPercent(int curr, int max){
        return (double) max / 100 * curr;
    }
}