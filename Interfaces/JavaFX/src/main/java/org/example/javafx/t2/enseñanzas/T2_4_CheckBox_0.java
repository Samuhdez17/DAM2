package org.example.javafx.t2.enseñanzas;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class T2_4_CheckBox_0 extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        CheckBox ch1 = new CheckBox("Opción 1");
        CheckBox ch2 = new CheckBox("Opción 2");

        HBox hbox = new HBox(7);
        hbox.getChildren().addAll(ch1, ch2);
        hbox.setMinSize(100, 100);

        Scene scene = new Scene(hbox);
        stage.setScene(scene);
        stage.setTitle("T2_4_CheckBox_0");
        stage.show();
    }
}
