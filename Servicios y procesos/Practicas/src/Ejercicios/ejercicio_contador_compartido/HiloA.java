package Ejercicios.ejercicio_contador_compartido;

public class HiloA extends Thread {
    private Contador contador;

    public HiloA(Contador contador) {
        super();
        this.contador = contador;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            contador.aumentar();
            try {
                sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        System.out.println("Valor contador: " + contador.getContador());
    }

}
