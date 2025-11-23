package Ejercicios.E008Chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Comunicacion implements Runnable {
    private Socket socket;
    private PrintWriter salida;

    public Comunicacion(Socket socket) throws IOException {
        this.socket = socket;
        this.salida = new PrintWriter(socket.getOutputStream(), true);
    }

    @Override
    public void run() {
        try (BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.print("Tú: ");
            String mensaje;
            while ((mensaje = teclado.readLine()) != null) {
                System.out.println();
                salida.println(mensaje);
                if (mensaje.equalsIgnoreCase("salir")) {
                    System.out.println("Cerrando conexion...");
                    socket.close();
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("Conexión cerrada.");
        }
    }
}