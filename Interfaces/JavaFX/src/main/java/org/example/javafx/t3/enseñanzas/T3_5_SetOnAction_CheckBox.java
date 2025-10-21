package org.example.javafx.t3.enseñanzas;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class T3_5_SetOnAction_CheckBox extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        Label label1 = new Label("Elija alguna o varias de las siguientes opciones: ");
        CheckBox checkBox1 = new CheckBox("Opcion 1");
        CheckBox checkBox2 = new CheckBox("Opcion 2");
        CheckBox checkBox3 = new CheckBox("Opcion 3");
        Button buttonAceptar = new Button("Aceptar");
        Label label2 = new Label("Por favor, seleccione antes de darle a aceptar: ");


        VBox vBox = new VBox();
        vBox.setSpacing(10);
        vBox.getChildren().addAll(label1, checkBox1, checkBox2, checkBox3, buttonAceptar, label2);
        Scene scene = new Scene(vBox, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();

        buttonAceptar.setOnAction(e -> {
            StringBuilder selectedOptions = new StringBuilder("Opciones Seleccionadas: \n");

            if (checkBox1.isSelected()) selectedOptions.append(checkBox1.getText()).append("\n");
            if (checkBox2.isSelected()) selectedOptions.append(checkBox2.getText()).append("\n");
            if (checkBox3.isSelected()) selectedOptions.append(checkBox3.getText()).append("\n");

            if (selectedOptions.toString().equals("Opciones Seleccionadas: \n")) {
                label2.setText("Ninguna Opción seleccionada.");
            } else {
                label2.setText(selectedOptions.toString());
            }
        });
    }
}
