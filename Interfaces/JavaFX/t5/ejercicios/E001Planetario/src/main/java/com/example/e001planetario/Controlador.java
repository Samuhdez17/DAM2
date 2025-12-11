package com.example.e001planetario;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;

public class Controlador {
    private static ToggleButton[] botonesPlanetas = new ToggleButton[8];
    private static Circle[] orbitas = new Circle[8];
    private static Circle[] planetas = new Circle[8];

    @FXML
    private Button botonAuto;

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
        for (int i = 1 ; i <= 8 ; i++) {
            botonesPlanetas[i].setId("b" + i);
            orbitas[i].setId("o" + i);
            planetas[i].setId("p" + i);
        }

    }

    @FXML
    void alternarAuto(ActionEvent event) {

    }

    @FXML
    void rotar(MouseEvent event) {

    }

    @FXML
    void seleccionarPlaneta(ActionEvent event) {

    }

}
