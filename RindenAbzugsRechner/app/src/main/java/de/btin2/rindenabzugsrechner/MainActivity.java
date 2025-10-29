package de.btin2.rindenabzugsrechner;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity{

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

    public void berechnen(View v){
        TextView inputBaumArt = findViewById(R.id.inputBaumArt);
        TextView inputBaumDM = findViewById(R.id.inputDM);
        TextView outputBaumMM = findViewById(R.id.textRindeMM);
        TextView outputBaumPercent = findViewById(R.id.textRindePercent);

        String baumArt = inputBaumArt.getText().toString();
        double dm = Double.valueOf(inputBaumDM.getText().toString());

        double rindeMM = RindenRechner.calcRindeMM(baumArt, dm);
        double rindePercent = RindenRechner.calcRindePercent(baumArt, dm);

        outputBaumMM.setText(rindeMM +"mm");
        outputBaumPercent.setText(String.valueOf(dm)+"%");
    }
}