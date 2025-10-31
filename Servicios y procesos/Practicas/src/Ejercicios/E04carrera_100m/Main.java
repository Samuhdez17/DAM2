package Ejercicios.E04carrera_100m;

import java.util.Random;

public class Main {
    private static final Random RANDOM = new Random();

    public static void main(String[] args) {
        Carrera carrera = new Carrera();
        Thread[] atletas = new Thread[8];
        int[] dorsales = new int[atletas.length];
        asignarDorsales(dorsales);

        for (int i = 0; i < atletas.length; i++) {
            atletas[i] = new Thread(new Atleta(dorsales[i], carrera));
            atletas[i].start();
        }

        carrera.darSalida();

        try {
            for (Thread atleta : atletas) atleta.join();
            System.out.println("Fin de la carrera");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static void asignarDorsales(int[] dorsales) {
        for (int i = 0; i < dorsales.length; i++) {
            int dorsalFinal = (RANDOM.nextInt(50) + 150);

            while (dorsalFinal == dorsales[i]) {
                dorsalFinal = (RANDOM.nextInt(50) + 150);
            }

            dorsales[i] = dorsalFinal;
        }
    }
}
