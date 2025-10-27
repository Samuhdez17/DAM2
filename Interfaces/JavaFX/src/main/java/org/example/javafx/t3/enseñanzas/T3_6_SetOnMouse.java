package org.example.javafx.t3.enseñanzas;

import javafx.application.Application;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class T3_6_SetOnMouse extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        Label lb1 = new Label("Haz click sobre este texto");
        Label lb2 = new Label();

        VBox vBox = new VBox(10);
        vBox.getChildren().addAll(lb1, lb2);
        stage.setScene(new javafx.scene.Scene(vBox, 300, 200));
        stage.setTitle("T3_6_SetOnMouse");
        stage.show();

        lb1.setOnMouseEntered(e -> lb1.setText("Has entrado"));
        lb1.setOnMouseExited(e -> lb1.setText("Haz click sobre este texto"));
        lb1.setOnMouseClicked(e -> {
            if (lb2.getText().isEmpty()) lb2.setText("Has hecho click");
            else lb2.setText("");
        });
    }
}
