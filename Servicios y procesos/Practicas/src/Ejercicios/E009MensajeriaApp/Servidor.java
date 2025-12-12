package Ejercicios.E009MensajeriaApp;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

public class Servidor {
    private final static File buzon = new File("buzon.txt"); // src/Ejercicios/E009MensajeriaApp/
    private final static int PUERTO = 5000;
    private static int numClientes = 0;
    private final static int CLIENTES_MAX = 5;

    public static void main(String[] args) {
        HashMap<String, ArrayList<Mensaje>> diccionario = cargarDiccionario();

        try (ServerSocket servidor = new ServerSocket(PUERTO)) {
            System.out.println("Servidor iniciado en el puerto: " + PUERTO);

            while (true) {
                Socket cliente = servidor.accept();

                synchronized (Servidor.class) {
                    if (numClientes >= CLIENTES_MAX) {
                        PrintWriter salida = new PrintWriter(cliente.getOutputStream(), true);

                        salida.println("500 Cliente rechazado: maximo alcanzado");
                        cliente.close();
                        continue;
                    }

                    agregarCliente();
                }

                System.out.println("Cliente conectado desde: " + cliente.getInetAddress());
                Thread hiloCliente = new Thread(new MultiCliente(cliente, diccionario, buzon));
                hiloCliente.start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static HashMap<String, ArrayList<Mensaje>> cargarDiccionario() {
        HashMap<String, ArrayList<Mensaje>> diccionario = new HashMap<>();

        if (!buzon.exists()) {
            try {
                buzon.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        try (DataInputStream dis = new DataInputStream(new FileInputStream(buzon))) {
            try {
                while (true) {
                    String[] contenidoLinea = dis.readUTF().split("``");
                    agregarADiccionario(diccionario, contenidoLinea);
                }
            } catch (EOFException e) {
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return diccionario;
    }

    private static void agregarADiccionario(HashMap<String, ArrayList<Mensaje>> diccionario, String[] contenidoLinea) {
        diccionario.putIfAbsent(contenidoLinea[0], new ArrayList<>());

        ArrayList<Mensaje> mensajesParaMismoDestinatario = diccionario.get(contenidoLinea[0]);
        mensajesParaMismoDestinatario.add(new Mensaje(contenidoLinea));
    }

    private static void agregarCliente() { numClientes++; }

    public static void eliminarCliente() { numClientes--; }
}
