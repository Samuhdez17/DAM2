package org.example.javafx.t2.ejercicios;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;


public class T2_4_LogIn extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        Label bienvenida = new Label("Welcome! Please, sign in");
        bienvenida.setFont(Font.font("Times New Roman", 20));
        bienvenida.setAlignment(Pos.CENTER);
        bienvenida.setStyle("-fx-padding: 0 0 0 25");

        Label user = new Label("Username:");
        TextField username = new TextField();

        Label passw = new Label("Password:");
        PasswordField password = new PasswordField();

        Label checkBoxContent = new Label("Show password characters? ");
        checkBoxContent.setStyle("-fx-font-style: italic");
        CheckBox cb = new CheckBox();

        Button signIn = new Button("Sign In");
        signIn.setDisable(true);

        HBox hb1 = new HBox(10);
        hb1.getChildren().addAll(user, username);
        hb1.setAlignment(Pos.CENTER);

        HBox hb2 = new HBox(10);
        hb2.getChildren().addAll(passw, password);
        hb2.setAlignment(Pos.CENTER);

        VBox vb1 = new VBox(20);
        vb1.getChildren().addAll(bienvenida, hb1, hb2);

        HBox hb3 = new HBox();
        hb3.getChildren().addAll(checkBoxContent, cb);
        hb3.setAlignment(Pos.CENTER);

        VBox vb2 = new VBox(30);
        vb2.getChildren().addAll(hb3, signIn);
        vb2.setAlignment(Pos.BASELINE_RIGHT);

        AnchorPane anchorPane = new AnchorPane(vb1, vb2);
        AnchorPane.setLeftAnchor(vb1, 70.0);
        AnchorPane.setRightAnchor(vb1, 70.0);
        AnchorPane.setTopAnchor(vb1, 50.0);

        AnchorPane.setRightAnchor(vb2, 50.0);
        AnchorPane.setBottomAnchor(vb2, 30.0);

        Scene scene = new Scene(anchorPane, 400, 300);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("login.fxml");
        stage.show();
    }
}
