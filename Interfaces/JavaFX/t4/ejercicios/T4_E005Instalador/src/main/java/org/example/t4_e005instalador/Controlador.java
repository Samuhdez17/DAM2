package org.example.t4_e005instalador;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.io.IOException;

public class Controlador {
    Stage stage;

    // ELEMENTOS ESCENA 1
    static String idioma = "";
    @FXML
    private Button boton1;
    @FXML
    private Button boton2;

    @FXML
    private Button botonCancelarE1;

    @FXML
    private Button botonOKE1;

    @FXML
    private ComboBox comboBoxE1;


    // ELEMENTOS ESCENA 2
    @FXML
    private Button botonCancelarE2;

    @FXML
    private Button botonSiguienteE2;

    // MÉTODOS ESCENA 1
    @FXML
    void cancelar(ActionEvent event) { // También se usa en escena 2
        setStage(event);
        stage.close();
    }

    @FXML
    void ir12(ActionEvent event) throws IOException {
        idioma = comboBoxE1.getSelectionModel().getSelectedItem().toString();
        cargarStage(event, 1);
    }

    // MÉTODOS ESCENA 2
    @FXML
    void ir23(ActionEvent event) throws IOException {
        setStage(event);
        cargarStage(event, 2);
    }

    // MÉTODOS AUXILIARES
    @FXML
    void initialize() {
        if (comboBoxE1 != null) {
            comboBoxE1.getItems().addAll("Español", "English", "Français", "Româna");
            comboBoxE1.setValue("Español");
        }
    }

    private void cargarStage(ActionEvent event, int escena) throws IOException {
        FXMLLoader fxmlLoader = null;
        Scene scene =  null;

        if (escena == 1) fxmlLoader = new FXMLLoader(Aplicacion.class.getResource("escena2.fxml"));
        else if (escena == 2) fxmlLoader = new FXMLLoader(Aplicacion.class.getResource("escena3.fxml"));

        if (fxmlLoader != null) scene = new Scene(fxmlLoader.load());

        setStage(event);
        stage.setScene(scene);
        if (escena >= 1) stage.setTitle("Instalación de Bandicam");
    }

    private void setStage(Event e) {
        Button boton = (Button) e.getSource();
        this.stage = (Stage) boton.getScene().getWindow();
    }
}
