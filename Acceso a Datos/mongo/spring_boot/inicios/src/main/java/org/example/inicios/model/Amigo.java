package org.example.inicios.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "amigos")
public class Amigo {
    @Id
    private String id;
    private String nombre;
    private int edad;
    private List<String> hobbies;
    private List<String> telefonos;
    private List<Estudio> estudios;

    public Amigo() {
    }

    public Amigo(String id, String nombre, int edad, List<String> hobbies, List<String> telefonos, List<Estudio> estudios) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.hobbies = hobbies;
        this.telefonos = telefonos;
        this.estudios = estudios;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public List<String> getHobbies() {
        return hobbies;
    }

    public void setHobbies(List<String> hobbies) {
        this.hobbies = hobbies;
    }

    public List<String> getTelefonos() {
        return telefonos;
    }

    public void setTelefonos(List<String> telefonos) {
        this.telefonos = telefonos;
    }

    public List<Estudio> getEstudios() {
        return estudios;
    }

    public void setEstudios(List<Estudio> estudios) {
        this.estudios = estudios;
    }
}
