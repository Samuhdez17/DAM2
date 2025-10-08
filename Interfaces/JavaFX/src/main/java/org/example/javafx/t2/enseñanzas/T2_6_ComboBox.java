package org.example.javafx.t2.enseñanzas;

import javafx.application.Application;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class T2_6_ComboBox extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        ComboBox cb = new ComboBox();
        cb.getItems().addAll("Opcion 1", "Opcion 2", "Opcion 3");
        cb.setPromptText("Elige una opcion");

        ComboBox cb2 = new ComboBox();
        Label l1 = new Label("Estas opciones");
        Label l2 = new Label("si tienen formato");
        Label l3 = new Label("porque son labels");

        l1.setStyle("-fx-background-color: yellow");
        l2.setStyle("-fx-font-size: 20");
        l3.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 15));

        cb2.getItems().addAll(l1, l2, l3);

        HBox hb = new HBox(10, cb, cb2);

        stage.setScene(new javafx.scene.Scene(hb));
        stage.setTitle("T2_6_ComboBox");
        stage.show();
    }
}
