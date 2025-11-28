package PruebasIniciales;
import java.util.Scanner;

public class Ponermiles {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);

        System.out.print("Introduce un numero: ");
        Integer nuevoNumero = entrada.nextInt();
        StringBuilder numeroString = new StringBuilder(Integer.toString(nuevoNumero));

        if (nuevoNumero > 999) {
            numeroString.reverse();
            StringBuilder resultado = new StringBuilder();

            int contador = 1;

            for (int i = 0 ; i < numeroString.length() ; i++) {
                resultado.append(numeroString.charAt(i));
                if (contador % 3 == 0 && i != numeroString.length() - 1) resultado.append(".");
                contador++;
            }

            resultado.reverse();
            System.out.println(resultado);

        } else System.out.println(nuevoNumero);
    }
}
