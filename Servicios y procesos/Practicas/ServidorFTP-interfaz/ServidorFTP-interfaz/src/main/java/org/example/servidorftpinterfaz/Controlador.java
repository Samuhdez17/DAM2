package org.example.servidorftpinterfaz;

import cliente.ClienteFTP;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Controlador {
    @FXML
    private Button actualizarBt;

    @FXML
    private Button conectBt;

    @FXML
    private PasswordField contraField;

    @FXML
    private Button descargarBt;

    @FXML
    private TextField hostField;

    @FXML
    private ListView<String> listaArchivos;

    @FXML
    private TextArea logs;

    @FXML
    private TextField puertoField;

    @FXML
    private Button subirBt;

    @FXML
    private TextField usuarioField;

    private ClienteFTP clienteFTP;

    @FXML
    void initialize() {
        desactivarBotones();

        actualizarBtActualizar();

        hostField.textProperty().addListener((observable, oldValue, newValue) -> actualizarBtActualizar());
        puertoField.textProperty().addListener((observable, oldValue, newValue) -> actualizarBtActualizar());
        usuarioField.textProperty().addListener((observable, oldValue, newValue) -> actualizarBtActualizar());
        contraField.textProperty().addListener((observable, oldValue, newValue) -> actualizarBtActualizar());
    }

    private void activarBotones() {
        subirBt.setDisable(false);
        descargarBt.setDisable(false);
        actualizarBt.setDisable(false);
    }

    private void desactivarBotones() {
        subirBt.setDisable(true);
        descargarBt.setDisable(true);
        actualizarBt.setDisable(true);
    }

    private void actualizarBtActualizar() {
        boolean botonDeshabilitado = hostField.getText().isBlank() ||
                puertoField.getText().isBlank() ||
                usuarioField.getText().isBlank() ||
                contraField.getText().isBlank();

        conectBt.setDisable(botonDeshabilitado);
    }

    @FXML
    void conectar(ActionEvent event) {
        try {
            clienteFTP = new ClienteFTP(hostField.getText(), Integer.parseInt(puertoField.getText()));

            String respuesta = clienteFTP.logIn(usuarioField.getText(), contraField.getText());
            if (!respuesta.contains("correctamente"))
                throw new IOException(respuesta);

            logs.appendText(respuesta + "\n");
            listarArchivos();

            desactivarLogIn();
            activarBotones();
        } catch (NumberFormatException e) {
            logs.appendText("Error: Puerto debe ser un número válido\n");

        } catch (IOException e) {
            logs.appendText(e.getMessage() + "\n");
        }
    }

    @FXML
    void descargar(ActionEvent event) {
        String archivoSeleccionado = listaArchivos.getSelectionModel().getSelectedItem();

        if (archivoSeleccionado == null || archivoSeleccionado.isEmpty()) {
            logs.appendText("Selecciona un archivo de la lista para descargar\n");
            return;
        }

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Guardar archivo");
        fileChooser.setInitialFileName(archivoSeleccionado);

        Stage stage = (Stage) descargarBt.getScene().getWindow();
        File archivoDestino = fileChooser.showSaveDialog(stage);

        if (archivoDestino != null) {
            try {
                clienteFTP.cargarArchivo(archivoSeleccionado, archivoDestino.getAbsolutePath());
                logs.appendText("Archivo descargado correctamente en: " + archivoDestino.getAbsolutePath() + "\n");

            } catch (IOException e) {
                logs.appendText(e.getMessage() + "\n");
            }
        } else {
            logs.appendText("Operación de descarga cancelada.\n");
        }
    }

    @FXML
    void subir(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar archivo para subir");

        Stage stage = (Stage) subirBt.getScene().getWindow();
        File archivoSeleccionado = fileChooser.showOpenDialog(stage);

        if (archivoSeleccionado != null) {
            try {
                logs.appendText("Subiendo archivo: " + archivoSeleccionado.getName() + "\n");

                clienteFTP.subirArchivo(archivoSeleccionado.getAbsolutePath());
                logs.appendText("Archivo '" + archivoSeleccionado.getName() + "' subido correctamente\n");

                actualizarLista(event);

            } catch (IOException e) {
                logs.appendText("Error al subir el archivo: " + e.getMessage() + "\n");
            }
        } else {
            logs.appendText("Subida cancelada por el usuario\n");
        }
    }

    @FXML
    void actualizarLista(ActionEvent event) {
        listaArchivos.getItems().clear();
        listarArchivos();
    }


    private void listarArchivos() {
        try {
            List<String> archivos = clienteFTP.listarArchivos();

            for (String archivo : archivos)
                listaArchivos.getItems().add(archivo);

            logs.appendText("Lista actualizada\n");

        } catch (IOException e) {
            logs.appendText(e.getMessage());
        }
    }

    private void desactivarLogIn() {
        hostField.setDisable(true);
        puertoField.setDisable(true);
        usuarioField.setDisable(true);
        contraField.setDisable(true);
        conectBt.setDisable(true);
    }
}
