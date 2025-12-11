package Ejercicios.E009MensajeriaApp;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Servidor {
    private final static int PUERTO = 5000;
    private final static String RUTA_BUZON = "src/Ejercicios/E009MensajeriaApp/buzon";

    public static void main(String[] args) throws IOException {
        ServerSocket servidor = new ServerSocket(PUERTO);
        System.out.println("Servidor iniciado en el puerto: " + PUERTO);

        Socket cliente = servidor.accept();
        System.out.println("Cliente conectado desde: " + cliente.getInetAddress());

        BufferedReader entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
        PrintWriter salida = new PrintWriter(new OutputStreamWriter(cliente.getOutputStream()), true);

        salida.println("Bienvenido, por favor, indica tu nombre: ");
        String nombre = entrada.readLine();

        String comandoCliente = entrada.readLine();
        if (comandoCliente.equals("leer")) {
            DataInputStream dis = new DataInputStream(new FileInputStream(RUTA_BUZON));
            List<Mensaje> mensajes = new ArrayList<>();

            try {
                while (true) {
                    String[] linea = dis.readUTF().split("``");
                    if (linea.length <= 3 && linea[0].equals(nombre))
                        mensajes.add(new Mensaje(linea));
                }

            } catch (EOFException e) {

            }

            if (mensajes != null && !mensajes.isEmpty()) {
                salida.println("150 Inicio listado");
                for (Mensaje m : mensajes)
                    salida.printf("De: %s ; Mensaje %s\n", m.getEmisor(), m.getContenido());

                salida.println("226 Fin listado");

            } else {
                salida.println("404 No tienes mensajes");
            }

        } else if (comandoCliente.equals("enviar")) {

        } else {

        }
    }
}
