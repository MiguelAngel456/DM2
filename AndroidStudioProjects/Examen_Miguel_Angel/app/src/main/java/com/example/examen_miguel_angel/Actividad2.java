package com.example.examen_miguel_angel;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Actividad2 extends AppCompatActivity {

    private ListView list;
    private localidades[] arrLocalidades;
    @Override


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad2);

        list=findViewById(R.id.listLocalidades);

        arrLocalidades=new localidades[] { new localidades("Añana (Alava)",21.92 +"Km2"+157+" hab. (2015)","https://www.twitter.com",getDrawable(R.drawable.aniana_salinas_2)),
                new localidades("Mundaka (Bizkaia)",4.01 +"Km2"+1892+" hab. (2015)","http://www.mundaka.org",getDrawable(R.drawable.mundaka)),
                new localidades("Gernika-Lumo (Bizkaia)",8.60 +"Km2"+16763+" hab. (2015)","http://www.gernika-lumo.net",getDrawable(R.drawable.gernika)),
                new localidades("Laguardia (Alava)",81.08 +"Km2"+1520+" hab. (2015)","http://www.laguardia-alava.net",getDrawable(R.drawable.laguardia)),
                new localidades("Vitoria-Gasteiz (Alava)",276.92 +"Km2"+243918+" hab. (2015)","https://www.twitter.com",getDrawable(R.drawable.vitoria_gasteiz)),
        };
        //Adaptador
        Adaptadorlocalidad adapt =
                new Adaptadorlocalidad(this, arrLocalidades);
        list.setAdapter(adapt);

    }
    class Adaptadorlocalidad extends ArrayAdapter<localidades> {
        public Adaptadorlocalidad(@NonNull Context context, localidades[] localidades) {
            super(context, R.layout.listlocalidades, localidades);
        }
        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView,
                            @NonNull ViewGroup parent) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            View item = inflater.inflate(R.layout.listlocalidades, null);
            TextView localidad = (TextView)item.findViewById(R.id.lbllocalidad);
            localidad.setText(arrLocalidades[position].getNombre());
            TextView superfi = (TextView)item.findViewById(R.id.lblSuperficie);
            superfi.setText(arrLocalidades[position].getSuperficie());
            TextView link = (TextView)item.findViewById(R.id.lblWeb);
            link.setText(arrLocalidades[position].getUrl());
            ImageView imagen = (ImageView)item.findViewById(R.id.imageView);
            imagen.setImageDrawable(arrLocalidades[position].getImagen());

            return (item);
        }
    }
    public void cancelar(View view){
        Intent intent = new Intent(Actividad2.this, MainActivity.class);
        setResult(RESULT_CANCELED, intent);
        finish();
    }
}
