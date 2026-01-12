package org.example.ensenanzas;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class T5_5_TicTacToe extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {

        int alto = 300;
        int ancho = 300;

        Pane pane = new Pane();
        Scene scene = new Scene(pane, ancho, alto);
        stage.setScene(scene);
        stage.setTitle("T5_6_TicTacToe");
        stage.show();

        Color colorTablero = Color.GREY;
        Color colorAspas = Color.GREEN;
        Color colorCirculos = Color.RED;

        Line v1 = new Line(ancho / 3, 10, ancho / 3, alto - 10);
        Line v2 = new Line((int)(ancho / 1.5), 10, (int)(ancho / 1.5), alto - 10);
        Line h1 = new Line(10, alto / 3, ancho - 10, alto / 3);
        Line h2 = new Line(10, (int)(alto / 1.5), ancho - 10, (int)(alto / 1.5));

        v1.setStrokeWidth(10);
        v2.setStrokeWidth(10);
        h1.setStrokeWidth(10);
        h2.setStrokeWidth(10);

        v1.setStroke(colorTablero);
        v2.setStroke(colorTablero);
        h1.setStroke(colorTablero);
        h2.setStroke(colorTablero);

        // Grupos
        Group groupX = new Group();
        Group groupO = new Group();

        pane.getChildren().addAll(v1, v2, h1, h2, groupX, groupO);

        // Tamaño de celda
        int cellW = ancho / 3;
        int cellH = alto / 3;

        // División en 6 partes para centrar círculos
        int stepX = ancho / 6;
        int stepY = alto / 6;

        // Dibujar X y O
        for (int fila = 0; fila < 3; fila++) {
            for (int col = 0; col < 3; col++) {

                // Coordenadas enteras para las X
                int x1 = col * cellW + 20;
                int y1 = fila * cellH + 20;
                int x2 = (col + 1) * cellW - 20;
                int y2 = (fila + 1) * cellH - 20;

                Line l1 = new Line(x1, y1, x2, y2);
                Line l2 = new Line(x2, y1, x1, y2);

                l1.setStrokeWidth(10);
                l2.setStrokeWidth(10);
                l1.setStroke(colorAspas);
                l2.setStroke(colorAspas);

                groupX.getChildren().addAll(l1, l2);

                // Círculo rojo centrado
                int centerX = stepX * (1 + col * 2);
                int centerY = stepY * (1 + fila * 2);

                Circle circle = new Circle(centerX, centerY, Math.min(cellW, cellH) / 6);
                circle.setStroke(colorCirculos);
                circle.setFill(Color.TRANSPARENT);
                circle.setStrokeWidth(8);

                groupO.getChildren().add(circle);
            }
        }
    }
}