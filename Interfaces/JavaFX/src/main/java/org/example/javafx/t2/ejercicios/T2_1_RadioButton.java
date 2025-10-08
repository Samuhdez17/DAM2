package org.example.javafx.t2.ejercicios;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class T2_1_RadioButton extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        Label label = new Label("What is 10 + 20");
        RadioButton rb1 = new RadioButton("10");
        RadioButton rb2 = new RadioButton("20");
        RadioButton rb3 = new RadioButton("30");
        RadioButton rb4 = new RadioButton("40");
        Button b1 = new Button("Submit");

        ToggleGroup tg = new ToggleGroup();
        rb1.setToggleGroup(tg);
        rb2.setToggleGroup(tg);
        rb3.setToggleGroup(tg);
        rb4.setToggleGroup(tg);



        VBox vBox = new VBox(10);
        vBox.getChildren().addAll(label, rb1, rb2, rb3, rb4, b1);
        vBox.setMinSize(300, 150);

        Scene scene = new Scene(vBox);
        stage.setScene(scene);
        stage.setTitle("T2_1_RadioButton_1");
        stage.show();
    }
}
