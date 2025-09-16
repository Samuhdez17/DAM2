package org.example.t1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Button button  = new Button("boton 1");
        Button button1 = new Button("boton 2");
        Button button2 = new Button("boton 3");

        HBox hbox = new HBox();
        hbox.getChildren().addAll(button,button1,button2);
//        hbox.getChildren().add(button);

        Scene scene = new Scene(hbox);
        stage.setScene(scene);

        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
