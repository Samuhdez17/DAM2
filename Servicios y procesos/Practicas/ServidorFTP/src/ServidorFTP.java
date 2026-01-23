import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class ServidorFTP {
    private final static int PUERTO = 21;
    private final static int MAX_CLIENTES = 3;
    private static int numClientes = 0;
    private static final File usuariosBD = new File("src/files/usuariosBD.txt");
    private static final HashMap<String, String> usuarios = cargarUsuarios();


    public static void main(String[] args) {
        String raiz = args[0];
        try (ServerSocket servidor = new ServerSocket(PUERTO)) {
            System.out.println("Servidor iniciado en el puerto " + PUERTO);

            while (true) {
                Socket cliente = servidor.accept();
/*
                synchronized (ServidorFTP.class) {
                    try {
                        while (numClientes >= MAX_CLIENTES) {
                            PrintWriter salida = new PrintWriter(cliente.getOutputStream(), true);

                            salida.println("500 Cliente en espera, maximo alcanzado");
                        }
                    } catch (IOException e) {
                        System.out.println("Cliente desconectado en la espera");
                        continue;
                    }

                    agregarCliente();
                }
*/
                System.out.println("Cliente conectado desde: " + cliente.getInetAddress());
                Thread hiloCliente = new Thread(new HiloServidor(cliente, raiz));
                hiloCliente.start();
            }
        } catch (IOException e) {

        }
    }
    private static HashMap<String, String> cargarUsuarios() {
        HashMap<String, String> usuarios = new HashMap<>();

        if (!usuariosBD.exists()) {
            try {
                usuariosBD.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        try (DataInputStream dis = new DataInputStream(new FileInputStream(usuariosBD))) {
            try {
                while (true) {
                    String[] contenidoLinea = dis.readUTF().split("´´");
                    agregarAUsuariosBD(usuarios, contenidoLinea);
                }
            } catch (EOFException e) {
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return usuarios;
    }

    private static void agregarAUsuariosBD(HashMap<String, String> usuarios, String[] contenidoLinea) {
        if (usuarios.containsKey(contenidoLinea[0]))
            System.out.println("El usuario ya existe");
        else
            usuarios.put(contenidoLinea[0], contenidoLinea[1]);
    }

    private static void agregarCliente() { numClientes++; }

    public static void eliminarCliente() { numClientes--; }
}
