package org.example.t4_e005instalador;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

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
    static String tipoInstalacion = "";
    static ArrayList<String> componentes = new ArrayList<>();

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

    //ELEMENTOS ESCENA 5
    static String ruta = "";

    @FXML
    private Label almacenamiento;

    @FXML
    private Button botonAtrasE5;

    @FXML
    private Button botonCancelarE5;

    @FXML
    private Button botonInstalarE5;

    @FXML
    private Button examinar;

    @FXML
    private TextField rutaDescarga;

    // ELEMENTOS ESCENA 6
    @FXML
    private Button botonAtrasE6;

    @FXML
    private Button botonCancelarE6;

    @FXML
    private Label componentesFinalE6;

    @FXML
    private Label idiomaFinalE6;

    @FXML
    private Label rutaFinalFinalE6;

    @FXML
    private Label tipoInstalacionFinalE6;

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
        componentes.clear();
        tipoInstalacion = comboBoxE4.getSelectionModel().getSelectedItem().toString();
        if (checkBox1E4.isSelected()) componentes.add(checkBox1E4.getText());
        if (checkBox2E4.isSelected()) componentes.add(checkBox2E4.getText());
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

    //MÉTODOS ESCENA 5
    @FXML
    void cambiarRuta(ActionEvent event) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File file = directoryChooser.showDialog(null);

        ruta = file.getAbsolutePath();
        rutaDescarga.setText(file.getAbsolutePath());
    }

    @FXML
    void ir54(ActionEvent event) throws IOException {
        cargarStage(event, 3);
    }

    @FXML
    void irInstalacion(ActionEvent event) throws IOException {
        cargarStage(event, 5);
    }

    // MÉTODOS ESCENA 6
    @FXML
    void ir65(ActionEvent event) throws IOException {
        cargarStage(event, 4);
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

        if (almacenamiento != null && rutaDescarga != null) {
            File disco = new File("C:\\");
            long espacioLibre = disco.getFreeSpace();

            double espacio = (double) espacioLibre / (1024 * 1024 * 1024);

            almacenamiento.setText(String.format("%.2fGB", espacio));

            rutaDescarga.setText("C:\\Program Files (x86)\\Bandicam");
            ruta = rutaDescarga.getText();
        }

        if (idiomaFinalE6 != null && tipoInstalacionFinalE6 != null && componentesFinalE6 != null && rutaFinalFinalE6 != null) {
            idiomaFinalE6.setText(idioma);
            tipoInstalacionFinalE6.setText(tipoInstalacion);

            StringBuilder componentesFinales = new StringBuilder();
            for (int i = 0; i < componentes.size(); i++) {
                if (i == componentes.size() - 1) componentesFinales.append(componentes.get(i));
                else componentesFinales.append(componentes.get(i) + ", ");
            }
            componentesFinalE6.setText(componentesFinales.toString());

            rutaFinalFinalE6.setText(ruta);
        }
    }

    private void cargarValoresGenerales() {
        checkBox1E4.setSelected(true);
        checkBox2E4.setSelected(true);
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
