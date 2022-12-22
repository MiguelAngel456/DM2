package com.example.ev1_examen;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class Actividad2 extends AppCompatActivity {

    private Spinner spinnerProvincias;
    private ArrayList<String> provincias;
    private TextView textViewUbicacion;
    private RadioGroup radioGroupUbicacion;
    private String selectedProvincia;
    private ArrayList<String> provinciasImplementadas;

    private final Localidad[] localidades = new Localidad[]{
            new Localidad("Añana", "Araba", "Interior", "http://www.cuadrilladeanana.es/anana/", "anana.jpg"),
            new Localidad("Areatza", "Bizkaia", "Interior", "http://www.areatza.net", "areatza.jpg"),
            new Localidad("Astigarraga", "Gipuzkoa", "Interior", "http://astigarraga.eus", "astigarraga.jpg"),
            new Localidad("Bermeo", "Bizkaia", "Costa", "http://www.bermeo.eus", "bermeo.jpg"),
            new Localidad("Getxo", "Bizkaia", "Costa", "http://www.getxo.eus", "getxo.jpg"),
            new Localidad("Donostia", "Gipuzkoa", "Costa", "http://www.donostia.eus", "donostia.jpg"),
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad2);
        getSupportActionBar().setTitle("LOCALIDADES CON ENCANTO");

        textViewUbicacion = findViewById(R.id.textViewUbicacion);
        radioGroupUbicacion = findViewById(R.id.radioGroupUbicacion);

        provincias = new ArrayList<>();
        provincias.add("Araba");
        provincias.add("Bizkaia");
        provincias.add("Gipuzkoa");
        provincias.add("Nafarroa");
        provincias.add("Lapurdi");
        provincias.add("Behe-Nafarroa");
        provincias.add("Zuberoa");

        provinciasImplementadas = new ArrayList<>();
        provinciasImplementadas.add("Bizkaia");
        provinciasImplementadas.add("Gipuzkoa");

        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, provincias);
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerProvincias = findViewById(R.id.spinnerProvincias);
        spinnerProvincias.setAdapter(adaptador);
        spinnerProvincias.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                Log.d("spinner", parentView.getItemAtPosition(position).toString());
                String provincia = parentView.getItemAtPosition(position).toString();
                if(provinciasImplementadas.contains(provincia)) {
                    Boolean mostrar = false;
                    for (Localidad localidad : localidades) {
                        if (localidad.getProvincia().equals(provincia)) {
                            selectedProvincia = provincia;
                            if (localidad.getUbicacion().equals("Costa")) {
                                mostrar = true;
                            }
                        }
                    }
                    if (mostrar) {
                        mostrarUbicacion();
                    } else {
                        ocultarUbicacion();
                    }
                }else{
                    mostrarAlerta();
                    ocultarUbicacion();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }

        });

    }

    private void mostrarAlerta(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Información");
        builder.setMessage("Las localidades de esta provincia no estan disponibles actualmente. Opción sin implementar!");
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();
        AlertDialog alert = builder.create();
        alert.show();
    }

    private void mostrarUbicacion(){
        textViewUbicacion.setVisibility(View.VISIBLE);
        radioGroupUbicacion.setVisibility(View.VISIBLE);
    }

    private void ocultarUbicacion(){
        textViewUbicacion.setVisibility(View.INVISIBLE);
        radioGroupUbicacion.setVisibility(View.INVISIBLE);
    }

    public void visualizarLista(View view){
        Intent intent = new Intent(this, ActiviadadLocalidades.class);
        intent.putExtra("provincia", selectedProvincia);
        RadioButton rb = findViewById(radioGroupUbicacion.getCheckedRadioButtonId());
        String ubicacion = rb.getText().toString();
        intent.putExtra("ubicacion", ubicacion);
        startActivity(intent);
    }

    public void volver(View view){
        finish();
    }
}