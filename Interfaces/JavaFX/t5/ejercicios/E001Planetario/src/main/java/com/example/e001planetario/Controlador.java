package com.example.e001planetario;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

public class Controlador {
    private Circle[] orbitas;
    private Circle[] planetas;
    private Timeline[] lineasTemporales = new Timeline[8];
    private final boolean[] planetaEnAuto = new boolean[8];
    private static final String[][] datos = {
            {"0'055 Tierras","2440 km","57 millones de km","88 días","59 días","0"},
            {"0'866 Tierras","6052 km","108 millones de km","225 días","243 días","0"},
            {"5'97e24 kg","6371 km","150 millones de km","365 días","24 horas","1"},
            {"0'107 Tierras","3390 km","227 millones de km","687 días","24'6 horas","2"},
            {"317 Tierras","71492 km","778 millones de km","11 años","10 horas","≥ 95"},
            {"95'2 Tierras","58232 km","1426 millones de km","29 años","10 horas","≥ 146"},
            {"14,5 Tierras","25362 km","2871 millones de km","84 años","17 horas","≥ 27"},
            {"17'1 Tierras","24622 km","4498 millones de km","164 años","16 horas","≥ 14"},
    };

    private int planetaSeleccionado = -1;
    private double posAnterior = 0;

    @FXML
    private ToggleButton b1;

    @FXML
    private ToggleButton b2;

    @FXML
    private ToggleButton b3;

    @FXML
    private ToggleButton b4;

    @FXML
    private ToggleButton b5;

    @FXML
    private ToggleButton b6;

    @FXML
    private ToggleButton b7;

    @FXML
    private ToggleButton b8;

    @FXML
    private ToggleButton botonAuto;

    @FXML
    private Label d1;

    @FXML
    private Label d2;

    @FXML
    private Label d3;

    @FXML
    private Label d4;

    @FXML
    private Label d5;

    @FXML
    private Label d6;

    @FXML
    private Circle o1;

    @FXML
    private Circle o2;

    @FXML
    private Circle o3;

    @FXML
    private Circle o4;

    @FXML
    private Circle o5;

    @FXML
    private Circle o6;

    @FXML
    private Circle o7;

    @FXML
    private Circle o8;

    @FXML
    private Circle p1;

    @FXML
    private Circle p2;

    @FXML
    private Circle p3;

    @FXML
    private Circle p4;

    @FXML
    private Circle p5;

    @FXML
    private Circle p6;

    @FXML
    private Circle p7;

    @FXML
    private Circle p8;

    @FXML
    private Slider slider;

    @FXML
    void initialize() {
        orbitas = new Circle[] {o1, o2, o3, o4, o5, o6, o7, o8};
        planetas = new Circle[] { p1, p2, p3, p4, p5, p6, p7, p8 };

        for (int i = 0 ; i < orbitas.length ; i++) {
            Rotate rotate = new Rotate();
            rotate.setPivotX(orbitas[i].getCenterX());
            rotate.setPivotY(orbitas[i].getCenterY());

            KeyValue kvRotate = new KeyValue(rotate.angleProperty(), 360);
            KeyFrame kf = new KeyFrame(Duration.seconds(setDuracion(i)), kvRotate);
            lineasTemporales[i] = new Timeline(kf);

            lineasTemporales[i].setCycleCount(Timeline.INDEFINITE);
        }
    }

    private double setDuracion(int i) {
        double durMax = 60190.0;
        double tiempo = 0;
        String perOrbital = datos[i][3];
        String[] valores = perOrbital.split(" ");

        if (valores[1].equals("días")) {
            tiempo = Double.parseDouble(valores[0]);

        } else if (valores[1].equals("años")) {
            tiempo = (Double.parseDouble(valores[0]) * 365);
        }

        return (tiempo / durMax) * 60.0;
    }

    @FXML
    void alternarAuto(ActionEvent event) {

    }

    @FXML
    void rotar(MouseEvent event) {

    }

    @FXML
    void seleccionarPlaneta(ActionEvent event) {
        ToggleButton tg = (ToggleButton) event.getSource();

        String id = tg.getId();
        int numeroPlaneta = Integer.parseInt(
                String.valueOf(
                        id.charAt(id.length() - 1)
                )
        );

        if (planetaSeleccionado == -1) {
            planetaSeleccionado = numeroPlaneta;

            orbitas[planetaSeleccionado].setStyle("-fx-border-color: red");
        }

        else if (planetaSeleccionado == numeroPlaneta) {
            orbitas[planetaSeleccionado].setStyle("-fx-border-color: white");

            planetaSeleccionado = -1;
        }

        System.out.println(planetaSeleccionado);
    }

}
