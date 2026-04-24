package com.example.ej_crud_springboot.filamentos.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="especs")
public class Especs {
    @Id
    private String id;
    private int tempImp;
    private int tempCama;
    private int velImp;

    public Especs() {
    }

    public Especs(String id, int tempImp, int tempCama, int velImp) {
        this.id = id;
        this.tempImp = tempImp;
        this.tempCama = tempCama;
        this.velImp = velImp;
    }

    public Especs(int tempImp, int tempCama, int velImp) {
        this.tempImp = tempImp;
        this.tempCama = tempCama;
        this.velImp = velImp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getTempImp() {
        return tempImp;
    }

    public void setTempImp(int tempImp) {
        this.tempImp = tempImp;
    }

    public int getTempCama() {
        return tempCama;
    }

    public void setTempCama(int tempCama) {
        this.tempCama = tempCama;
    }

    public int getVelImp() {
        return velImp;
    }

    public void setVelImp(int velImp) {
        this.velImp = velImp;
    }
}
