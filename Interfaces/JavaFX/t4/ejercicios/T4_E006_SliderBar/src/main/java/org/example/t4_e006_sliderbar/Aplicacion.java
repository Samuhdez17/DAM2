package org.example.t4_e006_sliderbar;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Aplicacion extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Aplicacion.class.getResource("slider.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("T4.6 RGBA");
        stage.setScene(scene);
        stage.show();
    }
}
