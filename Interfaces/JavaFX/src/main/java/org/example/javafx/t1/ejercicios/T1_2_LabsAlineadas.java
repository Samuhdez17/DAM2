package org.example.javafx.t1.ejercicios;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class T1_2_LabsAlineadas extends Application {
    public static void main(String[] args) {launch(args);}

    @Override
    public void start(Stage stage) {
        // BorderPane 1
        Label lab1_1 = new Label("TOP 1");
        Label lab2_1 = new Label("BOTTOM 1");
        Label lab3_1 = new Label("LEFT 1");
        Label lab4_1 = new Label("RIGHT 1");

        lab1_1.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        lab1_1.setStyle("-fx-background-color: red; -fx-alignment: center");
        lab1_1.setFont(Font.font("Palatino Linotype",  FontWeight.BOLD, 15));
        lab1_1.setMinHeight(100);

        lab2_1.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        lab2_1.setStyle("-fx-background-color: cyan; -fx-alignment: center");
        lab2_1.setFont(Font.font("Palatino Linotype",  FontWeight.BOLD, 15));
        lab2_1.setMinHeight(100);

        lab3_1.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        lab3_1.setStyle("-fx-background-color: yellow; -fx-alignment: center");
        lab3_1.setFont(Font.font("Palatino Linotype",  FontWeight.BOLD, 15));
        lab3_1.setMinHeight(170);
        lab3_1.setMinWidth(100);

        lab4_1.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        lab4_1.setStyle("-fx-background-color: pink; -fx-alignment: center");
        lab4_1.setFont(Font.font("Palatino Linotype",  FontWeight.BOLD, 15));
        lab4_1.setMinHeight(170);
        lab4_1.setMinWidth(100);

        // BorderPane 2
        Label lab1_2 = new Label("TOP 2");
        Label lab2_2 = new Label("CENTER 2");
        Label lab3_2 = new Label("BOTTOM 2");
        Label lab4_2 = new Label("LEFT 2");
        Label lab5_2 = new Label("RIGHT 2");

        lab1_2.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        lab1_2.setStyle("-fx-background-color: red; -fx-alignment: center");
        lab1_2.setFont(Font.font("Monotype Corsiva",  FontWeight.EXTRA_LIGHT, 20));
        lab1_2.setMinHeight(100);

        lab2_2.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        lab2_2.setStyle("-fx-background-color: white; -fx-alignment: center");
        lab2_2.setFont(Font.font("Monotype Corsiva",  FontWeight.EXTRA_LIGHT, 20));
        lab2_2.setMinHeight(150);
        lab2_2.setMinWidth(150);

        lab3_2.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        lab3_2.setStyle("-fx-background-color: cyan; -fx-alignment: center");
        lab3_2.setFont(Font.font("Monotype Corsiva",  FontWeight.EXTRA_LIGHT, 20));
        lab3_2.setMinHeight(100);

        lab4_2.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        lab4_2.setStyle("-fx-background-color: yellow; -fx-alignment: center");
        lab4_2.setFont(Font.font("Monotype Corsiva",  FontWeight.EXTRA_LIGHT, 20));
        lab4_2.setMinHeight(170);
        lab4_2.setMinWidth(100);

        lab5_2.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        lab5_2.setStyle("-fx-background-color: pink; -fx-alignment: center");
        lab5_2.setFont(Font.font("Monotype Corsiva",  FontWeight.EXTRA_LIGHT, 20));
        lab5_2.setMinHeight(170);
        lab5_2.setMinWidth(100);

        BorderPane borderPane2 = new BorderPane();
        borderPane2.setTop   (lab1_2);
        borderPane2.setCenter(lab2_2);
        borderPane2.setBottom(lab3_2);
        borderPane2.setLeft  (lab4_2);
        borderPane2.setRight (lab5_2);

        BorderPane borderPane1 = new BorderPane();
        borderPane1.setTop   (lab1_1);
        borderPane1.setCenter(borderPane2);
        borderPane1.setBottom(lab2_1);
        borderPane1.setLeft  (lab3_1);
        borderPane1.setRight (lab4_1);

        Scene scene = new Scene(borderPane1);
        stage.setScene(scene);
        stage.setTitle("BorderPane1");
        stage.show();
    }
}
