package org.example.ensenanzas;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class T5_5_TicTacToe extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        Pane pane = new Pane();
        stage.setScene(new Scene(pane, 200, 200));
        Color colorTablero = Color.GRAY;

        Line l1 = new Line();
        l1.startXProperty().bind(pane.widthProperty().divide(3));
        l1.endXProperty().bind(pane.widthProperty().divide(3));
        l1.endYProperty().bind(pane.heightProperty().subtract(10));
        l1.setStrokeWidth(10);
        l1.setStroke(colorTablero);

        Line l2 = new Line();
        l2.startXProperty().bind(pane.widthProperty().divide(1.5));
        l2.endXProperty().bind(pane.widthProperty().divide(1.5));
        l2.endYProperty().bind(pane.heightProperty().subtract(10));
        l2.setStrokeWidth(10);
        l2.setStroke(colorTablero);

        Line l3 = new Line();
        l3.startYProperty().bind(pane.heightProperty().divide(3));
        l3.endYProperty().bind(pane.heightProperty().divide(3));
        l3.endXProperty().bind(pane.widthProperty().subtract(10));
        l3.setStrokeWidth(10);
        l3.setStroke(colorTablero);

        Line l4 = new Line();
        l4.startYProperty().bind(pane.heightProperty().divide(1.5));
        l4.endYProperty().bind(pane.heightProperty().divide(1.5));
        l4.endXProperty().bind(pane.widthProperty().subtract(10));
        l4.setStrokeWidth(10);
        l4.setStroke(colorTablero);

        

        pane.getChildren().setAll(l1, l2, l3, l4);
        stage.show();
    }
}
