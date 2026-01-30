package cliente;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ClienteFTP {
    private static Socket cliente;
    private static BufferedWriter salida;
    private static BufferedReader entrada;


    public ClienteFTP(String host, int puerto) throws IOException {
        setCliente(host, puerto);
        setSalida();
        setEntrada();
    }

    private void setCliente(String host, int puerto) throws IOException {
        try {
            this.cliente = new Socket(host, puerto);

        } catch (IOException e) {
            throw new IOException("SERVIDOR CERRADO O NO DISPONIBLE");
        }
    }

    private void setSalida() {
        try {
            salida = new BufferedWriter(new OutputStreamWriter(cliente.getOutputStream()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void setEntrada() {
        try {
            entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String hacerPing() {
        String respuesta;

        try {
            salida.write("HELLO\n");
            salida.flush();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        respuesta = leerRespuesta();

        return respuesta;
    }

    public String logIn(String usuario, String contrasenia) {
        try {
            salida.write(String.format("LOGIN``%s``%s\n", usuario, contrasenia));
            salida.flush();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String respuesta = leerRespuesta();

        if (respuesta == null)
            return "Error: No se recibió respuesta del servidor";

        if (respuesta.startsWith("200"))
            return "Sesion iniciada correctamente";

        else if (respuesta.startsWith("500"))
            return "Sesion ya iniciada";
        else
            return "Usuario o contraseña invalidos";
    }

    public List<String> listarArchivos() throws IOException {
        try {
            salida.write("LS\n");
            salida.flush();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return leerBucle();
    }

    public void cargarArchivo(String archivo, String ubicacion) throws IOException {
        salida.write(String.format("GET``%s\n", archivo));
        salida.flush();

        String respuesta = leerRespuesta();

        if (respuesta.startsWith("401"))
            throw new IOException("No se ha iniciado sesion");

        if (respuesta.startsWith("550"))
            throw new IOException("Archivo no encontrado o error en el servidor");

        if (!respuesta.startsWith("150"))
            throw new IOException("Error inesperado del servidor: " + respuesta);

        DataInputStream dis = new DataInputStream(cliente.getInputStream());
        long tamanio = Long.parseLong(respuesta.substring(4));

        FileOutputStream fos = new FileOutputStream(ubicacion);
        byte[] buffer = new byte[8192];
        long restantes = tamanio;

        while (restantes > 0) {
            int leidos = dis.read(buffer, 0, (int)Math.min(buffer.length, restantes));
            if (leidos == -1) break;
            fos.write(buffer, 0, leidos);
            restantes -= leidos;
        }

        fos.close();
    }

    public void subirArchivo(String archivo) throws IOException {
        File fichero = new File(archivo);
        if (!fichero.exists())
            throw new IOException("Fichero inexistente");

        salida.write(String.format("PUT``%s``%s\n", fichero.getName(), fichero.length()));
        salida.flush();

        String respuesta = leerRespuesta();

        if (!respuesta.startsWith("150"))
            throw new IOException("Servidor no preparado para recibir: " + respuesta);

        DataOutputStream dos = new DataOutputStream(cliente.getOutputStream());
        FileInputStream fis = new FileInputStream(fichero);
        byte[] buffer = new byte[8192];
        int leidos;

        while ((leidos = fis.read(buffer)) != -1)
            dos.write(buffer, 0, leidos);

        fis.close();
        dos.flush();

        respuesta = leerRespuesta();
        if (!respuesta.startsWith("226"))
            throw new IOException("Error al subir archivo: " + respuesta);
    }

    private List<String> leerBucle() throws IOException {
        List<String> archivos = new ArrayList<>();

        String linea = leerRespuesta();
        if (linea == null || linea.startsWith("401"))
            throw new IOException("401 Sesion no iniciada");

        if (linea.startsWith("550"))
            throw new IOException("550 Error al leer directorio. Directorio incorrecto o vacio");

        try {
            while ((linea = entrada.readLine()) != null && !linea.contains("226")) {
                archivos.add(linea);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return archivos;
    }

    private String leerRespuesta() {
        String respuesta;

        try {
            respuesta = entrada.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return respuesta;
    }

    public static void cerrar() {
        try {
            salida.write("EXIT\n");
            salida.flush();

            salida.close();
            entrada.close();
            cliente.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
