package model;

import java.util.List;

public class Curso {
    private String curso;
    private List<Alumno> alumnos;

    public String getCurso() {
        return curso;
    }

    public List<Alumno> getAlumnos() {
        return alumnos;
    }

    public void addAlumno(Alumno nuevoAlumno) {
        alumnos.add(nuevoAlumno);
    }
}
