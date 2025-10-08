package org.example.javafx.t2.enseñanzas;

import javafx.application.Application;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class T2_5_Hyperlink_MOD extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        Hyperlink hyperlink1 = new Hyperlink("https://www.google.com/?hl=es");
        Hyperlink hyperlink2 = new Hyperlink("https://www.donaldjtrump.com");
        hyperlink2.setVisited(true);

        VBox vBox = new VBox(10);
        vBox.getChildren().addAll(hyperlink1, hyperlink2);

        stage.setScene(new javafx.scene.Scene(vBox));
        stage.setTitle("T2_5_Hyperlink_MOD");
        stage.show();
    }
}
