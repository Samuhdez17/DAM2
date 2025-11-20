import java.util.ArrayList;

public class MochilaBacktrackingSoloValor {
    static int[] valores = {120,60,200,90,350,40,500,180,30,220};
    static int[] pesos = {2,1,3,1,4,2,5,4,1,3};
    static ArrayList<Integer> objetosFinales = new ArrayList<>();
    static ArrayList<Integer> valoresObjetosFinales = new ArrayList<>();
    static ArrayList<Integer> pesosObjetosFinales = new ArrayList<>();
    static int capacidad = 30;

    private static int mochila(int pos, int capacidadRestante) {
        if (pos < 0) {
            return 0;
        }
        if (pesos[pos] > capacidadRestante) {
            return mochila(pos-1, capacidadRestante);
        } else {
            int sinMeterElementoActual = mochila(pos-1, capacidadRestante             )               ;
            int metiendoElementoActual = mochila(pos-1, capacidadRestante - pesos[pos]) + valores[pos];

            if (sinMeterElementoActual > metiendoElementoActual) {
                return sinMeterElementoActual;
            } else {
                objetosFinales.add(pos);
                valoresObjetosFinales.add(valores[pos]);
                pesosObjetosFinales.add(pesos[pos]);

                return metiendoElementoActual;
            }
        }
    }

    public static void main(String[] args) {
        int valorFinal = mochila(valores.length - 1, capacidad);

        for (int i = 0; i < objetosFinales.size(); i++) {
            System.out.printf("Objeto %d ; Valor = %d ; Peso = %d\n", objetosFinales.get(i), valoresObjetosFinales.get(i), pesosObjetosFinales.get(i));
        }
        
        System.out.printf("\nValor final: %d", valorFinal);
    }
}