public class Inventario {
    int[] volumenesItems;
    int[] valoresItems;

    public Inventario(int[] volumenes, int[] valores) {
        this.volumenesItems = volumenes;
        this.valoresItems = valores;
    }

    public int getNumItems() {
        return this.volumenesItems.length;
    }
}
