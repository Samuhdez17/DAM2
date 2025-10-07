package org.example.javafx.t2.ejercicios;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class T2_2_ChekBox_1 extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        CheckBox cb1 = new CheckBox("Extra cheese");
        CheckBox cb2 = new CheckBox("Peperoni");
        CheckBox cb3 = new CheckBox("Sausage");
        CheckBox cb4 = new CheckBox("Green pepper");
        CheckBox cb5 = new CheckBox("Onion");
        CheckBox cb6 = new CheckBox("Anchovies");

        Button boton1 = new Button("Pizza price calculator");

        Label label1 = new Label("Price: $10.00");
        label1.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        VBox vBox1 = new VBox(3, cb1, cb2, cb3);
        VBox vBox2 = new VBox(3, cb4, cb5, cb6);
        vBox1.setStyle("-fx-padding: 10 0");
        vBox2.setStyle("-fx-padding: 10 0");

        BorderPane bp = new BorderPane();
        bp.setLeft(vBox1);
        bp.setRight(vBox2);
        bp.setCenter(boton1);
        bp.setBottom(label1);
        BorderPane.setAlignment(label1, Pos.CENTER);

        bp.setStyle("-fx-background-color: pink");

        Scene scene = new Scene(bp);
        stage.setScene(scene);
        stage.setTitle("T2_ChekBox_1");
        stage.show();
    }
}
