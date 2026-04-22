package com.example.crud_srpingboot.DAO.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="estudios")
public class Estudio {
    @Id
    private String id;
    private String titulo;
    private String centro;
    private int anio;

    public Estudio() {
    }

    public Estudio(String id, String titulo, String centro, int anio) {
        this.id = id;
        this.titulo = titulo;
        this.centro = centro;
        this.anio = anio;
    }

    public Estudio(String titulo, String centro, int anio) {
        this.titulo = titulo;
        this.centro = centro;
        this.anio = anio;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    @Override
    public String toString() {
        return String.format("""
            Titulo: %s
            Centro: %s
            Año: %d
                """, titulo, centro, anio);
    }
}
