package org.example;

import org.example.DAO.CursoDAO;
import org.example.conexion.ConexionMongo;
import org.example.model.Alumno;
import org.example.model.Curso;

import javax.crypto.spec.PSource;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        CursoDAO cursoDAO = new CursoDAO(ConexionMongo.getDataBase());

        Scanner sc = new Scanner(System.in);
        int opcion = -1;

        while (opcion != 0) {
            System.out.println("""
                        MENU PRINCIPAL
                1. Opciones del apartado .2
                2. Opciones del apartado .3
                3. Opciones del apartado .4
                4. Opciones del apartado .5
                0. Salir
                """);

            opcion = sc.nextInt();

            switch (opcion) {
                case 1 -> {
                    while (opcion != 0) {
                        System.out.println("""
                                    APARTADO .2
                            1. Listar cursos
                            2. Listar cursos activos
                            3. Listar alumnos de un curso
                            4. Listar cursos que algun alumno tiene mas de un 9
                            0. Salir al menu pricipal
                            """);

                        opcion = sc.nextInt();

                        switch (opcion) {
                            case 1 -> {
                                opcion = -1;
                                cursoDAO.listarCursos();
                            }

                            case 2 -> {
                                opcion = -1;
                                cursoDAO.listarCursosActivos();
                            }

                            case 3 -> {
                                opcion = -1;
                                System.out.println("indica codigo de curso:");
                                String codigo = sc.next();
                                cursoDAO.mostrarAlumnosPorCurso(codigo);
                            }

                            case 4 -> {
                                opcion = -1;
                                cursoDAO.listarCursosConAlumnoSupA9();
                            }

                            case 0 -> System.out.println("Saliendo al menu principal");
                        }
                    }

                    opcion = -1;
                }

                case 2 -> {
                    while (opcion != 0) {
                        System.out.println("""
                                        APARTADO .3
                                1. Insertar curso
                                0. Salir al menu pricipal
                                """);

                        opcion = sc.nextInt();

                        switch (opcion) {
                            case 1 -> {
                                opcion = -1;

                                System.out.println("indica codigo de curso:");
                                String codigo = sc.next();

                                System.out.println("indica nombre de curso:");
                                String nombre = sc.next();

                                System.out.println("indica horas de curso:");
                                int horas = sc.nextInt();

                                System.out.println("indica si esta activo el curso (s/n):");
                                char estado = sc.next().charAt(0);

                                System.out.println("indica numero de alumnos");
                                int alumnos = sc.nextInt();

                                ArrayList<Alumno> listaAlumnos = new ArrayList<>();

                                for  (int i = 0; i < alumnos; i++) {
                                    System.out.println("nuevo alumno\n");
                                    System.out.println("indica dni de alumno:");
                                    String dniAlumno = sc.next();

                                    System.out.println("indica nombre de alumno:");
                                    String nombreAlumno = sc.next();

                                    System.out.println("indica email de alumno:");
                                    String emailAlumno = sc.next();

                                    System.out.println("indica nota de alumno:");
                                    double notaAlumno = sc.nextDouble();

                                    listaAlumnos.add(new Alumno(dniAlumno, nombreAlumno, emailAlumno, notaAlumno));
                                }

                                cursoDAO.insertarCurso(
                                        new Curso(codigo, nombre, horas, estado == 's', listaAlumnos
                                ));
                            }

                            case 0 -> System.out.println("Saliendo al menu principal");
                        }
                    }

                    opcion = -1;
                }

                case 3 -> {
                    while (opcion != 0) {
                        System.out.println("""
                                        APARTADO .4
                                1. Modificar horas de curso
                                2. Modificar nota alumno
                                3. Modificar estado curso
                                0. Salir al menu pricipal
                                """);

                        opcion = sc.nextInt();

                        switch (opcion) {
                            case 1 -> {
                                opcion = -1;

                                System.out.println("indica codigo de curso:");
                                String codigo = sc.next();

                                System.out.println("indica las horas de curso:");
                                int horas = sc.nextInt();

                                cursoDAO.cambiarHoras(codigo, horas);
                            }

                            case 2 -> {
                                opcion = -1;

                                System.out.println("indica codigo de curso:");
                                String codigo = sc.next();

                                System.out.println("indica dni alumno:");
                                String dni = sc.next();

                                System.out.println("indica nueva nota:");
                                double nota = sc.nextDouble();

                                cursoDAO.cambiarNotaAlumno(codigo, dni, nota);
                            }

                            case 3 -> {
                                opcion = -1;

                                System.out.println("indica codigo de curso:");
                                String codigo = sc.next();

                                System.out.println("indica si esta activo el curso (s/n):");
                                char estado = sc.next().charAt(0);

                                cursoDAO.cambiarEstado(codigo, estado == 's');
                            }

                            case 0 -> System.out.println("Saliendo al menu principal");
                        }
                    }

                    opcion = -1;
                }

                case 4 -> {
                    while (opcion != 0) {
                        System.out.println("""
                                        APARTADO .5
                                1. Eliminar curso
                                2. Eliminar alumno
                                0. Salir al menu pricipal
                                """);

                        opcion = sc.nextInt();

                        switch (opcion) {
                            case 1 -> {
                                opcion = -1;

                                System.out.println("indica codigo de curso:");
                                String codigo = sc.next();

                                cursoDAO.eliminarCurso(codigo);
                            }

                            case 2 -> {
                                opcion = -1;

                                System.out.println("indica codigo de curso:");
                                String codigo = sc.next();

                                System.out.println("indica dni alumno:");
                                String dni = sc.next();

                                cursoDAO.eliminarAlumno(codigo, dni);
                            }

                            case 0 -> System.out.println("Saliendo al menu principal");
                        }
                    }

                    opcion = -1;
                }

                case 0 -> System.out.println("Saliendo...");
            }
        }
    }
}
