package org.example.calculadora;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class Aplicacion extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Aplicacion.class.getResource("calculadora.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), Color.TRANSPARENT);
        stage.setTitle("Calculadora");
        stage.setScene(scene);
        stage.show();
    }
}
