package practica_jaxb;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@XmlRootElement(name = "libreria")

public class Libreria {
    private String nombre;
    private String lugar;
    private final List<Libro> libros = new ArrayList<>();

    public Libreria(String nombre, String lugar) {
        this.nombre = nombre;
        this.lugar = lugar;
    }

    public Libreria() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    @XmlElementWrapper(name = "libros")
    @XmlElement(name = "libro")

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        this.libros.addAll(libros);
        for (Libro libro : libros) libro.setComparacion('t');
        ordenarLibros();
    }

    public void setLibro(Libro libroNuevo) {
        libros.add(libroNuevo);
        for (Libro libro : libros) libro.setComparacion('t');
        ordenarLibros();
    }

    public String titulosLibros() {
        StringBuilder sb = new StringBuilder();
        for (Libro libro : libros) libro.setComparacion('t');
        ordenarLibros();

        sb.append(String.format("""
                Libreria: %s ; Lugar: %s
                Titulos de libros:
                
                """, nombre, lugar));
        for (int i = 0; i < libros.size(); i++) {
            sb.append(String.format("Libro %d. ", i + 1)).append(libros.get(i).getTitulo()).append("\n");
        }

        return sb.toString();
    }

    public void ordenarLibros() {
        Collections.sort(this.libros);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("""
                Libreria: %s ; Lugar: %s
                Libros:
                
                """, nombre, lugar));
        for (Libro libro : libros) {
            sb.append(libro).append("\n");
        }

        return sb.toString();
    }
}
