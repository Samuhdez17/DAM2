package Ejercicios.E009MensajeriaApp;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MultiCliente implements Runnable {
    private final Socket cliente;
    private String nombreUsuario;
    private final HashMap<String, ArrayList<Mensaje>> diccionario;
    private final File buzon;

    public MultiCliente(Socket cliente, HashMap<String, ArrayList<Mensaje>> diccionario, File buzon) {
        this.cliente = cliente;
        this.diccionario = diccionario;
        this.buzon = buzon;
    }

    @Override
    public void run() {
        try {
            BufferedReader entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            PrintWriter salida = new PrintWriter(new OutputStreamWriter(cliente.getOutputStream()), true);

            salida.println("Bienvenido, por favor, indica tu nombre: ");
            nombreUsuario = entrada.readLine();
            String comandoCliente = " ";

            while (!comandoCliente.equals("exit")) {
                comandoCliente = entrada.readLine();
                switch (comandoCliente) {
                    case "leer" -> leerMensajes(salida);
                    case "enviar" -> enviarMensaje(salida, entrada);
                    case "exit" -> salida.println("Saliendo del servidor...");
                    default -> salida.println("400 Comando mal formado");
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);

        } catch (NullPointerException e) {
//
        } finally {
            try {
                System.out.println("Cliente: " + cliente.getInetAddress() + " se ha desconectado");
                cliente.close();
                Servidor.eliminarCliente();
            } catch (IOException e) {
                System.err.println("Error al cerrar cliente");
            }
        }
    }

    private void leerMensajes(PrintWriter salida) throws IOException {
        List<Mensaje> mensajes = diccionario.get(nombreUsuario);

        if (mensajes != null && !mensajes.isEmpty()) {
            salida.println("150 Inicio listado");
            for (Mensaje m : mensajes)
                salida.printf(m.mostrarMensaje());

            salida.println("226 Fin listado");

        } else {
            salida.println("404 No tienes mensajes");
        }
    }

    private void enviarMensaje(PrintWriter salida, BufferedReader entrada) throws IOException {
        salida.println("Indica el nombre del destinatario:");
        String destinatario = entrada.readLine();

        salida.println("Escribe tu mensaje:");
        String contenido = entrada.readLine();

        Mensaje mensaje = new Mensaje(destinatario, nombreUsuario, contenido);
        agregarADiccionario(mensaje);
        DataOutputStream dos = new DataOutputStream(new FileOutputStream(buzon, true));
        dos.writeUTF(mensaje.escribirEnFichero());
    }

    private void agregarADiccionario(Mensaje mensaje) {
        diccionario.putIfAbsent(mensaje.getDestinatario(), new ArrayList<>());

        ArrayList<Mensaje> mensajesParaMismoDestinatario = diccionario.get(mensaje.getDestinatario());
        mensajesParaMismoDestinatario.add(mensaje);
    }
}
