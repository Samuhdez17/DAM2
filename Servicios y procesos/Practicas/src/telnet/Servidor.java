package telnet;

import java.io.*;
import java.net.*;

public class Servidor {
    public static void main(String[] args) {
        int puerto = 5000;

        try (ServerSocket servidor = new ServerSocket(puerto)) {
            Socket cliente = null;
            String mensaje = "";

            while (true) {
                System.out.println("Servidor iniciado. Esperando conexión en el puerto " + puerto + "...");

                // Espera hasta que un cliente se conecte
                cliente = servidor.accept();
                System.out.println("Cliente conectado desde: " + cliente.getInetAddress().getHostAddress());

                do {
                    // Flujos de entrada/salida
                    BufferedReader entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
                    PrintWriter salida = new PrintWriter(cliente.getOutputStream(), true);

                    // Comunicación
                    mensaje = entrada.readLine();
                    System.out.println("Cliente dice: " + mensaje);

                    salida.println("Mensaje recibido: " + mensaje);
                } while (!mensaje.equals("salir"));

                // Cierre
                cliente.close();
                System.out.println("Conexión cerrada con el cliente.");
            }

        } catch (IOException e) {
            System.out.println("Error en el servidor: " + e.getMessage());
        }
    }
}

