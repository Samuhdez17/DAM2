import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClienteFTP {
    private Socket cliente;
    private static PrintWriter salida;
    private static BufferedReader entrada;


    public ClienteFTP() {
        setCliente();
        setSalida();
        setEntrada();
    }

    private void setCliente() {
        try {
            this.cliente = new Socket("localhost", 21);

        } catch (IOException ex) {
            System.out.println("SERVIDOR CERRADO O NO DISPONIBLE");
        }
    }

    private void setSalida() {
        try {
            salida = new PrintWriter(cliente.getOutputStream(), true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void setEntrada() {
        try {
            entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void logIn(String usuario, String contrasenia) {
        salida.printf("LOGIN``%s``%s", usuario, contrasenia);
        leerRespuesta();
    }

    private static void leerRespuesta() {
        try {
            System.out.println(entrada.readLine());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
