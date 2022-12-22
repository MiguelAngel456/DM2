package com.example.prueba;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class Actividad_4 extends AppCompatActivity {
    private Button btnG,btnY,btnB;
    private ImageView respuesta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad4);

        btnB = findViewById(R.id.btnBin);
        btnG = findViewById(R.id.btnGo);
        btnY = findViewById(R.id.btnYa);
        respuesta = findViewById(R.id.imgNav);
        btnB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                respuesta.setImageResource(R.drawable.bing);
            }
        });
        btnY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                respuesta.setImageResource(R.drawable.yh);
            }
        });
        btnG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                respuesta.setImageResource(R.drawable.google);
            }
        });

    }
}
