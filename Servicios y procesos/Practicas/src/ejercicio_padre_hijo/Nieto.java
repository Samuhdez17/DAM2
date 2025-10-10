package ejercicio_padre_hijo;

import java.util.Scanner;

public class Nieto {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        String numeroHijo = entrada.nextLine();

        int numero = Integer.parseInt(numeroHijo);

        System.out.println(numero + 10);
        System.out.flush();
        entrada.close();
    }
}
