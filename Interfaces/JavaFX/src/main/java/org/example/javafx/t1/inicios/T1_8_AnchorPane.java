package org.example.javafx.t1.inicios;

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
        Button b5 = new Button("Boton 5");

        AnchorPane anchorPane = new AnchorPane();
        anchorPane.getChildren().addAll(b4,b2,b3,b1, b5);

        // TOP
        AnchorPane.setTopAnchor(b1, 10.0);
        AnchorPane.setTopAnchor(b2, 50.0);
        AnchorPane.setTopAnchor(b4, 70.0);

        // DERECHA
        AnchorPane.setRightAnchor(b1, 10.0);
        AnchorPane.setRightAnchor(b3, 80.0);
        AnchorPane.setRightAnchor(b4, 50.0);

        //IZQUIERDA
        AnchorPane.setLeftAnchor(b1, 10.0);
        AnchorPane.setLeftAnchor(b2, 30.0);
        AnchorPane.setLeftAnchor(b3, 80.0);
        AnchorPane.setLeftAnchor(b4, 50.0);

        // BOTT
        AnchorPane.setBottomAnchor(b3, 80.0);
        AnchorPane.setBottomAnchor(b4, 50.0);

        Scene scene = new Scene(anchorPane, 500, 300);
        stage.setScene(scene);
        stage.setTitle("T1_8_AnchorPane");
        stage.show();
    }
}
