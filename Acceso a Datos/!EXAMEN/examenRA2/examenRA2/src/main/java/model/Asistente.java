package model;

public class Asistente {
    private int ID;
    private String nombre;
    private String email;
    private int evento;

    public Asistente(String nombre, String email, int idEvento) {
        setNombre(nombre);
        setEmail(email);
        setEvento(idEvento);
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getEvento() {
        return evento;
    }

    public void setEvento(int evento) {
        this.evento = evento;
    }

    @Override
    public String toString() {
        return String.format("Asistente [ID = %d ; nombre = %s ; email = %s]", ID, nombre, email);
    }
}
