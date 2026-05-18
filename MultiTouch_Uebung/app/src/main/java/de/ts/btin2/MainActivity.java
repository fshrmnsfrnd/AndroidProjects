package de.ts.btin2;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import de.ts.btin2.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        binding.myLayout.setOnTouchListener(
            new ConstraintLayout.OnTouchListener(){
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    handleTouch(event);
                    return true;
                }
            }
            );
    }
    public void handleTouch(MotionEvent event){
        binding.tvAnzeige1.setText("");
        binding.tvAnzeige2.setText("");
        int pointerCount = event.getPointerCount();
        for (int i = 0; i < pointerCount; i++) {
            int x = (int) event.getX(i);
            int y = (int) event.getY(i);
            int id = event.getPointerId(i);
            int action = event.getActionMasked();
            int actionIndex = event.getActionIndex();
            String actionString;
            switch (action) {
                case MotionEvent.ACTION_DOWN:
                    actionString = "DOWN";
                    break;
                case MotionEvent.ACTION_UP:
                    actionString = "UP";
                    break;
                case MotionEvent.ACTION_POINTER_DOWN:
                    actionString = "PNTR DOWN";
                    break;
                case MotionEvent.ACTION_POINTER_UP:
                    actionString = "PNTR UP";
                    break;
                case MotionEvent.ACTION_MOVE:
                    actionString = "MOVE";
                    break;
                default:
                    actionString = "";
            }
            String touchStatus ="Pointer:" + i + " Action: " + actionString + "A-Index: " + actionIndex + " ID: " + id + "\n X: " + x + " Y: " + y;
            switch (i) {
                case 0:
                    binding.tvAnzeige1.setText(touchStatus);
                    break;
                case 1:
                    binding.tvAnzeige2.setText(touchStatus);
                    break;
            }
        }
    }
}