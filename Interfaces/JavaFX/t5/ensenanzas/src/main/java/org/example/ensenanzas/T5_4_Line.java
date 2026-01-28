package org.example.ensenanzas;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class T5_4_Line extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        Pane pane = new Pane();
        Scene scene = new Scene(pane, 200, 200);
        stage.setScene(scene);

        Line lineRojo = new Line(10, 10, 500, 500); // inicioX, finalX, inicioY, finalY
        lineRojo.endXProperty().bind(pane.widthProperty().subtract(10));    // |
        lineRojo.endYProperty().bind(pane.heightProperty().subtract(10));   // |_> Metodos para establecer el final de la linea de forma dinamica
        lineRojo.setStroke(Color.RED);
        lineRojo.setStrokeWidth(10);

        // Metodo para hacer que las lineas sean discontinuas
        lineRojo.getStrokeDashArray().addAll(50d, lineRojo.getStrokeWidth() + 20d); //trazo 1, hueco 1

        Line lineAzul = new Line(500, 10, 10, 500);
        lineAzul.setStroke(Color.BLUE);
        lineAzul.setStrokeWidth(10);
        lineAzul.startXProperty().bind(pane.widthProperty().subtract(10));
        lineAzul.endYProperty().bind(pane.heightProperty().subtract(10));

        lineAzul.getStrokeDashArray().addAll(50d, lineAzul.getStrokeWidth() + 20d); //trazo 1, hueco 1

        Line lineVerde = new Line(250, 10, 250, 500);
        lineVerde.startXProperty().bind(pane.widthProperty().divide(2));
        lineVerde.endXProperty().bind(pane.widthProperty().divide(2));
        lineVerde.endYProperty().bind(pane.heightProperty().subtract(10));
        lineVerde.setStroke(Color.GREEN);
        lineVerde.setStrokeWidth(10);

        pane.getChildren().addAll(lineRojo, lineAzul, lineVerde);
        stage.show();
    }
}
