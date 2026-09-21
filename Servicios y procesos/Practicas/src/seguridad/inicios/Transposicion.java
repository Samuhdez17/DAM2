package seguridad.inicios;

import java.util.Scanner;

public class Transposicion {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("comando:");
        String comando = sc.next();

        System.out.println("columnas:");
        int columnas = sc.nextInt();
        sc.nextLine();

        System.out.println("mensaje:");
        String mensaje = sc.nextLine();
        
        int filas = (int) Math.ceil((double) mensaje.length() / columnas);

        char[][] mapa = new char[filas][columnas];

        System.out.println("Resultado: " + (comando.equals("cifrar") ? cifrar(mensaje, mapa) : descifrar(mensaje, mapa)));
    }

    private static String cifrar(String mensaje, char[][] mapa) {
        int letra = 0;

        for (int fila = 0 ; fila < mapa.length ; fila++) {
            for (int col = 0; col < mapa[0].length; col++) {
                if (letra < mensaje.length())
                    mapa[fila][col] = mensaje.charAt(letra);

                letra++;
            }
        }

        StringBuilder sb = new StringBuilder();

        for (int col = 0; col < mapa[0].length; col++) {
            for (int fila = 0; fila < mapa.length; fila++) {
                sb.append(mapa[fila][col]);
            }
        }

        return sb.toString();
    }

    private static String descifrar(String mensaje, char[][] mapa) {
        int letra = 0;
        int numColumnasConTodasLasFilasRellenas = mensaje.length() % mapa[0].length;

        if (numColumnasConTodasLasFilasRellenas == 0)
            numColumnasConTodasLasFilasRellenas = mapa[0].length;

        for (int col = 0; col < mapa[0].length; col++) {
            for (int fila = 0; fila < mapa.length; fila++) {
                if (col < numColumnasConTodasLasFilasRellenas || fila < mapa.length - 1) {
                    if (letra < mensaje.length())
                        mapa[fila][col] = mensaje.charAt(letra);

                    letra++;
                }
            }
        }

        StringBuilder sb = new StringBuilder();

        for (int fila = 0; fila < mapa.length; fila++) {
            for (int col = 0; col < mapa[0].length; col++) {
                sb.append(mapa[fila][col]);
            }
        }

        return sb.toString();
    }
}
