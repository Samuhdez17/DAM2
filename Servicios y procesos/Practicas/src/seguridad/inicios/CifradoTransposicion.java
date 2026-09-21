package seguridad.inicios;

import java.util.Scanner;

public class CifradoTransposicion {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Comando: ");
        String comando = sc.next();
        System.out.println();

        System.out.print("Columnas: ");
        int columnas = sc.nextInt();
        System.out.println();

        System.out.print("Mensaje: ");
        String mensaje = sc.nextLine();
        sc.next();
        System.out.println();

        int filas = mensaje.length() / columnas;
        if (filas % columnas > 0) {
            filas++;
        }

        char[][] caracteres = new char[filas][columnas];

        switch (comando) {
            case "cifrar" -> {
                String mensajeCifrado = cifrar(caracteres, mensaje.replaceAll("\s", ""));
                System.out.println(mensajeCifrado);
            }

            case "descifrar" -> {
                String mensajeDescifrado = descifrar(caracteres, mensaje.replaceAll("\s", ""));
                System.out.println(mensajeDescifrado);
            }

            default -> {
            }
        }

    }

    public static String cifrar(char[][] caracteres, String mensaje) {
        int indice = 0;

        // Rellenamos la matriz con las letras del mensaje. De arriba a abajo y de izquierda a derecha
        for (int fila = 0; fila < caracteres.length; fila++) {
            for (int columna = 0; columna < caracteres[0].length; columna++) {
                if (indice < mensaje.length())
                    caracteres[fila][columna] = mensaje.charAt(indice);
                indice++;
            }
        }

        // Recorremos la matriz para construir el mensaje cifrado. De izquierda a derecha y de arriba a abajo
        StringBuilder sb = new StringBuilder();
        for (int columna = 0; columna < caracteres[0].length; columna++) {
            for (int fila = 0; fila < caracteres.length; fila++) {
                if (caracteres[fila][columna] != (char) 0)
                    sb.append(caracteres[fila][columna]);
            }
        }

        return sb.toString();
    }

    public static String descifrar(char[][] caracteres, String mensaje) {
        int indice = 0;
        int numColumnasConTodasLasFilasRellenas = mensaje.length() % caracteres[0].length;

        if (numColumnasConTodasLasFilasRellenas == 0)
            numColumnasConTodasLasFilasRellenas = caracteres[0].length;
        
        for (int columna = 0; columna < caracteres[0].length; columna++) {
            for (int fila = 0; fila < caracteres.length; fila++) {
                if (columna < numColumnasConTodasLasFilasRellenas || fila < caracteres.length - 1) {
                    if (indice < mensaje.length()) {
                        caracteres[fila][columna] = mensaje.charAt(indice);
                    }
                    indice++;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int fila = 0; fila < caracteres.length; fila++) {
            for (int columna = 0; columna < caracteres[0].length; columna++) {
                if (caracteres[fila][columna] != (char) 0)
                    sb.append(caracteres[fila][columna]);
            }
        }

        return sb.toString();
    }
}
