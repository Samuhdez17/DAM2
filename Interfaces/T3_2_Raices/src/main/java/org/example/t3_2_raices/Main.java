package org.example.t3_2_raices;

import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.LinkedList;

public class Main extends Application {

    private static final String IMAGES_ALUMNOS = "images/alumnos";
    private static final String IMAGES_ICONOS = "images/iconos";
    private static final Label[] NOMBRE_ALUMNOS = {
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

    private static final int[] TAMANIO_VENTANA = {1400, 800};

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws FileNotFoundException {
        // SCENE 1
        ImageView[] imagenesAlumnos = setImageViewAlumnos();

        VBox[] alumnos = setAlumnos(imagenesAlumnos);

        Button aceptar = crearBoton("_Aceptar", "aceptar.png");
        Button borrar = crearBoton("_Borrar", "borrar.png");
        Button cerrar = crearBoton("_Cerrar", "cerrar.png");

        DatePicker datePicker = new DatePicker();
        datePicker.setValue(LocalDate.now());
        datePicker.prefHeightProperty().bind(aceptar.heightProperty());

        HBox botonesHbox = new HBox();
        botonesHbox.getChildren().addAll(aceptar, borrar, cerrar);
        botonesHbox.setAlignment(Pos.CENTER);
        botonesHbox.setSpacing(10);
        botonesHbox.setStyle("-fx-padding: 20");

        FlowPane flowPane = new FlowPane();
        flowPane.setOrientation(Orientation.HORIZONTAL);
        flowPane.setPrefWidth(Double.MAX_VALUE);
        flowPane.setHgap(10);
        flowPane.setVgap(10);
        for (VBox alumno : alumnos) flowPane.getChildren().add(alumno);

        ScrollPane scrollPane = new ScrollPane(flowPane);
        scrollPane.setFitToWidth(true);

        AnchorPane anchorPane = new AnchorPane();
        anchorPane.getChildren().addAll(datePicker, botonesHbox);
        AnchorPane.setTopAnchor(datePicker, 20.0);
        AnchorPane.setLeftAnchor(datePicker, 10.0);

        AnchorPane.setRightAnchor(botonesHbox, 10.0);

        VBox vBox = new VBox();
        vBox.getChildren().addAll(anchorPane, scrollPane);

        Scene faltasAlumnos = new Scene(vBox, TAMANIO_VENTANA[0], TAMANIO_VENTANA[1]);

        stage.setScene(faltasAlumnos);
        stage.setTitle("Raices");
        stage.show();
        
        aceptar.setOnAction(e -> {
            // SCENE 2
            LinkedList<HBox> elementosListView = new LinkedList<>();
            for (VBox alumno : alumnos) {
                HBox hBox = new HBox();
                Label nombreAlumno = new Label();
                Label fecha = new Label();
                Label tipoFalta = new Label();

                Label labelAlumno = (Label) alumno.getChildren().get(0);
                nombreAlumno.setText(labelAlumno.getText());
                fecha.setText(String.valueOf(datePicker.getValue()));

                HBox botones = (HBox) alumno.getChildren().get(1);
                for (Node nodo : botones.getChildren()) {
                    if (nodo instanceof RadioButton radio) {
                        if (radio.isSelected()) tipoFalta.setText(radio.getText());
                    }
                }

                hBox.getChildren().addAll(nombreAlumno, fecha, tipoFalta);
                hBox.setSpacing(20);
                elementosListView.add(hBox);
            }

            ListView listView = new ListView();
            listView.getItems().addAll(elementosListView);

            BorderPane boton = new BorderPane();
            Button botonRegresar = crearBoton("_Regresar", "regresar.png");
            boton.setCenter(botonRegresar);
            botonRegresar.setAlignment(Pos.CENTER);

            SplitPane splitPane = new SplitPane(listView, boton);
            Scene registroFaltas = new Scene(splitPane, TAMANIO_VENTANA[0], TAMANIO_VENTANA[1]);

            stage.setScene(registroFaltas);
            stage.setTitle("Registro faltas");

            botonRegresar.setOnAction(e2 -> stage.setScene(faltasAlumnos));
        });

        borrar.setOnAction(e -> {
            for (VBox alumno : alumnos) {
                HBox botones = (HBox) alumno.getChildren().get(1);

                for (Node nodo : botones.getChildren()) {
                    if (nodo instanceof RadioButton radio) {
                        radio.setSelected(false);
                    }
                }
            }
        });

        cerrar.setOnAction(e -> stage.close());
    }

    private Button crearBoton(String contenido, String image) {
        Button boton = new Button(contenido);
        try {
            ImageView img = new ImageView(new Image(new FileInputStream(IMAGES_ICONOS + "/" + image)));
            boton.setMnemonicParsing(true);
            img.setFitWidth(40);
            img.setFitHeight(40);

            boton.setGraphic(img);
            boton.setContentDisplay(ContentDisplay.TOP);
        } catch (Exception e) {
            System.out.println("No se encontró la imagen");
        }
        return boton;
    }

    private static VBox[] setAlumnos(ImageView[] imagenesAlumnos) {
        VBox[] alumnos = new VBox[NOMBRE_ALUMNOS.length];

        for (int i = 0; i < alumnos.length ; i++) {
            HBox opcionesHbox = getOpcionesHbox();
            opcionesHbox.setAlignment(Pos.CENTER);
            opcionesHbox.setSpacing(10);

            alumnos[i] = new VBox();
            NOMBRE_ALUMNOS[i].setGraphic(imagenesAlumnos[i]);
            NOMBRE_ALUMNOS[i].setContentDisplay(ContentDisplay.TOP);
            alumnos[i].setSpacing(10);
            alumnos[i].setStyle("-fx-padding: 20");

            alumnos[i].getChildren().addAll(NOMBRE_ALUMNOS[i], opcionesHbox);
            alumnos[i].setAlignment(Pos.CENTER);
        }

        return alumnos;
    }

    private static ImageView[] setImageViewAlumnos() throws FileNotFoundException {
        ImageView[] imagenesAlumnos = new ImageView[NOMBRE_ALUMNOS.length];

        for (int i = 0; i < imagenesAlumnos.length ; i++) {
            try {
                FileInputStream ruta = new FileInputStream(IMAGES_ALUMNOS + "/" + (i+1) + ".jpg");
                imagenesAlumnos[i] = new ImageView(new Image(ruta));

            } catch (Exception e) {
                FileInputStream rutaDefault = new FileInputStream(IMAGES_ALUMNOS + "/default.jpg");
                imagenesAlumnos[i] = new ImageView(new Image(rutaDefault));
            }

            imagenesAlumnos[i].setFitWidth(100);
            imagenesAlumnos[i].setFitHeight(100);
        }

        return imagenesAlumnos;
    }

    private static HBox getOpcionesHbox() {
        RadioButton[] opciones = { new RadioButton("I"), new RadioButton("J"), new RadioButton("R") };

        ToggleGroup tg = new ToggleGroup();
        for (RadioButton btn : opciones) btn.setToggleGroup(tg);

        HBox opcionesHbox = new HBox();
        opcionesHbox.getChildren().addAll(opciones);
        return opcionesHbox;
    }
}
