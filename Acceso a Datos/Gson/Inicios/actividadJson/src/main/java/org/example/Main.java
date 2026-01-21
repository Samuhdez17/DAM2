package org.example;

import model.Alumno;
import model.Curso;
import service.CursoService;

import java.util.List;
import java.util.Scanner;

public class Main {
    static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        CursoService cursoService = new CursoService();

        Curso curso = cursoService.cargarCurso();

//        for (Alumno alumno : curso.getAlumnos())
//            if (alumno.esRepetidor())
//                System.out.printf("%s, %d años\n", alumno.getNombre(), alumno.getEdad());
//
//        System.out.println("Indica un modulo: ");
//        String modulo = entrada.next().toUpperCase();
//
//        for (Alumno alumno : curso.getAlumnos())
//            if (alumno.getModulos().contains(modulo))
//                System.out.printf("%s, %d años\n", alumno.getNombre(), alumno.getEdad());

        Alumno nuevoAlumno = new Alumno(
                12,
                20,
                "Samu",
                List.of("DI", "AD", "PV"),
                false
        );

        curso.addAlumno(nuevoAlumno);

        for (Alumno alumno : curso.getAlumnos()) {
            System.out.println(alumno.getNombre());
        }

        cursoService.actualizarCurso(curso);
    }
}
