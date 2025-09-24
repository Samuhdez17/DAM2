package org.example.t1.inicios;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class T1_6_BorderPane_1 extends Application {
    public static void main(String[] args) {launch(args);}

    @Override
    public void start(Stage stage) {
        Button[] botones = new Button[6];
        for (int i = 0; i < botones.length; i++) {
            botones[i] = new Button("Boton " + (i+1));
        }

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(botones[0]);
        borderPane.setCenter(botones[1]);
        borderPane.setBottom(botones[2]);
        borderPane.setLeft(botones[3]);
        borderPane.setRight(botones[4]);

        for (Button botone : botones) {
            BorderPane.setAlignment(botone, Pos.CENTER);
        }

        Scene scene = new Scene(borderPane);
        stage.setScene(scene);
        stage.setTitle("BorderPane1");
        stage.show();
    }
}
