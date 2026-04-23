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
import javafx.scene.control.Alert;
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
        // pest1.actualizarLista();
        System.out.println("Actualizando lista"); // log

        try {
            lista = apiClient.listarAmigos(tipoOrdenacion);
            mensajeBreve.setText("Lista actualizada");
            System.out.println("Tamanio lista: " + lista.size());// log
            System.out.println("Contenido: " + lista);// log
            
        } catch (IOException | InterruptedException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error de conexión");
            alert.setContentText("No se puede conectar con el servidor.");
            alert.showAndWait();

            System.out.println(e.getStackTrace());
        }

        tablaAmigos.getItems().setAll(lista);

        System.out.printf("""
        Lista actualziada:

            Value ComboBox: %s
            Tipo ordenacion: %s


        """, cbOrdenacion.getValue(), tipoOrdenacion);
    }

    @FXML
    void cambiarOrdenacion(ActionEvent event) {
        // pest1.cambiarOrdenacion();
        String tipo = cbOrdenacion.getValue();

        switch (tipo) {
            case "Nombre ASC"      -> { tipoOrdenacion = "nombremM";    }
            case "Nombre DES"      -> { tipoOrdenacion = "nombreMm";    }
            case "Edad ASC"        -> { tipoOrdenacion = "edadmM";      }
            case "Edad DES"        -> { tipoOrdenacion = "edadMm";      }
            case "Hobbies ASC"     -> { tipoOrdenacion = "hobbiesmM";   }
            case "Hobbies DES"     -> { tipoOrdenacion = "hobbiesMm";   }
            case "Telefonos ASC"   -> { tipoOrdenacion = "telefonosmM"; }
            case "Telefonos DES"   -> { tipoOrdenacion = "telefonosMm"; }
            case "Estudios ASC"    -> { tipoOrdenacion = "estudiosmM";  }
            case "Estudios DES"    -> { tipoOrdenacion = "estudiosMm";  }
            default -> System.out.println("SALTA DEFAULT");
        }

        actualizarLista(event);
    }

    @FXML
    void editarAmigo(ActionEvent event) {
        borrarDatos();

        editando = true;
        amigoEditando = tablaAmigos.getSelectionModel().getSelectedItem();

        tfNombre.setText(amigoEditando.getNombre());
        tfEdad.  setText(String.valueOf(amigoEditando.getEdad()));

        hobbies = (ArrayList<String>) amigoEditando.getHobbies();

        telefonos = (ArrayList<String>) amigoEditando.getTelefonos();

        estudios = (ArrayList<Estudio>) amigoEditando.getEstudios();

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
            int anio;

            try {
                anio = verificarAnioEstudio();
            } catch (NumberFormatException e) {
                msgErr.setText("El año introducido no es un numero");
                return;
            }

            if (anio < 1900 || anio > LocalDate.now().getYear()) {
                msgErr.setText("El año introducido no es valido");
                return;
            }

            Estudio estudio = new Estudio(tfTitulo.getText(), tfCentro.getText(), anio);

            listaEstudios.getChildren().add(crearFilaEstudio(estudio));
            estudios.add(estudio);

            System.out.println(estudios.size());
        }
    }

    private int verificarAnioEstudio() {
        msgErr.setText("");
        return Integer.parseInt(tfAnio.getText());
    }

    @FXML
    void agregarHobbie(ActionEvent event) {
        String hobbie = tfHobbie.getText();

        if (!hobbie.isEmpty()) {
            listaHobbies.getChildren().add(crearFilaHobbie(hobbie));
            tfHobbie.clear();

            hobbies.add(hobbie);
        }

        System.out.println(hobbies.size());
    }

    @FXML
    void agregarTelefono(ActionEvent event) {
        String telefono = tfTelefono.getText();
        
        if (!telefono.isEmpty()) {
            listaTelefonos.getChildren().add(crearFilaTelefono(telefono));
            tfTelefono.clear();

            telefonos.add(telefono);
        }

        System.out.println(telefonos.size());
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
                tfNombre.getText(),
                Integer.parseInt(tfEdad.getText()),
                hobbies,
                telefonos,
                estudios
            );

            try {
                if (editando) {
                    apiClient.actualizarAmigo(amigoEditando.getId(), amigoNuevo);
    
                } else {
                    apiClient.insertarAmigo(amigoNuevo);
                }

                mensajeBreve.setText("Amigo agregado correctamente");
                
            } catch (IOException | InterruptedException e) {
                mensajeBreve.setText("Fallo al agregar amigo");
                // meter en log el error

            } finally {
                actualizarLista(event);
                editando = false;
                // Cambiamos de pestaña
                tabAmigos.getSelectionModel().select(pestaniaAmigos);
            }
        }
    }

    private HBox crearFilaHobbie(String hobbie) {
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

    private HBox crearFilaTelefono(String telefono) {
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

    private HBox crearFilaEstudio(Estudio estudio) {
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

    private void borrarDatos() {
        listaEstudios.getChildren().clear();
        listaHobbies.getChildren().clear();
        listaTelefonos.getChildren().clear();

        hobbies.clear();
        telefonos.clear();
        estudios.clear();
    }
}