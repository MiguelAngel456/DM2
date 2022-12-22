package com.example.pestanastoolbar;



import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTabHost;

public class PestanasEj2 extends FragmentActivity {
    private FragmentTabHost tabHost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pestanas_ej2);


        tabHost= (FragmentTabHost) findViewById(android.R.id.tabhost);
        tabHost.setup(this,
                getSupportFragmentManager(),android.R.id.tabcontent);
        //Introduce una nueva pestaña usando el método addTab().
        //Se indican3 parametros:
        // Un objeto TabSpec
        // una clse con el fragment a visulizar en la pestaña,
        // un Bundle por si se quiere pasar información a la lengüeta.
        //El método newTabSpec() crea una nueva pestaña en un TabHost. Se le puede
        // pasar como parametro un String, que se utiliza como identificador y
        // devuelve el objeto de tipo TabSpec creado.
        tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator("Pestaña 1"),
                        Tab1.class, null);
        tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator("Pestaña 2"),
                Tab2.class, null);
        tabHost.addTab(tabHost.newTabSpec("tab3").setIndicator("Pestaña 3"),
                Tab3.class, null);
    }
}

