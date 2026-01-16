import java.io.*;
import java.net.Socket;

public class HiloServidor implements Runnable {
    private final Socket cliente;
    private final String raiz;
    private boolean sesionIniciada = false;

    public HiloServidor(Socket cliente, String raiz) {
        this.cliente = cliente;
        this.raiz = raiz;
    }

    @Override
    public void run() {
        try {
            BufferedReader entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            PrintWriter salida = new PrintWriter(new OutputStreamWriter(cliente.getOutputStream()), true);

            String[] linea;
            while ((linea = entrada.readLine().split("``")) != null) {
                switch (linea[0]) {
                    case "HELLO" -> {

                    }

                    case "LOGIN" -> {

                    }

                    case "LS" -> {

                    }

                    case "GET" -> {

                    }

                    case "PUT" -> {

                    }

                    case "EXIT" -> {

                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
