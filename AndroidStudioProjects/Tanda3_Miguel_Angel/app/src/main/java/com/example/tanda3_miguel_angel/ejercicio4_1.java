package com.example.tanda3_miguel_angel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;

public class ejercicio4_1 extends AppCompatActivity {
    private Button btnVolver;
    private CheckBox chpregunta1,chpregunta2,chpregunta3;
    private ImageView img;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ejercicio4_1);

        btnVolver=findViewById(R.id.btnVolver1);
        chpregunta1=findViewById(R.id.chpregunta1);
        chpregunta2=findViewById(R.id.chpregunta2);
        chpregunta3=findViewById(R.id.chpregunta3);
        img=findViewById(R.id.img);
        Bundle extras = getIntent().getExtras();
        int s = extras.getInt("niv");
        if (s==1){
            chpregunta1.setText("Dragon ball");
            chpregunta2.setText("Naruto");
            chpregunta3.setText("One Piece");
            img.setImageResource(R.drawable.db);

        }
        if (s==2){
            chpregunta1.setText("Shingeki no kyoyin");
            chpregunta2.setText("Parasyte");
            chpregunta3.setText("Tokyo Ghoul");
            img.setImageResource(R.drawable.tg);
        }
        if (s==3){
            chpregunta1.setText("Tomb Raider King");
            chpregunta2.setText("Solo Leveling");
            chpregunta3.setText("solo Leveleo");
            img.setImageResource(R.drawable.sl);
        }
        btnVolver.setOnClickListener(new View.OnClickListener() {

            int resp=1;
            public void onClick(View view) {
                if(s==1){
                    if(chpregunta1.isChecked()){
                        resp=1;
                    }else{
                        resp=0;
                    }
                }
                if(s==2){
                    if(chpregunta3.isChecked()){
                        resp=1;
                    }else{
                        resp=0;
                    }
                }
                if(s==3){
                    if(chpregunta2.isChecked() && chpregunta3.isChecked()){
                        resp=1;
                    }else{
                        resp=0;
                    }
                }
                Intent intent = new Intent(ejercicio4_1.this, ejercicio4.class);
                intent.putExtra("resp", resp);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}
