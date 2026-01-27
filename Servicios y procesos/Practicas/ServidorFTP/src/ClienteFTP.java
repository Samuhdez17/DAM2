import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ClienteFTP {
    private final int puerto = 21;
    private final String host = "localhost";
    private Socket cliente;
    private BufferedWriter salida;
    private BufferedReader entrada;


    public ClienteFTP() {
        setCliente();
        setSalida();
        setEntrada();
    }

    private void setCliente() {
        try {
            this.cliente = new Socket(host, puerto);

        } catch (IOException e) {
            System.out.println("SERVIDOR CERRADO O NO DISPONIBLE");
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

    // TODO Corregir inicio de sesion e interfaz
    public String logIn(String usuario, String contrasenia) {
        try {
            salida.write(String.format("LOGIN``%s``%s\n", usuario, contrasenia));
            salida.flush();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String respuesta = leerRespuesta();

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

    public void cargarArchivo(String archivo) throws IOException {
        salida.write(String.format("GET``%s\n", archivo));
        salida.flush();

        String respuesta = leerRespuesta();

        if (respuesta.startsWith("401"))
            throw new IOException("No se ha iniciado sesion");

        if (respuesta.startsWith("550"))
            throw new IOException("Comando mal formado, falta nombre de archivo");

        DataInputStream dis = new DataInputStream(cliente.getInputStream());

        long tamanio = dis.readLong();

        FileOutputStream fos = new FileOutputStream(archivo, true);

        byte[] buffer = new byte[8192]; // Leemos en bloques de 8KB
        long restantes = tamanio;
        int leidos;

        while (restantes > 0 &&
                (leidos = dis.read(buffer, 0
                        , (int)Math.min(buffer.length, restantes))) != -1) {

            fos.write(buffer, 0, leidos);
            restantes -= leidos;
        }

        fos.close();

        leerRespuesta(); // Para liberar el ultimo mensaje de la lista (226)
    }

    public void subirArchivo(String archivo) throws IOException {
        File fichero = new File(archivo);
        if (!fichero.exists())
            throw new IOException("Fichero inexistente");

        long tamanio = fichero.length();
        String nombre = fichero.getName();

        salida.write(String.format("PUT``%s``%d\n", nombre, tamanio));
        salida.flush();

        String respuesta = leerRespuesta();

        if (respuesta.startsWith("401"))
            throw new IOException("No se ha iniciado sesion");

        if (respuesta.startsWith("550"))
            throw new IOException("Comando mal formado, falta nombre de archivo");

        FileInputStream fis = new FileInputStream(fichero);
        OutputStream os = cliente.getOutputStream();

        byte[] buffer = new byte[8192];
        int leidos;

        while ((leidos = fis.read(buffer)) != -1) {
            os.write(buffer, 0, leidos);
        }

        os.flush();
        fis.close();

        leerRespuesta();
    }

    private List<String> leerBucle() throws IOException {
        List<String> archivos = new ArrayList<>();

        String linea = leerRespuesta();
        if (linea == null || linea.startsWith("401"))
            throw new IOException("401 Sesion no iniciada");

        if (linea.startsWith("550"))
            throw new IOException("550 Error al leer directorio. Directorio incorrecto o vacio");

        try {
            while ((linea = entrada.readLine()) != null && !linea.equals("226")) {
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

    public void cerrar() {
        try {
            salida.write("EXIT");
            salida.flush();

            salida.close();
            entrada.close();
            cliente.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
