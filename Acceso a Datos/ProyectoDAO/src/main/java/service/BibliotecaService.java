package service;

import dao.autor.AutorDAO;
import dao.libro.LibroDAO;
import dao.libro_autor.LibroAutorDAO;
import dao.prestamo.PrestamoDAO;
import dao.usuario.UsuarioDAO;
import model.*;

import java.util.List;

public class BibliotecaService {
    private final AutorDAO autorDAO;
    private final LibroDAO libroDAO;
    private final LibroAutorDAO libroAutorDAO;
    private final PrestamoDAO prestamoDAO;
    private final UsuarioDAO usuarioDAO;

    public BibliotecaService(LibroDAO libroDAO, AutorDAO autorDAO, LibroAutorDAO libroAutorDAO, PrestamoDAO prestamoDAO, UsuarioDAO usuarioDAO) {
        this.libroDAO = libroDAO;
        this.autorDAO = autorDAO;
        this.libroAutorDAO = libroAutorDAO;
        this.prestamoDAO = prestamoDAO;
        this.usuarioDAO = usuarioDAO;

    }

    // AUTOR
    public void registrarAutor(String nombre) {
        try {
            Autor autor = new Autor(nombre);
            autorDAO.addAutor(autor);
        } catch (Exception e) {
            System.err.println("Error al registrar autor: " + e.getMessage());
        }
    }

    public List<Autor> listarAutores() {
        try {
            return autorDAO.getAllAutores();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void cambiarNombre(int id, String nuevoNombre) {
        try {
            Autor autor = autorDAO.getAutorById(id);
            if (autor != null) {
                autor.setNombre(nuevoNombre);
                autorDAO.updateAutor(autor);
            } else {
                System.out.println("Service: No se encontró el autor con id = " + id);
            }
        } catch (Exception e) {
            System.err.println("Error al actualizar autor: " + e.getMessage());
        }
    }

    public void eliminarAutor(int id) {
        try {
            autorDAO.deleteAutor(id);
            libroAutorDAO.deleteAutor(id);
        } catch (Exception e) {
            System.err.println("Error al eliminar autor: " + e.getMessage());
        }
    }

    // LIBRO
    public void registrarLibro(String autor, String titulo, String isbn) {
        try {
            Libro libro = new Libro(titulo, isbn);
            libroDAO.addLibro(libro);

            Libro lib = libroDAO.getLibroByTitulo(titulo);
            Autor aut = autorDAO.getAutorByNombre(autor);
            libroAutorDAO.addLibroAutor(new LibroAutor(lib.getId(), aut.getId()));
        } catch (Exception e) {
            System.err.println("Error al registrar libro: " + e.getMessage());
        }
    }

    public List<Libro> listarLibros() {
        try {
            return libroDAO.getAllLibros();
        } catch (Exception e) {
            System.err.println("Error al listar libros: " + e.getMessage());
            return List.of();
        }
    }

    public void cambiarTitulo(int id, String nuevoTitulo) {
        try {
            Libro libro = libroDAO.getLibroById(id);
            if (libro != null) {
                libro.setTitulo(nuevoTitulo);
                libroDAO.updateLibro(libro);
            } else {
                System.out.println("Service: No se encontró el libro con id = " + id);
            }
        } catch (Exception e) {
            System.err.println("Error al actualizar libro: " + e.getMessage());
        }
    }

    public void eliminarLibro(int id) {
        try {
            libroDAO.deleteLibro(id);
        } catch (Exception e) {
            System.err.println("Error al eliminar libro: " + e.getMessage());
        }
    }

    // LIBRO_AUTOR
    public List<Libro> listarLibrosPorAutor(int id) {
        try {
            return libroAutorDAO.getLibrosByAutor(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // PRESTAMO
    public void registrarPrestamo(String fechaInicio, String fechaFin, int libroId, int usuarioId) {
        try {
            prestamoDAO.addPrestamo(new Prestamo(fechaInicio, fechaFin, libroId, usuarioId));
        } catch (Exception e) {
            System.err.println("Error al registrar préstamo: " + e.getMessage());
        }
    }

    public List<Prestamo> listarPrestamos() {
        try {
            return prestamoDAO.getAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void eliminarPrestamo(int id) {
        try {
            prestamoDAO.deletePrestamo(id);
        } catch (Exception e) {
            System.err.println("Error al eliminar préstamo: " + e.getMessage());
        }
    }

    public List<Prestamo> listarRetrasos() {
        try {
            return prestamoDAO.verRetrasos();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // USUARIO
    public void agregarUsuario(String nombre) {
        try {
            usuarioDAO.addUsuario(new Usuario(nombre));
        } catch (Exception e) {
            System.err.println("Error al agregar usuario: " + e.getMessage());
        }
    }

    public List<Usuario> listarUsuarios() {
        try {
            return usuarioDAO.getAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void actualizarUsuario(int id, String nuevoNombre) {
        try {
            Usuario usuario = usuarioDAO.getUsuarioById(id);
            if (usuario != null) {
                usuario.setNombre(nuevoNombre);
                usuarioDAO.updateUsuario(usuario);
            } else {
                System.out.println("Service: No se encontró el usuario con id = " + id);
            }
        } catch (Exception e) {
            System.err.println("Error al actualizar usuario: " + e.getMessage());
        }
    }

    public void eliminarUsuario(int id) {
        try {
            usuarioDAO.deleteUsuario(id);
        } catch (Exception e) {
            System.err.println("Error al eliminar usuario: " + e.getMessage());
        }
    }
}