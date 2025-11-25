package de.btin2.rindenabzugsrechner;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Set;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    ImageView imgMebis;
    int mebisImageNumber = 1;
    //Spinner

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

        Spinner spinner = (Spinner) findViewById(R.id.spinnerBaumArt);
        ArrayList<String> options = new ArrayList<>(RindenRechner.BaumKoffizienten.keySet());
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, options);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        Spinner spinnerBild = (Spinner) findViewById(R.id.imgSpinner);
        ArrayAdapter<CharSequence> adapterBild = ArrayAdapter.createFromResource(this, R.array.imgTypes, android.R.layout.simple_spinner_item);
        adapterBild.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerBild.setAdapter(adapterBild);
        spinnerBild.setOnItemSelectedListener(this);

        imgMebis = (ImageView) findViewById(R.id.imgMebis);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id){
        Log.d("myTag", "onSelect");
        Resources res = view.getContext().getResources();
        Drawable img =null;
        String pictureToDisplay;
        pictureToDisplay = parent.getItemAtPosition(position).toString();

        switch (pictureToDisplay){
            case "Bild1":
                img = ResourcesCompat.getDrawable(res, R.drawable.baumkuchen, null);
                mebisImageNumber = 2;
                break;
            case "Bild2":
            default:
                img = ResourcesCompat.getDrawable(res, R.drawable.baumkuchen2, null);
                mebisImageNumber = 1;
        }
        Log.d("myTag",String.valueOf(mebisImageNumber));
        imgMebis.setImageDrawable(img);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent){
        //empty
    }

    public void berechnen(View v){
        Spinner inputBaumArt = findViewById(R.id.spinnerBaumArt);
        TextView inputBaumDM = findViewById(R.id.inputDM);
        TextView outputBaumMM = findViewById(R.id.textRindeMM);
        TextView outputBaumPercent = findViewById(R.id.textRindePercent);

        String baumArt = inputBaumArt.getSelectedItem().toString();
        double dm = Double.valueOf(inputBaumDM.getText().toString());
        Log.d("myTag", baumArt);
        if(RindenRechner.validateBaumart(baumArt)){
            double rindeMM = RindenRechner.calcRindeMM(baumArt, dm);
            double rindePercent = RindenRechner.calcRindePercent(baumArt, dm);

            outputBaumMM.setText(rindeMM + "mm");
            outputBaumPercent.setText(String.valueOf(rindePercent) + "%");
        }else{
            outputBaumMM.setText("Baumart ungueltig");
            outputBaumPercent.setText("Baumart ungueltig");
        }
    }

    public void changeImage(View view){
        Resources res = view.getContext().getResources();
        Drawable img =null;

        switch (mebisImageNumber){
            case 1:
                img = ResourcesCompat.getDrawable(res, R.drawable.baumkuchen, null);
                mebisImageNumber = 2;
                break;
            case 2:
                default:
                img = ResourcesCompat.getDrawable(res, R.drawable.baumkuchen2, null);
                mebisImageNumber = 1;
                }
                Log.d("myTag",String.valueOf(mebisImageNumber));
        imgMebis.setImageDrawable(img);
    }

    public void startTransition(View view){
        Resources res = view.getContext().getResources();
        TransitionDrawable transition;

        switch (mebisImageNumber){
            case 1:
                transition = (TransitionDrawable) ResourcesCompat.getDrawable(res, R.drawable.mebistransition2, null);
                mebisImageNumber = 2;
                break;
            case 2:
                default:
                transition = (TransitionDrawable) ResourcesCompat.getDrawable(res, R.drawable.mebistransition, null);
                mebisImageNumber = 1;
        }
        Log.d("myTag",String.valueOf(mebisImageNumber));
        imgMebis.setImageDrawable(transition);
        //Transition starten
        transition.startTransition(3000);
    }
}