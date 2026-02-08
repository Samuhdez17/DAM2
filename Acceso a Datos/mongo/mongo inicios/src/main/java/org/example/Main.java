package org.example;

import org.example.DAO.AmigoDAO;
import org.example.model.Amigos;
import org.example.model.Estudios;

import java.util.Scanner;

public class Main {
    public static final String MENU = """
                    ===== MENÚ DE AMIGOS ====="
                    "1. Agregar amigo"
                    "2. Listar amigos"
                    "3. Actualizar amigo"
                    "4. Eliminar amigo"
                    "0. Salir"
                    "=========================="
                    "Seleccione una opción: """;

    static void main() {
        Scanner entrada = new Scanner(System.in);
        AmigoDAO amigos = new AmigoDAO();

        int opcion = -1;
        while (opcion != 0) {
            System.out.println(MENU);
            opcion = entrada.nextInt();
            System.out.println();

            switch (opcion) {
                case 1 -> agregarAmigo(entrada, amigos);

                case 2 -> amigos.listar();

                case 3 -> actualizarAmigo(entrada, amigos);

                case 4 -> eliminarAmigo(entrada, amigos);

                case 0 -> System.out.println("Saliendo....");

                default -> System.out.println("ERROR. Opcion invalida");
            }
        }
    }

    private static void actualizarAmigo(Scanner entrada, AmigoDAO amigos) {
        System.out.println("Nombre a eliminar:");
        String nombre = entrada.nextLine();

        System.out.println("Nueva edad:");
        int nuevaEdad = entrada.nextInt();

        amigos.actualizar(nombre, nuevaEdad);
    }

    private static void eliminarAmigo(Scanner entrada, AmigoDAO amigos) {
        System.out.println("Nombre a eliminar:");
        String nombre = entrada.nextLine();

        amigos.eliminar(nombre);
    }

    private static void agregarAmigo(Scanner entrada, AmigoDAO amigos) {
        System.out.println("Nombre:");
        entrada.nextLine();
        String nombre = entrada.nextLine();

        System.out.println("Edad:");
        int edad = entrada.nextInt();

        System.out.println("Num hobbies:");
        int numHobbies = entrada.nextInt();
        String[] hobbies = new String[numHobbies];
        entrada.nextLine();

        for (int i = 0; i < numHobbies; i++) {
            System.out.println("Hobbie:");
            hobbies[i] = entrada.nextLine();
        }

        System.out.println("Num telefonos:");
        int numTelfs = entrada.nextInt();
        int[] telfs = new int[numTelfs];
        entrada.nextLine();

        for (int i = 0; i < numTelfs; i++) {
            System.out.println("Telefono:");
            telfs[i] = entrada.nextInt();
        }

        System.out.println("Num estudios:");
        int numEstudios = entrada.nextInt();
        Estudios[] estudios = new Estudios[numEstudios];
        entrada.nextLine();

        for (int i = 0; i < numEstudios; i++) {
            System.out.println("Titulo:");
            String titulo = entrada.nextLine();
            System.out.println();

            System.out.println("Centro:");
            String centro = entrada.nextLine();
            System.out.println();

            System.out.println("Año:");
            int anio = entrada.nextInt();
            System.out.println();

            estudios[i] = new Estudios(titulo, centro, anio);
        }

        amigos.insertar(new Amigos(nombre, edad, hobbies, telfs, estudios));
    }
}
