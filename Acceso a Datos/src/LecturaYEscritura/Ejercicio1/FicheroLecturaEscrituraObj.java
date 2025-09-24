package LecturaYEscritura.Ejercicio1;

import java.io.*;
import java.util.Scanner;

public class FicheroLecturaEscrituraObj {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);

        char opcion = ' ';

        while (opcion  != 'q') {
            System.out.println("------ MENU ------");
            System.out.println("C. Crear persona");
            System.out.println("L. Leer personas");
            System.out.println("Q. Salir");
            System.out.println("------------------");

            System.out.print("Ingrese opcion: ");
            opcion = entrada.next().toUpperCase().charAt(0);
            System.out.println();

            switch (opcion) {
                case 'C'-> crearPersona(entrada);
                case 'L'-> leerPersonas();
                case 'Q' -> System.out.println("Saliendo.....");
                default -> System.out.println("Opción inválida");
            }
        }
    }

    private static void crearPersona(Scanner entrada) {
        System.out.print("Nombre: ");
        entrada.nextLine();
        String nombre = entrada.nextLine();
        System.out.println();

        System.out.print("Edad: ");
        int edad = entrada.nextInt();
        System.out.println();

        Persona persona = new Persona(edad, nombre);

        try {
            File fichero = new File("src/LecturaYEscritura/Ejercicio1/personas.dat");
            FileOutputStream fos = new FileOutputStream(fichero, true);

            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(persona);
            oos.close();
            fos.close();
            System.out.println("Persona guardada correctamente");

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void leerPersonas() {
        File fichero = new File("src/LecturaYEscritura/Ejercicio1/personas.dat");

        if (!fichero.exists()) System.out.println("No existe el fichero.");

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fichero))) {
            while (true) {
                Persona persona = (Persona) ois.readObject();
                System.out.println(persona);
            }

        } catch (EOFException e) {
            System.out.println("Fin del fichero");
            System.out.println();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}

// crear con la clase sobrante hay que indicar que no meta cabezeras