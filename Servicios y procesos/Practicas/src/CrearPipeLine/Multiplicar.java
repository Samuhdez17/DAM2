package CrearPipeLine;

import java.util.Scanner;

public class Multiplicar {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);

        int numero = entrada.nextInt();

        numero *= 2;

        System.out.println(numero);
    }
}
