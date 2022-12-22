package com.example.tanda3_miguel_angel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class actividad1_1 extends AppCompatActivity {
    private Button btnAcept,btnRech;
    private TextView lblNombre;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad1_1);
        lblNombre = findViewById(R.id.lblNombre);

        Bundle extras = getIntent().getExtras();
        String s = extras.getString("usuario");
        lblNombre.setText("Hola "+s+" Aceptas las condiciones");

        btnAcept= findViewById(R.id.btnAcept);
        btnRech= findViewById(R.id.btnRech);


        btnAcept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(actividad1_1.this, actividad1.class);
                intent.putExtra("condicion", "Aceptado");
                setResult(RESULT_OK, intent);
                finish();
            }
        });
        btnRech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(actividad1_1.this, actividad1.class);
                intent.putExtra("condicion", "rechazado");
                setResult(RESULT_OK, intent);
                finish();
            }
        });

    }

}
