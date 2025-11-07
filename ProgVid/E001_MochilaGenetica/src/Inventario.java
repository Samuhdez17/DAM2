public class Inventario {
    private final Item[] items;

    public Inventario(Item[] items) {
        this.items = items;
    }

    public int getVolumenItem(int pos) {
        return items[pos].getVolumen();
    }

    public int getValorItem(int pos) {
        return items[pos].getValor();
    }

    public int getTamanio() {
        return items.length;
    }
}
