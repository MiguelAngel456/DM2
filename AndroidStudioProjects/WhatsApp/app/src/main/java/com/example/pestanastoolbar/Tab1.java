package com.example.pestanastoolbar;

import android.content.Context;
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
import androidx.fragment.app.Fragment;

public class Tab1 extends AppCompatActivity{
    private ListView vista;
    private chats[] lista;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab1);
        lista = new chats[] { new chats("Gustavo","Hola que tal",getDrawable(R.drawable.rana)),
                new chats("Homer","Me voy a trabajar",getDrawable(R.drawable.homer)),
                new chats("Carlos","Adios",getDrawable(R.drawable.feliz))};
//
//

        //lista = new chats[] { new chats("Patxi","Hola! ¿Que tal el dia?",getDrawable(R.drawable.rana))};
        vista = (ListView) findViewById(R.id.vista);


        //ADAPTADOR
        AdaptadorWebs adapt =new AdaptadorWebs(this, lista);
        vista.setAdapter(adapt);
        vista=(ListView) findViewById(R.id.vista);
    }

    @Nullable
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.tab1, container,false);

        
    }
    class AdaptadorWebs extends ArrayAdapter<chats> {
        public AdaptadorWebs(@NonNull Context context, chats[] webs) {
            super(context, R.layout.listitem, webs);
        }
        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView,
                            @NonNull ViewGroup parent) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            View item = inflater.inflate(R.layout.listitem, null);
            TextView nombre = (TextView)item.findViewById(R.id.Nombre);
            nombre.setText(lista[position].getNombre());
            TextView link = (TextView)item.findViewById(R.id.link);
            link.setText(lista[position].getUrl());
            ImageView imagen = (ImageView)item.findViewById(R.id.imageView);
            imagen.setImageDrawable(lista[position].getImagen());

            return (item);
        }
    }
}
