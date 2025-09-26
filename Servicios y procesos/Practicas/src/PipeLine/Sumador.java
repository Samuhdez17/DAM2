package PipeLine;

import java.util.Scanner;

public class Sumador {

    public static void main(String[] args) {

        Scanner entrada = new Scanner(System.in);

        int numero = entrada.nextInt();

        numero += numero;

        System.out.println(numero);
    }
}
