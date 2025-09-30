package org.example.javafx.t2.inicios;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class T2_2_Buttons extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws FileNotFoundException {
        FileInputStream fis = new FileInputStream("images/foto.jpg");
        Image imagen  = new Image(fis);
        ImageView imageView = new ImageView(imagen);

        Button boton1 = new Button("Boton normal");
        Button boton2 = new Button("Boton con texto muy largo pero no pasa nada porque puedo arreglarme");
        Button boton3 = new Button("Boton desactivado");
        Button boton4 = new Button("Boton con imagen", imageView);

        boton2.setWrapText(true);
        boton3.setDisable(true);

        VBox vbox = new VBox(10);
        vbox.getChildren().addAll(boton1, boton2, boton3, boton4);

        Scene escena = new Scene(vbox);
        stage.setScene(escena);
        stage.setTitle("T2_2_Botones");
        stage.show();
    }
}
