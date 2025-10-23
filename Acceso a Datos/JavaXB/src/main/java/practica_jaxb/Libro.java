package practica_jaxb;

import jakarta.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"autor", "titulo", "editorial", "precio", "isbn"})
public class Libro {
    private String autor;
    private String titulo;
    private String editorial;
    private String  precio;
    private String isbn;

    public Libro(String titulo, String autor, String editorial, String precio, String isbn) {
        this.titulo = titulo;
        this.autor = autor;
        this.editorial = editorial;
        this.precio = precio;
        this.isbn = isbn;
    }

    public Libro() {
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return String.format("""
                   Título:   %s
                    Autor:   %s
                Editorial:   %s
                   Precio:   %s
                     ISBN:   %s
                """, titulo, autor, editorial, precio, isbn);
    }
}
