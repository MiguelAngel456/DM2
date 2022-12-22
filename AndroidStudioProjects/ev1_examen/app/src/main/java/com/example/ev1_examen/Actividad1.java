package com.example.ev1_examen;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class Actividad1 extends AppCompatActivity {

    private ArrayList<String> provincias;
    private static int candidatos;

    private EditText editTextNombre;
    private EditText editTextDia;
    private EditText editTextMes;
    private EditText editTextAnio;
    private RadioGroup radioGroup;
    private RadioButton selectedRadio;
    private CheckBox chbPHP;
    private CheckBox chbJava;
    private CheckBox chbHTML;
    private CheckBox chbCss;
    private Spinner spinnerProvincias;
    private ArrayList<String> conocimientos;
    private TextView textViewCandidatos;
    private Button btnEvaluar;
    private Button btnSalirCandidato;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad1);
        getSupportActionBar().setTitle("CANDIDATO/A");

        candidatos = 0;

        provincias = new ArrayList<>();
        spinnerProvincias = findViewById(R.id.spinnerProvincias);
        radioGroup = findViewById(R.id.radioGroup);

        editTextNombre = findViewById(R.id.editTextNombre);
        editTextDia = findViewById(R.id.editTextDia);
        editTextMes = findViewById(R.id.editTextMes);
        editTextAnio = findViewById(R.id.editTextAnio);
        selectedRadio = (RadioButton) findViewById(radioGroup.getCheckedRadioButtonId());
        chbPHP = findViewById(R.id.checkBoxPHP);
        chbJava = findViewById(R.id.checkBoxJava);
        chbHTML = findViewById(R.id.checkBoxHtml);
        chbCss = findViewById(R.id.checkBoxCss);

        provincias.add("Araba");
        provincias.add("Bizkaia");
        provincias.add("Gipuzkoa");
        provincias.add("Nafarroa");
        provincias.add("Lapurdi");
        provincias.add("Behe-Nafarroa");
        provincias.add("Zuberoa");

        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, provincias);
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerProvincias.setAdapter(adaptador);

        textViewCandidatos = findViewById(R.id.textViewCandidatosResult);
        textViewCandidatos.setText(candidatos+"");

        btnEvaluar = findViewById(R.id.buttonEvaluar);
        btnSalirCandidato = findViewById(R.id.buttonSalirCandidato);
    }

    public void evaluar(View view){
        if(validateForm()){
            Intent intent = new Intent(this, ActividadEvaluar.class);
            intent.putExtra("nombre", editTextNombre.getText().toString());
            intent.putExtra("diaNac", editTextDia.getText().toString());
            intent.putExtra("mesNac", editTextMes.getText().toString());
            intent.putExtra("anioNac", editTextAnio.getText().toString());
            intent.putExtra("sexo", selectedRadio.getText().toString());
            intent.putExtra("provincia", spinnerProvincias.getSelectedItem().toString());
            intent.putExtra("conocimientos", conocimientos);
            startActivityForResult(intent, 1);
        }
    }

    private boolean validateForm(){
        if( TextUtils.isEmpty(editTextNombre.getText())){
            editTextNombre.setError( "Nombre requerido" );
            return false;
        }
        if( TextUtils.isEmpty(editTextDia.getText())){
            editTextDia.setError( "Día requerido" );
            return false;
        }
        if( TextUtils.isEmpty(editTextMes.getText())){
            editTextMes.setError( "Mes requerido" );
            return false;
        }
        if( TextUtils.isEmpty(editTextAnio.getText())){
            editTextAnio.setError( "Año requerido" );
            return false;
        }

        conocimientos = new ArrayList<>();
        if (chbPHP.isChecked()) {
            conocimientos.add(chbPHP.getText().toString());
        }
        if(chbJava.isChecked()){
            conocimientos.add(chbJava.getText().toString());
        }
        if(chbHTML.isChecked()){
            conocimientos.add(chbHTML.getText().toString());
        }
        if(chbCss.isChecked()){
            conocimientos.add(chbCss.getText().toString());
        }
        return true;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            candidatos ++;
            textViewCandidatos.setText(candidatos+"");
            editTextNombre.setText("");
            editTextDia.setText("");
            editTextMes.setText("");
            editTextAnio.setText("");
            spinnerProvincias.setSelection(0);
            ((RadioButton)radioGroup.getChildAt(0)).setChecked(true);
            chbPHP.setChecked(false);
            chbJava.setChecked(false);
            chbHTML.setChecked(false);
            chbCss.setChecked(false);

            if(candidatos > 3){
                cambiarBoton();
            }
        }
    }

    private void cambiarBoton(){
        btnEvaluar.setVisibility(View.INVISIBLE);
        btnSalirCandidato.setVisibility(View.VISIBLE);
    }

    public void salir(View view){
        finish();
    }

}