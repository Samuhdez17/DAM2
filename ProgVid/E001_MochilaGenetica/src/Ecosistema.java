import java.util.ArrayList;
import java.util.Random;

public class Ecosistema {
    private final ArrayList<Individuo> individuos = new ArrayList<>();
    private final Inventario inventario;
    private final Random random = new Random();

    public Ecosistema(Inventario inventario) {
        this.inventario = inventario;
    }

    public Ecosistema(Inventario inventario, int cantidad) {
        this.inventario = inventario;
        for  (int i = 0; i < cantidad; i++) {
            Individuo individuo = new Individuo(inventario);
            individuos.add(individuo);
        }
    }

    private Individuo obtenerIndividuoAleatoriamente() {
        return individuos.get(random.nextInt(individuos.size()));
    }
    public void reproducirIndividuoNuevo() {
        // Si es menor a 2 o sc diciendo que no hay suficientes o añadir?
        if (individuos.size() < 2) {
            individuos.add(new Individuo(inventario));
        } else {
            Individuo padre = obtenerIndividuoPorIdoneidad();
            Individuo madre = obtenerIndividuoPorIdoneidad();
            individuos.add(new Individuo(padre, madre, inventario));
        }
    }

    private Individuo obtenerIndividuoPorIdoneidad() {
        double sumaTotal = 0;
        for (Individuo ind : individuos) {
            sumaTotal += ind.getIdoneidad();
        }

        double randomValue = random.nextDouble() * sumaTotal;
        double acumulador = 0;

        for (Individuo ind : individuos) {
            acumulador += ind.getIdoneidad();
            if (randomValue <= acumulador) {
                return ind;
            }
        }
        return individuos.getLast();
    }

    public void aniadirIndividuo(Individuo individuo) {
        individuos.add(individuo);
    }

    public void eliminarIndividuoMenosIdoneo() {
        Individuo menosIdoneo = individuos.getFirst();

        for (Individuo individuo : individuos) {
            if (individuo.getIdoneidad() < menosIdoneo.getIdoneidad()) menosIdoneo = individuo;
        }

        individuos.remove(menosIdoneo);
    }

    public double calcularIdoneidadMedia() {
        double suma = 0;
        for (Individuo individuo : individuos) {
            suma += individuo.getIdoneidad();
        }
        return suma / individuos.size();
    }

    public double calcularIdoneidadMinima() {
        Individuo individuo = individuos.getFirst();
        for (Individuo individuo1 : individuos) {
            if (individuo1.getIdoneidad() < individuo.getIdoneidad()) individuo = individuo1;
        }
        return individuo.getIdoneidad();
    }

    public double calcularIdoneidadMaxima() {
        Individuo individuo = individuos.getFirst();
        for (Individuo individuo1 : individuos) {
            if (individuo1.getIdoneidad() > individuo.getIdoneidad()) individuo = individuo1;
        }
        return individuo.getIdoneidad();
    }
}
