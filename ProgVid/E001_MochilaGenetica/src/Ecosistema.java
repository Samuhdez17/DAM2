import java.util.ArrayList;
import java.util.Random;

public class Ecosistema {
    ArrayList<Individuo> individuos = new ArrayList<>();

    private Individuo individuo = obtenerIndividuoAleatoriamente(); // Ponderará por idoneidad, más probable cuanto más idóneo sea

    private Individuo obtenerIndividuoAleatoriamente() {
        Random random = new Random();
        return individuos.get(random.nextInt(individuos.size()));
    }
    public void reproducirIndividuoNuevo() {}

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
