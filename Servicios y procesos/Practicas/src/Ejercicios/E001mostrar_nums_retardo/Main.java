package Ejercicios.E001mostrar_nums_retardo;

public class Main {
    public static void main(String[] args) {
        HiloA hiloA = new HiloA();
        HiloB hiloB = new HiloB();

        hiloA.start();
        hiloB.start();
    }
}
