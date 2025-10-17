package Ejercicios.ejercicio_contador_compartido;

public class Main {
    public static void main(String[] args) {
        Contador contador = new Contador();
        HiloA hiloA = new HiloA(contador);
        HiloB hiloB = new HiloB(contador);

        hiloA.setPriority(Thread.MAX_PRIORITY);
        hiloB.setPriority(Thread.MIN_PRIORITY);

        hiloA.start();
        hiloB.start();
    }
}
