public class Inventario {
    private Item[] items;

    public Inventario(Item[] items) {
        setItems(items);
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

    public void setItems(Item[] items) {
        this.items = items;
    }
}
