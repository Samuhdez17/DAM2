package org.example.javafx.t1.ejercicios;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class T2_3_ComboBox extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        Random rand = new Random();

        ComboBox cb1 = new ComboBox();
        for (int i = 8 ; i <= 32 ; i++) cb1.getItems().add(i);

        ComboBox cb2 = new ComboBox();
        Label labelColores;
        for (int i = 0 ; i < 20 ; i++) {
            int rojo = rand.nextInt(256);
            int verde = rand.nextInt(256);
            int azul = rand.nextInt(256);

            labelColores = new Label();
            labelColores.setMinSize(50, 15);
            labelColores.setStyle("-fx-background-color: rgb(" + rojo + ", " + verde + ", " + azul + ")");
            cb2.getItems().add(labelColores);
        }

        ComboBox cb3 = new ComboBox();
        List<String> fontsList = Font.getFamilies();
        String[] fontsArray = new String[20];
        for (int i = 0 ; i < 20 ; i++) fontsArray[i] = fontsList.get(rand.nextInt(fontsList.size()));

        Label labelFonts;

        for (String font : fontsArray) {
            labelFonts = new Label(font);
            labelFonts.setFont(Font.font(font, 15));

            cb3.getItems().add(labelFonts);
        }

        HBox hb = new HBox(10, cb1, cb2, cb3);

        Scene scene = new Scene(hb);
        stage.setTitle("T2_3_ComboBox");
        stage.setScene(scene);
        stage.show();
    }
}
