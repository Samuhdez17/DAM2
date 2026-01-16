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

        System.out.println(MENU);
        System.out.print("Indica una opcion: ");
        int opcion = teclado.nextInt();
        System.out.println();

        switch (opcion) {
            case 2 -> {
                System.out.print("Introduce usuario: ");
                String usuario = teclado.nextLine();
                System.out.println();

                System.out.print("Introduce contraseña: ");
                String contrasenia = teclado.nextLine();
                System.out.println();

                clienteFTP.logIn(usuario, contrasenia);
            }
        }
    }
}
