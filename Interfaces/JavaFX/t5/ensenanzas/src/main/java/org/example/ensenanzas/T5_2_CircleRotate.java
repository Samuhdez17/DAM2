package org.example.ensenanzas;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

public class T5_2_CircleRotate extends Application {
    double posAnterior = 0;
    boolean botonActivo = false;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        Pane pane = new Pane();
        Slider slider = new Slider(0, 360, 0);
        Circle circle1 = new Circle();
        Circle circle2 = new Circle();
        Button boton = new Button("Auto");

        slider.setPrefSize(600, 0);
        slider.setMajorTickUnit(30);
        slider.setShowTickMarks(true);
        slider.setShowTickLabels(true);

        circle1.setCenterX(300);
        circle1.setCenterY(300);
        circle1.setRadius(200);
        circle1.setStroke(Color.BLACK);
        circle1.setFill(Color.TRANSPARENT);

        circle2.setCenterX(circle1.getRadius() - 100);
        circle2.setCenterY(300);
        circle2.setRadius(50);
        circle2.setStroke(Color.BLACK);
        circle2.setFill(Color.TRANSPARENT);

        boton.setLayoutX(280);
        boton.setLayoutY(550);

        pane.getChildren().addAll(slider, circle1, circle2, boton);
        Scene scene = new Scene(pane, 600, 600);
        stage.setTitle("Circle rotate");
        stage.setScene(scene);
        stage.show();


        slider.setOnMouseDragged(e -> {
            rotarCirculo(slider.getValue(), circle1, circle2);
        });

        slider.setOnMouseReleased(e -> {
            rotarCirculo(slider.getValue(), circle1, circle2);
        });

        boton.setOnAction(e -> {
            slider.setDisable(true);
            Rotate rotate = new Rotate();
            rotate.setPivotX(circle1.getCenterX());
            rotate.setPivotY(circle1.getCenterY());
            circle2.getTransforms().add(rotate);

            if (slider.getValue() == 0) {
                // Se establecen los valores finales de la animacion
                KeyValue kvRotate = new KeyValue(rotate.angleProperty(), 360);

                KeyValue kvSlider = new KeyValue(slider.valueProperty(), 360);

                // Se define la animacion con su respectiva duracion y valores finales
                KeyFrame kf = new KeyFrame(Duration.seconds(3), kvRotate, kvSlider);
                Timeline tl = new Timeline(kf);
                tl.play(); // Se reproduce
                tl.setCycleCount(10); // Se dice el numero de ciclos que se quieren hacer

                // Se establecen unos valores para que se establezcan hasta que acabe la animacion
                tl.setOnFinished(e1 -> {
                    slider.setDisable(false);
                    slider.setValue(360);
                });

            } else {
                KeyValue kvRotate = new KeyValue(rotate.angleProperty(), 0);

                KeyValue kvSlider = new KeyValue(slider.valueProperty(), 0);

                KeyFrame kf = new KeyFrame(Duration.seconds(3), kvRotate, kvSlider);
                Timeline tl = new Timeline(kf);
                tl.play();

                tl.setOnFinished(e1 -> {
                    slider.setDisable(false);
                    slider.setValue(0);
                });
            }
        });
    }

    private void rotarCirculo(double valor, Circle circle1, Circle circle2) {
        // Se crea un rotate con las indicaciones de cuanto y en qeu punto rotar
        Rotate rotate = new Rotate(); // angulo, pivoteX, pivoteY
        rotate.setAngle(valor - posAnterior);
        rotate.setPivotX(circle1.getCenterX());
        rotate.setPivotY(circle1.getCenterY());

        // Se establece a la figura a la que queremos rotar y se guarda la posicion para que siga por donde esta
        circle2.getTransforms().add(rotate);
        posAnterior = valor;
    }
}
