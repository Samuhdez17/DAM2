import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class Ecosistema {
    ArrayList<Individuo> individuos = new ArrayList();

    public Ecosistema(int tamanoPoblacion, int volumenMochila) {
        for(int i = 0; i < tamanoPoblacion; ++i) {
            this.individuos.add(new Individuo(volumenMochila));
        }

    }

    private Individuo obtenerIndividuoAleatoriamente() {
        double totalFitness = this.individuos.stream().mapToDouble((i) -> i.getIdoneidad()).sum();
        double random = (new Random()).nextDouble() * totalFitness;
        double acumulado = (double)0.0F;

        for(Individuo ind : this.individuos) {
            acumulado += ind.getIdoneidad();
            if (acumulado >= random) {
                return ind;
            }
        }

        return (Individuo)this.individuos.getFirst();
    }

    public void reproducirIndividuoNuevo() {
        Individuo padre = this.obtenerIndividuoAleatoriamente();
        Individuo madre = this.obtenerIndividuoAleatoriamente();
        this.individuos.add(new Individuo(padre, madre));
    }

    public void eliminarIndividuoMenosIdoneo() {
        this.individuos.sort(Comparator.comparingDouble((i) -> i.getIdoneidad()));
        this.individuos.remove(0);
    }

    public Individuo getMejor() {
        return (Individuo)this.individuos.stream().max(Comparator.comparingDouble((i) -> i.getIdoneidad())).orElse((Individuo) null);
    }
}
