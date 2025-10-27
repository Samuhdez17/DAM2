package LecturaYEscritura.Ej2_AccesoADatosEnFicheros;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {
    private static final String CONCORDANCIAS_TXT = "src/LecturaYEscritura/Ej2_AccesoADatosEnFicheros/concordancias.txt";

    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);

        System.out.print("Indica el nombre del fichero en el que almacenar los usuarios: ");
        String nombreFichero = entrada.nextLine();
        System.out.println();

        String nombreFicheroCorregido = corregirEspacios(nombreFichero);
        String ruta = "src/LecturaYEscritura/Ej2_AccesoADatosEnFicheros/" + nombreFicheroCorregido + ".txt";

        File fichero = comprobarExistenciaFichero(entrada, ruta);

        int opcion = 0;
        while (opcion != 5) {
            System.out.println("""
              ------------ MENU ------------
              1. Añadir usuario
              2. Mostrar usuarios
              3. Generar fichero de concordancia
              5. Salir
              ------------------------------
              """);
            System.out.print("Elige una opción: ");
            opcion = entrada.nextInt();

            switch (opcion) {
                case 1 -> aniadirUsuario(entrada, fichero);

                case 2 -> leerUsuarios(fichero);

                case 3 -> generarFicheroConcordancia(entrada, fichero);

                case 5 -> System.out.println("Saliendo....");

                default -> System.out.println("Opción inválida\n");
            }
        }
    }

    public static File comprobarExistenciaFichero(Scanner entrada, String ruta) {
        File fichero = new File(ruta);

        if (!fichero.exists()) {
            System.out.print("Este fichero no existe, quieres crearlo? (s/n): ");
            char respuesta = entrada.next().charAt(0);

            while (respuesta != 's' && respuesta != 'n') {
                System.out.print("Respuesta inválida, responda de nuevo (s/n): ");
                respuesta = entrada.next().charAt(0);
                System.out.println();
            }

            if (respuesta == 'n') {
                while (!fichero.exists()) {
                    System.out.print("Indica el nombre del archivo: ");
                    entrada.nextLine();
                    String nombreFichero = entrada.nextLine();
                    System.out.println();

                    String nombreFicheroCorregido = corregirEspacios(nombreFichero);

                    ruta = "src/LecturaYEscritura/Ej2_AccesoADatosEnFicheros/" + nombreFicheroCorregido + ".txt";
                    fichero = new File(ruta);

                    if (fichero.exists()) System.out.println("Fichero cambiado correctamente");
                    else {
                        System.out.print("No existe el fichero, quieres crearlo o introducir nuevo nombre? (c/i): ");
                        respuesta = entrada.next().charAt(0);

                        while (respuesta != 'c' && respuesta != 'i') {
                            System.out.print("Respuesta inválida, responda de nuevo (c/i): ");
                            respuesta = entrada.next().charAt(0);
                            System.out.println();
                        }

                        if (respuesta == 'c') {
                            System.out.println("Fichero creado correctamente");
                            break;
                        }
                    }
                }

            } else System.out.println("Fichero seleccionado correctamente");
        }

        return fichero;
    }

    private static void aniadirUsuario(Scanner entrada, File fichero) {
        System.out.print("Indica las aficiones del usuario separado por comas: ");
        entrada.nextLine();
        String aficiones = entrada.nextLine().toUpperCase();
        String[] aficionesArray = aficiones.split(",");

        while (aficionesArray.length == 0) {
            System.out.print("No se ha indicado ninguna afición, ingrese como mínimo una afición (o más separado por comas): ");
            entrada.nextLine();
            aficiones = entrada.nextLine().toUpperCase();
            aficionesArray = aficiones.split(",");
        }

        GenerarCodUsuario genCod = new GenerarCodUsuario(fichero);

        Usuario usuario = new Usuario(genCod.getCodigo(), aficionesArray);

        try (FileWriter fw = new FileWriter(fichero,  true)) {
            fw.write(usuario + "\n");

            System.out.println("Usuario guardado correctamente");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void leerUsuarios(File fichero) {
        if (fichero.length() >= 1000) {
            System.out.printf("El fichero no se puede leer porque es demasiado grande (%d bytes)\n\n", fichero.length());
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(fichero))) {
            String linea;

            System.out.println("Lista de usuarios:\n");
            while ((linea = br.readLine()) != null) {
                System.out.println(linea);
                System.out.println("-------------------------------");
            }

            System.out.println("Fin de la lista\n");
        } catch (IOException e) {
            System.out.println("Error al leer el fichero");
            throw new RuntimeException(e);
        }
    }

    private static void generarFicheroConcordancia(Scanner entrada, File fichero) {
        int numConcorMin = pedirConcordanciasMinimas(entrada);
        ArrayList<String> lineas = new ArrayList<>();

        // Creamos un arraylist con todos los usuarios y sus aficiones para posteriormente comparar
        try (BufferedReader br = new BufferedReader(new FileReader(fichero))) {
            String linea;
            while ((linea = br.readLine()) != null) lineas.add(linea);
        } catch (IOException e) {}

        revisarConcordancias(lineas, numConcorMin);
    }

    private static int pedirConcordanciasMinimas(Scanner entrada) {
        System.out.print("Ingrese el número mínimo de concordancias: ");
        int numConcorMin = entrada.nextInt();
        System.out.println();

        while (numConcorMin < 1) {
            System.out.print("El número es demasiado pequeño, introduce un número mayor o igual a 1: ");
            numConcorMin = entrada.nextInt();
            System.out.println();
        }

        return numConcorMin;
    }

    private static void revisarConcordancias(ArrayList<String> lineas, int numConcorMin) {
        int numConcordancias = 0;

        for (int i = 0; i < lineas.size() ; i++) {
            for (int j = i + 1; j < lineas.size() ; j++) {

                String[] linea1 = lineas.get(i).split(" ");
                String[] linea2 = lineas.get(j).split(" ");

                ArrayList<String> comunes = encontrarConcordancias(linea1, linea2);

                // Si las concordancias que ha sacado son mayor o igual al mínimo indicado por el usuario, se ordenan y se escriben en un fichero de texto plano
                if (comunes.size() >= numConcorMin) {
                    Collections.sort(comunes);

                    try (FileWriter fw = new FileWriter(CONCORDANCIAS_TXT, true)) {
                        fw.write(String.format("%s %s ", linea1[0], linea2[0]));
                        for (String aficion : comunes) fw.write(aficion + " ");
                        fw.write("\n");
                        numConcordancias++;
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }

        if (numConcordancias > 0) {
            System.out.printf("""
            Fichero creado correctamente.
            Número de parejas encontradas: %d
        """, numConcordancias);

        } else System.out.println("No existen concordancias, no se ha generado el fichero");
    }

    private static ArrayList<String> encontrarConcordancias(String[] linea1, String[] linea2) {
        // Recorre las aficiones de la segunda persona (linea2) y las compara con las de la primera (linea1)
        ArrayList<String> comunes = new ArrayList<>();
        for (int aficion1 = 1; aficion1 < linea1.length ; aficion1++) {
            for (int aficion2 = 1; aficion2 < linea2.length ; aficion2++) {
                if (linea1[aficion1].equals(linea2[aficion2])) {
                    if (!comunes.contains(linea1[aficion1])) {
                        comunes.add(linea1[aficion1]);
                    }
                }
            }
        }
        return comunes;
    }

    private static String corregirEspacios(String nombre) {
        StringBuilder nombreCorregido = new StringBuilder();
        for (int i = 0; i < nombre.length() ; i++) {
            if (nombre.charAt(i) == ' ') {
                if (i == 0 && i == nombre.length() - 1) nombreCorregido.append('_');
            }
            else nombreCorregido.append(nombre.charAt(i));
        }
        return nombreCorregido.toString();
    }
}
