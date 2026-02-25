package org.example.model;

public class Alumno {
    private String dni;
    private String nombre;
    private String email;
    private double nota;

    public Alumno(String dni, String nombre, String email, double nota) {
        this.dni = dni;
        this.nombre = nombre;
        this.email = email;
        this.nota = nota;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }
}
