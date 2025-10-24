package Ejercicios.E04carrera_100m;

public class Carrera {
    private boolean salidaDada = false;
    private int inicioCarrera;

    public synchronized void esperarSalida() {
        while (!salidaDada) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void darSalida() {
        System.out.println("Preparados...");
        try { Thread.sleep(1000); } catch (InterruptedException e) {}

        System.out.println("Listos...");
        try { Thread.sleep(1000); } catch (InterruptedException e) {}

        System.out.println("¡YA!");
        salidaDada = true;
        inicioCarrera = (int) (System.currentTimeMillis() / 1000);
        notifyAll();
    }

    public synchronized void notificarLlegada(int dorsal) {
        int tiempo = (int) (System.currentTimeMillis() / 1000) - inicioCarrera;
        System.out.println("Atleta " + dorsal + " tarda " + tiempo);
    }
}
