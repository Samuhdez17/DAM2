package org.example.calculadora;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.util.concurrent.atomic.AtomicBoolean;

public class Controlador {
    boolean[] botonesPulsados =  new boolean[4]; // suma, resta, multi, div

    @FXML
    private Button borrar;

    @FXML
    private Button cero;

    @FXML
    private Button cerrarApp;

    @FXML
    private Button cinco;

    @FXML
    private HBox cuartaFila;

    @FXML
    private Button cuatro;

    @FXML
    private Button division;

    @FXML
    private Button dos;

    @FXML
    private Button multi;

    @FXML
    private Button nueve;

    @FXML
    private Button ocho;

    @FXML
    private Label pantalla;

    @FXML
    private HBox primeraFila;

    @FXML
    private Button resta;

    @FXML
    private Button resultado;

    @FXML
    private HBox segundaFila;

    @FXML
    private Button seis;

    @FXML
    private Button siete;

    @FXML
    private Button suma;

    @FXML
    private HBox terceraFila;

    @FXML
    private Button tres;

    @FXML
    private Button uno;

    @FXML
    void cerrarApp(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    void darResultado(ActionEvent event) {
    }

    @FXML
    void dividir(ActionEvent event) {
        pulsado(division, 4);

    }

    @FXML
    void limpiarPantalla(ActionEvent event) {

    }

    @FXML
    void multiplicar(ActionEvent event) {
        pulsado(multi, 3);
    }

    @FXML
    void restar(ActionEvent event) {
        pulsado(resta, 1);

    }

    @FXML
    void sumar(ActionEvent event) {
        pulsado(suma, 0);
    }


    void pulsado(Button boton, int id) {
        int contador = 0;

        for (int i = 0; i < botonesPulsados.length; i++) {


            if (botonesPulsados[i] && i == id) boton.setStyle("-fx-background-color: orange;");
            else boton.setStyle("-fx-background-color: red;");

        }
    }
}
