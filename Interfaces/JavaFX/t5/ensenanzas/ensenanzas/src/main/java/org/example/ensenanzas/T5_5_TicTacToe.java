package org.example.ensenanzas;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class T5_5_TicTacToe extends Application {
    private final int[][] puntuacion = new int[3][3];

    private final Color COLOR_TABLERO = Color.GREY;
    private final Color COLOR_ASPAS = Color.GREEN;
    private final Color COLOR_CIRCULOS = Color.RED;

    private final int GROSOR_X = 7;
    private final int GROSOR_O = 7;

    private boolean turnoX = true;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        Group tablero = new Group();
        Group aspas = new Group();
        Group circulos = new Group();
        Group celdas = new Group();

        Pane pane = new Pane(celdas, tablero, aspas, circulos);
        Scene scene = new Scene(pane, 300,300);
        stage.setScene(scene);
        stage.setTitle("T5_5_TicTacToe");
        stage.show();
        
        Line line1 = new Line();
        line1.startXProperty().bind(pane.widthProperty().divide(3));
        line1.setStartY(10);
        line1.endXProperty().bind(pane.widthProperty().divide(3));
        line1.endYProperty().bind(pane.heightProperty().subtract(10));
        line1.setStrokeWidth(10);
        line1.setStroke(COLOR_TABLERO);

        Line line2 = new Line();
        line2.setStartX(10);
        line2.startYProperty().bind(pane.heightProperty().divide(3));
        line2.endXProperty().bind(pane.widthProperty().subtract(10));
        line2.endYProperty().bind(pane.heightProperty().divide(3));
        line2.setStrokeWidth(10);
        line2.setStroke(COLOR_TABLERO);

        Line line3 = new Line();
        line3.setStartY(10);
        line3.startXProperty().bind(pane.widthProperty().divide(1.5));
        line3.endYProperty().bind(pane.heightProperty().subtract(10));
        line3.endXProperty().bind(pane.widthProperty().divide(1.5));
        line3.setStrokeWidth(10);
        line3.setStroke(COLOR_TABLERO);

        Line line4 = new Line();
        line4.setStartX(10);
        line4.startYProperty().bind(pane.heightProperty().divide(1.5));
        line4.endXProperty().bind(pane.widthProperty().subtract(10));
        line4.endYProperty().bind(pane.heightProperty().divide(1.5));
        line4.setStrokeWidth(10);
        line4.setStroke(COLOR_TABLERO);

        tablero.getChildren().addAll(line1, line2, line3, line4);

        int filas = 3;
        int columnas = 3;
        double padding = 20;

        for (int x = 0; x < filas; x++) {
            for (int y = 0; y < columnas; y++) {
                Line l1 = new Line();
                Line l2 = new Line();

                // Línea \
                l1.startXProperty().bind(pane.widthProperty().divide(columnas).multiply(y).add(padding));
                l1.startYProperty().bind(pane.heightProperty().divide(filas).multiply(x).add(padding));

                l1.endXProperty().bind(pane.widthProperty().divide(columnas).multiply(y + 1).subtract(padding));
                l1.endYProperty().bind(pane.heightProperty().divide(filas).multiply(x + 1).subtract(padding));

                // Línea /
                l2.startXProperty().bind(pane.widthProperty().divide(columnas).multiply(y + 1).subtract(padding));
                l2.startYProperty().bind(pane.heightProperty().divide(filas).multiply(x).add(padding));

                l2.endXProperty().bind(pane.widthProperty().divide(columnas).multiply(y).add(padding));
                l2.endYProperty().bind(pane.heightProperty().divide(filas).multiply(x + 1).subtract(padding));

                l1.setStrokeWidth(GROSOR_X);
                l2.setStrokeWidth(GROSOR_X);

                l1.setStroke(COLOR_ASPAS);
                l2.setStroke(COLOR_ASPAS);
                l1.setFill(Color.TRANSPARENT);

                // Circulos
                Circle circle = new Circle();
                circle.radiusProperty().bind(pane.widthProperty().divide(10));

                circle.centerXProperty().bind(
                        pane.widthProperty().divide(6).add(pane.widthProperty().divide(3).multiply(y))
                );

                circle.centerYProperty().bind(
                        pane.heightProperty().divide(6).add(pane.heightProperty().divide(3).multiply(x))
                );

                circle.setStroke(COLOR_CIRCULOS);
                circle.setFill(Color.TRANSPARENT);
                circle.setStrokeWidth(GROSOR_O);

                // Hitbox de celdas
                Rectangle rect = new Rectangle();
                rect.xProperty().bind(pane.widthProperty().divide(3).multiply(y));
                rect.yProperty().bind(pane.heightProperty().divide(3).multiply(x));

                rect.widthProperty().bind(pane.widthProperty().divide(3));
                rect.heightProperty().bind(pane.heightProperty().divide(3));

                rect.setFill(Color.TRANSPARENT);
                rect.setStroke(Color.TRANSPARENT);

                int xx = x;
                int yy = y;

                rect.setOnMouseClicked(e -> {
                    if (puntuacion[xx][yy] == 0) {
                        if (turnoX) {
                            l1.setVisible(true);
                            l2.setVisible(true);

                            puntuacion[xx][yy] = 1;
                        } else {
                            circle.setVisible(true);

                            puntuacion[xx][yy] = -1;
                        }
                    }

                    turnoX = !turnoX;
                    revisarTablero(celdas);
                });

                l1.setVisible(false);
                l2.setVisible(false);
                circle.setVisible(false);

                circulos.getChildren().add(circle);
                aspas.getChildren().addAll(l1, l2);
                celdas.getChildren().add(rect);
            }
        }
    }

    private void revisarTablero(Group celdas) {
        // Filas
        for (int x = 0; x < 3; x++) {
            int suma = puntuacion[x][0] + puntuacion[x][1] + puntuacion[x][2];
            if (suma == 3 || suma == -3) {
                marcarGanadoras(celdas, new int[][]{{x,0},{x,1},{x,2}});
            }
        }

        // Columnas
        for (int y = 0; y < 3; y++) {
            int suma = puntuacion[0][y] + puntuacion[1][y] + puntuacion[2][y];
            if (suma == 3 || suma == -3) {
                marcarGanadoras(celdas, new int[][]{{0,y},{1,y},{2,y}});
            }
        }

        // Diagonal 1
        int d1 = puntuacion[0][0] + puntuacion[1][1] + puntuacion[2][2];
        if (d1 == 3 || d1 == -3) {
            marcarGanadoras(celdas, new int[][]{{0,0},{1,1},{2,2}});
        }

        // Diagonal 2
        int d2 = puntuacion[0][2] + puntuacion[1][1] + puntuacion[2][0];
        if (d2 == 3 || d2 == -3) {
            marcarGanadoras(celdas, new int[][]{{0,2},{1,1},{2,0}});
        }
    }

    private void marcarGanadoras(Group celdas, int[][] pos) {
        for (int[] c : pos) {
            Rectangle r = getRectangulo(celdas, c[0], c[1]);
            r.setFill(Color.YELLOW);
        }
    }

    private Rectangle getRectangulo(Group celdas, int x, int y) {
        return (Rectangle) celdas.getChildren().get(x * 3 + y);
    }
}