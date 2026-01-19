package org.example.ensenanzas;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class T5_7_setRotateCentro extends Application {

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

        pane.getChildren().add(rectangle);

        double pivotX = X0 + ANCHO / 2;
        double pivotY = Y0 + ALTO / 2;

        pane.setOnMousePressed(
                e1 -> {
                    pane.setOnMouseDragged(
                            e2 -> {
                                double anguloPressed = Math.toDegrees(
                                        Math.atan2(e1.getSceneY() - pivotY, e1.getSceneX() - pivotX)
                                );
                                double anguloDragged = Math.toDegrees(
                                        Math.atan2(e2.getSceneY() - pivotY, e2.getSceneX() - pivotX)
                                );

                                double angulo = anguloDragged - anguloPressed + anguloFinal;
                                rectangle.setRotate(angulo);

                                pane.setOnMouseReleased(
                                        e3 -> {
                                            anguloFinal = angulo;
                                        }
                                );
                            }
                    );
                }
        );
    }
}
