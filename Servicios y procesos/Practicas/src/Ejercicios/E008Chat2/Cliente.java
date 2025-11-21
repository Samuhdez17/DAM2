package Ejercicios.E008Chat2;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {

    public static void main(String[] args) {
        String ip = "192.168.1.150"; // 192.168.1.150 | localhost
        int puerto = 5000;

        try {
            Socket socket = new Socket(ip, puerto);
            System.out.println("Conectado al servidor.");

            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            Scanner sc = new Scanner(System.in);

            Thread receptor = new Thread(() -> {
                try {
                    while (true) {
                        String msg = in.readUTF();
                        System.out.println(msg);
                    }
                } catch (IOException e) {
                    System.out.println("Desconectado del servidor.");
                }
            });

            receptor.start();
            String mensaje;

            do {
                mensaje = sc.nextLine();
                out.writeUTF(mensaje);
            } while (mensaje.equals("exit"));

        } catch (IOException e) {
            System.out.println("Error de conexión: " + e.getMessage());
        }
    }
}
