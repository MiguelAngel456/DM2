package com.example.leyendagrupo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;


public class Zona3_1 extends AppCompatActivity {
    private Button btnGrupos, btnOrden, btnComprobar;
    private TextView txtleyenda;
    private DialogoGrupos dialogoGrupos;

    private String[] palabras={"barro", "puente", "peces"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnGrupos=findViewById(R.id.zona3_btnGrupos);
        btnOrden=findViewById(R.id.zona3_btnOrden);
        btnComprobar=findViewById(R.id.zona3_btnComprobar);
        txtleyenda=findViewById(R.id.zona3_txtLeyenda);


//*************************** Cogemos el array de la base de datos se lo pasamos a un arrayList ************************************

        String[] arrAlumnos={"Pepe","Juan","Iker","Emma","Miguel","Airam","Alayn","Xavi","Xabi","Javi","Pablo"};
        ArrayList<String> listaAlumnos=new ArrayList<>();
        for(String s: arrAlumnos){
            listaAlumnos.add(s);
        }

//**************** Creamos una variable para saber cuántos grupos tenemos que hacer y hacemos un random de dichos alumnos ************************************

        ArrayList<String> alumnos=new ArrayList<>();
        int cantGrupos = listaAlumnos.size()/3;
        if (listaAlumnos.size()%3!=0) {
            cantGrupos++;
        }

        while(listaAlumnos.size()>0){
            int a = (int) (Math.random()*listaAlumnos.size());
            alumnos.add(listaAlumnos.get(a));
            listaAlumnos.remove(a);
        }


//************************** Asignar Arraylist a Array para pasarselo al Dialogo a la vez que creamos un mapara para manteber dichos grupos de 3 ************************************

        String[] total=new String[alumnos.size()+cantGrupos];
        int cont=1;
        int pos=0;
        HashMap<Integer,ArrayList<String>> mapaGrupos=new HashMap<>();
        for (int i = 0; i < total.length; i++) {
            if(i%4==0 ){
                total[i]="Grupo "+cont+":";

                //**************** Parte Mapa ******************
                mapaGrupos.put(cont, new ArrayList<String>());

                cont++;
            }else{
                total[i]="\t\t\t"+alumnos.get(pos);

                //**************** Parte Mapa ******************
                mapaGrupos.get(cont-1).add(alumnos.get(pos));

                pos+=1;
            }
        }

//********************************* Mostrar Grupos ***************************

        btnGrupos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirDialogo(total);
            }
        });

//****************************** Comprobar la leyenda **************************

        btnComprobar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int espacios=0;
                for (int e = 0; e < txtleyenda.length(); e++) {
                    if(txtleyenda.getText().charAt(e)==' '){
                        espacios++;
                    }
                }
                int contEsta=0;
                for (int i = 0; i < palabras.length; i++) {
                    if(txtleyenda.getText().toString().contains(palabras[i])){
                        contEsta++;
                    }
                }


//                btnGrupos.setText(espacios+"------"+contEsta);
                if(espacios<29 || contEsta!=3 || espacios>39){
                    String errores ="";
                    if (contEsta!=3){
                        errores+="La leyenda debe contener las palabras: barro, puente y peces \n\n";
                    }
                    if (espacios<29 || espacios>39){
                        errores+="La leyenda debe contener entre 30 y 40 palabras";
                    }
                    txtleyenda.setBackgroundResource(R.drawable.bordes_edittext_mal);
                    btnOrden.setEnabled(false);
                    Toast.makeText(Zona3_1.this,errores, Toast.LENGTH_LONG).show();
                }else{
                    txtleyenda.setBackgroundResource(R.drawable.bordes_edittext_bien);
                    btnOrden.setEnabled(true);
                }

            }
        });

//************************* Ordenar los grupos de 3 ****************************

        ArrayList<String> alumnos2=new ArrayList<>();
        String[] total2 = new String[total.length];


        //************************* Ordenar los grupos de 3 por cada par clave valor del mapa ****************************
        for (Integer i: mapaGrupos.keySet()) {
            System.out.println(mapaGrupos.get(i));
        }

        for (Integer i: mapaGrupos.keySet()){
            while(mapaGrupos.get(i).size()>0){
                int a = (int) (Math.random()*mapaGrupos.get(i).size());
                alumnos2.add(mapaGrupos.get(i).get(a));
                mapaGrupos.get(i).remove(a);
            }
        }

//************************* Generamos de nuevo la lista grande ya ordenada con  los grupos de 3 y llamamos al diálogo para que lo muestre ****************************

        cont=1;
        pos=0;
        for (int i = 0; i < total2.length; i++) {
            if(i%4==0 ){
                total2[i]="Grupo "+cont+":";
                cont++;
            }else{
                total2[i]="\t\t\t"+alumnos2.get(pos);
                pos+=1;
            }
        }
        for(String s: total2){
            System.out.println(s);
        }

        btnOrden.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                abrirDialogo(total2);
            }

        });

    }

    private void abrirDialogo(String[] total) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        dialogoGrupos = new DialogoGrupos(total);
        dialogoGrupos.show(fragmentManager, "Hacer Grupos");
    };

}