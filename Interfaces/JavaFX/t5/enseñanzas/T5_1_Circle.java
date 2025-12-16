package enseñanzas;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class T5_1_Circle extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        Pane pane = new Pane();
        Circle circle1 = new Circle();

        circle1.setCenterX(100);
        circle1.setCenterY(100);
        circle1.setRadius(50);
        circle1.setStroke(Color.CYAN);
        circle1.setFill(Color.LIGHTGRAY);

        Button button = new Button("Rojo");
        button.setOnAction(e -> {
            circle1.setFill(Color.RED);
        });

        Circle circle2 = new Circle();
        circle2.setRadius(25);
        circle2.centerXProperty().bind(pane.widthProperty().divide(2));
        circle2.centerYProperty().bind(pane.heightProperty().divide(2));
        circle2.setFill(null);
        circle2.setStroke(Color.BLACK);

        pane.getChildren().addAll(circle1, button, circle2);
        Scene scene = new Scene(pane);
        stage.setTitle("Circle");
        stage.setScene(scene);
        stage.show();
    }
}
