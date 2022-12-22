package com.example.unidad2tanda3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Ejercicio1 extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    private Spinner spOpciones;
    private TextView cadena;
    private int poblacion;
    private int superficie;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejercicio1);
        this.setTitle("Paises");
        spOpciones = (Spinner) findViewById(R.id.spinnerPaises);
        //Creamos el Array
        final String[] paises = new String [] {"Italia" ,"Grecia","Finlandia", "Polonia", "Irlanda", "Bulgaria"} ;
        //Creamos el adaptador para el spinner
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, paises);
        adaptador.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);
        spOpciones.setAdapter(adaptador);
        spOpciones.setOnItemSelectedListener(this);

    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        cadena = findViewById(R.id.cadena);
        TextView paisSeleccionado = (TextView) view;
        asignarDatosPais(paisSeleccionado.getText().toString());
        cadena.setText("Información del pais: \n\n\t\tPoblacion: "+poblacion+" millones\n\t\tSuperficie: "+superficie+" km²");
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Toast.makeText(this, "No se ha seleccionado ninguna opción" ,
                Toast.LENGTH_SHORT).show();
    }

    private void asignarDatosPais(String pais){
        if(pais.equals("Italia")){
            poblacion = 60;
            superficie = 310230;
        }
        if(pais.equals("Grecia")){
            poblacion = 11;
            superficie = 131957;
        }
        if(pais.equals("Finlandia")){
            poblacion = 6;
            superficie = 338440;
        }
        if(pais.equals("Polonia")){
            poblacion = 38;
            superficie = 322575;
        }
        if(pais.equals("Irlanda")){
            poblacion = 5;
            superficie = 70273;
        }
        if(pais.equals("Bulgaria")){
            poblacion = 7;
            superficie = 110994;
        }

    }
}