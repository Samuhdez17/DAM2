package programacion_hilos;

import static java.lang.Thread.MAX_PRIORITY;
import static java.lang.Thread.MIN_PRIORITY;

public class Main {
    public static void main(String[] args) {
        HiloA hiloA = new HiloA();
        HiloB hiloB = new HiloB();

        hiloA.setPriority(MIN_PRIORITY);
        hiloB.setPriority(MAX_PRIORITY);
        hiloA.start();
        hiloB.start();
    }
}
