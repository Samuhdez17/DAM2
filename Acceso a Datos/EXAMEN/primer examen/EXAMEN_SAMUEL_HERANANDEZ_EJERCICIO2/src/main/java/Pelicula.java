import jakarta.xml.bind.annotation.*;

@XmlType (name = "pelicula", propOrder = {"titulo", "director", "año", "genero", "duracion"} )
@XmlAccessorType (value = XmlAccessType.FIELD)
public class Pelicula {
    private String titulo;
    private String director;
    private int año;
    private String genero;
    private int duracion;

    public Pelicula() {
    }

    public Pelicula(String titulo, String director, int anio, String genero, int duracionMins) {
        this.titulo = titulo;
        this.director = director;
        this.año = anio;
        this.genero = genero;
        this.duracion = duracionMins;
    }

    public int getAño() {
        return año;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }
}
