package org.example.ensenanzas;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

public class T5_7_Ejercicio extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    double anguloFinal = 0;

    @Override
    public void start(Stage stage) {
        Pane pane = new Pane();
        Scene scene = new Scene(pane, 500, 500);
        stage.setScene(scene);
        stage.show();

        double X0 = 50, Y0 = 50, ANCHO = 200, ALTO = 100;
        Rectangle rectangle = new Rectangle(X0, Y0, ANCHO, ALTO);
        rectangle.setFill(Color.PERU);

        double pivotX = scene.getWidth() / 2;
        double pivotY = scene.getHeight() / 2;
        double radioMira = 10;

        Circle circle = new Circle(pivotX, pivotY, radioMira);
        circle.setFill(Color.TRANSPARENT);
        circle.setStroke(Color.BLACK);

        Line l1 = new Line(pivotX - radioMira, pivotY, pivotX + radioMira, pivotY);
        Line l2 = new Line(pivotX, pivotY - radioMira, pivotX, pivotY + radioMira);

        l1.setStroke(Color.BLACK);
        l2.setStroke(Color.BLACK);
        l1.setStrokeWidth(2);
        l2.setStrokeWidth(2);

        Group mira = new Group(circle, l1, l2);

        pane.getChildren().addAll(rectangle, mira);

        Rotate rotate = new Rotate();
        rotate.setPivotX(pivotX);
        rotate.setPivotY(pivotY);
        rotate.setAngle(anguloFinal);
        rectangle.getTransforms().add(rotate);

        pane.setOnMousePressed(e1 -> {
            pane.setOnMouseDragged(e2 -> {

                double anguloPressed = Math.toDegrees(
                        Math.atan2(e1.getSceneY() - pivotY, e1.getSceneX() - pivotX)
                );
                double anguloDragged = Math.toDegrees(
                        Math.atan2(e2.getSceneY() - pivotY, e2.getSceneX() - pivotX)
                );

                double angulo = anguloDragged - anguloPressed + anguloFinal;

                rotate.setAngle(angulo);

                pane.setOnMouseReleased(e3 -> anguloFinal = angulo);
            });
        });
    }
}
