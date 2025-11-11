package org.example.calculadora;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.concurrent.atomic.AtomicInteger;

public class Controlador {
    boolean[] botonesPulsados =  new boolean[4]; // suma, resta, multi, div
    double valorA = 0;
    double valorB = 0;
    char operacion = ' ';
    boolean reescribir = false;

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
        Button boton = (Button) event.getSource();
        Stage stage = (Stage) boton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void darResultado(ActionEvent event) {
        valorB = Double.parseDouble(pantalla.getText());

        switch (operacion) {
            case  '+' -> {
                pantalla.setText(String.format("%.0f",valorA + valorB));
                pulsarBoton(suma, 0);
            }
            case  '-' -> {
                pantalla.setText(String.format("%.0f",valorA - valorB));
                pulsarBoton(resta, 1);
            }
            case  '*' -> {
                pantalla.setText(String.format("%.0f",valorA * valorB));
                pulsarBoton(multi, 2);
            }
            case  '/' -> {
                pantalla.setText(String.format("%.4f",valorA / valorB));
                pulsarBoton(division, 3);
            }
        }
    }

    @FXML
    void dividir(ActionEvent event) {
        pulsarBoton(division, 3);
        operacion = '/';
    }

    @FXML
    void escribirNumero(ActionEvent event) {
        Button boton = (Button) event.getSource();
        if (pantalla.getText().equals("0") || reescribir) {
            pantalla.setText(boton.getText());
            reescribir = false;
        }
        else pantalla.setText(pantalla.getText() + boton.getText());
    }

    @FXML
    void limpiarPantalla(ActionEvent event) {
        pantalla.setText("0");
    }

    @FXML
    void multiplicar(ActionEvent event) {
        pulsarBoton(multi, 2);
        operacion = '*';
    }

    @FXML
    void restar(ActionEvent event) {
        pulsarBoton(resta, 1);
        operacion = '-';
    }

    @FXML
    void sumar(ActionEvent event) {
        pulsarBoton(suma, 0);
        operacion = '+';
    }

    private void pulsarBoton(Button boton, int id) {
        cambiarEstado(id);
        cambiarColor(boton, id);
        valorA = Double.parseDouble(pantalla.getText());
        reescribir = true;
    }

    private void cambiarColor(Button boton, int id) {
        if (!botonesPulsados[id]) {
            if (id != 3) boton.setStyle("-fx-background-color: orange; -fx-background-radius: 6");
            else boton.setStyle("-fx-background-color: orange; -fx-background-radius: 0 0 20 0");
        }
        else {
            if (id != 3) boton.setStyle("-fx-background-color: red; -fx-background-radius: 6");
            else boton.setStyle("-fx-background-color: red; -fx-background-radius: 0 0 20 0");
        }
    }

    void cambiarEstado(int id) {
        int numBotonesPulsados = 0;
        for (Boolean b : botonesPulsados) if (b) numBotonesPulsados++;

        if (numBotonesPulsados == 0) botonesPulsados[id] = !botonesPulsados[id];
        else if (botonesPulsados[id]) botonesPulsados[id] = false;
    }
}
