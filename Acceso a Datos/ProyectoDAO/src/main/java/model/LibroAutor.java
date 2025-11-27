package model;

public class LibroAutor {
    private int idLibro;
    private int idAutor;

    public LibroAutor(int idLibro, int idAutor) {
        this.idLibro = idLibro;
        this.idAutor = idAutor;
    }
    public int getIdLibro() {
        return idLibro;
    }
    public void setIdLibro(int idLibro) {
        this.idLibro = idLibro;
    }
    public int getIdAutor() {
        return idAutor;
    }
    public void setIdAutor(int idAutor) {
        this.idAutor = idAutor;
    }
}
