package seguridad.ejercicios;

public class ej1 {
    public static void main(String[] args) {
        if (args.length < 3)
            throw  new IllegalArgumentException("Argumentos incorrectos, indica 3 argumentos");

        String comando = args[0];
        int cols = Integer.parseInt(args[1]);
        String msg = args[2].replaceAll("\s", "");

        int filas = (int) Math.ceil((double) msg.length() / cols);
        char[][] tabla = new char[filas][cols];

        System.out.println(
                comando.equalsIgnoreCase("cifrar") ?
                        cifrar(tabla, msg) :
                        descifrar(tabla, msg)
        );
    }

    private static String descifrar(char[][] tabla, String msg) {
        StringBuilder sb = new StringBuilder();

        int caracter = 0;
        for (int c = 0; c < tabla[0].length; c++)
            for (int f = 0; f < tabla.length; f++)
                if (caracter < msg.length()) {
                    tabla[f][c] = msg.charAt(caracter);
                    caracter++;
                }

        for (int f = 0; f < tabla.length; f++)
            for (int c = 0; c < tabla[0].length; c++)
                sb.append(tabla[f][c]);

        return sb.toString();
    }

    private static String cifrar(char[][] tabla, String msg) {
        StringBuilder sb = new StringBuilder();

        int caracter = 0;
        for (int f = 0; f < tabla.length; f++)
            for (int c = 0; c < tabla[0].length; c++)
                if (caracter < msg.length()) {
                    tabla[f][c] = msg.charAt(caracter);
                    caracter++;
                }

        for (int c = 0; c < tabla[0].length; c++)
            for (int f = 0; f < tabla.length; f++)
                sb.append(tabla[f][c]);

        return sb.toString();
    }
}
