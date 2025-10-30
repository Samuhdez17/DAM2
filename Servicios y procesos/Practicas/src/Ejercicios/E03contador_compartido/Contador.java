package Ejercicios.E03contador_compartido;

public class Contador implements Runnable {
    private int contador = 0;

    public Contador() {
        super();
    }

    public void run() {
        for (int i = 0; i < 1000; i++) {
            contador++;
//            try {
//                Thread.sleep(10);
//                System.out.printf("Hilo %c: valor contador -> %d\n", Thread.currentThread().getName().charAt(7), contador);
//            } catch (InterruptedException e) {
//
//            }
    }
        System.out.printf("Hilo %c: valor contador final -> %d\n", Thread.currentThread().getName().charAt(7), contador);
    }
}
