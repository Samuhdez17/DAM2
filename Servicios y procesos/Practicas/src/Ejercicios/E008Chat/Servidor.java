package Ejercicios.E008Chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    public static void main(String[] args) {
        int puerto = 5000;

        try (ServerSocket servidor = new ServerSocket(puerto)) {
            Socket cliente = null;
            String respuesta = "";

            while (true) {
                System.out.println("Servidor iniciado. Esperando conexión en el puerto " + puerto + "...");

                // Espera hasta que un cliente se conecte
                cliente = servidor.accept();
                System.out.println("Cliente conectado desde: " + cliente.getInetAddress().getHostAddress());

                do {
                    // Flujos de entrada/salida
                    BufferedReader entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));

                    Comunicacion comunicacion = new Comunicacion(cliente);
                    Thread comunicacionThread = new Thread(comunicacion);
                    comunicacionThread.start();

                    respuesta = entrada.readLine();  // Leer respuesta del servidor
                    if (!respuesta.isBlank()) System.out.println("\nOtro: " + respuesta);
                } while (!respuesta.equals("salir") /*&& !respuesta.equals("salir -f")*/);

                // Cierre
                cliente.close();

                if (respuesta.equals("salir")) System.out.println("Saliendo del chat...");
//                if (respuesta.equals("salir -f")) {
//                    servidor.close();
//                    System.out.println("Servidor cerrado.");
//                    break;
//                }
            }

        } catch (IOException e) {
            System.out.println("Error en el servidor: " + e.getMessage());
        }
    }
}

