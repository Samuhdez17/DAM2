import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner teclado = new Scanner(System.in);
    private static final String MENU = """
                ===========  MENU  ===========
                1. Ping
                2. Iniciar sesion
                3. Listar archivos
                4. Cargar archivo
                5. Subir archivo
                0. Salir
                ==============================
                
                """;

    public static void main(String[] args) {
        ClienteFTP clienteFTP = new ClienteFTP();

        int opcion = -1;
        while (opcion != 0) {
            System.out.println(MENU);
            System.out.print("Indica una opcion: ");
            opcion = teclado.nextInt();
            System.out.println();

            switch (opcion) {
                case 1 -> hacerPing(clienteFTP);

                case 2 -> iniciarSesion(clienteFTP);

                case 3 -> listarArchivos(clienteFTP);

                case 4 -> cargarArchivo(clienteFTP);

                case 5 -> subirArchivo(clienteFTP);

                case 0 -> System.out.println("Saliendo....");
            }
        }

        teclado.close();
        clienteFTP.cerrar();
    }

    private static void subirArchivo(ClienteFTP clienteFTP) {
        System.out.println("Indica el nombre del archivo a cargar (con extension)");
        String archivo = teclado.nextLine().replace(" ", "_");

        try {
            clienteFTP.subirArchivo(archivo);
            System.out.println("Archivo cargado correctamente");

        } catch (Exception e) {
            System.out.println("Error al cargar archivo");
        }
    }

    private static void cargarArchivo(ClienteFTP clienteFTP) {
        System.out.println("Quieres listar archivos antes? (s/n)");
        char respuesta = teclado.next().charAt(0);

        if (respuesta == 's') {
            listarArchivos(clienteFTP);

        } else {
            System.out.println("Indica el nombre del archivo a cargar (con extension)");
            String archivo = teclado.nextLine().replace(" ", "_");

            try {
                clienteFTP.cargarArchivo(archivo);
                System.out.println("Archivo cargado correctamente");

            } catch (Exception e) {
                System.out.println("Error al cargar archivo");
            }
        }
    }

    private static void hacerPing(ClienteFTP clienteFTP) {
        String respuesta = clienteFTP.hacerPing();
        System.out.println(respuesta == null ? "Servidor no disponible" : respuesta);
    }

    private static void listarArchivos(ClienteFTP clienteFTP) {
        List<String> archivos = clienteFTP.listarArchivos();

        if (archivos == null) {
            System.out.println("No se ha iniciado sesion.");
            iniciarSesion(clienteFTP);
            return;
        }

        for  (String archivo : archivos) {
            System.out.println(archivo);
        }
    }

    private static void iniciarSesion(ClienteFTP clienteFTP) {
        System.out.print("Introduce usuario: ");
        teclado.nextLine();
        String usuario = teclado.nextLine();
        System.out.println();

        System.out.print("Introduce contraseña: ");
        String contrasenia = teclado.nextLine();
        System.out.println();

        System.out.println(clienteFTP.logIn(usuario, contrasenia));
    }
}
