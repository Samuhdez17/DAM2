package PipeLine;

public class Inicial {

    public static void main(String[] args) {
        try {
            int numero = Integer.parseInt(args[0]);

            System.out.println(numero);
        }catch (NumberFormatException e){
            System.err.printf("El argumento %s no es un número \n", args[0]);
        }
    }
}
