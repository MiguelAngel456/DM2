package com.example.main_ejercicio;

import android.graphics.drawable.Drawable;

public class Clientes {
    private String nombreApe;
    private int deuda;

    public Clientes(String nom,int deuda){
        this.nombreApe=nom;
        this.deuda=deuda;
    }

    public String getNombreApe() {
        return nombreApe;
    }
    public int getSuperficie() {
        return deuda;
    }

}
