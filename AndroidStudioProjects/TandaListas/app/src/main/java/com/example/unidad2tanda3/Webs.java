package com.example.unidad2tanda3;

import android.graphics.drawable.Drawable;

public class Webs {
    private String nombre;
    private String url;
    private Drawable imagen;

    public Webs(String nom, String url, Drawable img){
        this.nombre=nom;
        this.url=url;
        this.imagen=img;
    }

    public String getNombre() {
        return nombre;
    }

    public String getUrl() {
        return url;
    }

    public Drawable getImagen() {
        return imagen;
    }
}
