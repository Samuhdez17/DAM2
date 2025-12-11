package Ejercicios.E009MensajeriaApp;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Servidor {
    private final static int PUERTO = 5000;

    public static void main(String[] args) throws IOException {
        ServerSocket servidor = new ServerSocket(PUERTO);
        System.out.println("Servidor iniciado en el puerto: " + PUERTO);

        Socket cliente = servidor.accept();
        System.out.println("Cliente conectado desde: " + cliente.getInetAddress());

        BufferedReader entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
        PrintWriter salida = new PrintWriter(new OutputStreamWriter(cliente.getOutputStream()));

        salida.print("Bienvenido, por favor, indica tu nombre: ");
        entrada.readLine();
        salida.println();

        String comandoCliente = entrada.readLine();
        if (comandoCliente.equals("leer")) {

        } else if (comandoCliente.equals("enviar")) {

        } else {

        }
    }
}
