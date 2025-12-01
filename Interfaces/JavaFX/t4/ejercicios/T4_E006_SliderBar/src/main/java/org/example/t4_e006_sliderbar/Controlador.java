package org.example.t4_e006_sliderbar;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class Controlador {

    @FXML private Label labelBlue;
    @FXML private Pane paneColor;
    @FXML private Label labelGreen;
    @FXML private Label labelOpacity;
    @FXML private Label labelRed;

    @FXML private Slider sliderBlue;
    @FXML private Slider sliderGreen;
    @FXML private Slider sliderOpacity;
    @FXML private Slider sliderRed;

    @FXML private TextField valueBlue;
    @FXML private TextField valueGreen;
    @FXML private TextField valueOpacity;
    @FXML private TextField valueRed;

    @FXML
    void cambioSlider(MouseEvent event) {
        if (event.getSource() == sliderRed) {
            valueRed.setText(String.format("%.0f", sliderRed.getValue()));
        } else if (event.getSource() == sliderGreen) {
            valueGreen.setText(String.format("%.0f", sliderGreen.getValue()));
        } else if (event.getSource() == sliderBlue) {
            valueBlue.setText(String.format("%.0f", sliderBlue.getValue()));
        } else if (event.getSource() == sliderOpacity) {
            valueOpacity.setText(String.format("%.2f", sliderOpacity.getValue()));
        }
        actualizarColor(
                (int) sliderRed.getValue(),
                (int) sliderGreen.getValue(),
                (int) sliderBlue.getValue(),
                sliderOpacity.getValue()
        );
    }

    @FXML
    void cambioValor(ActionEvent event) {
        try {
            if (event.getSource() == valueRed) {
                sliderRed.setValue(Double.parseDouble(valueRed.getText()));
            } else if (event.getSource() == valueGreen) {
                sliderGreen.setValue(Double.parseDouble(valueGreen.getText()));
            } else if (event.getSource() == valueBlue) {
                sliderBlue.setValue(Double.parseDouble(valueBlue.getText()));
            } else if (event.getSource() == valueOpacity) {
                sliderOpacity.setValue(Double.parseDouble(valueOpacity.getText()));
            }
        } catch (NumberFormatException e) {
        }

        actualizarColor(
                (int) sliderRed.getValue(),
                (int) sliderGreen.getValue(),
                (int) sliderBlue.getValue(),
                sliderOpacity.getValue()
        );
    }

    private void actualizarColor(int r, int g, int b, double a) {
        Color newColor = Color.rgb(r ,g ,b , a);
        BackgroundFill fill = new BackgroundFill(newColor, CornerRadii.EMPTY, Insets.EMPTY);
        paneColor.setBackground(new Background(fill));
    }
}