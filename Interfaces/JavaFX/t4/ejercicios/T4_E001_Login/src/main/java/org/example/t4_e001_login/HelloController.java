package org.example.t4_e001_login;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class HelloController {
    @FXML
    private CheckBox cb1;

    @FXML
    private Label checkContent;

    @FXML
    private GridPane gridPane;

    @FXML
    private HBox hBox;

    @FXML
    private Button logIn;

    @FXML
    private Label passwrd;

    @FXML
    private PasswordField pssFiled;

    @FXML
    private TextField txtFiled;

    @FXML
    private Label userName;

    @FXML
    private VBox vBox;

    @FXML
    private Label welkome;

    @FXML
    void showPassword(ActionEvent event) {

    }
}
