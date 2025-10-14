package org.example.javafx.t2.enseñanzas;

import javafx.application.Application;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class T2_11_Color_DatePicker extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        DatePicker dp = new DatePicker();
        ColorPicker cp = new ColorPicker();

        HBox hb = new HBox();
        hb.getChildren().addAll(dp, cp);

        stage.setScene(new javafx.scene.Scene(hb));
        stage.setTitle("T2_11_Color_DatePicker");
        stage.show();
    }
}
