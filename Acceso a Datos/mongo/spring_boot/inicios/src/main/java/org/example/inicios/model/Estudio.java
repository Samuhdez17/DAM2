package org.example.inicios.model;

import org.springframework.data.annotation.Id;

public class Estudio {
    @Id
    private String id;
    private String titulo;
    private String centro;
    private int anio;

    public Estudio(String id, String titulo, String centro, int anio) {
        this.id = id;
        this.titulo = titulo;
        this.centro = centro;
        this.anio = anio;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCentro() {
        return centro;
    }

    public void setCentro(String centro) {
        this.centro = centro;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }
}
