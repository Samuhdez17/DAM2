package ejercicio_contador_compartido;

public class Contador {
    private int contador = 0;

    public int getContador() {
        return contador;
    }

    public void aumentar() {
        contador++;
    }
}
