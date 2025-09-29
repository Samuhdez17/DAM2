package LecturaYEscritura.Ej2_AccesoADatosEnFicheros;

import java.io.File;
import java.util.Scanner;

import static java.lang.System.exit;

public class Main {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);

        System.out.print("Indica el nombre del fichero en el que almacenar los usuarios: ");
        String nombreFichero = entrada.nextLine();
        System.out.println();

        String nombreFicheroCorregido = corregirEspacios(nombreFichero);
        String ruta = "src/LecturaYEscritura/Ej2_AccesoADatosEnFicheros/" + nombreFicheroCorregido + ".txt";

        File fichero = new File(ruta);

        if (!fichero.exists()) {
        System.out.print("Este fichero no existe, quieres crearlo? (s/n): ");
            char respuesta = entrada.next().charAt(0);

            if (respuesta == 'n') {
                while (!fichero.exists()) {
                    System.out.print("Indica el nombre del archivo: ");
                    entrada.nextLine();
                    nombreFichero = entrada.nextLine();
                    System.out.println();

                    nombreFicheroCorregido = corregirEspacios(nombreFichero);

                    ruta = "src/LecturaYEscritura/Ej2_AccesoADatosEnFicheros/" + nombreFicheroCorregido + ".txt";
                    fichero = new File(ruta);

                    if (fichero.exists()) System.out.println("Fichero cambiado correctamente");
                    else {
                        System.out.print("No existe el fichero, cambiar nombre o salir? (c/s): ");
                        respuesta = entrada.next().charAt(0);
                        if (respuesta == 's') {
                            System.out.println("Saliendo...");
                            exit(0);
                        }
                    }
                }
            } else System.out.println("Fichero seleccionado correctamente");
        }

        int opcion = 0;
        while (opcion != 5) {
            System.out.println("------------ MENU ------------");
            System.out.println("1. Añadir usuario");
            System.out.println("2. Mostrar usuarios");
            System.out.println("Generar fichero de concordancia");
            System.out.println("5. Salir");
            System.out.println("------------------------------");
            System.out.println("Elige una opción");
            opcion = entrada.nextInt();

            switch (opcion) {
                case 1 -> {

                }

                case 2 -> {

                }

                case 3 -> {

                }

                case 5 -> System.out.println("Saliendo....");
            }
        }
    }

    private static String corregirEspacios(String nombreFichero) {
        StringBuilder nombreFicheroCorregido = new StringBuilder();
        for (int i = 0; i < nombreFichero.length() ; i++) {
            if (nombreFichero.charAt(i) == ' ') nombreFicheroCorregido.append('_');
            else nombreFicheroCorregido.append(nombreFichero.charAt(i));
        }
        return nombreFicheroCorregido.toString();
    }
}
