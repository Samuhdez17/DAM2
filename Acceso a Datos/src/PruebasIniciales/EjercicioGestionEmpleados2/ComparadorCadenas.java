package PruebasIniciales.EjercicioGestionEmpleados2;

public class ComparadorCadenas {

    public static int compararDni(String dniE1, String dniE2) {
        int numE1 = Integer.parseInt(dniE1.substring(0,8));
        char letraE1 = dniE1.charAt(8);

        int numE2 = Integer.parseInt(dniE2.substring(0,8));
        char letraE2 = dniE2.charAt(8);

        if (numE1 == numE2)  return Character.compare(letraE1, letraE2);
        else                 return Integer.compare(numE1, numE2);
    }

    public static int compararNombre(String nombreE1, String nombreE2) {
        char letraE1 = ' ';
        char letraE2 = ' ';

        // For iterativo para verificar las letras de los nombres, para saber si compararlas o no sin tener index out of bounds por la diferencia de letras de cada nombre
        for (int i = 0 ; i < nombreE1.length() ; i++) {
            if (i == nombreE1.length() - 1) letraE1 = nombreE1.charAt(i);

            for (int j = 0 ; j < nombreE2.length() ; j++) {
                if (nombreE1.charAt(i) != nombreE2.charAt(j)) {
                    letraE1 = nombreE1.charAt(i);
                    letraE2 = nombreE2.charAt(j);
                }

                if (j == nombreE2.length() - 1) letraE2 = nombreE2.charAt(j);
            }
        }

        return Character.compare(letraE1, letraE2);
    }

    public static int compararApellido(String apellidoE1, String apellidoE2) {
        return compararNombre(apellidoE1, apellidoE2); // Al ser igual que comparar el nombre, este hace un llamado al método del nombre y asi hay menos código
    }

    public static int compararTelefono(float telefonoE1, float telefonoE2) {
        return Float.compare(telefonoE1, telefonoE2);
    }

    public static int compararSalario(double salarioE1, double salarioE2){
        return Double.compare(salarioE1, salarioE2);
    }
}
