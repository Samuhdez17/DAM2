package model;

import java.util.List;

public class Alumno {
    private int id;
    private int edad;
    private String nombre;
    private List<String> modulos;
    private boolean repetidor;

    public int getId() {
        return id;
    }

    public int getEdad() {
        return edad;
    }

    public String getNombre() {
        return nombre;
    }

    public List<String> getModulos() {
        return modulos;
    }

    public boolean esRepetidor() {
        return repetidor;
    }

    public Alumno(int id, int edad, String nombre, List<String> modulos, boolean repetidor) {
        this.id = id;
        this.edad = edad;
        this.nombre = nombre;
        this.modulos = modulos;
        this.repetidor = repetidor;
    }
}
