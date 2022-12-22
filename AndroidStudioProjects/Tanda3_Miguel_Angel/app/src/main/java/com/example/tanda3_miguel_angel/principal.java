package com.example.tanda3_miguel_angel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class principal extends AppCompatActivity {
    private Button ej1,ej2,ej3,ej4;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.principal);

        ej1 = findViewById(R.id.ej1);
        ej2 = findViewById(R.id.ej2);
        ej3 = findViewById(R.id.ej3);
        ej4 = findViewById(R.id.ej4);
      ej1.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              Intent intent = new Intent(principal.this, ejercicio1.class);
              startActivity(intent);
          }
      });
        ej2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(principal.this, ejercicio2.class);
                startActivity(intent);
            }
        });
        ej3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(principal.this, ejercicio3.class);
                startActivity(intent);
            }
        });
        ej4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(principal.this, ejercicio4.class);
                startActivity(intent);
            }
        });
    }
}
