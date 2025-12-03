package org.example.t4_e006_sliderbar;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.*;

import java.io.*;
import java.util.Random;

public class Controlador {
    @FXML
    private Slider barraAzul;

    @FXML
    private Slider barraOpacidad;

    @FXML
    private Slider barraRojo;

    @FXML
    private Slider barraVerde;

    @FXML
    private Pane paneColor;

    @FXML
    private TextField valorAzul;

    @FXML
    private TextField valorOpacidad;

    @FXML
    private TextField valorRojo;

    @FXML
    private TextField valorVerde;

    @FXML
    private MenuBar menuBar;

    // FILE
    @FXML
    private Menu menuFile;

    @FXML
    private MenuItem loadItem;

    @FXML
    private MenuItem saveItem;

    @FXML
    private MenuItem cerrarItem;

    // EDIT
    @FXML
    private MenuItem resetItem;

    @FXML
    private MenuItem randomItem;

    @FXML
    private MenuItem cyanItem;

    @FXML
    private MenuItem magentaItem;

    @FXML
    private MenuItem yellowItem;

    @FXML
    private MenuItem blackItem;

    @FXML
    private MenuItem whiteItem;

    @FXML
    private MenuItem orangeItem;

    @FXML
    private MenuItem brownItem;

    @FXML
    private MenuItem pinkItem;

    // HELP
    @FXML
    private MenuItem aboutItem;

    @FXML
    void initialize() {
        if (menuBar != null) {
            // MENU FILE
            SeparatorMenuItem sep = new SeparatorMenuItem();
            menuFile.getItems().add( 2, sep);
            loadItem.setAccelerator(KeyCombination.keyCombination("Ctrl+L"));
            saveItem.setAccelerator(KeyCombination.keyCombination("Ctrl+S"));

            // MENU EDIT
            resetItem.setAccelerator(KeyCombination.keyCombination("Ctrl+Alt+0"));
            randomItem.setAccelerator(KeyCombination.keyCombination("Ctrl+Alt+A"));
            cyanItem.setAccelerator(KeyCombination.keyCombination("Ctrl+Alt+C"));
            magentaItem.setAccelerator(KeyCombination.keyCombination("Ctrl+Alt+N"));
            yellowItem.setAccelerator(KeyCombination.keyCombination("Ctrl+Alt+Y"));
            blackItem.setAccelerator(KeyCombination.keyCombination("Ctrl+Alt+1"));
            whiteItem.setAccelerator(KeyCombination.keyCombination("Ctrl+Alt+2"));
            orangeItem.setAccelerator(KeyCombination.keyCombination("Ctrl+Alt+3"));
            brownItem.setAccelerator(KeyCombination.keyCombination("Ctrl+Alt+4"));
            pinkItem.setAccelerator(KeyCombination.keyCombination("Ctrl+Alt+5"));
        }
    }

    @FXML
    void guardarColor(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Guardar color");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Archivo de texto", "*.txt"));

        Stage stage = (Stage) menuBar.getScene().getWindow();
        File file = fileChooser.showSaveDialog(stage);

        if (file != null) {
            try (PrintWriter salida = new PrintWriter(file)) {
                String a = valorOpacidad.getText();
                a = a.replace(',', '.');

                salida.printf("%s,%s,%s,%s\n", valorRojo.getText(), valorVerde.getText(), valorAzul.getText(), a);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void cargarColor(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Cargar color");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Archivo de texto", "*.txt"));

        Stage stage = (Stage) menuBar.getScene().getWindow();
        File file = fileChooser.showOpenDialog(stage);

        if (file != null) {
            try (BufferedReader entrada = new BufferedReader(new FileReader(file))) {
                String linea = entrada.readLine();
                if (linea != null) {
                    String[] partes = linea.split(",");
                    if (partes.length == 4) {
                        int r = Integer.parseInt(partes[0].trim());
                        int g = Integer.parseInt(partes[1].trim());
                        int b = Integer.parseInt(partes[2].trim());
                        double a = Double.parseDouble(partes[3].trim());

                        setValores(r, g, b, a);
                        actualizarColor(r, g, b, a);
                    }
                }
            } catch (IOException | NumberFormatException e) {
                e.printStackTrace();
            }
        }
    }


    @FXML
    void cerrarVentana(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void cambioSlider(MouseEvent e) {
             if (e.getSource() == barraRojo)        valorRojo.setText(String.format("%.0f", barraRojo.getValue()));
        else if (e.getSource() == barraVerde)       valorVerde.setText(String.format("%.0f", barraVerde.getValue()));
        else if (e.getSource() == barraAzul)        valorAzul.setText(String.format("%.0f", barraAzul.getValue()));
        else if (e.getSource() == barraOpacidad)    valorOpacidad.setText(String.format("%.2f", barraOpacidad.getValue()));

        actualizarColor(
                (int) barraRojo.getValue(),
                (int) barraVerde.getValue(),
                (int) barraAzul.getValue(),
                barraOpacidad.getValue()
        );
    }

    @FXML
    void cambioValor(ActionEvent e) {
        try {
                 if (e.getSource() == valorRojo)        barraRojo.setValue(Double.parseDouble(valorRojo.getText()));
            else if (e.getSource() == valorVerde)       barraVerde.setValue(Double.parseDouble(valorVerde.getText()));
            else if (e.getSource() == valorAzul)        barraAzul.setValue(Double.parseDouble(valorAzul.getText()));
            else if (e.getSource() == valorOpacidad)    barraOpacidad.setValue(Double.parseDouble(valorOpacidad.getText()));

        } catch (NumberFormatException ex) {
        }

        actualizarColor(
                (int) barraRojo.getValue(),
                (int) barraVerde.getValue(),
                (int) barraAzul.getValue(),
                barraOpacidad.getValue()
        );
    }

    private void actualizarColor(int r, int g, int b, double a) {
        Color colorNuevo = Color.rgb(r, g, b, a);
        BackgroundFill fill = new BackgroundFill(colorNuevo, CornerRadii.EMPTY, Insets.EMPTY);
        paneColor.setBackground(new Background(fill));
    }

    private void setValores(int r, int g, int b, double a) {
        barraRojo.setValue(r);
        barraVerde.setValue(g);
        barraAzul.setValue(b);
        barraOpacidad.setValue(a);

        valorRojo.setText(String.format("%d", r));
        valorVerde.setText(String.format("%d", g));
        valorAzul.setText(String.format("%d", b));
        valorOpacidad.setText(String.format("%.2f", a));
    }

    @FXML
    void colorRandom(ActionEvent e) {
        Random ran = new Random();

        int r = ran.nextInt(256);
        int g = ran.nextInt(256);
        int b = ran.nextInt(256);
        double a = ran.nextDouble(1);

        setValores(r, g, b, a);
        actualizarColor(r, g, b, a);
    }

    @FXML
    void resetearColor(ActionEvent e) {
        setValores(0, 0, 0, 1);
        actualizarColor(0, 0, 0, 1);
    }

    @FXML
    void ponerColor(ActionEvent e) {
        int r = 0;
        int g = 0;
        int b = 0;

        if (e.getSource() == cyanItem) {
            r = 0; g = 255; b = 255;

        }  else if (e.getSource() == magentaItem) {
            r = 255; g = 0; b = 255;

        } else if (e.getSource() == yellowItem) {
            r = 255; g = 255; b = 0;

        } else if (e.getSource() == blackItem) {
            r = 0; g = 0; b = 0;

        } else if (e.getSource() == whiteItem) {
            r = 255; g = 255; b = 255;

        } else if (e.getSource() == orangeItem) {
            r = 255; g = 165; b = 0;

        } else if (e.getSource() == brownItem) {
            r = 128; g = 64; b = 0;

        } else if (e.getSource() == pinkItem) {
            r = 255; g = 192; b = 203;
        }

        setValores(r, g, b, 1.00);
        actualizarColor(r, g, b, 1.00);
    }

    @FXML
    void generarVentana(ActionEvent event) {
        Label contenido = new Label("DAM2 ©2025");
        Button boton = new Button("OK");
        VBox vBox = new VBox(10);
        vBox.getChildren().addAll(contenido, boton);
        vBox.setAlignment(Pos.CENTER);
        vBox.setStyle("-fx-background-color: lightgrey;");

        Scene scene = new Scene(vBox, 200, 100);
        Stage stage = new Stage();
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.show();

        boton.setOnAction(e -> stage.close());
    }
}