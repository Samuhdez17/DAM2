package org.example.javafx.t1.inicios;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class T1_3_StacPane extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        Button button1 = new Button("Button                                          1");
        Button button2 = new Button("Button                      2");
        Button button3 = new Button("Button 3");

        button1.setPrefSize(300, 300);
        button2.setPrefSize(200, 200);
        button3.setPrefSize(100, 100);

        StackPane layout = new StackPane();
        layout.getChildren().addAll(button1, button2, button3);

        Scene scene = new Scene(layout, 500 ,500);
        stage.setScene(scene);

        stage.show();
    }
}
