package org.example.ensenanzas;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.time.LocalTime;

public class T5_10_Reloj extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) {
        StackPane stackPane = new StackPane();
        stage.setScene(new Scene(stackPane, 500, 500));
        stage.show();

        stackPane.setStyle("-fx-background-color: white;");
        stackPane.setAlignment(Pos.CENTER);

        double radius = 230;

        // Reloj
        Circle forma = new Circle(radius);
        forma.setFill(Color.TRANSPARENT);
        forma.setStroke(Color.BLACK);
        forma.setStrokeWidth(17);

        stackPane.getChildren().add(forma);

        // Marcas de cuartos y medias
        Rectangle[] rectangleHoras = new Rectangle[12];
        for (int i = 0; i < rectangleHoras.length; i++) {
            rectangleHoras[i] = new Rectangle(20, 5);
            rectangleHoras[i].setTranslateX(190 * Math.cos(Math.toRadians(30 * i)));
            rectangleHoras[i].setTranslateY(190 * Math.sin(Math.toRadians(30 * i)));
            rectangleHoras[i].setRotate(30*i);
            rectangleHoras[i].setFill(Color.BLACK);
            stackPane.getChildren().add(rectangleHoras[i]);
        }

        Rectangle[] rectangleMins = new Rectangle[60];
        for (int i = 0; i < rectangleMins.length; i++) {
            rectangleMins[i] = new Rectangle(10, 2);
            rectangleMins[i].setTranslateX(190 * Math.cos(Math.toRadians(6 * i)));
            rectangleMins[i].setTranslateY(190 * Math.sin(Math.toRadians(6 * i)));
            rectangleMins[i].setRotate(6 * i);
            if (i % 5 != 0) stackPane.getChildren().add(rectangleMins[i]);
        }

        // Números
        for (int i = 1; i <= 12; i++) {
            double angulo = Math.toRadians(i * 30);
            double distancia = radius - 75;

            Text numero = new Text(String.valueOf(i));
            numero.setFill(Color.BLACK);
            numero.setFont(Font.font(28));

            numero.setTranslateX(Math.sin(angulo) * distancia);
            numero.setTranslateY(-Math.cos(angulo) * distancia);

            stackPane.getChildren().add(numero);
        }

        // Manecillas
        Rectangle manecillaSegs = new Rectangle(240-5.0/2.0,40,5,250);
        manecillaSegs.setFill(Color.CRIMSON);
        manecillaSegs.setArcHeight(5);
        manecillaSegs.setArcWidth(5);

        Rectangle manecillaMins = new Rectangle(240-7.0/2.0, 80, 7, 180);
        manecillaMins.setFill(Color.BLACK);
        manecillaMins.setArcHeight(5);
        manecillaMins.setArcWidth(5);

        Rectangle manecillaHrs = new Rectangle(240-9.0/2.0, 120, 9, 140);
        manecillaHrs.setFill(Color.BLACK);
        manecillaHrs.setArcHeight(5);
        manecillaHrs.setArcWidth(5);
/*        Rectangle manecillaHrs = new Rectangle(240 - 5.0/2.0, 4 , 5, 250);
        manecillaHrs.setFill(Color.BLACK);
        manecillaHrs.setArcHeight(5);
        manecillaHrs.setArcWidth(10);

        Rectangle manecillaMins = new Rectangle(240 - 5.0/2.0, 4 , 5, 25);
        manecillaMins.setFill(Color.BLACK);
        manecillaMins.setArcHeight(5);
        manecillaMins.setArcWidth(10);

//        Rectangle manecillaHrs = new Rectangle(0, 6, 0, -radius + 130);
//        manecillaHrs.setStroke(Color.BLACK);
//        manecillaHrs.setStrokeWidth(6);
//
//        Rectangle manecillaMins = new Rectangle(0, 25, 0, -radius + 80);
//        manecillaMins.setStroke(Color.BLACK);
//        manecillaMins.setStrokeWidth(4);

        Rectangle manecillaSegs = new Rectangle(240 - 5.0/2.0, 40, 5, 250);
        manecillaSegs.setFill(Color.CRIMSON);
        manecillaSegs.setArcHeight(5);
        manecillaSegs.setArcWidth(10);

//        Rectangle manecillaSegs = new Rectangle(0, 30, 0, -radius + 60);
//        manecillaSegs.setStroke(Color.RED);
//        manecillaSegs.setStrokeWidth(2);
*/
        stackPane.getChildren().addAll(manecillaHrs, manecillaMins, manecillaSegs);

        // Animación
        Timeline animacion = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            LocalTime horaActual = LocalTime.now();

            double anguloSegs = horaActual.getSecond() * 6;
            double anguloMins = horaActual.getMinute() * 6 + horaActual.getSecond() * 0.1;
            double anguloHrs = (horaActual.getHour() % 12) * 30 + horaActual.getMinute() * 0.5;

            manecillaSegs.setRotate(anguloSegs);
            manecillaMins.setRotate(anguloMins);
            manecillaHrs.setRotate(anguloHrs);
        }));

        animacion.setCycleCount(Animation.INDEFINITE);
        animacion.play();
    }
}