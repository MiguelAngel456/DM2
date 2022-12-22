package com.example.examen_miguel_angel;

import android.graphics.drawable.Drawable;

public class localidades {
    private String nombre,superficie;
    private String url;
    private Drawable imagen;

    public localidades(String nom,String superficie, String url, Drawable img){
        this.nombre=nom;
        this.superficie=superficie;
        this.url=url;
        this.imagen=img;
    }

    public String getNombre() {
        return nombre;
    }
    public String getSuperficie() {
        return superficie;
    }

    public String getUrl() {
        return url;
    }

    public Drawable getImagen() {
        return imagen;
    }
}
