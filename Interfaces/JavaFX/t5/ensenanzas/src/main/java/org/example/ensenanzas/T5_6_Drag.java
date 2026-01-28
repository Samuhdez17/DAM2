package org.example.ensenanzas;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class T5_6_Drag extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        Pane pane = new Pane();
        Scene scene = new Scene(pane, 500, 500);
        stage.setScene(scene);
        stage.show();

        Rectangle rect = new Rectangle(50, 50, 200, 100);
        rect.setFill(Color.YELLOW);
        pane.getChildren().add(rect);

        rect.setOnMousePressed(e1 -> {
            rect.setOnMouseDragged(e2 -> {
                rect.setTranslateX(e2.getSceneX() - e1.getSceneX());
                rect.setTranslateY(e2.getSceneY() - e1.getSceneY());
            });
        });

        rect.setOnMouseReleased(e3 -> {
            rect.setLayoutX(rect.getLayoutX() + rect.getTranslateX());
            rect.setLayoutY(rect.getLayoutY() + rect.getTranslateY());

            rect.setTranslateX(0);
            rect.setTranslateY(0);
        });
    }
}
