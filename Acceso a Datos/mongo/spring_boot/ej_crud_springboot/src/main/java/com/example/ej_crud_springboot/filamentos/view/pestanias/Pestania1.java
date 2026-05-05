package com.example.ej_crud_springboot.filamentos.view.pestanias;

import java.io.IOException;

import com.example.ej_crud_springboot.filamentos.model.Filamento;
import com.example.ej_crud_springboot.filamentos.view.Controlador;

import javafx.scene.control.Alert;
import javafx.scene.paint.Color;

public class Pestania1 {
    private final Controlador ctrldr;

    public Pestania1(Controlador ctrldr) {
        this.ctrldr = ctrldr;
    }

    public void actualizarLista() {
        System.out.println("Actualizando lista"); // log

        try {
            ctrldr.lista = ctrldr.apiClient.listarFilamentos(ctrldr.tipoOrdenacion);
            ctrldr.mensajeBreve.setText("Lista actualizada");
            System.out.println("Tamanio lista: " + ctrldr.lista.size()); // log
            
        } catch (IOException | InterruptedException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error de conexión");
            alert.setContentText("No se pudo conectar con el servidor.");
            alert.showAndWait();

            System.out.println(e);
        }

        ctrldr.tablaFilamentos.getItems().clear();
        ctrldr.tablaFilamentos.getItems().setAll(ctrldr.lista);
    }

    public void editarFilamento() {
        ctrldr.borrarDatos();
        
        ctrldr.editando = true;
        ctrldr.filamentoEditando = ctrldr.tablaFilamentos.getSelectionModel().getSelectedItem();
    
        if (ctrldr.listaMateriales.contains(ctrldr.filamentoEditando.getMaterial())) {
            ctrldr.cbMaterial.setValue(ctrldr.filamentoEditando.getMaterial());

        } else {
            ctrldr.cbMaterial.setValue("Otro");
            ctrldr.tfMaterialCustom.setText(ctrldr.filamentoEditando.getMaterial());
        }

        if (ctrldr.listaMarcas.contains(ctrldr.filamentoEditando.getMarca())) {
            ctrldr.cbMarca.setValue(ctrldr.filamentoEditando.getMarca());

        } else {
            ctrldr.cbMarca.setValue("Otra");
            ctrldr.tfMarcaCustom.setText(ctrldr.filamentoEditando.getMarca());
        }

        ctrldr.cpColor.setValue(Color.web(ctrldr.filamentoEditando.getColor()));

        ctrldr.tfTempImp.setText(String.valueOf(ctrldr.filamentoEditando.getEspecs().getTempImp()));
        ctrldr.tfTempCama.setText(String.valueOf(ctrldr.filamentoEditando.getEspecs().getTempCama()));
        ctrldr.tfVelImp.setText(String.valueOf(ctrldr.filamentoEditando.getEspecs().getVelImp()));

        ctrldr.tfGrDispo.setText(String.valueOf(ctrldr.filamentoEditando.getGrDispo()));

        // Cambiamos de pestaña
        ctrldr.tabPane.getSelectionModel().select(ctrldr.pestaniaAgregar);
    }

    public void eliminarFilamento() {
        Filamento seleccionado = ctrldr.tablaFilamentos.getSelectionModel().getSelectedItem();

        try {
            ctrldr.apiClient.borrarFilamento(seleccionado.getId());
            actualizarLista();
            ctrldr.mensajeBreve.setText("Filamento eliminado");

        } catch (IOException | InterruptedException e) {
            System.out.println("Error al eliminar el amigo. " + e); // Logerr
        }
    }

    public void ordenar() {
        String tipo = ctrldr.cbOrdenacion.getValue();

        switch (tipo) {
            case "Material ASC" -> {
                ctrldr.tipoOrdenacion = "materialmM";
            }
            case "Material DES" -> {
                ctrldr.tipoOrdenacion = "materialMm";
            }
            case "Marca ASC" -> {
                ctrldr.tipoOrdenacion = "marcamM";
            }
            case "Marca DES" -> {
                ctrldr.tipoOrdenacion = "marcaMm";
            }
            case "Peso ASC" -> {
                ctrldr.tipoOrdenacion = "pesomM";
            }
            case "Peso DES" -> {
                ctrldr.tipoOrdenacion = "pesoMm";
            }
            default -> System.out.println("SALTA DEFAULT"); // Logerr
        }

        actualizarLista();
    }
}
