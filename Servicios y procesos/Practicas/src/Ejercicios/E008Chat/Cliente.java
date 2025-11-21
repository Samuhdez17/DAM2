package Ejercicios.E008Chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Cliente {
    public static void main(String[] args) {
        String host = "localhost";
        int puerto = 5000;

        try (Socket socket = new Socket(host, puerto)) {
            System.out.println("Conectado al servidor.");
            String mensaje = "";

            do {
                // Flujos de entrada/salida
                BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                Comunicacion comunicacion = new Comunicacion(socket);
                Thread comunicacionThread = new Thread(comunicacion);
                comunicacionThread.start();

                mensaje = entrada.readLine();  // Leer respuesta del servidor
                if (!mensaje.isBlank()) System.out.println(mensaje);
                System.out.println();
            } while (!mensaje.equals("salir"));

        } catch (IOException e) {
            System.out.println("Error en el cliente: " + e.getMessage());
        }
    }
}

