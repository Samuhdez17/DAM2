package model;

public class Evento {
    private int ID;
    private String titulo;
    private String fecha; // dia mes año
    private int aforoMax;

    public Evento(String titulo, String fecha, int aforo) {
        setTitulo(titulo);
        setFecha(fecha);
        setAforoMax(aforo);
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getID() {
        return ID;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getAforoMax() {
        return aforoMax;
    }

    public void setAforoMax(int aforoMax) {
        this.aforoMax = aforoMax;
    }

    public String strigSimple() {
        return String.format("Evento [ID = %d ; Titulo = %s", ID, titulo);
    }

    @Override
    public String toString() {
        return String.format("""
                %s - %s
                """, titulo, fecha);
    }
}
