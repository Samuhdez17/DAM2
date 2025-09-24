package org.example.t1.ejercicios;


import javafx.collections.ObservableList;
import javafx.scene.text.Font;

import java.util.List;

public class FamiliaFont {
    private static Font font;

    public static void main(String[] args) {
        List<String> fonts = Font.getFamilies();

        System.out.println(fonts);
    }
}
