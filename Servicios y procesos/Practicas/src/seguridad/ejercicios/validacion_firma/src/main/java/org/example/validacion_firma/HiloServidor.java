package org.example.validacion_firma;

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
import java.util.Scanner;

public class HiloServidor implements Runnable {
    private final Scanner sc;

    private final Socket cliente;
    public HiloServidor(Socket cliente, Scanner sc) {
        this.cliente = cliente;
        this.sc = sc;
    }

    @Override
    public void run() {
        try {
            String mensaje; String mensajeNuevo = "a";

            do {
                // Flujo de comunicacion
                // BufferedReader entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
                PrintWriter salida = new PrintWriter(new OutputStreamWriter(cliente.getOutputStream()), true);
            
                // Claves privada y publica
                KeyPairGenerator keyGenerator = KeyPairGenerator.getInstance("RSA");
                keyGenerator.initialize(2048);
                KeyPair claves = keyGenerator.generateKeyPair();

                PublicKey cPublica = claves.getPublic();
                PrivateKey cPrivada = claves.getPrivate();

                String claveCifrada = Base64.getEncoder().encodeToString(cPublica.getEncoded());

                // Mensaje a cliente
                System.out.print("Escribe un mensaje: ");
                mensaje = sc.nextLine();
                System.out.println();

                if (mensaje.isEmpty()) {
                    System.out.println("Desconectando usuario...");
                    salida.println("0`` ``0");
                    continue;
                }

                // Firma del mensaje
                Signature signature = Signature.getInstance("SHA256withRSA");
                signature.initSign(cPrivada);
                signature.update(mensaje.getBytes());

                byte[] firma = signature.sign();
                String firmaCifrada = Base64.getEncoder().encodeToString(firma);

                System.out.println("Mensaje firmado correctamente\n");
                System.out.println();

                System.out.println("Quieres modificar el mensaje antes de enviarlo? s/n (Prueba para sacar false)");
                char respuesta = sc.next().toLowerCase().charAt(0);
                sc.nextLine();

                System.out.println();
                if (respuesta == 's') {
                    System.out.println("Escribe el nuevo mensaje ( mensaje anterior: " + mensaje + " )");
                    mensajeNuevo = sc.nextLine();
                    salida.println(claveCifrada + "``" + mensajeNuevo + "``" + firmaCifrada);

                } else {
                    System.out.println("Mandando mensaje sin modificar");
                    salida.println(claveCifrada + "``" + mensaje + "``" + firmaCifrada);
                }

                System.out.printf("""

                        Informacion mandada:
                        - Clave: %s

                        - Mensaje: %s

                        - Firma: %s

                        Mensaje firmado: %s

                        """, 
                        claveCifrada, 
                        (respuesta == 's' ? mensajeNuevo : mensaje), // Si se ha modificado el mensaje antes de mandar, decimos que se ha mandado el nuevo mensaje
                        firmaCifrada, 
                        mensaje);

            } while (!mensaje.isEmpty());

            try {
                System.out.println("Usuario desconectado");
                cliente.close();
                Servidor.borrarCliente();
                System.out.println("Esperando conexion con cliente");


            } catch (IOException e) {

            }

        } catch (IOException e) {
            System.out.println("Error inesperado: " + e);
            
        } catch (NoSuchAlgorithmException | InvalidKeyException | SignatureException ex) {
            System.getLogger(HiloServidor.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);

        } 
    }
}
