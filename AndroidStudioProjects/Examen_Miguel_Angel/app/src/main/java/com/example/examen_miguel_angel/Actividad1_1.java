package com.example.examen_miguel_angel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Actividad1_1 extends AppCompatActivity {

    private Button btnSi, btnNo;
    private TextView txtFecha,txtNombre,txtApellidos,txtOrigen,txtDestino,txtHora;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad1_1);

        btnSi=findViewById(R.id.btnSi);
        btnNo=findViewById(R.id.btnNo);

        txtFecha=findViewById(R.id.txtFechaVer);
        txtNombre=findViewById(R.id.txtNombreVer);
        txtApellidos=findViewById(R.id.txtApellidosVer);

        txtOrigen=findViewById(R.id.txtOrigenVer);
        txtDestino=findViewById(R.id.txtDestinoVer);

        txtHora=findViewById(R.id.txtHoraVer);

        Bundle extras = getIntent().getExtras();
        String fecha=extras.getString("fecha");
        String nom=extras.getString("nom");
        String apellido=extras.getString("apellido");

        String origen=extras.getString("origen");
        String destino=extras.getString("destino");

        String hora=extras.getString("hora");

        txtFecha.setText(fecha);
        txtNombre.setText(nom);
        txtApellidos.setText(apellido);
        txtOrigen.setText(origen);
        txtDestino.setText(destino);
        txtHora.setText(hora);
    }

    public void no(View view){
        Intent intent = new Intent(Actividad1_1.this, Actividad1.class);
        setResult(RESULT_CANCELED, intent);
        finish();
    }
    public void si(View view){
        Intent intent = new Intent(Actividad1_1.this, Actividad1.class);
        setResult(RESULT_OK, intent);
        finish();
    }

}
