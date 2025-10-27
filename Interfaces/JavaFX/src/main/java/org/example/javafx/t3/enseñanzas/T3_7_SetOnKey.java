package org.example.javafx.t3.enseñanzas;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class T3_7_SetOnKey extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        Label lb1 = new Label("Pulsa las flechas del teclado");
        lb1.setAlignment(Pos.CENTER);

        Scene scene = new Scene(lb1, 300, 200);
        stage.setScene(scene);
        stage.setTitle("T3_6_SetOnMouse");
        stage.show();

        scene.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case UP  -> lb1.setText("flecha arriba");
                case DOWN -> lb1.setText("flecha abajo");
                case RIGHT -> lb1.setText("flecha derecha");
                case LEFT -> lb1.setText("flecha izquierda");
                default -> lb1.setText("Pulsa las flechas del teclado");
            }
        });
    }
}
