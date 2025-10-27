package org.example.t3_2_raices;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Main extends Application {

    private static final String IMAGES_ALUMNOS = "images/alumnos";
    private static final String IMAGES_ICONOS = "images/iconos";

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws FileNotFoundException {
        Label[] nombreAlumnos = {
                new Label("Acosta Carvajal, Jhon"),
                new Label("Barreno Tejero, Iván"),
                new Label("Buzarhon Navarro, María"),
                new Label("Campos Bermejo, Mario"),
                new Label("Carrasco Murillo, David"),
                new Label("Cerdán Banda, Jorge"),
                new Label("Fernandes Gomes, David"),
                new Label("Gómez Espinosa Muñoz, Iker"),
                new Label("Hernández Guerrero, Samuel"),
                new Label("Ibáñez del Cerro, Ismael"),
                new Label("Lin, Pablo"),
                new Label("Luque Ruiz, Carmen Jesús"),
                new Label("Muñoz Jiménez, Diego"),
                new Label("Murillo Chavez, Patrick"),
                new Label("Navia Rojo, Pablo"),
                new Label("Negro Oliva, Sergio"),
                new Label("Quicaño Huapaya, Sergio"),
                new Label("Rivera Garrido, Raúl"),
                new Label("Rodríguez Rodríguez, David"),
                new Label("Ruiz Clemente, Alber"),
                new Label("Samraoui Samraoui, Hiba"),
                new Label("Vaquero Portillo, Eric"),
                new Label("Venegas Cárdenas, Andrés"),
                new Label("Zambrano Álvarez, Naim")
        };

        ImageView[] imagenesAlumnos = new ImageView[nombreAlumnos.length];
        for (int i = 0 ; i < imagenesAlumnos.length ; i++) {
            FileInputStream ruta = new FileInputStream(IMAGES_ALUMNOS + "/" + nombreAlumnos[i] + ".jpg");
            String rutaDefault = IMAGES_ALUMNOS + "/default.jpg";
            try {
                imagenesAlumnos[i] = new ImageView(new Image(ruta));
            } catch (Exception e) {
                imagenesAlumnos[i] = new ImageView(new Image(rutaDefault));
            }
        }

        VBox[] alumnos = new VBox[nombreAlumnos.length];
        RadioButton[] opcionesFalta = {
                new RadioButton("I"),
                new RadioButton("J"),
                new RadioButton("R")
        };

        VBox opciones = new VBox();
        ToggleGroup tg = new ToggleGroup();
        for (RadioButton btn : opcionesFalta) {
            btn.setToggleGroup(tg);
            opciones.getChildren().add(btn);
        }

        for (int i = 0 ; i < alumnos.length ; i++) {
            alumnos[i] = new VBox();
            nombreAlumnos[i].setGraphic(imagenesAlumnos[i]);

            alumnos[i].getChildren().addAll(nombreAlumnos[i], opciones);
        }

        FlowPane flowPane = new FlowPane();
        for (VBox alumno : alumnos) flowPane.getChildren().add(alumno);
        ScrollPane scrollPane = new ScrollPane(flowPane);

        AnchorPane anchorPane = new AnchorPane();

        Scene faltasAlumnos = new Scene(scrollPane);
        stage.setScene(faltasAlumnos);
        stage.setTitle("Raices");
        stage.show();
    }
}
