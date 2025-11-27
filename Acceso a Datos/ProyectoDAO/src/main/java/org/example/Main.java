package org.example;

import dao.autor.AutorDAO;
import dao.autor.AutorImpl;
import dao.libro.LibroDAO;
import dao.libro.LibroImpl;
import dao.libro_autor.LibroAutorDAO;
import dao.libro_autor.LibroAutorImpl;
import dao.prestamo.PrestamoDAO;
import dao.prestamo.PrestamoImpl;
import dao.usuario.UsuarioDAO;
import dao.usuario.UsuarioImpl;
import service.BibliotecaService;
import model.Autor;
import model.Libro;
import model.Prestamo;
import model.Usuario;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final String MENU_PRINCIPAL = """
            ========= MENU PRINCIPAL =========
            1.  Acceder al menu autor
            2.  Acceder al menu libro
            3.  Acceder al menu préstamo
            4.  Acceder al menu usuario
            0.  Salir
            """;
    private static final String MENU_AUTOR = """
            ========= MENU AUTOR =========
            1. Registrar autor
            2. Listar autores
            3. Actualizar autor
            4. Borrar autor
            0. Salir al menu principal
            """;
    private static final String MENU_LIBRO = """
            ========= MENU LIBRO =========
            1. Registrar libro
            2. Listar libros
            3. Listar libros por autor
            4. Actualizar libro
            5. Borrar libro
            0. Salir al menu principal
            """;
    private static final String MENU_PRESTAMO = """
            ========= MENU PRÉSTAMO =========
            1. Registrar préstamo
            2. Listar préstamos
            3. Listar préstamos atrasados
            4. Borrar préstamo
            0. Salir al menu principal
            """;
    private static final String MENU_USUARIO = """
            ========= MENU USUARIO =========
            1. Registrar usuario
            2. Listar usuarios
            3. Actualizar usuario
            4. Borrar usuario
            0. Salir al menu principal
            """;
    private static final Scanner ENTRADA = new Scanner(System.in);
    public static void main(String[] args) {
        AutorDAO autorDAO = new AutorImpl();
        LibroDAO libroDAO = new LibroImpl();
        LibroAutorDAO libroAutorDAO = new LibroAutorImpl();
        PrestamoDAO prestamoDAO = new PrestamoImpl();
        UsuarioDAO usuarioDAO = new UsuarioImpl();
        BibliotecaService servicio = new BibliotecaService(libroDAO, autorDAO, libroAutorDAO, prestamoDAO, usuarioDAO);
        int opcion = -1;
        while (opcion != 0) {
            System.out.println(MENU_PRINCIPAL);
            opcion = ENTRADA.nextInt();
            switch (opcion) {
                case 1 -> {
                    opcion = -1;
                    while (opcion != 0) {
                        System.out.println(MENU_AUTOR);
                        opcion = ENTRADA.nextInt();
                        switch (opcion) {
                            case 1 -> {
                                registrarAutor(servicio);
                                opcion = -1;
                            }
                            case 2 -> {
                                listarAutores(servicio);
                                opcion = -1;
                            }
                            case 3 -> {
                                actualizarAutor(servicio);
                                opcion = -1;
                            }
                            case 4 -> {
                                eliminarAutor(servicio);
                                opcion = -1;
                            }
                            case 0 -> System.out.println("Volviendo al menú principal...");
                            default -> {
                                System.out.println("Opción no válida.");
                                opcion = -1;
                            }
                        }
                    }
                    opcion = -1;
                }
                case 2 -> {
                    opcion = -1;
                    while (opcion != 0) {
                        System.out.println(MENU_LIBRO);
                        opcion = ENTRADA.nextInt();
                        switch (opcion) {
                            case 1 -> {
                                registrarLibro(servicio);
                                opcion = -1;
                            }
                            case 2 -> {
                                listarLibros(servicio);
                                opcion = -1;
                            }
                            case 3 -> {
                                listarLibrosPorAutor(servicio);
                                opcion = -1;
                            }
                            case 4 -> {
                                cambiarTitulo(servicio);
                                opcion = -1;
                            }
                            case 5 -> {
                                eliminarLibro(servicio);
                                opcion = -1;
                            }
                            case 0 -> System.out.println("Volviendo al menú principal...");

                            default -> {
                                System.out.println("Opción no válida.");
                                opcion = -1;
                            }
                        }
                    }
                    opcion = -1;
                }
                case 3 -> {
                    opcion = -1;
                    while (opcion != 0) {
                        System.out.println(MENU_PRESTAMO);
                        opcion = ENTRADA.nextInt();
                        switch (opcion) {
                            case 1 -> {
                                ENTRADA.nextLine();
                                System.out.print("Fecha inicio (YYYY-MM-DD): ");
                                String fechaInicio = ENTRADA.nextLine();
                                System.out.print("Fecha fin (YYYY-MM-DD): ");
                                String fechaFin = ENTRADA.nextLine();
                                System.out.print("ID del libro: ");
                                int libroId = ENTRADA.nextInt();
                                System.out.print("ID del usuario: ");
                                int usuarioId = ENTRADA.nextInt();
                                servicio.registrarPrestamo(fechaInicio, fechaFin, libroId, usuarioId);
                                opcion = -1;
                            }
                            case 2 -> {
                                List<Prestamo> prestamos = servicio.listarPrestamos();
                                System.out.println("Préstamos:");
                                for (Prestamo p : prestamos) {
                                    System.out.println("id=" + p.getId() + " | inicio=" + p.getFechaInicio() + " | fin=" + p.getFechaFin() + " | libroId=" + p.getIdLibro() + " | usuarioId=" + p.getIdUsuario());
                                }
                                opcion = -1;
                            }
                            case 3 -> {
                                List<Prestamo> retrasos = servicio.listarRetrasos();
                                System.out.println("Préstamos atrasados:");
                                for (Prestamo p : retrasos) {
                                    System.out.println("id=" + p.getId() + " | inicio=" + p.getFechaInicio() + " | fin=" + p.getFechaFin() + " | libroId=" + p.getIdLibro() + " | usuarioId=" + p.getIdUsuario());
                                }
                                opcion = -1;
                            }
                            case 4 -> {
                                System.out.print("ID del préstamo a borrar: ");
                                int idPrestamo = ENTRADA.nextInt();
                                servicio.eliminarPrestamo(idPrestamo);
                                opcion = -1;
                            }
                            case 0 -> System.out.println("Volviendo al menú principal...");

                            default -> {
                                System.out.println("Opción no válida.");
                                opcion = -1;
                            }
                        }
                    }
                    opcion = -1;
                }

                case 4 -> {
                    opcion = -1;
                    while (opcion != 0) {
                        System.out.println(MENU_USUARIO);
                        opcion = ENTRADA.nextInt();
                        switch (opcion) {
                            case 1 -> {
                                ENTRADA.nextLine();
                                System.out.print("Nombre del usuario: ");
                                String nombreUsuario = ENTRADA.nextLine();
                                servicio.agregarUsuario(nombreUsuario);
                                opcion = -1;
                            }
                            case 2 -> {
                                List<Usuario> usuarios = servicio.listarUsuarios();
                                System.out.println("Usuarios:");
                                for (Usuario u : usuarios) {
                                    System.out.println("id=" + u.getId() + " | nombre=" + u.getNombre());
                                }
                                opcion = -1;
                            }
                            case 3 -> {
                                System.out.print("ID del usuario a actualizar: ");
                                int idUsuarioAct = ENTRADA.nextInt();
                                ENTRADA.nextLine();
                                System.out.print("Nuevo nombre: ");
                                String nuevoNomUsuario = ENTRADA.nextLine();
                                servicio.actualizarUsuario(idUsuarioAct, nuevoNomUsuario);
                                opcion = -1;
                            }
                            case 4 -> {
                                System.out.print("ID del usuario a borrar: ");
                                int idUsuarioDel = ENTRADA.nextInt();
                                servicio.eliminarUsuario(idUsuarioDel);
                                opcion = -1;
                            }
                            case 0 -> System.out.println("Volviendo al menú principal...");

                            default -> {
                                System.out.println("Opción no válida.");
                                opcion = -1;
                            }
                        }
                    }
                    opcion = -1;
                }
                case 0 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción no válida en menú principal.");
            }
        }
    }

    private static void eliminarLibro(BibliotecaService servicio) {
        preguntarSiListarLibros(servicio);
        System.out.print("ID del libro a borrar: ");
        int idLibroDel = ENTRADA.nextInt();
        servicio.eliminarLibro(idLibroDel);
    }

    private static void cambiarTitulo(BibliotecaService servicio) {
        preguntarSiListarLibros(servicio);
        System.out.print("ID del libro a actualizar: ");
        int idLibro = ENTRADA.nextInt();
        ENTRADA.nextLine();
        System.out.print("Nuevo título: ");
        String nuevoTitulo = ENTRADA.nextLine();
        servicio.cambiarTitulo(idLibro, nuevoTitulo);
    }

    private static void listarLibrosPorAutor(BibliotecaService servicio) {
        preguntarSiListarAutores(servicio);
        System.out.print("ID del autor: ");
        int idAutor = ENTRADA.nextInt();
        List<Libro> librosAutor = servicio.listarLibrosPorAutor(idAutor);
        System.out.println("Libros del autor:");
        for (Libro l : librosAutor) {
            System.out.printf("ID = %d| título = %s | isbn = %s\n", l.getId(), l.getTitulo(), l.getIsbn());
        }
    }

    private static void listarLibros(BibliotecaService servicio) {
        List<Libro> libros = servicio.listarLibros();
        System.out.println("Libros:");
        for (Libro l : libros) {
            System.out.println("id=" + l.getId() + " | titulo=" + l.getTitulo() + " | isbn=" + l.getIsbn());
        }
    }

    private static void registrarLibro(BibliotecaService servicio) {
        ENTRADA.nextLine();
        System.out.print("Nombre del autor: ");
        String autorNombre = ENTRADA.nextLine();
        System.out.print("Título del libro: ");
        String titulo = ENTRADA.nextLine();
        System.out.print("ISBN: ");
        String isbn = ENTRADA.nextLine();
        servicio.registrarLibro(autorNombre, titulo, isbn);
    }

    private static void preguntarSiListarLibros(BibliotecaService servicio) {
        System.out.println("Desea ver los libros antes?: s/n");
        char respuesta = verificarSN();
        System.out.println();
        if (respuesta == 's') listarLibros(servicio);
    }

    // GENÉRICOS
    private static char verificarSN() {
        char respuesta = ENTRADA.next().charAt(0);

        while (respuesta != 's' && respuesta != 'n') {
            System.out.println("ERROR, respuesta inválida. Indique s ó n");
            respuesta = ENTRADA.next().charAt(0);
        }

        return respuesta;
    }

    // MENU AUTOR
    private static void registrarAutor(BibliotecaService servicio) {
        ENTRADA.nextLine();
        System.out.print("Nombre del autor: ");
        String nombre = ENTRADA.nextLine();
        servicio.registrarAutor(nombre);
    }

    private static void listarAutores(BibliotecaService servicio) {
        List<Autor> autores = servicio.listarAutores();
        System.out.println("Autores:");
        for (Autor a : autores) {
            System.out.printf("ID = %d| %s\n", a.getId(), a.getNombre());
        }
    }

    private static void actualizarAutor(BibliotecaService servicio) {
        preguntarSiListarAutores(servicio);

        System.out.print("ID del autor a actualizar: ");
        int idAut = ENTRADA.nextInt();
        ENTRADA.nextLine();
        System.out.print("Indica el nuevo nombre: ");
        String nuevoNombre = ENTRADA.nextLine();
        servicio.cambiarNombre(idAut, nuevoNombre);
    }

    private static void eliminarAutor(BibliotecaService servicio) {
        preguntarSiListarAutores(servicio);
        System.out.print("ID del autor a borrar: ");
        int idDel = ENTRADA.nextInt();
        servicio.eliminarAutor(idDel);
    }

    private static void preguntarSiListarAutores(BibliotecaService servicio) {
        System.out.println("Desea ver los autores antes?: s/n");
        char respuesta = verificarSN();
        System.out.println();
        if (respuesta == 's') listarAutores(servicio);
    }

    // MENU LIBRO
    // MENU PRESTAMO
    // MENU USUARIO
}