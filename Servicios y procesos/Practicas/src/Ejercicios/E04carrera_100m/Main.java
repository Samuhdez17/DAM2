package Ejercicios.E04carrera_100m;

public class Main {
    public static void main(String[] args)  {
        int comienzoPrograma = (int) System.currentTimeMillis() / 1000;

        for (int i = 0; i < 10; i++) {
            int tiempoEnPrograma = ((int) System.currentTimeMillis() / 1000 - comienzoPrograma);

            System.out.println(tiempoEnPrograma);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
        }
    }
}
