package Ejercicios.E002sumar_pares_impares;

import static java.lang.Thread.MAX_PRIORITY;
import static java.lang.Thread.MIN_PRIORITY;

public class Main {
    public static void main(String[] args) {
        HiloPares hiloPares = new HiloPares();
        HiloImpares hiloImpares = new HiloImpares();

        hiloPares.setPriority(MIN_PRIORITY);
        hiloImpares.setPriority(MAX_PRIORITY);
        hiloPares.start();
        hiloImpares.start();
    }
}
