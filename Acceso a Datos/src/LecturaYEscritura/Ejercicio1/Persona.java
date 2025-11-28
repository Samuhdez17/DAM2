package LecturaYEscritura.Ejercicio1;

import java.io.Serializable;

public class Persona implements Serializable {
    private int edad;
    private String nombre;

    public Persona(int edad, String nombre) {
        this.edad = edad;
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String toString() {
        return String.format("Persona { Edad: %d, Nombre: %s }", this.edad, this.nombre);
    }
}
