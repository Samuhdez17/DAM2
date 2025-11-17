package org.example.t4_e003cambioescena;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {
    @FXML
    private Button botonEDos;
    @FXML
    private Button botonEUno;
    @FXML
    private TextField mensajeA1, mensajeA2;
    @FXML
    private Label mensajeE1, mensajeE2;

    static String contenido = "";


    @FXML
    void irADos(ActionEvent event) {
        try {
            contenido = mensajeA2.getText();
            cargarStage(event, 1);
        } catch (IOException e) {
            throw  new RuntimeException(e);
        }
    }

    @FXML
    void initialize() {
        if (mensajeE2 != null) mensajeE2.setText(contenido);

        if (mensajeE1 != null) mensajeE1.setText(contenido);
    }

    @FXML
    void botonEDos(ActionEvent event) {}

    @FXML
    void irAUno(ActionEvent event)  {
        try {
            contenido = mensajeA1.getText();
            cargarStage(event, 2);
        } catch (IOException e) {
            throw  new RuntimeException(e);
        }
    }

    private void cargarStage(ActionEvent event, int escena) throws IOException {
        FXMLLoader fxmlLoader = null;
        Scene scene =  null;

             if (escena == 1) fxmlLoader = new FXMLLoader(Application.class.getResource("scene2.fxml"));
             else if (escena == 2) fxmlLoader = new FXMLLoader(Application.class.getResource("scene1.fxml"));


        if (fxmlLoader != null) scene = new Scene(fxmlLoader.load());

        Button boton = (Button) event.getSource();
        Stage stage = (Stage) boton.getScene().getWindow();
        stage.setScene(scene);
    }
}
