package Ejercicios.E009MensajeriaApp;

public class Mensaje {
    private final String destinatario;
    private final String emisor;
    private final String contenido;

    public Mensaje(String[] contenido) {
        this.destinatario = contenido[0];
        this.emisor = contenido[1];
        this.contenido = contenido[2];
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
