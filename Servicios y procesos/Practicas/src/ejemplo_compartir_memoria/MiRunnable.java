package ejemplo_compartir_memoria;

public class MiRunnable implements Runnable {
    private int contador;

    public  MiRunnable() {
        this.contador = 0;
    }

    public void run() {
        for (int i = 0; i < 10; i++) {
            contador += 10;
        }

        System.out.printf("Contador hilo %s: %d\n", Thread.currentThread().getName().substring(7), contador);
    }
}
