package ejercicio_mostrar_nums_retardo;

public class HiloB extends Thread {
    public HiloB() {
        super();
    }

    @Override
    public void run() {
        for (int i = 65; i <= 90; i++) {
            System.out.printf("Hilo B: %c\n", i);
            try {
                Thread.sleep(600);
            } catch (InterruptedException e) {
            }
        }
        System.out.println("Hilo B terminado.");
    }
}
