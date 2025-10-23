package Ejercicios.E04carrera_100m;

import java.util.Random;

public class Main {
    private static final Random RANDOM = new Random();
    public static void main(String[] args)  {
        Carrera carrera = new Carrera();
        Thread[] atletas = {
                new Thread(carrera, "A"),
                new Thread(carrera, "A"),
                new Thread(carrera, "A"),
                new Thread(carrera, "A"),
                new Thread(carrera, "A"),
                new Thread(carrera, "A"),
                new Thread(carrera, "A"),
                new Thread(carrera, "A")
        };
        asignarDorsales(atletas);
        int comienzoPrograma = (int) System.currentTimeMillis() / 1000;

        for (int i = 0; i < 10; i++) {
            int tiempoEnPrograma = ((int) System.currentTimeMillis() / 1000 - comienzoPrograma);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
        }
    }

    private static void asignarDorsales(Thread[] atletas) {
        for (Thread atleta : atletas) {
            int dorsal = (RANDOM.nextInt(50) + 50);

            while (atleta.getName().compareTo(String.valueOf(dorsal)) == 0) {
                dorsal = (RANDOM.nextInt(50) + 50);
            }

            atleta.setName(String.valueOf(dorsal));
        }
    }
}
