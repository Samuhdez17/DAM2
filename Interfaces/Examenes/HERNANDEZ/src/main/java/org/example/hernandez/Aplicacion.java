package org.example.hernandez;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Aplicacion extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Aplicacion.class.getResource("examen.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Examen Ia");
        stage.setScene(scene);
        stage.show();
    }
}
