package org.example.t1.inicios;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class T1_8_AnchorPane extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        Button b1 = new Button("Boton 1");
        Button b2 = new Button("Boton 2");
        Button b3 = new Button("Boton 3");
        Button b4 = new Button("Boton 4");

        AnchorPane anchorPane = new AnchorPane();
        anchorPane.getChildren().addAll(b1,b2,b3,b4);

        AnchorPane.setTopAnchor(b1, 30.0);
        AnchorPane.setTopAnchor(b2, 15.0);
        AnchorPane.setTopAnchor(b3, 300.0);
        AnchorPane.setTopAnchor(b4, 50.0);

        AnchorPane.setRightAnchor(b1, 200.0);
        AnchorPane.setRightAnchor(b1, 308.0);
        AnchorPane.setRightAnchor(b1, 240.0);
        AnchorPane.setRightAnchor(b1, 300.0);

        Scene scene = new Scene(anchorPane, 500, 300);
        stage.setScene(scene);
        stage.setTitle("T1_8_AnchorPane");
        stage.show();
    }
}
