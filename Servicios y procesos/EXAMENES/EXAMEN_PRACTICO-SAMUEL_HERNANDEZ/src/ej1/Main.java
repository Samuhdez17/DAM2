package ej1;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static void main() {
        ArrayList<Double> datos = new ArrayList<>();
        System.out.print("Indica la base del triángulo grande: ");
        double bTriGran = sc.nextDouble();
        datos.add(bTriGran);

        System.out.print("Indica la altura del triángulo grande:");
        double hTriGran = sc.nextDouble();
        datos.add(hTriGran);

        System.out.print("Indica el ancho del rectángulo grande:");
        double aRecGran = sc.nextDouble();
        datos.add(aRecGran);

        System.out.print("Indica la altura del triángulo pequeño:");
        double hTriPeq = sc.nextDouble();
        datos.add(hTriPeq);

        System.out.print("Indica el ancho del rectángulo pequeño:");
        double aRecPeq = sc.nextDouble();
        datos.add(aRecPeq);

        System.out.print("Indica la longitud total de la base de la figura: ");
        double baseTotal = sc.nextDouble();
        datos.add(baseTotal);

        Calculador calculador = new Calculador(datos);

        Thread thread = new Thread(calculador);
        thread.start();
    }
}
