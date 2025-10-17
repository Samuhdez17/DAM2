package ejemplo_compartir_memoria;

public class Main {
    public static void main(String[] args) throws InterruptedException {
//        Hilo hilo1 = new Hilo();
//        Hilo hilo2 = new Hilo();
//
//        hilo1.start();
//        hilo2.start();


        MiRunnable miRunnable = new MiRunnable();
        Thread hilo3 = new Thread(miRunnable);
        Thread hilo4 = new Thread(miRunnable);

        hilo3.setPriority(Thread.MAX_PRIORITY);

        hilo3.start();
        hilo4.start();
        hilo4.join();
    }
}
