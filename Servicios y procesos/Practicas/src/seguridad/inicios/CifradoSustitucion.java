package seguridad.inicios;

public class CifradoSustitucion {
    String abcdario = "abcdefghijklmnopqrstuvwxy";
    String clave = "klmnoprstuvwxyz0123456789";

    public static void main(String[] args) {
        System.out.println(args[0].equalsIgnoreCase("cifrar") ? cifrarMensaje(args[1]) : descifrarMensaje(args[1]));
    }


    private static String descifrarMensaje(String msg) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i <= msg.length(); i++)
            sb.append(descifrado.get(msg.charAt(i)));

        return sb.toString();
    }

    private static String cifrarMensaje(String msg) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i <= msg.length(); i++)
            sb.append(cifrado.get(msg.charAt(i)));

        return sb.toString();
    }
}
