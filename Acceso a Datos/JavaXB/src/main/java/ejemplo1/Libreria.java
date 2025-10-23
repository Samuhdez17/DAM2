package ejemplo1;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "libreria")

public class Libreria {
    private String nombre;
    private String lugar;
    private List<Libro> libros = new ArrayList<>();

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
        this.libros = libros;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Librería: ").append(nombre).append(" Lugar: ").append(lugar).append("\n");
        sb.append("Libros: ");
        for (Libro libro : libros) {
            sb.append(libro).append("\n");
        }

        return sb.toString();
    }
}
