package com.example.tanda3_miguel_angel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class actividad1 extends AppCompatActivity {
    private EditText txtNombre,txtApellido;
    private Button btnVerificar;
    private TextView lblCondiciones;
    private String s="";



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad1);

        btnVerificar = findViewById(R.id.btnVerificar);
        txtNombre = findViewById(R.id.txtNombre);
        txtApellido = findViewById(R.id.txtApellido);
        lblCondiciones = findViewById(R.id.lblCondiciones);
        lblCondiciones.setText(s);
        btnVerificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(actividad1.this, actividad1_1.class);
                intent.putExtra("usuario", txtNombre.getText().toString()+" "+txtApellido.getText().toString());
                startActivityForResult(intent, 1235);
                Bundle extras = getIntent().getExtras();

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1235 && resultCode == RESULT_OK) {
            String res = data.getExtras().getString("condicion");
            lblCondiciones.setText(res);
        }
    }
}
