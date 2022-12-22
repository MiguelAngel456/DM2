package com.example.unidad2tanda3;

import android.graphics.drawable.Drawable;

public class Artistas {
    private String nombre;
    private String cancion;
    private String url;
    private Drawable album;


    public Artistas(String nombre,String cancion,String url,Drawable album){
        this.nombre=nombre;
        this.cancion=cancion;
        this.url=url;
        this.album=album;
    }

    public String getNombre() {
        return nombre;
    }

    public String getUrl() {
        return url;
    }

    public Drawable getAlbum() {
        return album;
    }

    public String getCancion() {
        return cancion;
    }
}
