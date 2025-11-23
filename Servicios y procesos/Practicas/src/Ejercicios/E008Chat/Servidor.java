package Ejercicios.E008Chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    public static void main(String[] args) {
        int puerto = 5000;

        try (ServerSocket servidor = new ServerSocket(puerto)) {
            System.out.println("Servidor iniciado. Esperando conexión...");

            Socket cliente = servidor.accept();
            System.out.println("Cliente conectado desde: " + cliente.getInetAddress());

            Thread envio = new Thread(new Comunicacion(cliente));
            envio.start();

            try (BufferedReader entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()))) {
                String mensaje;
                while ((mensaje = entrada.readLine()) != null) {
                    System.out.println("\nCliente: " + mensaje);
                    if (mensaje.equals("salir")) {
                        cliente.close();
                        break;
                    }
                }
            }

        } catch (IOException e) {
            System.out.println("Error en el servidor: " + e.getMessage());
        }
    }
}