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
        AutorDAO autorDAO           = new AutorImpl();
        LibroDAO libroDAO           = new LibroImpl();
        LibroAutorDAO libroAutorDAO = new LibroAutorImpl();
        PrestamoDAO prestamoDAO     = new PrestamoImpl();
        UsuarioDAO usuarioDAO       = new UsuarioImpl();
        BibliotecaService servicio  = new BibliotecaService(libroDAO, autorDAO, libroAutorDAO, prestamoDAO, usuarioDAO);

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
                            case 1 -> registrarAutor(servicio);

                            case 2 -> listarAutores(servicio);

                            case 3 -> actualizarAutor(servicio);

                            case 4 -> eliminarAutor(servicio);

                            case 0 -> System.out.println("Volviendo al menú principal...");

                            default -> System.out.println("Opción no válida.");
                        }

                        System.out.println();
                    }

                    opcion = -1;
                }

                case 2 -> {
                    opcion = -1;
                    while (opcion != 0) {
                        System.out.println(MENU_LIBRO);
                        opcion = ENTRADA.nextInt();
                        switch (opcion) {
                            case 1 -> registrarLibro(servicio);

                            case 2 -> listarLibros(servicio);

                            case 3 -> listarLibrosPorAutor(servicio);

                            case 4 -> cambiarTitulo(servicio);

                            case 5 -> eliminarLibro(servicio);

                            case 0 -> System.out.println("Volviendo al menú principal...");

                            default -> System.out.println("Opción no válida.");
                        }

                        System.out.println();
                    }

                    opcion = -1;
                }

                case 3 -> {
                    opcion = -1;
                    while (opcion != 0) {
                        System.out.println(MENU_PRESTAMO);
                        opcion = ENTRADA.nextInt();
                        switch (opcion) {
                            case 1 -> registrarPrestamo(servicio);

                            case 2 -> listarPrestamos(servicio);

                            case 3 -> listarPrestamosAtrasados(servicio);

                            case 4 -> borrarPrestamo(servicio);

                            case 0 -> System.out.println("Volviendo al menú principal...");

                            default -> System.out.println("Opción no válida.");

                        }

                        System.out.println();
                    }

                    opcion = -1;
                }

                case 4 -> {
                    opcion = -1;
                    while (opcion != 0) {
                        System.out.println(MENU_USUARIO);
                        opcion = ENTRADA.nextInt();
                        switch (opcion) {
                            case 1 -> registrarUsuario(servicio);

                            case 2 -> listarUsuarios(servicio);

                            case 3 -> actualizarUsuario(servicio);

                            case 4 -> borrarUsuario(servicio);

                            case 0 -> System.out.println("Volviendo al menú principal...");

                            default -> System.out.println("Opción no válida.");
                        }

                        System.out.println();
                    }

                    opcion = -1;
                }

                case 0 -> System.out.println("Saliendo...");

                default -> System.out.println("Opción no válida en menú principal.");
            }
        }
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
        System.out.print("""
        ================= AUTORES ================
        | ID   |            NOMBRE               |
        +------+---------------------------------+
        """);
        for (Autor a : autores) {
            System.out.printf("| %4d | %-30s  |\n", a.getId(), a.getNombre());
            System.out.println("+------+---------------------------------+");
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
        System.out.println();
        servicio.eliminarAutor(idDel);
    }

    private static void preguntarSiListarAutores(BibliotecaService servicio) {
        System.out.print("Desea ver los autores antes? s/n: ");
        char respuesta = verificarSN();
        System.out.println();
        if (respuesta == 's') listarAutores(servicio);
    }

    // MENU LIBRO
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

    private static void listarLibros(BibliotecaService servicio) {
        mostrarLibros(servicio.listarLibros());
    }

    private static void listarLibrosPorAutor(BibliotecaService servicio) {
        preguntarSiListarAutores(servicio);
        System.out.print("ID del autor: ");
        int idAutor = ENTRADA.nextInt();
        mostrarLibros(servicio.listarLibrosPorAutor(idAutor));
    }

    private static void mostrarLibros(List<Libro> libros) {
        System.out.print("""
        =========================== LIBROS =======================
        | ID   |              TÍTULO            |     ISBN       |
        +------+--------------------------------+----------------+
        """);
        for (Libro l : libros) {
            System.out.printf("| %4d | %-30s | %-14s |\n", l.getId(), l.getTitulo(), l.getIsbn());
            System.out.println("+------+--------------------------------+----------------+");
        }
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

    private static void eliminarLibro(BibliotecaService servicio) {
        preguntarSiListarLibros(servicio);
        System.out.print("ID del libro a borrar: ");
        int idLibroDel = ENTRADA.nextInt();
        servicio.eliminarLibro(idLibroDel);
    }

    private static void preguntarSiListarLibros(BibliotecaService servicio) {
        System.out.println("Desea ver los libros antes? s/n: ");
        char respuesta = verificarSN();
        System.out.println();
        if (respuesta == 's') listarLibros(servicio);
    }

    // MENU PRESTAMO
    private static void registrarPrestamo(BibliotecaService servicio) {
        ENTRADA.nextLine();
        System.out.print("Fecha inicio (aaaa-mm-dd): ");
        String fechaInicio = ENTRADA.nextLine();
        System.out.print("Fecha fin (aaaa-mm-dd): ");
        String fechaFin = ENTRADA.nextLine();
        System.out.print("ID del libro: ");
        int libroId = ENTRADA.nextInt();
        System.out.print("ID del usuario: ");
        int usuarioId = ENTRADA.nextInt();
        servicio.registrarPrestamo(fechaInicio, fechaFin, libroId, usuarioId);
    }

    private static void listarPrestamos(BibliotecaService servicio) {
        mostrarPrestamos(servicio, servicio.listarPrestamos());
    }

    private static void listarPrestamosAtrasados(BibliotecaService servicio) {
        mostrarPrestamos(servicio, servicio.listarRetrasos());
    }

    private static void mostrarPrestamos(BibliotecaService servicio, List<Prestamo> prestamos) {
        System.out.print("""
        ================================================= PRÉSTAMOS ======================================================
        |  ID  |   INICIO (dd/mm/aaaa)  |     FIN (dd/mm/aaaa)   |         LIBRO PRESTADO         |         USUARIO      |
        +------+------------------------+------------------------+--------------------------------+----------------------+
        """);

        for (Prestamo p : prestamos) {
            System.out.printf("| %4d | %-22s | %-22s | %-30s | %-20s |\n", p.getId(), p.getFechaInicio(), p.getFechaFin(), servicio.getNombreLibro(p.getIdLibro()), servicio.getNombreUsuario(p.getIdUsuario()));
            System.out.println("+------+------------------------+------------------------+--------------------------------+----------------------+");
        }
    }

    private static void borrarPrestamo(BibliotecaService servicio) {
        preguntarSiListarPrestamos(servicio);
        System.out.print("ID del préstamo a borrar: ");
        int idPrestamo = ENTRADA.nextInt();
        servicio.eliminarPrestamo(idPrestamo);
    }

    private static void preguntarSiListarPrestamos(BibliotecaService servicio) {
        System.out.println("Desea ver los préstamos antes? s/n: ");
        char respuesta = verificarSN();
        System.out.println();
        if (respuesta == 's') listarPrestamos(servicio);
    }


    // MENU USUARIO
    private static void registrarUsuario(BibliotecaService servicio) {
        ENTRADA.nextLine();
        System.out.print("Nombre del usuario: ");
        String nombreUsuario = ENTRADA.nextLine();
        servicio.agregarUsuario(nombreUsuario);
    }

    private static void listarUsuarios(BibliotecaService servicio) {
        List<Usuario> usuarios = servicio.listarUsuarios();

        System.out.print("""
        ================ USUARIOS ================
        |  ID  |            NOMBRE               |
        +------+---------------------------------+
        """);

        for (Usuario u : usuarios) {
            System.out.printf("| %4d | %-30s  |\n", u.getId(), u.getNombre());
            System.out.println("+------+---------------------------------+");
        }
    }

    private static void actualizarUsuario(BibliotecaService servicio) {
        preguntarSiListarUsuarios(servicio);
        System.out.print("ID del usuario a actualizar: ");
        int idUsuarioAct = ENTRADA.nextInt();
        ENTRADA.nextLine();
        System.out.print("Nuevo nombre: ");
        String nuevoNombre = ENTRADA.nextLine();
        servicio.actualizarUsuario(idUsuarioAct, nuevoNombre);
    }

    private static void borrarUsuario(BibliotecaService servicio) {
        System.out.print("ID del usuario a borrar: ");
        int idUsuarioDel = ENTRADA.nextInt();
        servicio.eliminarUsuario(idUsuarioDel);
    }

    private static void preguntarSiListarUsuarios(BibliotecaService servicio) {
        System.out.println("Desea ver los usuarios antes? s/n: ");
        char respuesta = verificarSN();
        System.out.println();
        if (respuesta == 's') listarUsuarios(servicio);
    }
}