package org.example.javafx.t3.enseñanzas;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class T3_4_SetOnAction_RadioButton extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        RadioButton radioButton1 = new RadioButton("Opción 1");
        RadioButton radioButton2 = new RadioButton("Opcion 2");
        ToggleGroup toggleGroup = new ToggleGroup();
        radioButton1.setToggleGroup(toggleGroup);
        radioButton2.setToggleGroup(toggleGroup);
        Button button = new Button("Submit");
        Label labelOpcion = new  Label("");

        VBox vBox = new VBox();
        vBox.setSpacing(10);
        vBox.getChildren().addAll(radioButton1,radioButton2,button,labelOpcion);
        Scene scene = new Scene(vBox, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();

        button.setOnAction(e -> {
            if (radioButton1.isSelected()) {
                labelOpcion.setText("Selececionaste la Opción 1");
            }else if (radioButton2.isSelected()) {
                labelOpcion.setText("Selececionaste la Opcion 2");
            }

        });
    }
}
