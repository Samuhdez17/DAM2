package org.example.javafx.t1.inicios;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class T1_2_VBox extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        Button button2 = new Button("Button2");
        Button button1 = new Button("Button1");
        Button button3 = new Button("Button3");

        VBox layout = new VBox();
        layout.getChildren().addAll(button1,button3, button2);

        Scene scene = new Scene(layout, 500 ,300);
        stage.setScene(scene);

        stage.show();
    }
}
