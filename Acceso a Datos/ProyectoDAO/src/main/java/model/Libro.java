package model;

public class Libro {
    private int id;
    private String titulo;
    private String isbn;
    public Libro(int id, String titulo, String isbn) {
        this.id = id;
        this.titulo = titulo;
        this.isbn = isbn;
    }

    public Libro(String titulo, String isbn) {
        this.titulo = titulo;
        this.isbn = isbn;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public String getIsbn() {
        return isbn;
    }
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Override
    public String toString() {
        return "ID = " + id + ", Título = " + titulo + ", ISBN = " + isbn;
    }
}
