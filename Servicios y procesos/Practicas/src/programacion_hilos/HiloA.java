package programacion_hilos;

public class HiloA extends Thread {
    public HiloA() {
        super();
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("Hilo A: " + i);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
            }
        }
        System.out.println("Hilo A terminado.");
    }
}
