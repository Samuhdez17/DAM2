package org.example.t4_e003cambioescena;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {
    @FXML
    private Button botonEUno;

    @FXML
    private Button botonEDos;

    @FXML
    void irADos(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("scene2.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        cargarStage(event, scene);
    }

    @FXML
    void irAUno(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("scene1.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        cargarStage(event, scene);
    }

    private void cargarStage(ActionEvent event, Scene scene) {
        Button boton = (Button) event.getSource();
        Stage stage = (Stage) boton.getScene().getWindow();
        stage.setScene(scene);
    }
}
