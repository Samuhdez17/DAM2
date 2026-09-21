package com.example.view.cliente;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class ControladorCliente {
    protected final String host = "localhost";
    protected final int puerto = 1312;

    @FXML
    protected Button btOk;

    @FXML
    protected Label lbEspera;

    @FXML
    protected Label lbFirmaValida;

    @FXML
    protected Label lbMsg;

    @FXML
    protected VBox vbContenido;

    @FXML
    public void initialize() {
        lbMsg.textProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null && !newVal.isBlank()) {
                lbEspera.setVisible(false);
                vbContenido.setVisible(true);
            }
        });
        
        Thread hiloCliente = new Thread(new HiloCliente(this));

        hiloCliente.setDaemon(true);
        hiloCliente.start();
    }

    @FXML
    void ocultarCampos(ActionEvent event) {
        vbContenido.setVisible(false);
        lbEspera.setVisible(true);
    }

}
