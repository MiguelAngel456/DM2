package com.example.ev1_examen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ActiviadadLocalidades extends AppCompatActivity {

    private final Localidad[] localidades = new Localidad[]{
            new Localidad("Añana", "Araba", "Interior", "http://www.cuadrilladeanana.es/anana/", "anana.jpg"),
            new Localidad("Areatza", "Bizkaia", "Interior", "http://www.areatza.net", "areatza.jpg"),
            new Localidad("Astigarraga", "Gipuzkoa", "Interior", "http://astigarraga.eus", "astigarraga.jpg"),
            new Localidad("Bermeo", "Bizkaia", "Costa", "http://www.bermeo.eus", "bermeo.jpg"),
            new Localidad("Getxo", "Bizkaia", "Costa", "http://www.getxo.eus", "getxo.jpg"),
            new Localidad("Donostia", "Gipuzkoa", "Costa", "http://www.donostia.eus", "donostia.jpg"),
    };

    private ArrayList<Localidad> localidadesProvincia;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activiadad_localidades);
        getSupportActionBar().setTitle("LOCALIDADES CON ENCANTO");

        Bundle bundle = getIntent().getExtras();
        String provincia = bundle.getString("provincia");
        String ubicacion = bundle.getString("ubicacion");

        localidadesProvincia = new ArrayList<>();

        for(Localidad localidad: localidades){
            if(localidad.getProvincia().equals(provincia)){
                localidadesProvincia.add(localidad);
            }
        }

        TextView textViewLocalidadTitle = findViewById(R.id.textViewLocalidadTitle);
        textViewLocalidadTitle.setText("---"+provincia+"---\n("+ubicacion+")");
    }

    public void volver(View view){
        finish();
    }
}