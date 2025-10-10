import java.util.Random;

public class Individuo {
    private int volumenMochila;
    private int volumenTotal;
    private int valorTotal;
    private double idoneidad;
    private boolean[] genoma;
    private Inventario inventario;
    private Random random = new Random();

    public Individuo(Inventario inventario) {
        this.inventario = inventario;

        volumenMochila = Integer.MIN_VALUE;
        volumenTotal = Integer.MIN_VALUE;
        valorTotal = Integer.MIN_VALUE;
        idoneidad = Double.MIN_VALUE;
        genoma = new boolean[inventario.getTamanio()];

        for (int i = 0; i < genoma.length; i++) genoma[i] = random.nextBoolean();
    }
    public Individuo(Individuo padre, Individuo madre) {
        combinar(padre, madre);
    }

    int getVolumenTotal(){
        return volumenTotal;
    }
    int getValorTotal() {
        return valorTotal;
    }
    double getIdoneidad() {
        return idoneidad;
    }
    boolean[] getGenoma() {
        return genoma;
    }
    boolean esViable() {
        return volumenTotal <= volumenMochila;
    }
    void combinar(Individuo ind1, Individuo ind2) {
        boolean[] gen1 = ind1.getGenoma();
        boolean[] gen2 = ind2.getGenoma();
        this.genoma = new boolean[inventario.getTamanio()];

        for (int i = 0; i < gen1.length; i++) {
            this.genoma[i] = random.nextBoolean() ? gen1[i] : gen2[i];
            if (random.nextInt(5) == 0) this.genoma[i] = !this.genoma[i];
        }
    }
    void mutar() {
        for (int i = 0; i < genoma.length; i++) {
            if (random.nextInt(5) == 0) this.genoma[i] = !this.genoma[i];
        }
    }
}
