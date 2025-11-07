package Ejercicios.E006fecha_y_hora;

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
            String mensaje = "";

            while (!mensaje.equals("salir -f")) {
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
                    switch (mensaje) {
                        case "fecha" -> {
                            System.out.println("Diciendo fecha y hora actual...");
                            String fecha = java.time.LocalDateTime.now().toString();
                            fecha = fecha.replaceAll("T", " ").substring(0, 19); // Se le pone un formato mejor

                            salida.printf("Fecha y hora actual: %s\n", fecha);
                        }
                        case "salir" -> System.out.println("Conexión cerrada con el cliente.");
                        case "salir -f" -> System.out.println("Conexión cerrada.");
                        default -> System.out.println("Cliente dice: " + mensaje);
                    }

                } while (!mensaje.equals("salir") && !mensaje.equals("salir -f"));

                // Cierre
                cliente.close();
            }

        } catch (IOException e) {
            System.out.println("Error en el servidor: " + e.getMessage());
        }
    }
}

