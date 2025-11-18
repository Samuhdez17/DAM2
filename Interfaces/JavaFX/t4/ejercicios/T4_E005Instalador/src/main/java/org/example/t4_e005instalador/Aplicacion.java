package org.example.t4_e005instalador;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Aplicacion extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Aplicacion.class.getResource("escena1.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Installer language");
        stage.setScene(scene);
        stage.show();
    }
}
