package org.example.javafx.t2.enseñanzas;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;

public class T2_1_Labels extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage stage) throws IOException {
        FileInputStream fis1 = new FileInputStream("images/foto.jpg");
        Image imagen  = new Image(fis1);
        ImageView imageView = new ImageView(imagen);

        Label label1 = new Label("Etiqueta simple");
        Label label2 = new Label("Etiqueta en rojo");
        Label label3 = new Label("Etiqueta con fondo amarillo");
        Label label4 = new Label("Etiqueta Times New Roman 32 y cursiva");
        Label label5 = new Label("Etiqueta con tamaño mínimo");
        Label label6 = new Label("Etiqueta con imagen", imageView);

        label2.setTextFill(Color.RED);
        label3.setStyle("-fx-background-color: yellow");
        label4.setFont(Font.font("Times New Roman", FontPosture.ITALIC, 32));
        label5.setMinSize(200, 200);
        label5.setStyle("-fx-background-color: cyan");

        VBox vbox = new VBox(10);
        vbox.getChildren().addAll(label1, label2, label3, label4, label5, label6);
        Scene scene = new Scene(vbox);

        stage.setScene(scene);
        stage.setTitle("T2_1_Labels");
        stage.show();
    }
}
