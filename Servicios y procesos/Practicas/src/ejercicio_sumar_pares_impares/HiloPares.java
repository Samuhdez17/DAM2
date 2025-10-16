package ejercicio_sumar_pares_impares;

public class HiloPares extends Thread {
    private int numParTotal = 0;

    public HiloPares() {
        super();
    }

    public int getNumeroParTotal() {
        return numParTotal;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 100; i++) {
            if (i % 2 == 0) {
                System.out.println("Hilo Pares: " + i);
                numParTotal += i;
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
            }
        }


    }
}
