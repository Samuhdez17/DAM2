package org.example.javafx.t2.enseñanzas;

import javafx.application.Application;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.stage.Stage;



public class T2_7_ListView extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        ListView listView = new ListView();
        listView.getItems().addAll("Opcion 1", "Opcion 2", "Opcion 3");
        listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        stage.setScene(new javafx.scene.Scene(listView));
        stage.setTitle("T2_7_ListView");
        stage.show();
    }
}
