package com.example.tanda2miguel;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class actividad3 extends AppCompatActivity {

    private Button validar;
    private EditText Numero,letra;
    private TextView respuesta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad3);

        validar = findViewById(R.id.validar);
        Numero= findViewById(R.id.Numero);
        letra=findViewById(R.id.letra);
        respuesta=findViewById(R.id.respuesta);
        long num = Integer.parseInt(Numero.getText().toString());

        //int num=Integer.parseInt(Numero.getText().toString());
        validar.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                long resultado=num%23;
                String l=String.valueOf(resultado);
                if(l==letra.getText().toString()){
                    respuesta.setText("ESTA BIEN");
                }else{
                    respuesta.setText("ESTA MAL");
                }
            }
        });

    }
}