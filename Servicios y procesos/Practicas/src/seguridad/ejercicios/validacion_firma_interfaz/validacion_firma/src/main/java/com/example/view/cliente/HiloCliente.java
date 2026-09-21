package com.example.view.cliente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import javafx.application.Platform;
import javafx.scene.control.Alert;

public class HiloCliente implements Runnable {
    public static HiloCliente hiloActivo = null;

    private final ControladorCliente controlador;
    private Socket servidor;
    private BufferedReader entrada;
    private PublicKey cPublica;
    
    public HiloCliente(ControladorCliente controlador) {
        hiloActivo = this;
        this.controlador = controlador;

        try {
            servidor = new Socket(controlador.host, controlador.puerto);
            entrada = new BufferedReader(new InputStreamReader(servidor.getInputStream()));

            // Clave publica
            byte[] decodificacion = Base64.getDecoder().decode(entrada.readLine());
            X509EncodedKeySpec spec = new X509EncodedKeySpec(decodificacion);
    
            KeyFactory kf = KeyFactory.getInstance("RSA");
            cPublica = kf.generatePublic(spec);
            
        } catch (IOException | NoSuchAlgorithmException | InvalidKeySpecException e) {
            Platform.runLater(() -> {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error de conexión");
                alert.setContentText("No se pudo conectar con el servidor.");
                alert.showAndWait();
                System.exit(0);
            });
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                // Recepcion de datos del servidor
                String linea = entrada.readLine();

                if (linea == null || linea.equals(" ")) {
                    Platform.runLater(() -> {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Desconectado");
                        alert.setHeaderText(null);
                        alert.setContentText("El servidor ha cerrado la conexión.");
                        alert.showAndWait();
                        System.exit(0);
                    });
                    
                    break;
                }

                String[] datos = linea.split("``"); // mensaje``firma
                
                // Mensaje
                String mensaje = datos[0];
                
                // Firma
                byte[] firma = Base64.getDecoder().decode(datos[1]);

                boolean firmaValida = verificarFirma(cPublica, mensaje, firma);

                Platform.runLater(() -> {
                    if (firmaValida) {
                        controlador.lbFirmaValida.setStyle("-fx-text-fill: green; -fx-align: center");
                        controlador.lbFirmaValida.setText("Firma valida");
                    } else {
                        controlador.lbFirmaValida.setStyle("-fx-text-fill: red; -fx-align: center");
                        controlador.lbFirmaValida.setText("Firma no valida");
                    }

                    controlador.lbMsg.setText(mensaje);
                    System.out.println(mensaje);
                });
            }

        } catch (IOException e) {

        } catch (NoSuchAlgorithmException | InvalidKeyException | SignatureException e) {
            Platform.runLater(() -> {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error de validación");
                alert.setContentText("No se pudo validar la firma.");
                alert.showAndWait();
            });
        }
    }
    
    public static boolean verificarFirma(PublicKey publicKey, String mensaje, byte[] firma) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initVerify(publicKey);
        signature.update(mensaje.getBytes());
        return signature.verify(firma);
    }

    public void cerrar() {
        try {
            servidor.close();
        } catch (IOException e) {
        }
    }
}
