package ejerciciosRepaso;

public class Ej1 {
    public static void main(String[] args) {
        int[] numerosRandom = {1,2,7,4,10,11,20,40};

        int contadorPares = 0;
        for (int numero : numerosRandom) {
            if (numero%2==0)contadorPares++;
        }

        System.out.println(contadorPares);
    }
}
