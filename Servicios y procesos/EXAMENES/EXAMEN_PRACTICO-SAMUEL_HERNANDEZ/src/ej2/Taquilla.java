package ej2;

public class Taquilla extends Thread {
    private int idTaquilla;
    private int entradasVendidas = 0;
    private Entradas entradas;

    public Taquilla(Entradas entradas, int idTaquilla) {
        this.entradas = entradas;
        this.idTaquilla = idTaquilla;
    }

    @Override
    public void run() {
        while (entradas.hayMas()) {
            if (entradas.hayMas()) {
                if (idTaquilla == 0 || idTaquilla == 1) {
                    synchronized (this) {
                        entradas.restar();
                        entradasVendidas++;
                    }
                } else {
                    entradas.restar();
                    entradasVendidas++;
                }
            }

            try {
                sleep(2);
            } catch (InterruptedException e) {}
        }

        System.out.printf("Taquilla numero %d, entradas vendidas -> %d\n",  idTaquilla, entradasVendidas);
    }
}
