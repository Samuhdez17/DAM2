package com.example.crud_srpingboot.interfaz;


import java.io.IOException;
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
    protected final ApiClient apiClient = new ApiClient();

    protected String tipoOrdenacion = "nombremM";

    protected List<Amigo> lista;

    protected Amigo amigoEditando = null;

    protected boolean editando = false;

    protected final Pestania1 pest1 = new Pestania1();

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

    // METODOS PESTANIA 1
    @FXML
    public void initialize() {
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
        borrarDatos();

        editando = true;
        amigoEditando = tablaAmigos.getSelectionModel().getSelectedItem();

        tfNombre.setText(amigoEditando.getNombre());
        tfEdad.  setText(String.valueOf(amigoEditando.getEdad()));

        for (String hobbie : amigoEditando.getHobbies()) {
            listaHobbies.getChildren().add(crearFilaHobbie(hobbie));
        }

        for (String tel : amigoEditando.getTelefonos()) {
            listaTelefonos.getChildren().add(crearFilaTelefono(tel));
        }

        for (Estudio est : amigoEditando.getEstudios()) {
            listaEstudios.getChildren().add(crearFilaEstudio(est));
        }

        // Cambiamos de pestaña
        tabAmigos.getSelectionModel().select(pestaniaAgregar);
    }

    @FXML
    void eliminarAmigo(ActionEvent event) {
        Amigo seleccionado = tablaAmigos.getSelectionModel().getSelectedItem();

        try {
            apiClient.borrarAmigo(seleccionado.getId());
            actualizarLista(event);
            mensajeBreve.setText("Amigo eliminado");

        } catch (IOException | InterruptedException e) {
            System.out.println("Error al eliminar el amigo. " + e);
        }
    }

    // METODOS PESTANIA 2
    @FXML
    void agregarEstudio(ActionEvent event) {
        if (
            !tfTitulo.getText().isEmpty() &&
            !tfCentro.getText().isEmpty() &&
            !tfAnio.getText().isEmpty()
        ) {
            int anio = 0;
            try {
                anio = verificarAnioEstudio();
            } catch (NumberFormatException e) {
                msgErr.setText("El año introducido no es un numero");
            }

            listaEstudios.getChildren().add(crearFilaEstudio(
                new Estudio(
                    tfTitulo.getText(), 
                    tfCentro.getText(),
                    anio
                ))
            );
        }
    }

    private int verificarAnioEstudio() {
        msgErr.setText("");
        return Integer.parseInt(tfAnio.getText());
    }

    @FXML
    void agregarHobbie(ActionEvent event) {
        String valor = tfHobbie.getText();

        if (!valor.isEmpty()) {
            listaHobbies.getChildren().add(crearFilaHobbie(valor));
            tfHobbie.clear();
        }
    }

    @FXML
    void agregarTelefono(ActionEvent event) {
        String valor = tfTelefono.getText();
        
        if (!valor.isEmpty()) {
            listaTelefonos.getChildren().add(crearFilaTelefono(valor));
            tfTelefono.clear();
        }
    }

    @FXML
    void guardarAmigo(ActionEvent event) {
        if (tfNombre.getText().isBlank()) {
            msgErr.setText("Campo de nombre vacio");

        } else if (tfEdad.getText().isBlank()) {
            msgErr.setText("Campo de edad vacio");

        } else {
            msgErr.setText("");
            // Se guardan los cambios
            Amigo amigoNuevo = new Amigo(

            );

            if (editando) {
                // apiClient.actualizarAmigo(amigoEditando.getId());

            } else {
                // apiClient.insertarAmigo();
            }
            editando = false;
        }
    }

    private HBox crearFilaHobbie(String hobbie) {
        HBox fila = new HBox(10);
        fila.setStyle("-fx-alignment: CENTER_LEFT;");

        Label label = new Label(hobbie);

        Button btnBorrar = new Button("X");
        btnBorrar.setOnAction(e -> listaHobbies.getChildren().remove(fila));

        fila.getChildren().addAll(label, btnBorrar);
        return fila;
    }

    private HBox crearFilaTelefono(String telefono) {
        HBox fila = new HBox(10);
        fila.setStyle("-fx-alignment: CENTER_LEFT;");

        Label label = new Label(telefono);

        Button btnBorrar = new Button("X");
        btnBorrar.setOnAction(e -> listaTelefonos.getChildren().remove(fila));
        
        fila.getChildren().addAll(label, btnBorrar);
        return fila;
    }

    private HBox crearFilaEstudio(Estudio estudio) {
        HBox fila = new HBox(10);
        fila.setStyle("-fx-alignment: CENTER_LEFT;");

        Label label = new Label(String.format("""
            [Titulo: %s  Centro: %s  Año: %d]
                """, estudio.getTitulo(), estudio.getCentro(), estudio.getAnio()));

        Button btnBorrar = new Button("X");
        btnBorrar.setOnAction(e -> listaEstudios.getChildren().remove(fila));
        
        fila.getChildren().addAll(label, btnBorrar);
        return fila;
    }

    private void borrarDatos() {
        listaEstudios.getChildren().clear();
        listaHobbies.getChildren().clear();
        listaTelefonos.getChildren().clear();
    }
}