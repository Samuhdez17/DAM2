package org.example.validacion_firma;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Servidor {
    private final static int PUERTO = 1312;
    private final static int MAX_CLIENTES = 1;
    private static int numClientes = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try (ServerSocket servidor = new ServerSocket(PUERTO)) {
            System.out.println("Esperando conexion con cliente");
            
            while (true) { 
                Socket cliente = servidor.accept();

                synchronized (Servidor.class) {
                    try {
                        while (numClientes >= MAX_CLIENTES) {
                            PrintWriter salida = new PrintWriter(cliente.getOutputStream(), true);
                            
                            salida.println("Servidor ocupado");
                        }
                        
                    } catch (IOException e) {
                        System.out.println("Cliente desconectado en sala espera");
                        continue;
                    }

                    agregarCliente();
                }
                
                System.out.println("Cliente conectado: " + cliente.getInetAddress());
                Thread hiloCliente = new Thread(new HiloServidor(cliente, sc));
                hiloCliente.start();
            }
            
        } catch (IOException e) {
        }
    }

    public static void agregarCliente() {
        numClientes++;
    }

    public static void borrarCliente() {
        numClientes--;
    }
}