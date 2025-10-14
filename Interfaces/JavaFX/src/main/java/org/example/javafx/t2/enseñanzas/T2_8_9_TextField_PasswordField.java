package org.example.javafx.t2.enseñanzas;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class T2_8_9_TextField_PasswordField extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        TextField tf = new TextField();
        PasswordField pf = new PasswordField();

        tf.setPromptText("Type your User name");
        pf.setPromptText("Type your Password");

        tf.appendText("Samu");
        VBox vBox = new VBox();
        vBox.getChildren().addAll(tf, pf);

        stage.setScene(new Scene(vBox));
        stage.setTitle("T2_8_9_TextField_PasswordField");
        stage.show();
    }
}
