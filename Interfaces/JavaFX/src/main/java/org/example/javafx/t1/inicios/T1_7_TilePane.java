package org.example.javafx.t1.inicios;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

public class T1_7_TilePane extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        TilePane tilePane = new TilePane();
        tilePane.setHgap(10);
        tilePane.setVgap(10);

        for (int i = 1 ; i <= 40 ; i++) {
            Button boton = new Button("Boton " + (i));

            tilePane.getChildren().add(boton);
        }

        Scene scene = new Scene(tilePane, 800, 300);
        stage.setScene(scene);
        stage.setTitle("T1_7_TailPane");
        stage.show();
    }
}
