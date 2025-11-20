import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        int[] volumenes = new int[]{2, 3, 4, 7, 1, 5};
        int[] valores = new int[]{3, 4, 5, 10, 2, 6};

        Inventario inventario = new Inventario(volumenes, valores);
        Individuo.setInventario(inventario);

        int generaciones = 100;
        Ecosistema ecosistema = new Ecosistema(20, 10);

        ArrayList<Double> maxCapacidad = new ArrayList();
        ArrayList<Double> mejorCapacidad = new ArrayList();

        for(int gen = 0; gen < generaciones; ++gen) {
            ecosistema.reproducirIndividuoNuevo();
            ecosistema.eliminarIndividuoMenosIdoneo();

            double max = ecosistema.getMejor().getIdoneidad();
            double mjr = ecosistema.individuos.stream().mapToDouble((ix) -> ix.getIdoneidad()).average().orElse((double)0.0F);

            maxCapacidad.add(max);
            mejorCapacidad.add(mjr);

            int maxBar = (int)(max * (double)2.0F);
            int mjrBar = (int)(mjr * (double)2.0F);
            System.out.printf("Gen %3d | Max:", gen);

            for(int i = 0; i < maxBar; ++i) {
                System.out.print("#");
            }

            System.out.printf(" %.1f | Avg:", max);

            for(int i = 0; i < mjrBar; ++i) {
                System.out.print("*");
            }

            System.out.printf(" %.1f\n", mjr);
        }

        Individuo mejor = ecosistema.getMejor();
        System.out.println("\n=== Mejor individuo final ===");
        System.out.println("Valor total: " + mejor.getValorTotal());
        System.out.println("Volumen total: " + mejor.getVolumenTotal());
        System.out.print("Genoma: ");

        for(boolean g : mejor.getGenoma()) {
            System.out.print(g ? "1 " : "0 ");
        }

        System.out.println();
    }
}
