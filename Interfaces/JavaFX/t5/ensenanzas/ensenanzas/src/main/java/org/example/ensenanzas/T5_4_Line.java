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
        Line line = new Line(10, 10, 10, 500);
        line.setStroke(Color.RED);
        line.setStrokeWidth(10);

        line.getStrokeDashArray().addAll(50d, line.getStrokeWidth() + 20d); //trazo 1, hueco 1

        Line line2 = new Line(10, 10, 500, 500);
        line2.setStroke(Color.BLUE);
        line2.setStrokeWidth(10);

        line2.getStrokeDashArray().addAll(50d, line2.getStrokeWidth() + 20d); //trazo 1, hueco 1

        Line line3 = new Line(250, 10, 500, 500);
        line3.setStroke(Color.GREEN);
        line3.setStrokeWidth(10);

        Pane pane = new Pane(line, line2, line3);
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }
}
