package Ejercicios.E008Chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Comunicacion implements Runnable {
    Socket socket;
    public Comunicacion(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));

            System.out.print("Tú: ");
            String mensaje = teclado.readLine();
            System.out.println();
            if (!mensaje.isBlank()) salida.println("Otro: " + mensaje);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
