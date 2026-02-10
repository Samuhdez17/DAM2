package org.example.ensenanzas;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.Event;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
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

import java.util.List;

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

    static int vida = 100;
    static int puntuacion = 0;
    static boolean corriendo = false;

    static List<Timeline> animaciones;

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
        ImageView policia1 = getCocheEnemigo("cocheGC.png", 1);
        ImageView policia2 = getCocheEnemigo("cocheGC.png", 2);

        // CIELO
        Rectangle cielo = getCielo();

        Circle sol = getSol();

        Text txtPuntuacion = getTxtPuntuacion();
        Button pausa = getBtPausa();

        Text cuentaAtras = getCuentaAtras();
        Text gameOver = getGameOver();

        // VIDA
        ProgressBar barraVida = getVida();

        pane.getChildren().addAll(
                carretera, division, policia1, policia2, cielo, sol,
                txtPuntuacion, pausa, barraVida, cuentaAtras, gameOver, coche
        );
        animaciones(division, coche, policia1, policia2, txtPuntuacion, barraVida, gameOver, cuentaAtras);

        setEventos(scene, coche, pausa);
    }

    private static Text getCuentaAtras() {
        Text t = new Text("3");

        t.setFill(Color.WHITE);
        t.setStyle("-fx-font-size: 100px; -fx-font-weight: bold;");
        t.setVisible(false);
        t.setLayoutX((ancho / 2) - 30);
        t.setLayoutY((mitadAlto / 1.3));

        return t;
    }

    private static Text getGameOver() {
        Text t = new Text("GAME OVER");
        t.setFill(Color.RED);
        t.setStyle("-fx-font-size: 80px; -fx-font-weight: bold;");

        t.setLayoutX((ancho - 400) / 2);
        t.setLayoutY(mitadAlto / 1.5);

        t.setVisible(false);

        return t;
    }

    private static ProgressBar getVida() {
        ProgressBar barraVida = new ProgressBar(150);
        barraVida.setLayoutX(ancho - 230);
        barraVida.setLayoutY(55);
        barraVida.setPrefWidth(200);
        barraVida.setPrefHeight(15);

        barraVida.setStyle("-fx-accent: limegreen;");

        return barraVida;
    }

    private Button getBtPausa() {
        Button bt = new Button("⏸");
        bt.setLayoutX(ancho - 210);
        bt.setLayoutY(80);

        return bt;
    }

    private static Text getTxtPuntuacion() {
        Text txtPuntuacion = new Text((ancho - 210), 40, "Score: 0");
        txtPuntuacion.setFill(Color.WHITE);
        txtPuntuacion.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        return txtPuntuacion;
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
        img.setFitWidth(100);
        img.setFitHeight(120);
        img.setPreserveRatio(true);
        img.setLayoutX(ancho / 1.8);
        img.setLayoutY((alto / 10) * 7);

        return img;
    }

    private static ImageView getCocheEnemigo(String imagen, int num) {
        ImageView img = new ImageView(new Image(imagen));

        img.setFitWidth(90);
        img.setFitHeight(110);
        img.setPreserveRatio(true);

        if (num == 1) {
            img.setLayoutX(ancho / 1.8);
            img.setLayoutY(-200);

        } else {
            img.setLayoutX(ancho / 2.8);
            img.setLayoutY(alto);
        }

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

    private static void setEventos(Scene scene, ImageView img, Button pausa) {
        scene.setOnKeyPressed(e ->{
            if (corriendo)
                switch (e.getCode()) {
                    case LEFT, A -> img.setLayoutX(img.getLayoutX() - 20);
                    case RIGHT, D -> img.setLayoutX(img.getLayoutX() + 20);
                    case UP, W -> img.setLayoutY(img.getLayoutY() - 15);
                    case DOWN, S -> img.setLayoutY(img.getLayoutY() + 15);
                }
        });

        pausa.setOnAction(e -> {
            corriendo = !corriendo;

            if (corriendo) {
                reanudarAnimaciones();
                pausa.setText("⏸");

            } else {
                pausarAnimaciones();
                pausa.setText("▶");
            }
        });
    }

    private static void pausarAnimaciones() {
        for (Timeline tl : animaciones)
            tl.pause();
    }

    private static void reanudarAnimaciones() {
        for (Timeline tl : animaciones)
            tl.play();
    }

    private static void actualizarColision(
            ImageView coche, ImageView pol1,
            ImageView pol2, ProgressBar barraVida,
            Text gameOver
    ) {
        if (
                coche.getBoundsInParent().intersects(pol1.getBoundsInParent())
             || coche.getBoundsInParent().intersects(pol2.getBoundsInParent())
        ) {
            coche.setOpacity(.5);
            vida -= 5;
            barraVida.setProgress(vida / 100.0);

            if (vida <= 0) {
                gameOver.setVisible(true);
                vida = 0;
                barraVida.setProgress(0);
                pausarAnimaciones();
                corriendo = false;
            }

        } else {
            coche.setOpacity(1);
        }
    }

    private static void animaciones(
            Line division, ImageView coche,
            ImageView p1, ImageView p2,
            Text txtPuntuacion, ProgressBar barraVida,
            Text gameOver, Text cuentaAtras
    ) {
        // CUENTA ATRAS
        cuentaAtras.setVisible(true);

        Timeline tlCuenta = new Timeline(
                new KeyFrame(Duration.seconds(0), e -> cuentaAtras.setText("3")),
                new KeyFrame(Duration.seconds(1), e -> cuentaAtras.setText("2")),
                new KeyFrame(Duration.seconds(2), e -> cuentaAtras.setText("1")),
                new KeyFrame(Duration.seconds(3), e -> cuentaAtras.setText("GO!")),
                new KeyFrame(Duration.seconds(4), e -> {
                    cuentaAtras.setVisible(false);
                    corriendo = true;
                })
        );

        // CARRETERA
        KeyValue kvMov = new KeyValue(division.strokeDashOffsetProperty(), 800);
        KeyFrame kfMov = new KeyFrame(Duration.seconds(1), kvMov);
        Timeline tlMov = new Timeline(kfMov);
        tlMov.setCycleCount(Timeline.INDEFINITE);

        // POLICIAS
        KeyValue kvPoli1 = new KeyValue(p1.layoutYProperty(), alto + 150);
        KeyFrame kfPoli1 = new KeyFrame(Duration.seconds(5), e -> {
            p1.setLayoutY(-150);
        }, kvPoli1);

        Timeline tlPoli1 = new Timeline(kfPoli1);
        tlPoli1.setCycleCount(Timeline.INDEFINITE);

        KeyValue kvPoli2 = new KeyValue(p2.layoutYProperty(), 80);
        KeyFrame kfPoli2 = new KeyFrame(Duration.seconds(3), kvPoli2);
        Timeline tlPoli2 = new Timeline(kfPoli2);
        tlPoli2.setCycleCount(Timeline.INDEFINITE);

        // PUNTUACION
        Timeline tlScore = new Timeline(
                new KeyFrame(Duration.seconds(0.1), e -> {
                    if (corriendo) {
                        puntuacion++;
                        txtPuntuacion.setText("Score: " + puntuacion);

                        actualizarColision(coche, p1, p2, barraVida, gameOver);
                    }
                })
        );
        tlScore.setCycleCount(Timeline.INDEFINITE);

        tlCuenta.play();
        tlCuenta.setOnFinished( e -> {
            tlMov.play();
            tlPoli1.play();
            tlPoli2.play();
            tlScore.play();
                });

        animaciones = List.of(tlMov, tlPoli1, tlPoli2, tlScore);
    }
}
