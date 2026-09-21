package com.example.view.servidor;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.util.Base64;
import java.util.concurrent.SynchronousQueue;

import javafx.application.Platform;
import javafx.scene.control.Alert;

public class HiloServidor implements Runnable {
    private final Socket cliente;
    private final ControladorServidor controlador;
    protected SynchronousQueue<String> colaMensajes = new SynchronousQueue<>();

    private PublicKey cPublica;
    private PrivateKey cPrivada;
    private Socket socket;
    private PrintWriter salida;

    public HiloServidor(Socket cliente, ControladorServidor controlador) {
        this.cliente = cliente;
        this.controlador = controlador;
        Servidor.hiloActivo = this;

        try {
            socket = cliente;
            salida = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);

        } catch (IOException e) {
        }

        // Claves privada y publica
        KeyPairGenerator keyGenerator;
        try {
            keyGenerator = KeyPairGenerator.getInstance("RSA");
            
            keyGenerator.initialize(2048);
            KeyPair claves = keyGenerator.generateKeyPair();
            
            cPublica = claves.getPublic();
            cPrivada = claves.getPrivate();

        } catch (NoSuchAlgorithmException e) {
        }

        String claveCifrada = Base64.getEncoder().encodeToString(cPublica.getEncoded());

        salida.println(claveCifrada);
    }

    @Override
    public void run() {
        // Detector de desconexión del cliente
        Thread detector = new Thread(() -> {
            try {
                cliente.getInputStream().read(); // bloquea hasta que el cliente cierre
            } catch (IOException e) {
            }
            colaMensajes.offer(""); // fuerza salida del take()
        });
        detector.setDaemon(true);
        detector.start();
        
        try {
            controlador.cargarStage(2);
            String mensaje;

            while (true) {
                System.out.println("Esperando mensaje en cola...");
                mensaje = colaMensajes.take();
                System.out.println("Mensaje recogido: " + mensaje);

                if (mensaje.isEmpty()) {
                    System.out.println("Desconectando usuario...");
                    salida.println(" ");
                    break;
                }

                // Firma del mensaje
                Signature signature = Signature.getInstance("SHA256withRSA");
                signature.initSign(cPrivada);
                signature.update(mensaje.getBytes());

                byte[] firma = signature.sign();
                String firmaCifrada = Base64.getEncoder().encodeToString(firma);

                salida.println(mensaje + "``" + firmaCifrada);
                System.out.println("Mensaje enviado: " + mensaje);

                if (salida.checkError())
                    break;
            }

        } catch (NoSuchAlgorithmException | InvalidKeyException | SignatureException e) {
            System.getLogger(HiloServidor.class.getName()).log(System.Logger.Level.ERROR, (String) null, e);

        } catch (InterruptedException e) {

        } finally {
            try { cliente.close(); } catch (IOException e) {}

            System.out.println("Usuario desconectado");
            Servidor.borrarCliente();

            Platform.runLater(() -> {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Cliente desconectado");
                alert.setHeaderText(null);
                alert.setContentText("El cliente ha cerrado la conexión.");
                alert.showAndWait();
                controlador.cargarStage(1);
            });
        }
    }

    public void enviarMensaje(String mensaje) {
        try {
            colaMensajes.put(mensaje);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
