package org.example.javafx.t1.inicios;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class T1_5_GridPane extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        Button b1 = new Button("Button1");
        Button b2 = new Button("Button2");
        Button b3 = new Button("Button3");
        Button b4 = new Button("Button4");

        GridPane gridPane = new GridPane();
        gridPane.setGridLinesVisible(true); //
        gridPane.setHgap(10);               // Comentables
        gridPane.setVgap(10);               //

        gridPane.add(b1, 0, 0);
        gridPane.add(b2, 1, 1);
        gridPane.add(b3, 2, 2);
        gridPane.add(b4, 3, 1);

        Scene scene = new Scene(gridPane);
        stage.setScene(scene);
        stage.show();
    }
}
