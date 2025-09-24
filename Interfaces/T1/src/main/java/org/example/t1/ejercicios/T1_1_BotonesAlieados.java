package org.example.t1.ejercicios;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class T1_1_BotonesAlieados extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        Button b1 = new Button("boton TOP");
        Button b2 = new Button("boton CENTER");
        Button b3 = new Button("boton BOTTOM");
        Button b4 = new Button("boton LEFT");
        Button b5 = new Button("boton RIGHT");

        b1.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        b1.setStyle("-fx-alignment: center");
        b1.setMinHeight(120);

        b2.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        b2.setStyle("-fx-alignment: center");
        b2.setMinHeight(200);
        b2.setMinWidth(200);

        b3.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        b3.setStyle("-fx-alignment: center");
        b3.setMinHeight(120);

        b4.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        b4.setStyle("-fx-alignment: center");
        b4.setMinHeight(200);
        b4.setMinWidth(150);

        b5.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        b5.setStyle("-fx-alignment: center");
        b5.setMinHeight(200);
        b5.setMinWidth(150);

        BorderPane borderPane = new BorderPane();
        borderPane.setTop   (b1);
        borderPane.setCenter(b2);
        borderPane.setBottom(b3);
        borderPane.setLeft  (b4);
        borderPane.setRight (b5);

        Scene scene = new Scene(borderPane);
        stage.setScene(scene);
        stage.setTitle("BorderPane1");
        stage.show();
    }
}
