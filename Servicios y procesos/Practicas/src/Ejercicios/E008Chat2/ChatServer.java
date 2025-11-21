package Ejercicios.E008Chat2;

import java.io.*;
import java.net.*;
import java.util.*;

public class ChatServer {

    private static final int PORT = 5000;
    private static final int MAX_CLIENTS = 28;

    // Lista sincronizada de clientes conectados
    private static final List<DataOutputStream> clients =
            Collections.synchronizedList(new ArrayList<>());

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(PORT);
        System.out.println("Servidor iniciado en el puerto " + PORT);

        while (true) {
            if (clients.size() < MAX_CLIENTS) {
                Socket socket = serverSocket.accept();
                System.out.println("Cliente conectado: " + socket.getInetAddress());

                new ClientHandler(socket).start();

            } else {
                System.out.println("Máximo de clientes alcanzado.");
                try {
                    Thread.sleep(1000);
                }catch(InterruptedException e)
                {
                    System.out.println("Interrumpido descanso del hilo");
                }
            }
        }
    }

    private static class ClientHandler extends Thread {
        private Socket socket;
        private DataOutputStream out;

        private String nombre;

        ClientHandler(Socket socket) {
            this.socket = socket;
            try {
                DataOutputStream output = new DataOutputStream(socket.getOutputStream());
                DataInputStream input = new DataInputStream(socket.getInputStream());

                output.writeUTF("¿Cual es tu nombre? ");
                String nombre = input.readUTF();
                System.out.println("Nuevo cliente " + nombre);
                this.nombre = nombre;
            }catch(Exception e)
            {
                System.out.println("Excepcion en el servidor al inicializar el cliente: " + e.getMessage());
            }
        }

        @Override
        public void run() {
            try {
                out = new DataOutputStream(socket.getOutputStream());
                clients.add(out);

                DataInputStream input = new DataInputStream(socket.getInputStream());

                String message;
                while ((message = input.readUTF()) != null) {
                    if ( message.equals("exit")) {
                        socket.close();
                        break;
                    }
                    broadcast(message);
                }

            } catch (IOException e) {
                System.out.println("Error con cliente.");
            } finally {
                clients.remove(out);
                try { socket.close(); } catch (IOException ignored) {}
            }
        }

        private void broadcast(String message) {
            synchronized (clients) {
                for (DataOutputStream pw : clients) {
                    try {
                        pw.writeUTF(nombre+": " + message);
                    } catch (Exception e) {
                        System.out.println("Error al escribir el mensaje en broadcast");
                    }
                }
            }
        }
    }
}