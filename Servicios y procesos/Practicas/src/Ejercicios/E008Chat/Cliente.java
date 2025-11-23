package Ejercicios.E008Chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Cliente {
    public static void main(String[] args) {
        String host = "localhost";
        int puerto = 5000;

        try (Socket socket = new Socket(host, puerto)) {
            System.out.println("Conectado al servidor.");

            Thread envio = new Thread(new Comunicacion(socket));
            envio.start();

            try (BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                String mensaje;
                while ((mensaje = entrada.readLine()) != null) {
                    System.out.println("\nServidor: " + mensaje);
                    if (mensaje.equals("salir")) {
                        socket.close();
                        break;
                    }
                }
            }

        } catch (IOException e) {
            System.out.println("Error en el cliente: " + e.getMessage());
        }
    }
}