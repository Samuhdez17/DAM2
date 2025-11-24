package org.example.hernandez;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Controlador {
    @FXML
    private Button alDer;

    @FXML
    private Button alIzq;

    @FXML
    private CheckBox cbCursiva;

    @FXML
    private CheckBox cbNegrita;

    @FXML
    private Label contenidoFinal;

    @FXML
    private RadioButton rbAzul;

    @FXML
    private RadioButton rbRojo;

    @FXML
    private RadioButton rbVerde;

    @FXML
    private TextField txtFiled;

    @FXML
    void initialize() {
        ToggleGroup grupo = new ToggleGroup();
        rbAzul.setToggleGroup(grupo);
        rbRojo.setToggleGroup(grupo);
        rbVerde.setToggleGroup(grupo);
    }

    @FXML
    void alinearDere(ActionEvent event) {
        if (cbCursiva.isSelected())                             contenidoFinal.setStyle("-fx-alignment: CENTER_RIGHT; -fx-font-style: ITALIC;");
        if (cbNegrita.isSelected())                             contenidoFinal.setStyle("-fx-alignment: CENTER_RIGHT; -fx-font-weight: BOLD");
        if (cbCursiva.isSelected() && cbNegrita.isSelected())   contenidoFinal.setStyle("-fx-alignment: CENTER_RIGHT; -fx-font-style: ITALIC; -fx-font-weight: BOLD");
    }

    @FXML
    void alinearIzq(ActionEvent event) {
        if (cbCursiva.isSelected())                             contenidoFinal.setStyle("-fx-alignment: CENTER_LEFT; -fx-font-style: ITALIC;");
        if (cbNegrita.isSelected())                             contenidoFinal.setStyle("-fx-alignment: CENTER_LEFT; -fx-font-weight: BOLD");
        if (cbCursiva.isSelected() && cbNegrita.isSelected())   contenidoFinal.setStyle("-fx-alignment: CENTER_LEFT; -fx-font-style: ITALIC; -fx-font-weight: BOLD");
    }

    @FXML
    void cambiarAAzul(ActionEvent event) {
    }

    @FXML
    void cambiarACursiva(ActionEvent event) {
        if (cbCursiva.isSelected()) contenidoFinal.setStyle("-fx-font-style: ITALIC;");
        else if (cbCursiva.isSelected() && cbNegrita.isSelected()) contenidoFinal.setStyle("-fx-font-style: ITALIC; -fx-font-weight: BOLD");
        else                        contenidoFinal.setStyle("-fx-font-style: NORMAL;");
    }

    @FXML
    void cambiarANegrita(ActionEvent event) {
        if (cbNegrita.isSelected()) contenidoFinal.setStyle("-fx-font-weight: BOLD");
        else if (cbNegrita.isSelected() && cbCursiva.isSelected()) contenidoFinal.setStyle("-fx-font-style: ITALIC; -fx-font-weight: BOLD");
        else                        contenidoFinal.setStyle("-fx-font-weight: NORMAL;");
    }

    @FXML
    void cambiarARojo(ActionEvent event) {

    }

    @FXML
    void cambiarAVerde(ActionEvent event) {

    }

    @FXML
    void enviarTexto(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            contenidoFinal.setText(txtFiled.getText());
        }
    }
}
