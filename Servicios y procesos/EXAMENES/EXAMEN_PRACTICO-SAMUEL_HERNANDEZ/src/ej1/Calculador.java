package ej1;

import java.util.ArrayList;

public class Calculador implements Runnable {
    private final double bTriGran;
    private final double hTriGran;
    private final double aRecGran;
    private final double hTriPeq;
    private final double aRecPeq;
    private final double baseTotal;

    Calculador(ArrayList<Double> datos) {
        bTriGran = datos.getFirst();
        hTriGran = datos.get(1);
        aRecGran = datos.get(2);
        hTriPeq = datos.get(3);
        aRecPeq = datos.get(4);
        baseTotal = datos.getLast();
    }

    @Override
    public void run() {
        synchronized (this){
            // Calculo triangulo grande
            double arTrigGran = (bTriGran * hTriGran) / 2;

            // Calculo rectangulo grande
            double arRecGran = aRecGran * hTriGran;

            // Calculo rectangulo pequeño
            double arRecPeq = aRecPeq * hTriPeq;

            // Calculo triangulo pequeño
            double bTriPequ = baseTotal - (bTriGran + aRecGran + aRecPeq);
            double arTriPeq = (bTriPequ * hTriPeq) / 2;

            // Calculo area total
            double areaTotal = arTrigGran + arRecGran + arRecPeq + arTriPeq;
            System.out.printf("El área total de la figura es: %.2f", areaTotal);
        }
    }
}
