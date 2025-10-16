package ejercicio_sumar_pares_impares;

public class HiloImpares extends Thread {
    private final HiloPares hiloPares;
    private int numImparTotal = 0;

    public HiloImpares(HiloPares hiloPares) {
        super();
        this.hiloPares = hiloPares;
    }

    public int getNumeroParTotal() {
        return numImparTotal;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 100; i++) {
            if (i % 2 != 0) {
                System.out.println("Hilo Impares:   " + i);
                numImparTotal += i;
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
            }
        }

        System.out.printf("Suma total: %d\n", (this.numImparTotal + hiloPares.getNumeroParTotal()));
    }
}
