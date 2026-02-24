package org.example;

import org.example.DAO.AmigosDAO;
import org.example.model.Amigo;
import org.example.model.Estudio;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static final String MENU = """
                    ===== MENÚ DE AMIGOS =====
                    1. Agregar amigo
                    2. Listar amigos
                    3. Actualizar amigo
                    4. Eliminar amigo
                    0. Salir
                    ==========================
                    Seleccione una opción:""";

    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        AmigosDAO amigos = new AmigosDAO();

        int opcion = -1;
        while (opcion != 0) {
            System.out.println(MENU);
            opcion = entrada.nextInt();

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

    private static void actualizarAmigo(Scanner entrada, AmigosDAO amigos) {
        System.out.println("Nombre a actualizar:");
        entrada.nextLine();
        String nombre = entrada.nextLine();

        System.out.println("Nueva edad:");
        int nuevaEdad = entrada.nextInt();
        entrada.nextLine();

        amigos.actualizar(nombre, nuevaEdad);
        System.out.println("Amigo actualizado");
    }

    private static void eliminarAmigo(Scanner entrada, AmigosDAO amigos) {
        System.out.println("Nombre a eliminar:");
        entrada.nextLine();
        String nombre = entrada.nextLine();

        amigos.eliminar(nombre);
    }

    private static void agregarAmigo(Scanner entrada, AmigosDAO amigos) {
        System.out.println("Nombre:");
        entrada.nextLine();
        String nombre = entrada.nextLine();

        System.out.println("Edad:");
        int edad = entrada.nextInt();
        entrada.nextLine();

        System.out.println("Num hobbies:");
        int numHobbies = entrada.nextInt();
        entrada.nextLine();
        ArrayList<String> hobbies = new ArrayList<>();

        for (int i = 0; i < numHobbies; i++) {
            System.out.println("Hobbie " + (i + 1) + ":");
            hobbies.add(entrada.nextLine());
        }

        System.out.println("Num telefonos:");
        int numTelfs = entrada.nextInt();
        entrada.nextLine();
        ArrayList<String> telfs = new ArrayList<>();

        for (int i = 0; i < numTelfs; i++) {
            System.out.println("Telefono " + (i + 1) + ":");
            telfs.add(entrada.nextLine());
        }

        System.out.println("Num estudios:");
        int numEstudios = entrada.nextInt();
        entrada.nextLine();
        ArrayList<Estudio> estudios = new ArrayList<>();

        for (int i = 0; i < numEstudios; i++) {
            System.out.println("Estudio " + (i + 1) + ":");

            System.out.println("Titulo:");
            String titulo = entrada.nextLine();

            System.out.println("Centro:");
            String centro = entrada.nextLine();

            System.out.println("Año:");
            int anio = entrada.nextInt();
            entrada.nextLine();

            estudios.add(new Estudio(titulo, centro, anio));
        }

        amigos.insertar(new Amigo(nombre, edad, hobbies, telfs, estudios));
        System.out.println("Amigo agregado");
    }
}