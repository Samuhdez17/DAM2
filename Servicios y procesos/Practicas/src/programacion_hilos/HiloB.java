package programacion_hilos;

public class HiloB extends Thread {
    public HiloB() {
        super();
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("Hilo B: " + i);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
            }
        }
        System.out.println("Hilo B terminado.");
    }
}
