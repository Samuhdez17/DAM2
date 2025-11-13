package ej2;

import java.util.ArrayList;

public class Main {
    static void main() {
        Entradas entradas = new Entradas();
        ArrayList<Taquilla> taquillas = new ArrayList<>();

        for (int i = 0 ; i < 5 ; i++) taquillas.add(new Taquilla(entradas, i));

//        for (int i = 0 ; i < 5 ; i++) taquillas.get(i).setPriority(i+1);

        for (Taquilla taquilla : taquillas) taquilla.start();

    }
}
