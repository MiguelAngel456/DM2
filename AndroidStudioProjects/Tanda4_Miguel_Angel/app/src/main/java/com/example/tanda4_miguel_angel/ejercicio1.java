package com.example.tanda4_miguel_angel;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ejercicio1 extends AppCompatActivity {
    private Spinner SpinPaises;
    private TextView lblPoblacion;
    private String[] poblaciones;
    private  String opcionSeleccionada;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ejercicio1);

        SpinPaises=findViewById(R.id.spinPaises);
        lblPoblacion=findViewById(R.id.lblPoblacions);

        poblaciones= new String[]{"","144,1 millones","125,8 millones","1,402 miles de millones","38,01 millones"};
        //PARA RELLENAR LA LISTA
        final String[] datos = new String[] {"España","Rusia","Japon","China", "Canada"};
        ArrayAdapter<String> adaptadorListView = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, datos);
        SpinPaises.setAdapter(adaptadorListView);
        SpinPaises.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String opcionSeleccionada=(SpinPaises.getItemAtPosition(i)).toString();
                lblPoblacion.setText("La poblacion del pais seleccionado es:");
                if(opcionSeleccionada=="España"){
                    lblPoblacion.setText(lblPoblacion.getText()+"\n47,35 millones");
                }else{
                    if(opcionSeleccionada=="Rusia"){
                        lblPoblacion.setText(lblPoblacion.getText()+"\n144,1 millones");
                    }else{
                        if(opcionSeleccionada=="Japon"){
                            lblPoblacion.setText(lblPoblacion.getText()+"\n125,8 millones");
                        }else{
                            if(opcionSeleccionada=="China"){
                                lblPoblacion.setText(lblPoblacion.getText()+"\n1,402 miles de millones");
                            }else{
                                lblPoblacion.setText(lblPoblacion.getText()+"\n38,01 millones");
                            }
                        }
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }
}
