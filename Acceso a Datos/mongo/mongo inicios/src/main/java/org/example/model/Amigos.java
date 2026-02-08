package org.example.model;

public class Amigos {
    private String nombre;
    private int edad;
    private String[] hobbies;
    private int[] telefonos;
    private Estudios[] estudios;

    public Amigos(String nombre, int edad, String[] hobbies, int[] telefonos, Estudios[] estudios) {
        this.nombre = nombre;
        this.edad = edad;
        this.hobbies = hobbies;
        this.telefonos = telefonos;
        this.estudios = estudios;
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

    public String[] getHobbies() {
        return hobbies;
    }

    public void setHobbies(String[] hobbies) {
        this.hobbies = hobbies;
    }

    public int[] getTelefonos() {
        return telefonos;
    }

    public void setTelefonos(int[] telefonos) {
        this.telefonos = telefonos;
    }

    public Estudios[] getEstudios() {
        return estudios;
    }

    public void setEstudios(Estudios[] estudios) {
        this.estudios = estudios;
    }
}
