package Ejercicios.E009MensajeriaApp;

public class Mensaje {
    private final String destinatario;
    private final String emisor;
    private final String contenido;

    public Mensaje(String destinatario, String emisor, String contenido) {
        this.destinatario = destinatario;
        this.emisor = emisor;
        this.contenido = contenido;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public String getEmisor() {
        return emisor;
    }

    public String getContenido() {
        return contenido;
    }
}
