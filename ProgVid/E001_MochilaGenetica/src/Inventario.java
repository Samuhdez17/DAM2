import java.util.ArrayList;
import java.util.Collections;

public class Inventario {
    private final ArrayList<Item> items;

    public Inventario(Item[] items) {
        this.items = new ArrayList<>();
        Collections.addAll(this.items, items);
    }

    void setItem(int volumen, int valor) {
        items.add(new Item(volumen, valor));
    }

    void getVolumenItem(int pos) {
        items.get(pos).getVolumen();
    }

    void getValorItem(int pos) {
        items.get(pos).getValor();
    }

    public int getTamanio() {
        return items.size();
    }
}
