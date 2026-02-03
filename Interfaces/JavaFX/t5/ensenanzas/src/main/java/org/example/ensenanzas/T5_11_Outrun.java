package org.example.ensenanzas;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
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
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Random;

public class T5_11_Outrun extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    static double ancho = 900;
    static double alto = 700;

    static double mitadAlto = alto / 2;
    static double cuartoAlto = alto / 4;
    static double mitadAncho = ancho / 2;
    static double tercioAncho = ancho / 3;
    static double cuartoAncho = ancho / 4;

    static IntegerProperty puntuacion = new SimpleIntegerProperty(0);
    static boolean corriendo = true;

    @Override
    public void start(Stage stage) {
        Pane pane = new Pane();
        pane.setStyle("-fx-background-color: #00bb2d;");

        Scene scene = new Scene(pane, ancho, alto);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();

        Polygon carretera = getCarretera();

        Line division = getLinea();

        ImageView coche = getCoche("coche.png");
        ImageView policia1 = getCoche("cocheGC.png");
        ImageView policia2 = getCoche("cocheGC.png");

        // CIELO
        Rectangle cielo = getCielo();

        Circle sol = getSol();

        Text txtPuntuacion = getTxtPuntuacion();

        pane.getChildren().addAll(carretera, division, policia1, policia2, cielo, sol, txtPuntuacion, coche);
        animaciones(carretera, division, policia1, policia2, txtPuntuacion);

        setEventos(scene, coche);
    }

    private static Text getTxtPuntuacion() {
        Text txtPuntuacion = new Text(20, 40, "Score: 0");
        txtPuntuacion.setFill(Color.WHITE);
        txtPuntuacion.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        return txtPuntuacion;
    }

    private static void setEventos(Scene scene, ImageView img) {
        scene.setOnKeyPressed(e ->{
            switch (e.getCode()) {
                case LEFT, A -> img.setLayoutX(img.getLayoutX() - 20);
                case RIGHT, D -> img.setLayoutX(img.getLayoutX() + 20);
                case UP, W -> img.setLayoutY(img.getLayoutY() - 15);
                case DOWN, S -> img.setLayoutY(img.getLayoutY() + 15);
            }
        });
    }

    private static Circle getSol() {
        Circle sol = new Circle(60);
        sol.setCenterX(cuartoAncho);
        sol.setCenterY(cuartoAlto);
        sol.setFill(Color.YELLOW);

        return sol;
    }

    private static Rectangle getCielo() {
        Rectangle cielo = new Rectangle(0,0, ancho, mitadAlto);
        Stop[] stops = new Stop[] { new Stop(0, Color.BLUE), new Stop(1, Color.LIGHTBLUE)};
        LinearGradient lg1 = new LinearGradient(1, 0, 0, 0, true, CycleMethod.NO_CYCLE, stops);
        cielo.setFill(lg1);

        return cielo;
    }

    private static ImageView getCoche(String imagen) {
        ImageView img = new ImageView(new Image(imagen));
        img.setFitWidth(130);
        img.setFitHeight(150);
        img.setPreserveRatio(true);
        img.setLayoutX(ancho / 1.8);
        img.setLayoutY((alto / 10) * 7);

        return img;
    }

    private static Line getLinea() {
        Line division = new Line(mitadAncho, alto, mitadAncho, mitadAlto);
        division.setStroke(Color.WHITE);
        division.setFill(Color.WHITE);
        division.setStrokeWidth(8);
        division.getStrokeDashArray().addAll(50d, 30d);

        return division;
    }

    private static Polygon getCarretera() {
        return new Polygon(
                tercioAncho, mitadAlto,
                (tercioAncho * 2), mitadAlto,
                ((ancho * 5) / 6), alto,
                ((ancho * 1) / 6), alto
        );
    }

    private static void animaciones(
            Polygon carretera, Line division,
            ImageView p1, ImageView p2,
            Text txtPuntuacion
            ) {
        // COLOR CARRETERA
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
                new KeyFrame(Duration.seconds(0.1), e -> {
                    Color colorNuevo = colores[ran.nextInt(colores.length)];
                    carretera.setFill(colorNuevo);
                })
        );
        tlCol.setCycleCount(Timeline.INDEFINITE);
        tlCol.play();

        // CARRETERA
        KeyValue kvMov = new KeyValue(division.strokeDashOffsetProperty(), 80);
        KeyFrame kfMov = new KeyFrame(Duration.seconds(0.200), kvMov);
        Timeline tlMov = new Timeline(kfMov);
        tlMov.setCycleCount(Timeline.INDEFINITE);
        tlMov.play();

        // POLICIAS
        KeyValue kvPoli1 = new KeyValue(p1.layoutYProperty(), 80);
        KeyFrame kfPoli1 = new KeyFrame(Duration.seconds(2), kvPoli1);
        Timeline tlPoli1 = new Timeline(kfPoli1);
        tlPoli1.setCycleCount(Timeline.INDEFINITE);
        tlPoli1.play();

        KeyValue kvPoli2 = new KeyValue(p2.layoutYProperty(), 80);
        KeyFrame kfPoli2 = new KeyFrame(Duration.seconds(1), kvPoli2);
        Timeline tlPoli2 = new Timeline(kfPoli2);
        tlPoli2.setCycleCount(Timeline.INDEFINITE);
        tlPoli2.play();

        // PUNTUACION
        Timeline scoreTl = new Timeline(
                new KeyFrame(Duration.seconds(0.1), e -> {
                    if (corriendo) {
                        puntuacion++;
                        txtPuntuacion.setText("Score: " + puntuacion);
                    }
                })
        );
        scoreTl.setCycleCount(Timeline.INDEFINITE);
        scoreTl.play();
    }
}
