package org.example.ensenanzas;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Random;

public class T5_11_Outrun extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        double ancho = 900;
        double alto = 700;

        double mitadAlto = alto / 2;
        double cuartoAlto = alto / 4;
        double mitadAncho = ancho / 2;
        double tercioAncho = ancho / 3;
        double cuartoAncho = ancho / 4;

        Pane pane = new Pane();
        pane.setStyle("-fx-background-color: #00bb2d;");

        Scene scene = new Scene(pane, ancho, alto);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();

        Polygon carretera = new Polygon(
                tercioAncho, mitadAlto,
                (tercioAncho * 2), mitadAlto,
                ((ancho * 5) / 6), alto,
                ((ancho * 1) / 6), alto
        );
        carretera.setFill(Color.PERU);

        Line division = new Line(mitadAncho, alto, mitadAncho, mitadAlto);
        division.setStroke(Color.WHITE);
        division.setFill(Color.WHITE);
        division.setStrokeWidth(8);
        division.getStrokeDashArray().addAll(50d, 30d);

        ImageView img = new ImageView(new Image("fotoCoche.png"));

        img.setFitWidth(130);
        img.setFitHeight(150);
        img.setPreserveRatio(true);
        img.setLayoutX(ancho / 1.8);
        img.setLayoutY((alto / 10) * 7);

        // CIELO
        Rectangle cielo = new Rectangle(0,0,ancho, mitadAlto);
        Stop[] stops = new Stop[] { new Stop(0, Color.BLUE), new Stop(1, Color.LIGHTBLUE)};
        LinearGradient lg1 = new LinearGradient(1, 0, 0, 0, true, CycleMethod.NO_CYCLE, stops);
        cielo.setFill(lg1);

        Circle sol = new Circle(60);
        sol.setCenterX(cuartoAncho);
        sol.setCenterY(cuartoAlto);
        sol.setFill(Color.YELLOW);

        pane.getChildren().addAll(carretera, division, img, cielo, sol);
        animaciones(carretera, division);
    }

    private static void animaciones(Polygon carretera, Line division) {
        Color[] colores = new Color[]{
                Color.PINK,
                Color.WHITE,
                Color.CYAN,
                Color.RED,
                Color.YELLOW,
                Color.LIGHTCYAN,
                Color.LIGHTGREEN,
                Color.LIGHTPINK,
                Color.LIGHTGREY
        };

        Random ran = new Random();

        Timeline tlCol = new Timeline(
                new KeyFrame(Duration.seconds(0.01), e -> {
                    Color colorNuevo = colores[ran.nextInt(colores.length)];
                    carretera.setFill(colorNuevo);
                })
        );
        tlCol.setCycleCount(Timeline.INDEFINITE);
        tlCol.play();

        KeyValue kvMov = new KeyValue(division.strokeDashOffsetProperty(), 80);
        KeyFrame kfMov = new KeyFrame(Duration.seconds(0.200), kvMov);
        Timeline tlMov = new Timeline(kfMov);
        tlMov.setCycleCount(Timeline.INDEFINITE);
        tlMov.play();
    }
}
