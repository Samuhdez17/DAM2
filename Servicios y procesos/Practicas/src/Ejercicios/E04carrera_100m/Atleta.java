package Ejercicios.E04carrera_100m;

import java.util.Random;

public class Atleta implements Runnable {
    private final int dorsal;
    private final Carrera carrera;
    private final Random random = new Random();

    public Atleta(int dorsal, Carrera carrera) {
        this.dorsal = dorsal;
        this.carrera = carrera;
    }

    @Override
    public void run() {
        try {
            carrera.esperarSalida();

            Thread.sleep(random.nextInt(3000) + 8000);

            carrera.notificarLlegada(dorsal);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Atleta " + dorsal + " fue interrumpido.");
        }
    }
}
