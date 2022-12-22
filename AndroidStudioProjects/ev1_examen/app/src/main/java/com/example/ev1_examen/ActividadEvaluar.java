package com.example.ev1_examen;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class ActividadEvaluar extends AppCompatActivity {

    private TextView textViewCandidatoNombre;
    private TextView textViewCandidatoFechaNac;
    private TextView textViewCandidatoProvincia;
    private TextView textViewCandidatoSexo;
    private TextView textViewCandidatoConocimientos;

    private int candidatos;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluar);

        textViewCandidatoNombre = findViewById(R.id.textViewCandidatoNombre);
        textViewCandidatoFechaNac = findViewById(R.id.textViewCandidatoFechaNac);
        textViewCandidatoProvincia = findViewById(R.id.textViewCandidatoProvincia);
        textViewCandidatoSexo = findViewById(R.id.textViewCandidatoSexo);
        textViewCandidatoConocimientos = findViewById(R.id.textViewCandidatoConocimientos);

        Bundle bundle = getIntent().getExtras();

        String nombre = bundle.getString("nombre");
        String diaNac = bundle.getString("diaNac");
        String mesNac = bundle.getString("mesNac");
        String anioNac = bundle.getString("anioNac");
        String provincia = bundle.getString("provincia");
        String sexo = bundle.getString("sexo");
        ArrayList<String> conocimientos = bundle.getStringArrayList("conocimientos");
        String cononocimientosString = String.join(", ", conocimientos);

        textViewCandidatoNombre.setText(nombre);
        textViewCandidatoFechaNac.setText(diaNac+"/"+mesNac+"/"+anioNac);
        textViewCandidatoProvincia.setText(provincia);
        textViewCandidatoSexo.setText(sexo);
        textViewCandidatoConocimientos.setText(cononocimientosString);
    }

    public void aceptar(View view){
        setResult(RESULT_OK);
        finish();
    }

    public void rechazar(View view){
        setResult(RESULT_CANCELED);
        finish();
    }
}