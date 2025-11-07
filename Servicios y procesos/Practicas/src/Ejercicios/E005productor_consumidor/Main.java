package Ejercicios.E005productor_consumidor;

public class Main {
    public static void main(String[] args) {
        Cola cola = new Cola();

        for (int i = 1 ; i <= 2 ; i++) {
            new Thread(new Productor(cola, i)).start();
        }

        for (int i = 1 ; i <= 3 ; i++) {
            new Thread(new Consumidor(cola, i)).start();
        }
    }
}
