package org.example.t4_e005instalador;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.io.IOException;

public class Controlador {
    Stage stage;

    // ELEMENTOS ESCENA 1
    static String idioma = "";
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

    // ELEMENTOS ESCENA 3
    @FXML
    private Button botonAceptoE3;

    @FXML
    private Button botonAtrasE3;

    @FXML
    private Button botonCancelarE3;

    // ELEMENTOS ESCENA 4
    @FXML
    private Button botonAtrasE4;

    @FXML
    private Button botonCancelarE4;

    @FXML
    private Button botonSiguienteE4;

    @FXML
    private CheckBox checkBox1E4;

    @FXML
    private CheckBox checkBox2E4;

    @FXML
    private ComboBox comboBoxE4;

    // MÉTODOS ESCENA 1
    @FXML
    void cancelar(ActionEvent event) { // También se usa en las escenas con boton de cancelar
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
        cargarStage(event, 2);
    }

    // MÉTODOS ESCENA 3
    @FXML
    void ir34(ActionEvent event) throws IOException { // Acepto
        cargarStage(event, 3);
    }

    @FXML
    void ir32(ActionEvent event) throws IOException { // Atrás
        cargarStage(event, 1);
    }

    // MÉTODOS ESCENA 4
    @FXML
    void ir43(ActionEvent event) throws IOException {
        cargarStage(event, 2);
    }

    @FXML
    void ir45(ActionEvent event) throws IOException {
        cargarStage(event, 4);
    }

    @FXML
    void instalacionGeneral(ActionEvent event) {
        if (comboBoxE4.getValue().equals("General")) cargarValoresGenerales();
    }



    @FXML
    void valoresInstalacion(ActionEvent event) {
        if (checkBox1E4.isSelected() && checkBox2E4.isSelected()) comboBoxE4.setValue("General");
        else                                                      comboBoxE4.setValue("Personalizado");
    }

    // MÉTODOS AUXILIARES
    @FXML
    void initialize() {
        if (comboBoxE1 != null) {
            comboBoxE1.getItems().addAll("Español", "English", "Français", "Româna");
            comboBoxE1.setValue("Español");
        }

        if (comboBoxE4 != null) {
            comboBoxE4.getItems().addAll("General", "Personalizado");
            comboBoxE4.setValue("General");
            cargarValoresGenerales();
        }
    }

    private void cargarValoresGenerales() {
        checkBox1E4.setSelected(true);
        checkBox2E4.setSelected(true);
    }

    private void cargarValoresPersonalizados() {
        checkBox1E4.setSelected(false);
        checkBox2E4.setSelected(false);
    }

    private void cargarStage(ActionEvent event, int escena) throws IOException {
        FXMLLoader fxmlLoader = null;
        Scene scene =  null;

        switch (escena) {
            case 1 -> fxmlLoader = new FXMLLoader(Aplicacion.class.getResource("escena2.fxml"));
            case 2 -> fxmlLoader = new FXMLLoader(Aplicacion.class.getResource("escena3.fxml"));
            case 3 -> fxmlLoader = new FXMLLoader(Aplicacion.class.getResource("escena4.fxml"));
            case 4 -> fxmlLoader = new FXMLLoader(Aplicacion.class.getResource("escena5.fxml"));
            case 5 -> fxmlLoader = new FXMLLoader(Aplicacion.class.getResource("escena6.fxml"));
        }

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
