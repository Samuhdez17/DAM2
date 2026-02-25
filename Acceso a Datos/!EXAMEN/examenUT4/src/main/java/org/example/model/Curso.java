package org.example.model;

import java.util.List;

public class Curso {
    private String codigo;
    private String nombre;
    private int horas;
    private boolean activo;
    private List<Alumno> alumnos;

    public Curso(String codigo, String nombre, int horas, boolean activo, List<Alumno> alumnos) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.horas = horas;
        this.activo = activo;
        this.alumnos = alumnos;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getHoras() {
        return horas;
    }

    public void setHoras(int horas) {
        this.horas = horas;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public List<Alumno> getAlumnos() {
        return alumnos;
    }

    public void setAlumnos(List<Alumno> alumnos) {
        this.alumnos = alumnos;
    }
}
