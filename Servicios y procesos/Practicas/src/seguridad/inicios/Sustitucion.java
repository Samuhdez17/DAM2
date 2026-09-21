package seguridad.inicios;

import java.util.Scanner;

public class Sustitucion {
    static String abc   = "abcdefghijklmnopqrstuvwxyz ";
    static String trans = "klnmopqrstuvwxyz0123456789 ";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println(trans.length());

        System.out.println("comando:");
        String comando = sc.next();

        sc.nextLine();
        System.out.println("mensaje:");
        String mensaje = sc.nextLine();

        System.out.println("Resultado " + (comando.equals("cifrar") ? cifrar(mensaje) : descifrar(mensaje)));
    }

    private static String cifrar(String mensaje) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0 ; i < mensaje.length() ; i++) {
            for (int j = 0 ; j < abc.length() ; j++) {
                if (mensaje.charAt(i) == abc.charAt(j))
                    sb.append(trans.charAt(j));
            }
        }

        return sb.toString();
    }

    private static String descifrar(String mensaje) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < mensaje.length(); i++) {
            for (int j = 0; j < trans.length(); j++) {
                if (mensaje.charAt(i) == trans.charAt(j))
                    sb.append(abc.charAt(j));
            }
        }

        return sb.toString();
    }
}
