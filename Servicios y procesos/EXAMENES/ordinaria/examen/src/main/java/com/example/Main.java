package com.example;

public class Main {
    static String abc   = "abcdefghijklmnopqrstuvwxyz "; // |
    static String trans = "klmnopqrstuvwxyz1234567890 "; // |_> Strings de referencia para saber que valor tiene cada letra del abecedario, tanto para cifrar como para descifrar

    public static void main(String[] args) {
        // Se comprueba que se tienen 2 parametros (comando "mensaje") para poder realizar la función
        if (args.length != 2) {
            System.out.println("ERROR. Numero de argumentos no validos, indique comando (cifrar / descifrar) y mensaje");
            return;
        }

        // Al pedir cifrado, al usuario se le muestra el contenido del mensaje cifrado
        if (args[0].equalsIgnoreCase("cifrar")) {
            System.out.println("Mensaje cifrado: " + cifrar(args[1].toLowerCase()));
        }

        // Al pedir descifrado, al usuario se le muestra el contenido del mensaje descifrado
        if (args[0].equalsIgnoreCase("descifrar")) {
            System.out.println("Mensaje descifrado: " + descifrar(args[1].toLowerCase()));
        }
    }

    /*
     Aqui tanto el cifrado como el descifrado realizan lo mismo de manera inversamente proporcional:

     - CIFRADO: Lee el mensaje y compara las letras con las de la referencia 'abc', si las letras coinciden se toma la posición
                de la referencia 'abc' para obtener su nuevo valor en la referencia 'trans'.
        
    - DESCIFRADO: Lee el mensaje y compara las letras con las de la referencia 'trans', si las letras coinciden se toma la posición
                  de la referencia 'trans' para obtener su nuevo valor en la referencia 'abc'.
    */

    private static String cifrar(String msg) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < msg.length(); i++) {
            for (int j = 0; j < abc.length(); j++) {
                if (msg.charAt(i) == abc.charAt(j))
                    sb.append(trans.charAt(j));
            }
        }

        return sb.toString();
    }
    
    private static String descifrar(String msg) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < msg.length(); i++) {
            for (int j = 0; j < trans.length(); j++) {
                if (msg.charAt(i) == trans.charAt(j))
                    sb.append(abc.charAt(j));
            }
        }

        return sb.toString();
    }
}