package com.example.view.servidor;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor implements Runnable {
    public final int maxClientes = 1;
    public static int numClientes = 0;

    private final ControladorServidor controlador;

    public static HiloServidor hiloActivo = null;

    public Servidor(ControladorServidor controlador) {
        this.controlador = controlador;
    }

    @Override
    public void run() {
        try (ServerSocket servidor = new ServerSocket(controlador.puerto)) {
            System.out.println("Esperando conexion con cliente");
            
            while (true) { 
                Socket cliente = servidor.accept();

                synchronized (Servidor.class) {
                    if (numClientes >= maxClientes) {
                        try {
                            PrintWriter salida = new PrintWriter(cliente.getOutputStream(), true);
                            salida.println("Servidor ocupado");
                            cliente.close();
                        } catch (IOException e) {
                            System.out.println("Error rechazando cliente");
                        }
                        continue;
                    }

                    agregarCliente();
                }
                
                System.out.println("Cliente conectado: " + cliente.getInetAddress());
                Thread hiloCliente = new Thread(new HiloServidor(cliente, controlador));
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
