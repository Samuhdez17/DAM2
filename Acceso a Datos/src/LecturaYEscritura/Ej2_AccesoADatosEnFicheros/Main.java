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

        File fichero = new File(ruta);

        if (!fichero.exists()) {
        System.out.print("Este fichero no existe, quieres crearlo? (s/n): ");
            char respuesta = entrada.next().charAt(0);

            if (respuesta != 's' && respuesta != 'n') {
                while (respuesta != 's' && respuesta != 'n') {
                    System.out.print("Respuesta inválida, responda de nuevo (s/n): ");
                    respuesta = entrada.next().charAt(0);
                    System.out.println();
                }
            }

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
                        System.out.print("No existe el fichero, quieres crearlo o introducir nuevo nombre? (c/i): ");
                        respuesta = entrada.next().charAt(0);

                        if (respuesta != 'c' && respuesta != 'i') {
                            while (respuesta != 'c' && respuesta != 'i') {
                                System.out.print("Respuesta inválida, responda de nuevo (c/i): ");
                                respuesta = entrada.next().charAt(0);
                                System.out.println();
                            }
                        }

                        if (respuesta == 'c') {
                            System.out.println("Fichero creado correctamente");
                            break;
                        }
                    }
                }
            } else System.out.println("Fichero seleccionado correctamente");
        }

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
                case 1 -> aniadirUsuario(entrada, ruta, fichero);

                case 2 -> leerUsuarios(fichero);

                case 3 -> {
                    System.out.print("Ingrese el número mínimo de concordancias: ");
                    int numConcorMin = entrada.nextInt();
                    System.out.println();

                    ArrayList<String> lineas = new ArrayList<>();

                    try (BufferedReader br = new BufferedReader(new FileReader(fichero))) {
                        String linea;
                        while ((linea = br.readLine()) != null) lineas.add(linea);
                    } catch (IOException e) {}

                    for (String linea : lineas) System.out.println(linea);

                    String[] lineaEjemplo;

                    for (int usuario = 1 ; usuario < lineas.size() ; usuario++) {
                        int numConcor = 0;
                        lineaEjemplo = lineas.getFirst().split(" ");

                        String[] lineaAComparar = lineas.get(usuario).split(" ");

                        ArrayList<String> comunes = new ArrayList<>();
                        for (String usuario1 : lineaEjemplo) {
                            for (String usuario2 : lineaAComparar) {
                                if (usuario1.equals(usuario2)) {
                                    comunes.add(usuario1);
                                    numConcor++;
                                }
                            }
                        }

                        if (numConcor >= numConcorMin) {
                            Collections.sort(comunes);

                            try (FileWriter fw = new FileWriter(CONCORDANCIAS_TXT, true)) {
                                fw.write(String.format("%s %s ", lineaEjemplo[0], lineaAComparar[0]));
                                for (String aficion : comunes) fw.write(aficion + " ");
                                fw.write("\n");
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                }

                case 5 -> System.out.println("Saliendo....");
            }
        }
    }

    private static void leerUsuarios(File fichero) {
        try (BufferedReader br = new BufferedReader(new FileReader(fichero))) {
            String linea;

            System.out.println("Lista de usuarios:\n");
            while ((linea = br.readLine()) != null) {
                System.out.println(linea);
                System.out.println("-------------------------------");
            }

            System.out.println("\n\n");
        } catch (EOFException e) {
            System.out.println("Fin del fichero");
        } catch (IOException e) {
            System.out.println("Error al leer el fichero");
            throw new RuntimeException(e);
        }
    }

    private static void aniadirUsuario(Scanner entrada, String ruta, File fichero) {
        System.out.print("Indica las aficiones del usuario separado por comas: ");
        entrada.nextLine();
        String aficiones = entrada.nextLine().toUpperCase();

        String[] aficionesArray = aficiones.split(",");
        GenerarCodUsuario genCod = new GenerarCodUsuario(ruta);

        Usuario usuario = new Usuario(genCod.getCodigo(), aficionesArray);

        try (FileWriter fw = new FileWriter(fichero,  true)) {
            fw.write(usuario + "\n");

            System.out.println("Usuario guardada correctamente");
        } catch (IOException e) {
            throw new RuntimeException(e);
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
