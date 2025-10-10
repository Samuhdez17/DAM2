import java.util.ArrayList;
import java.util.Random;

public class Ecosistema {
    ArrayList individuos;
    private Individuo individuo = obtenerIndividuoAleatoriamente(); // Ponderará por idoneidad, más probable cuanto más idóneo sea

    private Individuo obtenerIndividuoAleatoriamente() {
        Random random = new Random();
        return individuos.get(random.nextInt(individuos.size()));
    }
    public void reproducirIndividuoNuevo() {}
    public void eliminarIndividuoMenosIdoneo() {}
    public double calcularIdoneidadMedia() {}
    public double calcularIdoneidadMinima() {}
    public double calcularIdoneidadMaxima() {}
}
