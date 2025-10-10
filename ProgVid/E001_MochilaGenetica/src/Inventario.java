public class Inventario {
    private int[] volumenesItems;
    private int[] valoresItems;

    public Inventario(int[] volumenesItems, int[] valoresItems) {
        this.volumenesItems = volumenesItems;
        this.valoresItems = valoresItems;

    }

    public int getTamanio() {
        return volumenesItems.length;
    }

}
