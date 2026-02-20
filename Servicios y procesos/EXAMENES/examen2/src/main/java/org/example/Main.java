package org.example;

import java.util.Scanner;

public class Main {
    static String abcd  = " abcdefghijklmnopqrstuvwxyz";
    static String trans = " klmnopqrstuvwxyzabcdefghij";

    static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);

        String comando = entrada.next().toLowerCase();
        entrada.nextLine();

        String mensaje = entrada.nextLine().toLowerCase();

//        if (args.length != 2) NO RECUERDO COMO HACER QUE ESCRIBA POR CMD
//            System.out.println("Parametros incorrectos");

//        else {
            switch (comando) {
                case "cifrar" -> System.out.println(cifrarMensaje(mensaje));
                case "descifrar" -> System.out.println(descifrarMensaje(mensaje));
                default ->  System.out.println("Orden no encontrada");
            }
//        }
    }

    static String cifrarMensaje(String mensaje) {
        StringBuilder msgFinal = new StringBuilder();

        /*
        Recorremos el mensaje, si la letra del mensaje coincide con la posicion de la constante 'abcd'
        agregamos al StringBuilder la letra de la posicion de la constante 'trans', ya que contiene el cambio
        en base a las posiciones
         */
        for (int i = 0 ; i < mensaje.length() ; i++) {
            for (int j = 0 ; j < abcd.length() ; j++) {
                if (mensaje.charAt(i) == abcd.charAt(j)) {
                    msgFinal.append(trans.charAt(j));
                    break;
                }
            }
        }

        return msgFinal.toString();
    }

    static String descifrarMensaje(String mensaje) {
        StringBuilder msgFinal = new StringBuilder();

        /*
        Recorremos el mensaje, si la letra del mensaje coincide con la posicion de la constante 'trans'
        agregamos al StringBuilder la letra de la posicion de la constante 'abcd', ya que contiene el cambio
        en base a las posiciones
         */

        for (int i = 0 ; i < mensaje.length() ; i++) {
            for (int j = 0 ; j < trans.length() ; j++) {
                if (mensaje.charAt(i) == trans.charAt(j)) {
                    msgFinal.append(abcd.charAt(j));
                    break;
                }
            }
        }

        return msgFinal.toString();
    }
}
