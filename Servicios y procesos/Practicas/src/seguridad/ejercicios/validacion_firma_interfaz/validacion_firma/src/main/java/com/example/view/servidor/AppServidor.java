package com.example.view.servidor;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AppServidor extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AppServidor.class.getResource("/com/example/inicioServidor.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setOnCloseRequest(event -> {
            if (Servidor.hiloActivo != null) {
                Servidor.hiloActivo.enviarMensaje("");
            }
        });
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}