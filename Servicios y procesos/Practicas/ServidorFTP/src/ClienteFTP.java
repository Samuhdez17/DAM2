import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ClienteFTP {
    private Socket cliente;
    private static PrintWriter salida;
    private static BufferedReader entrada;


    public ClienteFTP() {
        setCliente();
        setSalida();
        setEntrada();
    }

    private void setCliente() {
        try {
            this.cliente = new Socket("localhost", 21);

        } catch (IOException e) {
            System.out.println("SERVIDOR CERRADO O NO DISPONIBLE");
        }
    }

    private void setSalida() {
        try {
            salida = new PrintWriter(cliente.getOutputStream(), true);
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

        salida.println("HELLO");
        respuesta = leerRespuesta();

        return respuesta;
    }

    public String logIn(String usuario, String contrasenia) {
        salida.printf("LOGIN``%s``%s", usuario, contrasenia);
        String respuesta = leerRespuesta();

        if (respuesta.equals("200"))
            return "Sesion iniciada correctamente";

        else if (respuesta.equals("500"))
            return "Sesion ya iniciada";
        else
            return "Usuario o contraseña invalidos";
    }

    public List<String> listarArchivos() {
        salida.println("LS");
        return leerBucle();
    }

    public void cargarArchivo(String archivo) {

    }

    private List<String> leerBucle() {
        List<String> archivos = new ArrayList<>();

        String linea;
        try {
            while (!(linea = entrada.readLine()).equals("226")) {
                if (linea.equals("500"))
                    return null;

                if (!linea.equals("150"))
                    archivos.add(linea);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return archivos;
    }

    private static String leerRespuesta() {
        String respuesta;

        try {
            respuesta = entrada.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return respuesta;
    }
}
