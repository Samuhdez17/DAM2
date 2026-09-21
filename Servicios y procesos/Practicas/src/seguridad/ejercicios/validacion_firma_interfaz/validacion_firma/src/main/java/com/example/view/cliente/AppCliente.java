package com.example.view.cliente;

import java.io.IOException;

import com.example.view.servidor.AppServidor;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AppCliente extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AppServidor.class.getResource("/com/example/cliente.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setOnCloseRequest(event -> {
            if (HiloCliente.hiloActivo != null) {
                HiloCliente.hiloActivo.cerrar();
            }
        });
        stage.show();
    }
}