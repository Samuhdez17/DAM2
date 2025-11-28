package PruebasIniciales.EjercicioGestionEmpleados1;

public class ComparadorCadenas {

    public static int compararDni(String dniE1, String dniE2) {
        int numE1 = Integer.parseInt(dniE1.substring(0,8));
        char letraE1 = dniE1.charAt(8);

        int numE2 = Integer.parseInt(dniE2.substring(0,8));
        char letraE2 = dniE2.charAt(8);

        if (numE1 == numE2)  return Character.compare(letraE1, letraE2);
        else                 return Integer.compare(numE1, numE2);
    }
}
