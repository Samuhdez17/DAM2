package org.example.javafx.t3.enseñanzas;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;
import javafx.stage.Stage;

public class T3_2_SetOnAction_Contador extends Application {
    private int contador = 0;
    @Override
    public void start(Stage primaryStage) {

        Button button2 = new Button("Incrementar contador");
        Label numeroContador = new Label(contador+"");
        Button button3 = new Button("Reiniciar contador");
        VBox vBox = new VBox( button2, numeroContador ,button3);

        vBox.setAlignment(Pos.CENTER);




        Scene scene = new Scene(vBox, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();

        button2.setOnAction(event -> numeroContador.setText(""+(contador = contador + 1)));
        button3.setOnAction(event -> numeroContador.setText(""+(contador=0)));

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
