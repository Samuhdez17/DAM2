package org.example.ensenanzas;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class T5_9_Transdormaciones extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    double angulo = 0;

    @Override
    public void start(Stage stage) {
        Pane pane = new Pane();
        Scene scene = new Scene(pane, 500 ,500);
        stage.setScene(scene);
        stage.show();

        Rectangle r1 = new Rectangle(40, 40, 110, 80);
        r1.setFill(Color.YELLOW);

        Rectangle r2 = new Rectangle(240, 80, 50, 150);
        r2.setFill(Color.RED);

        Rectangle r3 = new Rectangle(70, 200, 110, 80);
        r3.setFill(Color.GREEN);

        Rectangle[] rectangulos = { r1, r2, r3 };

        pane.getChildren().addAll(r1, r2, r3);
        
        for (Rectangle r : rectangulos) {
            double[] datos = new double[4]; // sceneX, sceneY, angulo, seleccionado
            r.setUserData(datos);
            
            r.setOnMouseClicked(e -> {
                r.setStroke(Color.BLACK);
                ((double[]) r.getUserData())[3] = 1;
                e.consume();
            });

            r.setOnMousePressed(
                    e1 -> {
                        ((double[]) r.getUserData())[0] = e1.getSceneX();
                        ((double[]) r.getUserData())[1] = e1.getSceneY();
                    }
            );
        }

        pane.setOnMouseClicked(
                e -> {
                    for (Rectangle r : rectangulos) {
                        r.setStroke(null);
                        ((double[]) r.getUserData())[3] = 0;
                    }
                }
        );

        pane.setOnMouseDragged(e2 -> {
            for (Rectangle r : rectangulos) {
                double[] datos = (double[]) r.getUserData();
                if (datos[3] == 0)
                    continue;

                double pivotX = r.getX() + r.getWidth() / 2;
                double pivotY = r.getY() + r.getHeight() / 2;

                double anguloPressed = Math.toDegrees(
                        Math.atan2(datos[1] - pivotY, datos[0] - pivotX)
                );
                double anguloDragged = Math.toDegrees(
                        Math.atan2(e2.getSceneY() - pivotY, e2.getSceneX() - pivotX)
                );

                double a = anguloDragged - anguloPressed + datos[2];
                angulo = a;
                r.setRotate(a);
            }
        });

        pane.setOnMouseReleased(e3 -> {
            for (Rectangle r : rectangulos) {
                double[] datos = (double[]) r.getUserData();
                if (datos[3] == 0) continue;

                datos[2] = angulo;
            }
        });
    }
}
