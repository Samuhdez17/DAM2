package Ejercicios.E02sumar_pares_impares;

public class HiloImpares extends Thread {
    private int numImparTotal = 0;

    public HiloImpares() {
        super();
    }

    @Override
    public void run() {
        for (int i = 1; i <= 100; i++) {
            if (i % 2 != 0) {
                System.out.println("Hilo Impares:   " + i);
                numImparTotal += i;
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
            }
        }

        System.out.println("La suma de los números impares es: " + numImparTotal);
    }
}
