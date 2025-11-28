package PruebasIniciales;
import java.util.Scanner;

public class DividirFrasePorSeparador {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);

        System.out.print("Introduce una cadena: ");
        String nuevaCadena = entrada.nextLine();
        System.out.println();

        String[] cadenaArray = nuevaCadena.split(" ");
        for (String palabra : cadenaArray) System.out.println(palabra);
    }
}
