package org.example.ensenanzas;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class T5_9_Transformaciones extends Application {

    public static void main(String[] args) {
        launch(args);
    }

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

        // Se guarda la info del click
        for (Rectangle r : rectangulos) {
            double[] datos = new double[4]; // sceneX, sceneY, angulo, seleccionado
            r.setUserData(datos);

            r.setOnMouseClicked(e -> {
                r.setStroke(Color.BLACK);
                ((double[]) r.getUserData())[3] = 1;
                e.consume();
            });
        }

        for (int i = 0; i < rectangulos.length; i++) {
            int ii = i;

            // Desplazamiento de los rectangulos seleccionados
            rectangulos[ii].setOnMouseDragged(
                    e2 -> {
                        for (Rectangle r : rectangulos) {
                            double[] datos = (double[]) r.getUserData();

                            if (datos[3] == 1) {
                                r.getStrokeDashArray().setAll(10.0, 5.0);

                                r.setTranslateX(e2.getSceneX() - datos[0]);
                                r.setTranslateY(e2.getSceneY() - datos[1]);
                            }
                        }

                        e2.consume();
                    }
            );

            // Guardado de posiciones de los rectangulos movidos
            rectangulos[ii].setOnMouseReleased(e3 -> {
                for (Rectangle r : rectangulos) {
                    double[] datos = (double[]) r.getUserData();

                    if (datos[3] == 1) {
                        r.getStrokeDashArray().clear();

                        r.setLayoutX(r.getLayoutX() + r.getTranslateX());
                        r.setLayoutY(r.getLayoutY() + r.getTranslateY());

                        r.setTranslateX(0);
                        r.setTranslateY(0);
                    }
                }
            });
        }

        // Se guarda donde se hace el click para guardar el pivote de rotacion
        pane.setOnMousePressed(e -> {
            for (Rectangle r : rectangulos) {
                double[] datos = (double[]) r.getUserData();
                if (datos[3] == 1) {
                    datos[0] = e.getSceneX();
                    datos[1] = e.getSceneY();
                }
            }
        });

        // Se deseleccionan los rectangulos sí se pulsa en el pane
        pane.setOnMouseClicked(
                e -> {
                    for (Rectangle r : rectangulos) {
                        r.setStroke(null);
                        ((double[]) r.getUserData())[3] = 0;
                    }
                }
        );

        // Rotacion de los rectangulos en base al pivote de los rectangulos seleccionados
        pane.setOnMouseDragged(e2 -> {
            for (Rectangle r : rectangulos) {
                double[] datos = (double[]) r.getUserData();
                if (datos[3] == 0)
                    continue;

                double pivotX = r.getLayoutX() + r.getX() + r.getWidth() / 2.0;
                double pivotY = r.getLayoutY() + r.getY() + r.getHeight() / 2.0;

                double anguloPressed = Math.toDegrees(
                        Math.atan2(datos[1] - pivotY, datos[0] - pivotX)
                );
                double anguloDragged = Math.toDegrees(
                        Math.atan2(e2.getSceneY() - pivotY, e2.getSceneX() - pivotX)
                );

                double a = anguloDragged - anguloPressed + datos[2];
                r.setRotate(a);
            }
        });

        // Se guarda la rotacion hecha
        pane.setOnMouseReleased(e3 -> {
            for (Rectangle r : rectangulos) {
                double[] datos = (double[]) r.getUserData();
                if (datos[3] == 0)
                    continue;

                datos[2] = r.getRotate();
            }
        });
    }
}
