package com.example.pestanastoolbar;

import android.graphics.drawable.Drawable;

public class chats {
    private String nombre;
    private String mensj;
    private Drawable imagen;

    public chats(String nom, String mensj, Drawable img){
        this.nombre=nom;
        this.mensj=mensj;
        this.imagen=img;
    }

    public String getNombre() {
        return nombre;
    }

    public String getUrl() {
        return mensj;
    }

    public Drawable getImagen() {
        return imagen;
    }
}
