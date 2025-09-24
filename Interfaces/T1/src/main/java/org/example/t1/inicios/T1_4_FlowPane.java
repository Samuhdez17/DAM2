package org.example.t1.inicios;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class T1_4_FlowPane extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        Button button1 = new Button("Button1");
        Button button2 = new Button("Button2");
        Button button3 = new Button("Button3");
        Button button4 = new Button("Button4");

        FlowPane layout = new FlowPane();
        layout.getChildren().addAll(button1, button2, button3, button4);

        Scene scene = new Scene(layout);
        stage.setScene(scene);

        stage.show();
    }
}
