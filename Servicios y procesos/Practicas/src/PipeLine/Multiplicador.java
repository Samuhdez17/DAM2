package PipeLine;

import java.util.Scanner;

public class Multiplicador {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);

        int numero = entrada.nextInt();

        numero *= 3;

        System.out.println(numero);
    }
}
