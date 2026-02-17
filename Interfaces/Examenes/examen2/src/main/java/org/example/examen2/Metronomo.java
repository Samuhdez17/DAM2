package org.example.examen2;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Metronomo extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        Pane pane = new Pane();
        Scene scene = new Scene(pane, 600, 700);

        stage.setScene(scene);
        stage.show();

        Polygon estructura = new Polygon(
                ((pane.getWidth() / 2) - 70), 70,
                ((pane.getWidth() / 2) + 70), 70,

                ((pane.getWidth() / 4) * 3), 500,
                ((pane.getWidth() / 4)), 500
        );
        estructura.setFill(Color.LIGHTGREEN);
        estructura.setStrokeWidth(10);
        estructura.setStroke(Color.RED);

        Rectangle linea = new  Rectangle(5, 320);
        linea.setFill(Color.BLACK);
        linea.setLayoutX((pane.getWidth() / 2) -2);
        linea.setLayoutY(120);

        Circle circle = new Circle(10);
        circle.setFill(Color.BLACK);
        circle.setCenterX(pane.getWidth() / 2);
        circle.setCenterY(400);

        Rotate rotate = new Rotate(10);
        rotate.setPivotX(circle.getCenterX());
        rotate.setPivotY(circle.getCenterY());
        linea.getTransforms().add(rotate);

        Slider velocidad = new Slider();
        velocidad.setLayoutX((pane.getWidth() / 4));
        velocidad.setLayoutY(pane.getHeight() - 100);
        velocidad.setPrefWidth((pane.getWidth() / 4) * 2);

        velocidad.setValue(60);
        velocidad.setMin(30);
        velocidad.setMax(180);

        velocidad.setShowTickLabels(true);
        velocidad.setShowTickMarks(true);
        velocidad.setMajorTickUnit(30);

        pane.getChildren().addAll(estructura, linea, circle, velocidad);

        KeyValue keyValue = new KeyValue(rotate.angleProperty(), 30);
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(60 / velocidad.getValue()), keyValue);
        Timeline timeline = new Timeline(keyFrame);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.setAutoReverse(true);

        timeline.play();
    }
}
