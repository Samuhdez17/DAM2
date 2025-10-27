package Ejercicios.E05productor_consumidor;

public class Productor implements Runnable {
    String[] textos = {"mensaje 1", "mensaje 2", "mensaje 3", "mensaje 4", "mensaje 5"};
    private final ColaMensajes colaMensajes;
    private final int id;

    public Productor(ColaMensajes colaMensajes, int id) {
        this.colaMensajes = colaMensajes;
        this.id = id;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < textos.length; i++) {
                Mensaje mensaje = new Mensaje((i + id * 500), textos[i]);
                colaMensajes.agregarEnCola(mensaje);
                System.out.printf("Productor %d creó mensaje %d\n", id, mensaje.getId());
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
