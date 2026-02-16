package org.example.t6_1_reproductor;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.util.List;

public class Controlador {
    @FXML
    private VBox barraReproduccion;

    @FXML
    private Button btMute;

    @FXML
    private Button btPausa;

    @FXML
    private Button btMaximizar;

    @FXML
    private ListView<String> listaVideos;

    @FXML
    private StackPane principal;

    @FXML
    private Slider progresoVideo;

    @FXML
    private MediaView video;

    @FXML
    private Slider volumen;

    static boolean reproduciendo;
    private boolean muteado;
    private boolean maximizado;
    private boolean actualizandoSlider = false;

    private MediaPlayer reproductor;
    private double volumenAnterior;
    private ObservableList<File> archivosVideo;
    private int indiceActual = -1;

    private Timeline ocultarInterfaz;
    private static final double TIMER = 1.5;
    @FXML
    void initialize() {
        reproduciendo = false;
        muteado = false;
        archivosVideo = FXCollections.observableArrayList();

        // Ocultar interfaz
        barraReproduccion.setVisible(false);
        listaVideos.setVisible(false);
        volumen.setVisible(false);

        // Configuracion del MediaView
        video.setPreserveRatio(true);
        video.fitWidthProperty().bind(principal.widthProperty());
        video.fitHeightProperty().bind(principal.heightProperty());

        // Configuracion del volumen
        volumen.setValue(50);
        volumen.setMin(0);
        volumen.setMax(100);

        // Configuracion del slider de progreso
        progresoVideo.setMin(0);
        progresoVideo.setValue(0);

        // Configuracion del ListView para que cuando hagas doble click ponga el video
        listaVideos.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {

                int videoSeleccionado = listaVideos.getSelectionModel().getSelectedIndex();

                if (videoSeleccionado >= 0) {
                    reproducirVideo(videoSeleccionado);
                }
            }

            reiniciarTemporizador();
        });

        // Configuracion del root para que se oculte la interfaz en caso de no mover el raton
        principal.setOnMouseMoved(event -> {
            barraReproduccion.setVisible(true);
            listaVideos.setVisible(true);
            principal.setCursor(Cursor.DEFAULT);

            reiniciarTemporizador();
        });

        iniciarTemporizador();
    }

    @FXML
    void actualizarMomento(MouseEvent event) {
        if (reproductor != null && !actualizandoSlider) {
            Duration duracionTotal = reproductor.getTotalDuration();

            if (duracionTotal != null) {
                Duration nuevaDuracion = duracionTotal.multiply(progresoVideo.getValue() / progresoVideo.getMax());
                reproductor.seek(nuevaDuracion);
            }
        }
    }

    @FXML
    void alternarReproduccion(ActionEvent event) {
        if (reproductor == null)
            return;

        reproduciendo = !reproduciendo;
        ImageView img = new ImageView();
        img.setFitWidth(20);
        img.setFitHeight(20);

        if (reproduciendo) {
            reproductor.play();
            img.setImage(new Image(("/pausar.png")));
        } else {
            reproductor.pause();
            img.setImage(new Image(("/continuar.png")));
        }

        btPausa.setGraphic(img);
    }

    @FXML
    void anteriorVideo(ActionEvent event) {
        if (archivosVideo.isEmpty())
            return;

        indiceActual--;
        if (indiceActual < 0)
            indiceActual = archivosVideo.size() - 1;

        reproducirVideo(indiceActual);
    }

    @FXML
    void dragOver(DragEvent event) {
        Dragboard db = event.getDragboard();

        if (db.hasFiles()) {
            // Verificar que tiene algun video
            boolean tieneVideos = db.getFiles().stream().anyMatch(file -> esVideo(file.getName()));

            if (tieneVideos) {
                event.acceptTransferModes(TransferMode.COPY);

                barraReproduccion.setVisible(true);
                listaVideos.setVisible(true);

                listaVideos.setOpacity(0.9);
            }
        }

        event.consume();
    }

    @FXML
    void drop(DragEvent event) {
        Dragboard db = event.getDragboard();
        boolean hayVideo = false;

        if (db.hasFiles()) {
            List<File> archivos = db.getFiles();

            for (File archivo : archivos) {
                if (esVideo(archivo.getName())) {
                    if (!archivosVideo.contains(archivo)) {
                        archivosVideo.add(archivo);

                        listaVideos.getItems().add(archivo.getName());
                        hayVideo = true;
                    }
                }
            }

            if (hayVideo && reproductor == null && !archivosVideo.isEmpty())
                reproducirVideo(0);
        }

        listaVideos.setOpacity(0.8);
        event.setDropCompleted(hayVideo);
        event.consume();
    }

    @FXML
    void maximizar(ActionEvent event) {
        maximizado = !maximizado;

        ImageView img = new ImageView();
        img.setFitWidth(28);
        img.setFitHeight(27);

        if (maximizado)
            img.setImage(new Image(("/minimizar.png")));

        else
            img.setImage(new Image(("/maximizar.png")));

        btMaximizar.setGraphic(img);

        Stage stage = (Stage) principal.getScene().getWindow();
        stage.setFullScreen(!stage.isFullScreen());
    }

    @FXML
    void mostrarVolumen(MouseEvent event) {
        volumen.setVisible(true);
    }

    @FXML
    void ocultarVolumen(MouseEvent event) {
        volumen.setVisible(false);
    }

    @FXML
    void mostrarInterfaz(MouseEvent event) {
        barraReproduccion.setVisible(true);
        listaVideos.setVisible(true);
        principal.setCursor(Cursor.DEFAULT);

        reiniciarTemporizador();
    }

    @FXML
    void ocultarInterfaz(MouseEvent event) {
        barraReproduccion.setVisible(false);
        listaVideos.setVisible(false);
    }

    @FXML
    void mutear(ActionEvent event) {
        if (reproductor == null)
            return;

        muteado = !muteado;
        ImageView img = new ImageView();
        img.setFitWidth(30);
        img.setFitHeight(31);

        if (muteado) {
            volumenAnterior = reproductor.getVolume();
            reproductor.setVolume(0);
            img.setImage(new Image(("/muted.png")));

        } else {
            reproductor.setVolume(volumenAnterior);
            volumen.setValue(volumenAnterior * 1000);
            img.setImage(new Image(("/volumen.png")));
        }

        btMute.setGraphic(img);
    }

    @FXML
    void parar(ActionEvent event) {
        if (reproductor != null) {
            reproductor.stop();
            reproduciendo = false;

            ImageView img = new ImageView();
            img.setFitWidth(20);
            img.setFitHeight(20);
            img.setImage(new Image(("/continuar.png")));
            btPausa.setGraphic(img);

            progresoVideo.setValue(0);
        }
    }

    @FXML
    void siguienteVideo(ActionEvent event) {
        if (archivosVideo.isEmpty())
            return;

        indiceActual++;
        if (indiceActual >= archivosVideo.size())
            indiceActual = 0;

        reproducirVideo(indiceActual);
    }

    private boolean esVideo(String nombreArchivo) {
        String extension = nombreArchivo.substring(nombreArchivo.lastIndexOf(".") + 1).toLowerCase();

        return extension.equals("mp4") || extension.equals("avi") || extension.equals("mkv") ||
                extension.equals("mov") || extension.equals("flv") || extension.equals("wmv") ||
                extension.equals("webm") || extension.equals("m4v");
    }

    private void iniciarTemporizador() {
        ocultarInterfaz = new Timeline(new KeyFrame(
                Duration.seconds(TIMER),
                event -> {
                    barraReproduccion.setVisible(false);
                    listaVideos.setVisible(false);

                    principal.setCursor(Cursor.NONE);
                }
        ));

        ocultarInterfaz.setCycleCount(1);
    }

    private void reiniciarTemporizador() {
        if (ocultarInterfaz != null) {
            ocultarInterfaz.stop();
            ocultarInterfaz.playFromStart();
        }
    }

    private void reproducirVideo(int indice) {
        if (indice < 0 || indice >= archivosVideo.size())
            return;

        // Detener el reproductor anterior si existe
        if (reproductor != null) {
            reproductor.stop();
            reproductor.dispose();
        }

        indiceActual = indice;
        File videoAReproducir = archivosVideo.get(indice);

        try {
            Media media = new Media(videoAReproducir.toURI().toString());
            reproductor = new MediaPlayer(media);
            video.setMediaPlayer(reproductor);

            // Configurar volumen
            reproductor.setVolume(volumen.getValue() / 1000.0);

            // Listener para actualizar el slider de volumen
            volumen.valueProperty().addListener((observable, oldValue, newValue) -> {
                if (!muteado)
                    reproductor.setVolume(newValue.doubleValue() / 1000.0);

                reiniciarTemporizador();
            });

            // Configurar la barra de progreso
            reproductor.currentTimeProperty().addListener((observable, oldValue, newValue) -> {
                if (!progresoVideo.isValueChanging()) {
                    actualizandoSlider = true;

                    Duration total = reproductor.getTotalDuration();

                    if (total != null && total.toMillis() > 0)
                        progresoVideo.setValue((newValue.toMillis() / total.toMillis()) * progresoVideo.getMax());

                    actualizandoSlider = false;
                }
            });

            // Configurar el maximo del slider cuando se sepa la duracion
            reproductor.setOnReady(() -> {
                Duration total = reproductor.getTotalDuration();

                if (total != null)
                    progresoVideo.setMax(100);
            });

            // Reproducir el siguiente video al terminar
            reproductor.setOnEndOfMedia(() -> {
                siguienteVideo(null);
            });

            // Iniciar reproduccion
            Scene scene = principal.getScene();
            Stage stage = (Stage) scene.getWindow();
            stage.setTitle(archivosVideo.get(indice).getName());
            reproductor.play();
            reproduciendo = true;

            // Actualizar icono del boton de pausa
            ImageView img = new ImageView();
            img.setFitWidth(20);
            img.setFitHeight(20);

            img.setImage(new Image(("/pausar.png")));

            btPausa.setGraphic(img);

            // Seleccionar el video en la lista
            listaVideos.getSelectionModel().select(indice);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
