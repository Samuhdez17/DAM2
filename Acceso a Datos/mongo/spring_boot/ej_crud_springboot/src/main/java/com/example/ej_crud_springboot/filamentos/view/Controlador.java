package com.example.ej_crud_springboot.filamentos.view;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.ej_crud_springboot.filamentos.client.ApiClient;
import com.example.ej_crud_springboot.filamentos.model.Filamento;
import com.example.ej_crud_springboot.filamentos.service.MiObjectOutputStream;
import com.example.ej_crud_springboot.filamentos.view.pestanias.Pestania1;
import com.example.ej_crud_springboot.filamentos.view.pestanias.Pestania2;

import javafx.application.Platform;
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
import javafx.stage.Stage;

@Component
public class Controlador {
    private File marcasFile;

    private File materialesFile;

    private File logErr;

    public final ApiClient apiClient = new ApiClient();

    private final Pestania1 pest1 = new Pestania1(this);

    private final Pestania2 pest2 = new Pestania2(this, pest1);

    public List<Filamento> lista;

    public String tipoOrdenacion = "materialmM";

    public Filamento filamentoEditando = null;

    public boolean editando = false;

    public ArrayList<String> listaMateriales = new ArrayList<>(List.of("ABS", "ASA", "PLA", "PETG", "TPU"));

    public ArrayList<String> listaMarcas = new ArrayList<>(List.of("BambuLab", "Sataka", "Winkle"));

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
    private Label msgErr;

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
        // Creacion de fichero log para errores
        logErr = new File("src/main/resources/files/logsErr.txt");
        if (!logErr.exists()) {
            try {
                logErr.createNewFile();
            } catch (IOException e) {
                System.err.println("Error al crear logsErr.txt");
            }
        }

        // Se rellenan los ficheros con las marcas y materiales de manera local
        try {
            rellenarFicherosMarcasYMateriales();
        } catch (IOException e) {
            registrarError("Error al rellenar ficheros");
        }
        
        // Listener para saber si hay una fila seleccionada.
        tablaFilamentos.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            boolean haySeleccion = newVal != null;
            btEditar.setDisable(!haySeleccion);
            btEliminar.setDisable(!haySeleccion);
        });

        // Relleno del combo box de ordenaciones
        String[] ordenaciones = {
            "Marca ASC"  , "Marca DES", 
            "Material ASC" , "Material DES",
            "Peso ASC"   , "Peso DES"
        };
        cbOrdenacion.getItems().addAll(ordenaciones);
        cbOrdenacion.setValue("Marca ASC");
        
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

        // (en caso de seleccionar 'Otro' u 'Otra' en los combo box, aparece un text filed para indicar el nuevo material o marca)
        cbMaterial.setOnAction(e -> tfMaterialCustom.setVisible("Otro".equals(cbMaterial.getValue())));

        cbMarca.setOnAction(e -> tfMarcaCustom.setVisible("Otra".equals(cbMarca.getValue())));

        // Fix para Windows: la ventana del color personalizado minimiza la ventana principal al cerrarse
        cpColor.showingProperty().addListener((obs, wasShowing, isNowShowing) -> {
            if (!isNowShowing) {
                Platform.runLater(() -> ((Stage) cpColor.getScene().getWindow()).toFront());
            }
        });

        this.actualizarLista(null);
    }

    public void setMsgErr(String msg) {
        msgErr.setText(msg);
    }

    private void refrescarCBMaterialesYMarcas() {
        cbMaterial.getItems().clear();
        cbMarca.getItems().clear();

        cbMaterial.getItems().addAll(listaMateriales);
        cbMarca.getItems().addAll(listaMarcas);

        cbMaterial.getItems().add("Otro");
        cbMarca.getItems().add("Otra");
    }

    private void rellenarFicherosMarcasYMateriales() throws IOException {
        marcasFile = new File("src/main/resources/files/marcas.dat");
        materialesFile = new File("src/main/resources/files/materiales.dat");

        // Si el fichero no existe creamos uno nuevo y asignamos los valores por defecto que viene en el arraylist 
        // Si existe actializamos el arraylist
        if (!marcasFile.exists()) {
            marcasFile.createNewFile();
            guardarMarcas();

        } else {
            actualizarListaMarcas();
        }

        // Si el fichero no existe creamos uno nuevo y asignamos los valores por defecto que viene en el arraylist
        // Si existe actializamos el arraylist
        if (!materialesFile.exists()) {
            materialesFile.createNewFile();
            guardarMateriales();

        } else {
            actualizarListaMateriales();
        }

        refrescarCBMaterialesYMarcas();
    }

    private void actualizarListaMarcas() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(marcasFile))) {
            listaMarcas.clear();

            while (true) {
                String marca = ois.readUTF();
                listaMarcas.add(marca);
            }

        } catch (EOFException e) {
            Collections.sort(listaMarcas);

        } catch (IOException e) {
            registrarError("Error al rellenar array marcas con lectura de fichero");
        }
    }

    private void actualizarListaMateriales() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(materialesFile))) {
            listaMateriales.clear();

            while (true) {
                String material = ois.readUTF();
                listaMateriales.add(material);
            }

        } catch (EOFException e) {
            Collections.sort(listaMateriales);

        } catch (IOException e) {
            registrarError("Error al rellenar array materiales con lectura de fichero");
        }
    }

    private void guardarMarcas() {
        try {
            FileOutputStream fos = new FileOutputStream(marcasFile, true);

            try (ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                listaMarcas.remove("Otra");
                Collections.sort(listaMarcas);

                for (String marca : listaMarcas) {
                    oos.writeUTF(marca);
                }
            }

        } catch (IOException e) {
            registrarError("Error al escribir marca");
        }
    }

    private void guardarMateriales() {
        try {
            FileOutputStream fos = new FileOutputStream(materialesFile, true);

            try (ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                listaMateriales.remove("Otro");
                Collections.sort(listaMateriales);

                for (String material : listaMateriales) {
                    oos.writeUTF(material);
                }
            }

        } catch (IOException e) {
            registrarError("Error al escribir material");
        }
    }

    public void registrarError(String mensaje) {
        try (FileWriter fw = new FileWriter(logErr, true)) {
            fw.write("[" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")) + "]" + "  " + mensaje + "\n");

        } catch (IOException e) {
            System.out.println("Error al escribir en logsErr.txt");
        }
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

    public void guardarMarca(String marca) {
        try {
            FileOutputStream fos = new FileOutputStream(marcasFile, true);

            try (ObjectOutputStream oos = new MiObjectOutputStream(fos)) {
                oos.writeUTF(marca);
            }

            listaMarcas.add(marca);
            Collections.sort(listaMarcas);

            refrescarCBMaterialesYMarcas();

        } catch (IOException e) {
            registrarError("Error al escribir marca");
        }
    }

    public void guardarMaterial(String material) {
        try {
            FileOutputStream fos = new FileOutputStream(materialesFile, true);

            try (ObjectOutputStream oos = new MiObjectOutputStream(fos)) {
                oos.writeUTF(material);
            }

            listaMateriales.add(material);
            Collections.sort(listaMateriales);

            refrescarCBMaterialesYMarcas();

        } catch (IOException e) {
            registrarError("Error al escribir material");
        }
    }
}
