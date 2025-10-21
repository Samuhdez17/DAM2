package org.example.javafx.t3.enseñanzas;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;
import javafx.stage.Stage;

public class T3_1_SetOnAction_Button extends Application {
    private int contador = 0;
    @Override
    public void start(Stage primaryStage) {
        Button button1 = new Button("Haz click aqui");
        Label label = new Label("");
        VBox vBox = new VBox(button1, label);
        vBox.setAlignment(Pos.CENTER);


        Scene scene = new Scene(vBox, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();

        button1.setOnAction(event -> label.setText("Hiciste Click"));


        /* Código sustituido por la expresion LAMBDA
        button1.setOnAction(new  EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                label.setText("Hiciste Click");
            }
        });
        */
    }

    public static void main(String[] args) {
        launch(args);
    }
}
