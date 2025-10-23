package ejemplo_compartir_memoria;

public class Hilo extends Thread {
    private int id;
    private int contador = 0;

    public Hilo(int id) {
        super();
        this.id = id;
    }

    public void run() {
        for (int i = 0; i < 10; i++) {
            contador++;
        }

        System.out.printf("Hilo %d: %d\n", id, contador);
    }
}
