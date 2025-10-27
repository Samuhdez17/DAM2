package Ejercicios.E05productor_consumidor2;

public class Productor implements  Runnable {
    private final Cola cola;
    private final int id;

    public Productor(Cola cola, int id) {
        this.cola = cola;
        this.id = id;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            int idMensaje = i + (id * 10);
            Mensaje mensaje = new Mensaje(idMensaje, String.format("[P] Productor %d genera mensaje %d", id, idMensaje));

            try {
                cola.aniadirACola(mensaje);
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

}
