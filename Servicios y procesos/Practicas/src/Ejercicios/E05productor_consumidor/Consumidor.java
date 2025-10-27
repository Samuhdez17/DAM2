package Ejercicios.E05productor_consumidor;

public class Consumidor implements  Runnable {
    private final ColaMensajes colaMensajes;
    private final int id;

    public Consumidor(ColaMensajes colaMensajes, int id) {
        this.colaMensajes = colaMensajes;
        this.id = id;
    }

    @Override
    public void run() {
        try {
            int i = 0;
            while (i < 5) {
                Mensaje mensaje = colaMensajes.getMensaje();
                if (mensaje.getEstado().equals("Listo")) {
                    System.out.printf("Consumidor %d consumió mensaje %d: %s\n", id, mensaje.getId(), mensaje.getDescripcion());
                    mensaje.setEstado("Consumido");
                }
                Thread.sleep(200);
                i++;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
