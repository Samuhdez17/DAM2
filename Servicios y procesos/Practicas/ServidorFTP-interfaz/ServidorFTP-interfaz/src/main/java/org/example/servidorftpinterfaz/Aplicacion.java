package org.example.servidorftpinterfaz;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Aplicacion extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Aplicacion.class.getResource("ftp.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("App FTP");
        stage.setScene(scene);
        stage.show();
    }
}
