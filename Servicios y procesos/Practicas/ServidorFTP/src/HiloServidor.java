import java.io.*;
import java.net.Socket;
import java.util.HashMap;

public class HiloServidor implements Runnable {
    private final Socket cliente;
    private final String raiz;
    private final HashMap<String, String> usuarios;
    private boolean sesionIniciada = false;

    public HiloServidor(Socket cliente, String raiz, HashMap<String, String> usuarios) {
        this.cliente = cliente;
        this.raiz = raiz;
        this.usuarios = usuarios;
    }

    @Override
    public void run() {
        try {
            BufferedReader entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            PrintWriter salida = new PrintWriter(new OutputStreamWriter(cliente.getOutputStream()), true);

            String[] linea;
            while ((linea = entrada.readLine().split("``")) != null) {
                switch (linea[0]) {
                    case "HELLO" -> salida.println("Bienvenido al servidor FTP ");

                    case "LOGIN" -> {
                        if (sesionIniciada) {
                            salida.println("500");
                            return;
                        }

                        String contrasenia = usuarios.get(linea[1]);

                        if (contrasenia == null) {
                            salida.println("401");

                        } else {
                            if (contrasenia.equals(linea[2])) {
                                sesionIniciada = true;
                                salida.println("200");

                            } else {
                                salida.println("401");
                            }
                        }

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
