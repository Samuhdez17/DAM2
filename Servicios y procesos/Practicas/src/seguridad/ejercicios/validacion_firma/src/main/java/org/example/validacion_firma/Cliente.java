package org.example.validacion_firma;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class Cliente {
    private static final String HOST = "localhost";
    private static final int PUERTO = 1312;
    public static void main(String[] args) {
        try (Socket servidor = new Socket(HOST, PUERTO)) {
	    // Flujo de comunicacion
            BufferedReader entrada = new BufferedReader(new InputStreamReader(servidor.getInputStream()));
            // PrintWriter salida = new PrintWriter(new OutputStreamWriter(servidor.getOutputStream()), true);

            while (true) {
                // Rececpion de datos del servidor
                String[] datos = entrada.readLine().split("``");
                if (datos[1].isBlank()) {
                    System.out.println("Desconectado por el servidor");
                    break;
                }

                System.out.println("Mensaje recibido: " + datos[1]);

                // Clave publica
                byte[] decodificacion = Base64.getDecoder().decode(datos[0]);
                X509EncodedKeySpec spec = new X509EncodedKeySpec(decodificacion);

                KeyFactory kf = KeyFactory.getInstance("RSA");
                PublicKey cPublica = kf.generatePublic(spec);

                // Mensaje
                String mensaje = datos[1];

                // Firma
                byte[] firma = Base64.getDecoder().decode(datos[2]);


                System.out.println("Firma " + (verificarFirma(cPublica, mensaje, firma) ? "" : "no") + " valida\n");
            }
            
        } catch (Exception e) {
            System.out.println("Error inesperado: " + e);
        }
    }
    
    public static boolean verificarFirma(PublicKey publicKey, String mensaje, byte[] firma) throws Exception {
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initVerify(publicKey);
        signature.update(mensaje.getBytes());
        return signature.verify(firma);
    }
}