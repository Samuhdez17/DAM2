package org.example.t1;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class T1_4_FlowPane_2 extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        FlowPane flowPane1 = new FlowPane();
        FlowPane flowPane2 = new FlowPane();
        FlowPane flowPane3 = new FlowPane();

        VBox vBox = new VBox(20);

//      FORMA 1
        for (int i = 1 ; i <= 10 ; i++) {
            Button button = new Button("Boton " + i);

            flowPane1.getChildren().add(button);
        }

        Button boton8C = (Button) flowPane1.getChildren().get(7);
        boton8C.setText("ESTE ES EL 8");


//      FORMA 2
        for (int i = 1 ; i <= 10 ; i++) {
            Button button = new Button("Boton " + i);
            flowPane2.getChildren().add(button);

            button.setId("b" + i);
        }

        Button button1 = (Button) flowPane2.lookup("#b1");
        button1.setText("BOTON 1 LOL");

//      FORMA 3
        Button[] botones = new Button[10];
        for (int i = 0 ; i < botones.length ; i++) {
            botones[i] = new Button("Boton " + (i+1));
        }

        flowPane3.getChildren().addAll(botones);
        botones[9].setText("Boton 10!");

        vBox.getChildren().addAll(flowPane1, flowPane2, flowPane3); // Se pueden meter varios layouts dentro de un layout

        Scene scene = new Scene(vBox, 300, 200);
        stage.setScene(scene);
        stage.setTitle("FlowPane con bucle for");
        stage.show();
    }
}
