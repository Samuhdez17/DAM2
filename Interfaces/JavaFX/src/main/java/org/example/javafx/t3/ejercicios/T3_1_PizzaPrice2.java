package org.example.javafx.t3.ejercicios;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class T3_1_PizzaPrice2 extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        TextField textField = new TextField("0");
        textField.setMaxWidth(30.0);
        int[] precios = { 1, 2, 3, 4, 5, 6, 0 };

        CheckBox[] checkBoxes = new CheckBox[7];
        checkBoxes[0] = new CheckBox(String.format("Extra cheese %d$", precios[0]));
        checkBoxes[1] = new CheckBox(String.format("Pepperoni %d$", precios[1]));
        checkBoxes[2] = new CheckBox(String.format("Sausage %d$", precios[2]));
        checkBoxes[3] = new CheckBox(String.format("Green pepper %d$", precios[3]));
        checkBoxes[4] = new CheckBox(String.format("Onion %d$", precios[4]));
        checkBoxes[5] = new CheckBox(String.format("Anchovies %d$", precios[5]));
        checkBoxes[6] = new CheckBox("Tip ");

        Button boton1 = new Button("Pizza price calculator");

        Label label1 = new Label("Price: ");
        Label precio = new Label("10$");
        label1.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
        precio.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
        HBox hBoxPrecio = new HBox(label1, precio);
        hBoxPrecio.setAlignment(Pos.CENTER);

        Button ordenar = new Button("Order");
        VBox vBoxPrecio = new VBox(3, hBoxPrecio, ordenar);
        vBoxPrecio.setAlignment(Pos.CENTER);

        HBox tips = new HBox(1, checkBoxes[6], textField, new Label("$"));
        VBox vBox1 = new VBox(3, checkBoxes[0], checkBoxes[1], checkBoxes[2]);
        VBox vBox2 = new VBox(3, checkBoxes[3], checkBoxes[4], checkBoxes[5], tips);
        tips.setAlignment(Pos.CENTER);
        tips.setStyle("-fx-padding: 0 30 0 0");
        vBox1.setStyle("-fx-padding: 10 0");
        vBox2.setStyle("-fx-padding: 10 0");

        BorderPane bp = new BorderPane();
        bp.setLeft(vBox1);
        bp.setRight(vBox2);
        bp.setCenter(boton1);
        bp.setBottom(vBoxPrecio);
        bp.setStyle("-fx-background-color: pink");

        BorderPane orderReceived = new BorderPane(new Label("Order received! Please wait"));
        BorderPane.setAlignment(orderReceived, Pos.CENTER);

        Scene scene = new Scene(bp, 400, 200);
        Scene sceneOrderReceived = new Scene(orderReceived, 400, 200);
        stage.setScene(scene);
        stage.setTitle("T2_ChekBox_1");
        stage.show();
        stage.setResizable(false);

        // SET ON ACTION
        boton1.setOnAction(e -> {
            int precioPizza = 10;
            precios[6] = Integer.parseInt(textField.getText());

            for (int i = 0; i < checkBoxes.length; i++) {
                if (checkBoxes[i].isSelected()) {
                    precioPizza += precios[i];
                }
            }

            precio.setText(precioPizza + "$");
        });

        ordenar.setOnAction(e -> {
            stage.setScene(sceneOrderReceived);
        });
    }
}
