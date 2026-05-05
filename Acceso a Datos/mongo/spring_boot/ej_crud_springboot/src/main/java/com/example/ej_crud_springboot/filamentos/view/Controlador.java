package com.example.ej_crud_springboot.filamentos.view;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.ej_crud_springboot.filamentos.client.ApiClient;
import com.example.ej_crud_springboot.filamentos.model.Filamento;
import com.example.ej_crud_springboot.filamentos.view.pestanias.Pestania1;
import com.example.ej_crud_springboot.filamentos.view.pestanias.Pestania2;

import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

@Component
public class Controlador {
    public final ApiClient apiClient = new ApiClient();

    private final Pestania1 pest1 = new Pestania1(this);

    private final Pestania2 pest2 = new Pestania2(this, pest1);

    public List<Filamento> lista;

    public String tipoOrdenacion = "materialmM";

    public Filamento filamentoEditando = null;

    public boolean editando = false;

    public ArrayList<String> listaMateriales = new ArrayList<>();

    public ArrayList<String> listaMarcas = new ArrayList<>();

    @FXML
    public Tab pestaniaAgregar;

    @FXML
    public Tab pestaniaInventario;

    @FXML
    public TabPane tabPane;

    // PESTAÑA 1
    @FXML
    public TableView<Filamento> tablaFilamentos;

    @FXML
    public Button btEditar;

    @FXML
    public Label mensajeBreve;

    @FXML
    public Button btActualizar;

    @FXML
    public Button btEliminar;
    
    @FXML
    public ComboBox<String> cbOrdenacion;
    
    @FXML
    public TableColumn<Filamento, String> colColor;
    
    @FXML
    public TableColumn<Filamento, String> colEspecs;
    
    @FXML
    public TableColumn<Filamento, String> colGrDispo;
    
    @FXML
    public TableColumn<Filamento, String> colMarca;
    
    @FXML
    public TableColumn<Filamento, String> colMaterial;

    // PESTAÑA 2
    @FXML
    public ComboBox<String> cbMaterial;

    @FXML
    public Button btGuardar;

    @FXML
    public ColorPicker cpColor;

    @FXML
    public Label msgErr;

    @FXML
    public TextField tfGrDispo;

    @FXML
    public ComboBox<String> cbMarca;

    @FXML
    public TextField tfMarcaCustom;

    @FXML
    public TextField tfMaterialCustom;

    @FXML
    public TextField tfTempCama;

    @FXML
    public TextField tfTempImp;

    @FXML
    public TextField tfVelImp;

    @FXML
    public void initialize() {
        rellenarListaMarcasYMateriales();

        // Listener para saber si hay una fila seleccionada.
        tablaFilamentos.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            boolean haySeleccion = newVal != null;
            btEditar.setDisable(!haySeleccion);
            btEliminar.setDisable(!haySeleccion);
        });

        // Relleno del combo box de ordenaciones
        String[] ordenaciones = {
            "Material ASC" , "Material DES",
            "Marca ASC"  , "Marca DES", 
            "Peso ASC"   , "Peso DES"
        };

        cbOrdenacion.getItems().addAll(ordenaciones);
        cbOrdenacion.setValue("Material ASC");
        
        // Relleno de tabla
        colMaterial.    setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMaterial()));
        colMarca.       setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMarca()));
        colEspecs.      setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEspecs().toString()));
        colColor.       setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getColor()));
        colGrDispo.     setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getGrDispo())));

        // Pantallita para ver los colores de las celdas color y disponibilidad
        colColor.setCellFactory(col -> new TableCell<Filamento, String>() {
            private final Rectangle rect = new Rectangle(50, 30);

            @Override
            protected void updateItem(String color, boolean empty) {
                super.updateItem(color, empty);
                if (empty || color == null) {
                    setGraphic(null);
                } else {
                    rect.setFill(Color.web(color));
                    rect.setStroke(Color.BLACK);
                    setAlignment(Pos.CENTER);
                    rect.setArcWidth(6);
                    rect.setArcHeight(6);
                    setGraphic(rect);
                }
            }
        });

        colGrDispo.setCellFactory(col -> new TableCell<Filamento, String>() {
            private final Rectangle rect = new Rectangle(70, 30);

            @Override
            protected void updateItem(String grDispo, boolean empty) {
                super.updateItem(grDispo, empty);

                if (empty || grDispo == null) {
                    setGraphic(null);
                    setText(null);

                } else {
                    int gr = Integer.parseInt(grDispo);

                    if (gr > 700) {
                        rect.setFill(Color.web("#6FDD65")); // verde

                    } else if (gr > 500) {
                        rect.setFill(Color.web("#FFCA56")); // amarillo

                    } else if (gr > 200) {
                        rect.setFill(Color.web("#DA7D44")); // naranja

                    } else {
                        rect.setFill(Color.web("#DA2F2F")); // rojo
                    }

                    setAlignment(Pos.CENTER);
                    rect.setArcWidth(6);
                    rect.setArcHeight(6);
                    setGraphic(rect);
                }
            }
        });

        // Relleno combo box de material y marca 
        // (en caso de seleccionar 'Otro' u 'Otra', aparece un text filed para indicar el nuevo material o marca)
        cbMaterial.getItems().addAll(listaMateriales);
        cbMaterial.getItems().add("Otro");
        cbMaterial.setOnAction(e -> tfMaterialCustom.setVisible("Otro".equals(cbMaterial.getValue())));

        cbMarca.getItems().addAll(listaMarcas);
        cbMarca.getItems().add("Otra");
        cbMarca.setOnAction(e -> tfMarcaCustom.setVisible("Otra".equals(cbMarca.getValue())));

        
        this.actualizarLista(null);
    }

    private void rellenarListaMarcasYMateriales() {
        // // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'rellenarListaMarcasYMateriales'");
    }

    // METODOS PESTAÑA 1
    @FXML
    void actualizarLista(ActionEvent event) {
        pest1.actualizarLista();
    }

    @FXML
    void editarFilamento(ActionEvent event) {
        pest1.editarFilamento();
    }

    @FXML
    void eliminarFilamento(ActionEvent event) {
        pest1.eliminarFilamento();
    }

    @FXML
    void ordenar(ActionEvent event) {
        pest1.ordenar();
    }

    // METODOS PESTAÑA 2
    @FXML
    void guardarFilamento(ActionEvent event) {
        pest2.guardarFilamento();
    }

    // METODOS COMUNES DE LAS PESTAÑAS
    public void borrarDatos() {
        cbMaterial.setValue("");
        tfMaterialCustom.clear();

        cbMarca.setValue("");
        tfMarcaCustom.clear();

        cpColor.setValue(Color.WHITE);

        tfTempCama.clear();
        tfTempImp.clear();
        tfVelImp.clear();

        tfGrDispo.clear();
    }
}
