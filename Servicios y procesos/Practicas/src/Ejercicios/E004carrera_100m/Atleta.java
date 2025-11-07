package Ejercicios.E004carrera_100m;

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

            int tiempo = random.nextInt(2000) + 9000;
            Thread.sleep(tiempo);

            carrera.notificarLlegada(dorsal, tiempo);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Atleta " + dorsal + " fue interrumpido.");
        }
    }
}
