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
    void irADos(ActionEvent event) {
        try {
            cargarStage(event, 1);
        } catch (IOException e) {
            throw  new RuntimeException(e);
        }
    }

    @FXML
    void irAUno(ActionEvent event)  {
        try {
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
