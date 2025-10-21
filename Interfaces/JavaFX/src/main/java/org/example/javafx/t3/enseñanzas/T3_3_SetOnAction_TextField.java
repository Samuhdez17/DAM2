package org.example.javafx.t3.enseñanzas;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class T3_3_SetOnAction_TextField extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Label label = new Label("Write your name");
        VBox vBox = new VBox(label);
        vBox.setAlignment(Pos.CENTER);

        TextField textFieldname = new TextField();
        textFieldname.setPromptText("Enter your name");
        textFieldname.setMaxSize(200, 200);


        Button button = new Button("and Click here");
        Label labelmodificado = new Label("");
        vBox.getChildren().addAll(textFieldname,button,labelmodificado);


        Scene scene = new Scene(vBox, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();

        button.setOnAction(e ->
                labelmodificado.setText("Name: " + textFieldname.getText()));
    }
}
