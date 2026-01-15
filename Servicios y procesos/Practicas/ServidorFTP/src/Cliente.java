import java.io.*;
import java.net.Socket;

public class Cliente implements Runnable {
    private final Socket cliente;
    public Cliente(Socket cliente) {
        this.cliente = cliente;
    }

    @Override
    public void run() {
        try {
            BufferedReader entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            PrintWriter salida = new PrintWriter(new OutputStreamWriter(cliente.getOutputStream()), true);


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
