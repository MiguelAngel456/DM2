package com.example.tanda3_miguel_angel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ejercicio2_1 extends AppCompatActivity {
    private TextView informacion;
    private Button btnVolver;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ejercicio2_1);

        informacion = findViewById(R.id.informacion);
        btnVolver=findViewById(R.id.btnVolver);

        Bundle extras = getIntent().getExtras();


        int num1= Integer.parseInt(extras.getString("num1"));
        int num2= Integer.parseInt(extras.getString("num2"));
        int Escrito=Integer.parseInt(extras.getString("result"));
        int result=num1+num2;
        if(result==Escrito){
            informacion.setText("CORRECTO");
        }else{
            informacion.setText("INCORRECTO");
        }


        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(result==Escrito){
                    Intent intent = new Intent(ejercicio2_1.this, ejercicio2.class);
                    intent.putExtra("condicion", "bien");
                    setResult(RESULT_OK, intent);
                    finish();
                }else{
                    Intent intent = new Intent(ejercicio2_1.this, ejercicio2.class);
                    intent.putExtra("condicion", "mal");setResult(RESULT_OK, intent);
                    setResult(RESULT_OK, intent);
                    finish();

                }

            }
        });

    }

}
