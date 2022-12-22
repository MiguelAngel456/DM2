package com.example.ev1_examen;

import java.io.Serializable;

public class Localidad implements Serializable {
    private String nombre;
    private String provincia;
    private String ubicacion;
    private String enlace;
    private String imagen;

    public Localidad(String nombre, String provincia, String ubicacion, String enlace, String imagen) {
        this.nombre = nombre;
        this.provincia = provincia;
        this.ubicacion = ubicacion;
        this.enlace = enlace;
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getEnlace() {
        return enlace;
    }

    public void setEnlace(String enlace) {
        this.enlace = enlace;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}
