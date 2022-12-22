package com.example.tanda_fragments;

public class Manga {
//    private String img;
    private String asunto;
    private String texto;
    public Manga(String asunto, String texto) {
       // this.img = de;
        this.asunto = asunto;
        this.texto = texto;
    }
//    public String getImg() {
//        return img;
//    }
    public String getAsunto() {
        return asunto;
    }
    public String getTexto() {
        return texto;
    }
}
