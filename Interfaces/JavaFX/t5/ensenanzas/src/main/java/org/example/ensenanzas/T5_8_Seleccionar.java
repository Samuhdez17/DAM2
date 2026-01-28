package org.example.ensenanzas;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class T5_8_Seleccionar extends Application {

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

        for (Rectangle r : rectangulos) {
            r.setOnMouseClicked(e -> {
                r.setStroke(Color.BLACK);
                e.consume();
            });
        }

        pane.setOnMouseClicked(
                e -> {
                    for (Rectangle r : rectangulos)
                        r.setStroke(null);
                }
        );
    }
}
