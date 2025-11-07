package Ejercicios.E004carrera_100m;

public class Carrera {
    private boolean salidaDada = false;

    public void esperarSalida() {
        synchronized (this) {
            while (!salidaDada) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void darSalida() {
        synchronized (this) {
            System.out.println("Preparados");
            try { Thread.sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); }

            System.out.println("Listos");
            try { Thread.sleep(1000); } catch (InterruptedException e) { e.printStackTrace();}

            System.out.println("YA!");
            salidaDada = true;
            notifyAll();
        }
    }

    public void notificarLlegada(int dorsal, int tiempo) {
        synchronized (this) {
            System.out.printf("Atleta %d ha llegado en %d.%d segundos\n", dorsal, tiempo / 1000, tiempo % 1000); // Segundos y milisegundos del atleta
        }
    }
}
