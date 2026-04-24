package com.example.ej_crud_springboot.filamentos.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javafx.scene.paint.Color;

@Document(collection="filamentos")
public class Filamento {
    @Id
    private String id;
    private String material;
    private String marca;
    private Color color;
    private Especs especs;
    private int grDispo;

    public Filamento() {
    }

    public Filamento(String id, String material, String marca, Color color, Especs especs, int grDispo) {
        this.id = id;
        this.material = material;
        this.marca = marca;
        this.color = color;
        this.especs = especs;
        this.grDispo = grDispo;
    }

    public Filamento(String material, String marca, Color color, Especs especs, int grDispo) {
        this.material = material;
        this.marca = marca;
        this.color = color;
        this.especs = especs;
        this.grDispo = grDispo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Especs getEspecs() {
        return especs;
    }

    public void setEspecs(Especs especs) {
        this.especs = especs;
    }

    public int getGrDispo() {
        return grDispo;
    }
    
    public void setGrDispo(int grDispo) {
        this.grDispo = grDispo;
    }
}

