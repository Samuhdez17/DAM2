package Ejercicios.E02sumar_pares_impares;

public class HiloPares extends Thread {
    private int numParTotal = 0;

    public HiloPares() {
        super();
    }

    @Override
    public void run() {
        for (int i = 1; i <= 100; i++) {
            if (i % 2 == 0) {
                System.out.println("Hilo Pares: " + i);
                numParTotal += i;
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
            }
        }

        System.out.println("La suma de los números pares es: " + numParTotal);
    }
}
