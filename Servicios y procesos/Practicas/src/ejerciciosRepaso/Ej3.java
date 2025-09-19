package ejerciciosRepaso;

import java.util.ArrayList;
import java.util.Collections;

public class Ej3 {
    public static void main(String[] args) {
        int[] lista = {1, 1, 4, 10, 11, 10, 5, 1, 4, 6, 5};
        ArrayList<Integer> numerosRepes = new ArrayList<>();
        ArrayList<Integer> resultado = new ArrayList<>();

        int contador;
        for (int num1 : lista) {
            contador = 0;

            for (int num2 : lista) {
                if (num1 == num2) contador++;
            }

            if (contador > 1) {
                if (!(numerosRepes.contains(num1))) numerosRepes.add(num1);

            } else resultado.add(num1);
        }

        for (int num : numerosRepes) {
            resultado.add(num);
        }

        Collections.sort(resultado);

        for (int numero : resultado) {
            System.out.printf("%d ", numero);
        }
    }
}
