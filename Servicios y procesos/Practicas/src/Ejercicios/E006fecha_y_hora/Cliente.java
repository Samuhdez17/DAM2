package Ejercicios.E006fecha_y_hora;

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
            BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
//            BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            System.out.print("Escribe un mensaje para el servidor: ");
            mensaje = teclado.readLine();

            salida.println(mensaje);  // Enviar mensaje al servidor
            } while (!mensaje.equals("salir"));
//            String respuesta = entrada.readLine();  // Leer respuesta del servidor
//            System.out.println("Servidor responde: " + respuesta);

        } catch (IOException e) {
            System.out.println("Error en el cliente: " + e.getMessage());
        }
    }
}

