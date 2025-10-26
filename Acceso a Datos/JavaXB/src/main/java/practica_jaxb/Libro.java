package practica_jaxb;

import jakarta.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"autor", "titulo", "editorial", "precio", "isbn"})
public class Libro implements Comparable<Libro> {
    private String autor;
    private String titulo;
    private String editorial;
    private String  precio;
    private String isbn;
    private char comparacion;

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

    public void setComparacion(char comparacion) {
        this.comparacion = comparacion;
    }

    @Override
    public String toString() {
        return String.format("""
                    Autor:   %s
                   Título:   %s
                Editorial:   %s
                   Precio:   %s
                     ISBN:   %s
                """, autor, titulo, editorial, precio, isbn);
    }

    @Override
    public int compareTo(Libro otro) {
        switch (comparacion) {
            case 'a' -> {return compararAutor     (this.autor, otro.autor);         }
            case 't' -> {return compararTitulo    (this.titulo, otro.titulo);       }
            case 'e' -> {return compararEditorial (this.editorial, otro.editorial); }
            case 'p' -> {return compararPrecio    (this.precio, otro.precio);       }

            default -> {return 0;}
        }
    }

    private int compararPrecio(String precio, String precio1) {
        double precioLib1 = Double.parseDouble(String.valueOf(precio));
        double precioLib2 = Double.parseDouble(String.valueOf(precio1));

        return Double.compare(precioLib1, precioLib2);
    }

    private int compararEditorial(String editorial, String editorial1) {
        return editorial.compareTo(editorial1);
    }

    private int compararTitulo(String titulo, String titulo1) {
        return titulo.compareTo(titulo1);
    }

    private int compararAutor(String autor, String autor1) {
        return autor.compareTo(autor1);
    }
}
