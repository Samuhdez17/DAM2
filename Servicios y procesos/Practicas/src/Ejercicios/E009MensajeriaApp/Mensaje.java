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

    public String mostrarMensaje() {
        return String.format("De: %s ; Mensaje: %s\n", emisor, contenido);
    }

    public String escribirEnFichero() {
        return String.format("%s``%s``%s\n", destinatario, emisor, contenido);
    }
}
