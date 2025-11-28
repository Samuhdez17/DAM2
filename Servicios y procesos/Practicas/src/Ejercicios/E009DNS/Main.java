package Ejercicios.E009DNS;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {
    private final static String RUTA_TXT = "dominios";
    private final static int PUERTO = 5000;

    public static void main(String[] args) throws IOException {
        File ficheroDominios = new File(RUTA_TXT);
        HashMap<String, ArrayList<Registro>> registros = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(ficheroDominios))) {
            String linea;

            while ((linea = br.readLine()) != null) {
                String[] contenidoRegistro = linea.split(" ");

                registros.putIfAbsent(contenidoRegistro[0], new ArrayList<>());

                ArrayList<Registro> registrosDelMismoDominio = registros.get(contenidoRegistro[0]);
                registrosDelMismoDominio.add(new Registro(contenidoRegistro));
            }

        } catch (IOException e) {
            System.err.println("Error al leer el fichero");
            throw new RuntimeException(e);
        }

        ServerSocket serverSocket = new ServerSocket(PUERTO);
        System.out.println("Servidor iniciado en el puerto " + PUERTO);
        Socket socket = serverSocket.accept();

        ArrayList<Registro> prueba = registros.get("google.com");
        for (Registro r : prueba) {
            System.out.println(r);
        }
    }
}
