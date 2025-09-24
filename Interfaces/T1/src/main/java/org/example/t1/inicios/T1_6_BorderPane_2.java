package org.example.t1.inicios;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class T1_6_BorderPane_2 extends Application {
    public static void main(String[] args) {launch(args);}

    @Override
    public void start(Stage stage) {
        Label lab1 = new Label("TOP");
        Label lab2 = new Label("CENTER");
        Label lab3 = new Label("BOTTOM");
        Label lab4 = new Label("LEFT");
        Label lab5 = new Label("RIGHT");

        lab1.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        lab1.setStyle("-fx-background-color: red; -fx-alignment: center");
        lab1.setMinHeight(120);

        lab2.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        lab2.setStyle("-fx-background-color: white; -fx-alignment: center");
        lab2.setMinHeight(200);
        lab2.setMinWidth(200);

        lab3.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        lab3.setStyle("-fx-background-color: cyan; -fx-alignment: center");
        lab3.setMinHeight(120);

        lab4.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        lab4.setStyle("-fx-background-color: yellow; -fx-alignment: center");
        lab4.setMinHeight(200);
        lab4.setMinWidth(150);

        lab5.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        lab5.setStyle("-fx-background-color: pink; -fx-alignment: center");
        lab5.setMinHeight(200);
        lab5.setMinWidth(150);

        BorderPane borderPane = new BorderPane();
        borderPane.setTop   (lab1);
        borderPane.setCenter(lab2);
        borderPane.setBottom(lab3);
        borderPane.setLeft  (lab4);
        borderPane.setRight (lab5);

        Scene scene = new Scene(borderPane);
        stage.setScene(scene);
        stage.setTitle("BorderPane1");
        stage.show();
    }
}
