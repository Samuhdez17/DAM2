package org.example.javafx.t2.inicios;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class T2_3_RadioButton_0 extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        RadioButton rb1 = new RadioButton("Opcion 1");
        RadioButton rb2 = new RadioButton("Opcion 2");
        RadioButton rb3 = new RadioButton("Opcion 3");

        ToggleGroup tg = new ToggleGroup();
        rb1.setToggleGroup(tg);
        rb2.setToggleGroup(tg);

        VBox vBox = new VBox(10);
        vBox.getChildren().addAll(rb1, rb2, rb3);
        vBox.setMinSize(300, 150);

        Scene scene = new Scene(vBox);
        stage.setScene(scene);
        stage.setTitle("T2_3_RadioButton_0");
        stage.show();
    }
}
