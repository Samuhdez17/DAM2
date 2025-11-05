package org.example.t4;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class HelloController {
    int contador = 0;

    @FXML
    private Button botonContador;

    @FXML
    private Button botonReiniciar;

    @FXML
    private VBox vBox;

    @FXML
    private Label valor;

    @FXML
    void incrementar(ActionEvent event) {
        contador++;
        valor.setText(String.valueOf(contador));
    }

    @FXML
    void reiniciar(ActionEvent event) {
        contador = 0;
        valor.setText(String.valueOf(contador));
    }

}
