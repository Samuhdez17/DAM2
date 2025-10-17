package Ejercicios.ejercicio_mostrar_nums_retardo;

public class HiloA extends Thread {
    public HiloA() {
        super();
    }

    @Override
    public void run() {
        for (int i = 1; i <= 20; i++) {
            System.out.println("Hilo A: " + i);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
            }
        }
        System.out.println("Hilo A terminado.");
    }
}
