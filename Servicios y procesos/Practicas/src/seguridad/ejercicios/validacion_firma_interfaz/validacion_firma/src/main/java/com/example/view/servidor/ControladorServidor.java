package com.example.view.servidor;

import java.io.IOException;
import java.util.concurrent.SynchronousQueue;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;

public class ControladorServidor {
    protected final int puerto = 1312;
    private Thread hiloServidor;

    @FXML
    protected TextField tfMensaje;

    @FXML
    public void initialize() {
        if (hiloServidor == null || !hiloServidor.isAlive()) {
            hiloServidor = new Thread(new Servidor(this));
            hiloServidor.setDaemon(true);
            hiloServidor.start();
        }
    }

    @FXML
    void enviarMensaje(ActionEvent event) {
        String texto = tfMensaje.getText();
        tfMensaje.clear();

        if (Servidor.hiloActivo != null) {
            new Thread(() -> Servidor.hiloActivo.enviarMensaje(texto)).start();
        }
    }

    protected void cargarStage(int escena) {
        String fxml = (escena == 1)
                ? "/com/example/inicioServidor.fxml"
                : "/com/example/servidor.fxml";

        Platform.runLater(() -> {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(AppServidor.class.getResource(fxml));
                Stage stage = (Stage) Window.getWindows().get(0);
                stage.setScene(new Scene(fxmlLoader.load()));
            } catch (IOException e) {
                System.out.println("Error cargando escena: " + e);
                e.printStackTrace(); // ← añade esto
            } catch (Exception e) {
                System.out.println("Error inesperado: " + e);
                e.printStackTrace(); // ← y esto
            }
        }); 
    }
}
