package org.example.ensenanzas;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class T5_3_Rectangle extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Pane pane = new Pane();

        Rectangle rectangle1 = new Rectangle(/* centroX, centroY, anchura, altura */);
        rectangle1.setX(50.0);                  // Establecer el centro en eje x
        rectangle1.setY(20.0);                  // Establecer el centro en eje y
        rectangle1.setWidth(120.0);             // Establece la anchura del rectangulo
        rectangle1.setHeight(60.0);             // Establece la altura del rectangulo
        rectangle1.setStyle("-fx-color: red");

        Rectangle rectangle2 = new Rectangle(50, 100, 120, 60);
        rectangle2.setStroke(Color.BLUE);
        rectangle2.setFill(Color.WHITE);

        Rectangle rectangle3 = new Rectangle(50, 200, 120, 60);
        rectangle3.setArcHeight(30); // |
        rectangle3.setArcWidth(30);  // |_> Metodos para redondear las esquias

        Text text1 = new Text("r1");
        text1.setX(20.0);
        text1.setY(54.0);

        Text text2 = new Text(20, 134, "r2");

        Text text3 = new Text(20, rectangle3.getY() + 34, "r3");

        pane.getChildren().addAll(rectangle1, text1, rectangle2, text2, rectangle3, text3);

        float N = 10;

        for (int i = 0 ; i < N ; i++) {
            Rectangle rectangle = new Rectangle(200, 100, 200, 60);
            rectangle.setFill(Color.color(Math.random(), Math.random(), Math.random()));
            rectangle.setRotate(i * 180 / N); // Rota x grados con respecto a su centro
            pane.getChildren().add(rectangle);
        }

        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Rectangulo");
        primaryStage.show();
    }
}