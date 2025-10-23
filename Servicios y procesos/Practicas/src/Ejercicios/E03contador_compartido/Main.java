package Ejercicios.E03contador_compartido;

public class Main {
    public static void main(String[] args) {
        Contador contador = new Contador();
        Thread hiloA = new Thread(contador);
        Thread hiloB = new Thread(contador);

        hiloA.setPriority(Thread.MAX_PRIORITY);
        hiloB.setPriority(Thread.MIN_PRIORITY);

        hiloA.start();
        hiloB.start();
    }
}
