package com.example.crud_srpingboot.interfaz;


import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.crud_srpingboot.DAO.model.Amigo;
import com.example.crud_srpingboot.DAO.model.Estudio;
import com.example.crud_srpingboot.DAO.network.ApiClient;

import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

@Component
public class Controlador {
    private Pestania1 pest1;
    
    private Pestania2 pest2;

    protected final ApiClient apiClient = new ApiClient();

    protected String tipoOrdenacion = "nombremM";

    protected List<Amigo> lista;

    protected Amigo amigoEditando = null;

    protected boolean editando = false;

    ArrayList<String> hobbies = new ArrayList<>();

    ArrayList<String> telefonos = new ArrayList<>();

    ArrayList<Estudio> estudios = new ArrayList<>();

    @FXML
    protected TableView<Amigo> tablaAmigos;

    @FXML
    protected TabPane tabAmigos;

    @FXML
    protected Tab pestaniaAgregar;

    @FXML
    protected Tab pestaniaAmigos;

    // PESTANIA 1
    @FXML
    protected Button btActualizar;

    @FXML
    protected Button btEditar;

    @FXML
    protected Button btEliminar;

    @FXML
    protected TableColumn<Amigo, String> colEdad;

    @FXML
    protected TableColumn<Amigo, String> colEstudios;

    @FXML
    protected TableColumn<Amigo, String> colNombre;

    @FXML
    protected TableColumn<Amigo, String> colTelfs;

    @FXML
    protected TableColumn<Amigo, String> colHobbies;

    @FXML
    protected Label mensajeBreve;

    @FXML
    protected ComboBox<String> cbOrdenacion;

    // PESTANIA 2
    @FXML
    protected VBox listaEstudios;

    @FXML
    protected VBox listaHobbies;

    @FXML
    protected VBox listaTelefonos;

    @FXML
    protected TextField tfEdad;

    @FXML
    protected TextField tfHobbie;

    @FXML
    protected TextField tfNombre;

    @FXML
    protected TextField tfTelefono;
    
    @FXML
    protected TextField tfTitulo;
    
    @FXML
    protected TextField tfCentro;

    @FXML
    protected TextField tfAnio;

    @FXML
    protected Label msgErr;

    @FXML
    public void initialize() {
        pest1 = new Pestania1(this);
        pest2 = new Pestania2(this, pest1); // Mandamos pest1 ya que el metodo de guardarAmigo() actualiza la lista de amigos

        // Listener para saber si hay una fila seleccionada.
        tablaAmigos.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            boolean haySeleccion = newVal != null;
            btEditar.setDisable(!haySeleccion);
            btEliminar.setDisable(!haySeleccion);
        });

        // Relleno del combo box
        String[] ordenaciones = {
            "Nombre ASC" , "Nombre DES"   , "Edad ASC"     , "Edad DES"    , "Hobbies ASC", 
            "Hobbies DES", "Telefonos ASC", "Telefonos DES", "Estudios ASC", "Estudios DES"
        };

        cbOrdenacion.getItems().addAll(ordenaciones);
        cbOrdenacion.setValue("Nombre ASC");
        
        // Relleno de tabla
        colNombre.  setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
        colEdad.    setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getEdad())));
        colHobbies. setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getHobbies().toString()));
        colTelfs.   setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTelefonos().toString()));
        colEstudios.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEstudios().toString()));

        this.actualizarLista(null);
    }
    
    @FXML
    void actualizarLista(ActionEvent event) {
        pest1.actualizarLista();
    }

    @FXML
    void cambiarOrdenacion(ActionEvent event) {
        pest1.cambiarOrdenacion();
    }

    @FXML
    void editarAmigo(ActionEvent event) {
        pest1.editarAmigo();
    }

    @FXML
    void eliminarAmigo(ActionEvent event) {
        pest1.borrarAmigo();
    }

    // METODOS PESTANIA 2
    @FXML
    void agregarHobbie(ActionEvent event) {
        pest2.agregarHobbie();
    }

    @FXML
    void agregarTelefono(ActionEvent event) {
        pest2.agregarTelefono();
    }

    @FXML
    void agregarEstudio(ActionEvent event) {
        pest2.agregarEstudio();
    }

    @FXML
    void guardarAmigo(ActionEvent event) {
        pest2.guardarAmigo();
    }

    // Metodos que comparten pest1 y pest2
    protected HBox crearFilaHobbie(String hobbie) {
        HBox fila = new HBox(10);
        fila.setStyle("-fx-alignment: CENTER_LEFT;");

        Label label = new Label(hobbie);

        Button btnBorrar = new Button("X");
        btnBorrar.setOnAction(e -> {
            listaHobbies.getChildren().remove(fila);

            hobbies.remove(hobbie);
        });

        fila.getChildren().addAll(label, btnBorrar);
        return fila;
    }

    protected HBox crearFilaTelefono(String telefono) {
        HBox fila = new HBox(10);
        fila.setStyle("-fx-alignment: CENTER_LEFT;");

        Label label = new Label(telefono);

        Button btnBorrar = new Button("X");
        btnBorrar.setOnAction(e -> {
            listaTelefonos.getChildren().remove(fila);

            telefonos.remove(telefono);
        });
        
        fila.getChildren().addAll(label, btnBorrar);
        return fila;
    }

    protected HBox crearFilaEstudio(Estudio estudio) {
        HBox fila = new HBox(10);
        fila.setStyle("-fx-alignment: CENTER_LEFT;");

        Label label = new Label(String.format("""
            [Titulo: %s  Centro: %s  Año: %d]
                """, estudio.getTitulo(), estudio.getCentro(), estudio.getAnio()));

        Button btnBorrar = new Button("X");
        btnBorrar.setOnAction(e -> {
            listaEstudios.getChildren().remove(fila);

            estudios.remove(estudio);
        });
        
        fila.getChildren().addAll(label, btnBorrar);
        return fila;
    }
}