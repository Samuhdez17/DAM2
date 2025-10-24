package Ejercicios.E04carrera_100m;

public class Atleta implements Runnable {
    private final int dorsal;
    private final Carrera carrera;

    public Atleta(int dorsal, Carrera carrera) {
        this.dorsal = dorsal;
        this.carrera = carrera;
    }

    @Override
    public void run() {
        try {
            carrera.esperarSalida();

            Thread.sleep(8000 + (int) (Math.random() * 2000));

            carrera.notificarLlegada(dorsal);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Atleta " + dorsal + " fue interrumpido.");
        }
    }
}
