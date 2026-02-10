package org.example.t6_1_reproductor;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Controlador {
    static boolean reproduciendo = true;

    @FXML
    private Button btMute;

    @FXML
    private Button btPausa;

    @FXML
    void alternarReproduccion(ActionEvent event) {
        reproduciendo = !reproduciendo;

        if (reproduciendo) {
            btPausa.setGraphic(new ImageView(new Image("pausar.png")));
        } else {
            btPausa.setGraphic(new ImageView(new Image("continuar.png")));
        }
    }

    @FXML
    void maximizar(ActionEvent event) {

    }

    @FXML
    void mutear(ActionEvent event) {

    }

}
