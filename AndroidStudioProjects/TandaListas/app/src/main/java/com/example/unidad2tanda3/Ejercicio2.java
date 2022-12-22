package com.example.unidad2tanda3;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class Ejercicio2 extends AppCompatActivity {
    private ListView vista;
    private Webs[] listaWebs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejercicio2);

        listaWebs = new Webs[] { new Webs("Twitter","https://www.twitter.com",getDrawable(R.drawable.tw)),
                new Webs("Guaixe","https://guaixe.eus/",getDrawable(R.drawable.guaixe)),
                new Webs("Berria","https://www.berria.eus\n",getDrawable(R.drawable.berria)),
                new Webs("Naiz","https://www.naiz.eus\n",getDrawable(R.drawable.naiz)),
                new Webs("Spotify","https://open.spotify.com/",getDrawable(R.drawable.spoty))};

        vista = (ListView) findViewById(R.id.vista);
        this.setTitle("Mis webs favoritas");

        //Adaptador
        AdaptadorWebs adapt =
                new AdaptadorWebs(this, listaWebs);
        vista.setAdapter(adapt);

    }
    class AdaptadorWebs extends ArrayAdapter <Webs>{
        public AdaptadorWebs(@NonNull Context context, Webs[] webs) {
            super(context, R.layout.listitem_webs, webs);
        }
        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView,
                            @NonNull ViewGroup parent) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            View item = inflater.inflate(R.layout.listitem_webs, null);
            TextView nombre = (TextView)item.findViewById(R.id.Nombre);
            nombre.setText(listaWebs[position].getNombre());
            TextView link = (TextView)item.findViewById(R.id.link);
            link.setText(listaWebs[position].getUrl());
            ImageView imagen = (ImageView)item.findViewById(R.id.imageView);
            imagen.setImageDrawable(listaWebs[position].getImagen());

            return (item);
        }
    }
}


