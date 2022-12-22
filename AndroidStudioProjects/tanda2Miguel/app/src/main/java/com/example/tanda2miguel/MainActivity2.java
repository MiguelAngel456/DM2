package com.example.tanda2miguel;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;

public class MainActivity2 extends AppCompatActivity {
    private Button btnEncender,btnApagar;
    private LinearLayout fondo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad2);

        btnEncender = findViewById(R.id.encender);
        btnApagar = findViewById(R.id.Apagar);
        fondo=findViewById(R.id.fondo);
        btnEncender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fondo.setBackgroundColor(Color.GREEN);
            }
        });
        btnApagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fondo.setBackgroundColor(Color.RED);
            }
        });
    }
}