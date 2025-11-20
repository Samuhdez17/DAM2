import java.util.Random;

public class Individuo {
    private static Inventario inventario;
    private int volumenMochila;
    private final boolean[] genoma;
    private int volumenTotal;
    private int valorTotal;
    private double idoneidad;

    public Individuo(int volumenMochila) {
        this.volumenMochila = volumenMochila;
        Random r = new Random();
        this.genoma = new boolean[inventario.getNumItems()];

        for(int i = 0; i < this.genoma.length; ++i) {
            this.genoma[i] = r.nextBoolean();
        }

        this.evaluar();
    }

    public Individuo(Individuo padre, Individuo madre) {
        this.volumenMochila = padre.volumenMochila;
        Random r = new Random();
        this.genoma = new boolean[inventario.getNumItems()];
        int punto = r.nextInt(this.genoma.length);

        for(int i = 0; i < this.genoma.length; ++i) {
            this.genoma[i] = i < punto ? padre.genoma[i] : madre.genoma[i];
        }

        this.mutar();
        this.evaluar();
    }

    public static Inventario getInventario() {
        return inventario;
    }

    public static void setInventario(Inventario inventario) {
        Individuo.inventario = inventario;
    }

    public int getVolumenMochila() {
        return volumenMochila;
    }

    public void setVolumenMochila(int volumenMochila) {
        this.volumenMochila = volumenMochila;
    }

    public boolean[] getGenoma() {
        return genoma;
    }

    public int getVolumenTotal() {
        return volumenTotal;
    }

    public void setVolumenTotal(int volumenTotal) {
        this.volumenTotal = volumenTotal;
    }

    public int getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(int valorTotal) {
        this.valorTotal = valorTotal;
    }

    public double getIdoneidad() {
        return idoneidad;
    }

    public void setIdoneidad(double idoneidad) {
        this.idoneidad = idoneidad;
    }

    public boolean esViable() {
        return this.volumenTotal <= this.volumenMochila;
    }

    public void evaluar() {
        this.volumenTotal = 0;
        this.valorTotal = 0;

        for(int i = 0; i < this.genoma.length; ++i) {
            if (this.genoma[i]) {
                this.volumenTotal += inventario.volumenesItems[i];
                this.valorTotal += inventario.valoresItems[i];
            }
        }

        this.idoneidad = this.esViable() ? (double)this.valorTotal : (double)0.0F;
    }

    public void mutar() {
        Random random = new Random();
        int pos = random.nextInt(this.genoma.length);
        this.genoma[pos] = !this.genoma[pos];
    }

    public String toString() {
        return "Valor=" + this.valorTotal + " Volumen=" + this.volumenTotal + " Idoneidad=" + this.idoneidad;
    }
}
