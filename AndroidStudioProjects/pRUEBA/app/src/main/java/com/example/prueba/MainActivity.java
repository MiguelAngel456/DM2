package com.example.prueba;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button btnSumar,btnResta,btnMulti,btnDivision;
    private EditText txtValor1,txtValor2;
    private TextView lblResultado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad1);

        btnSumar = findViewById(R.id.btnSuma);
        btnResta = findViewById(R.id.btnResta);
        btnMulti = findViewById(R.id.btnMulti);
        btnDivision = findViewById(R.id.btnDivision);
        txtValor1=findViewById(R.id.txtValor1);
        txtValor2=findViewById(R.id.txtValor2);
        lblResultado=findViewById(R.id.lblResultado);
        btnSumar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int num1=Integer.parseInt(txtValor1.getText().toString());
                int num2=Integer.parseInt(txtValor2.getText().toString());
                lblResultado.setText(String.valueOf(num1+num2));
            }
        });
        btnResta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int num1=Integer.parseInt(txtValor1.getText().toString());
                int num2=Integer.parseInt(txtValor2.getText().toString());
                lblResultado.setText(String.valueOf(num1-num2));
            }
        });
        btnMulti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int num1=Integer.parseInt(txtValor1.getText().toString());
                int num2=Integer.parseInt(txtValor2.getText().toString());
                lblResultado.setText(String.valueOf(num1*num2));
            }
        });
        btnDivision.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int num1=Integer.parseInt(txtValor1.getText().toString());
                int num2=Integer.parseInt(txtValor2.getText().toString());
                lblResultado.setText(String.valueOf(num1/num2));
            }
        });


    }
}