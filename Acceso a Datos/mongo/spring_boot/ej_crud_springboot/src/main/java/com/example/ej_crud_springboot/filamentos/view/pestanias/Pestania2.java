package com.example.ej_crud_springboot.filamentos.view.pestanias;

import java.io.IOException;

import com.example.ej_crud_springboot.filamentos.model.Especs;
import com.example.ej_crud_springboot.filamentos.model.Filamento;
import com.example.ej_crud_springboot.filamentos.view.Controlador;

import javafx.scene.paint.Color;

public class Pestania2 {
    private final Controlador ctrldr;
    private final Pestania1 pest1;

    public Pestania2(Controlador ctrldr, Pestania1 pest1) {
        this.ctrldr = ctrldr;
        this.pest1 = pest1;
    }

    public void guardarFilamento() {
        // Verificaciones de campos rellenos
        if (ctrldr.cbMarca.getValue() == null || ctrldr.cbMarca.getValue().isBlank()) {
            ctrldr.msgErr.setText("Marca no seleccionada");

        } else if (ctrldr.cbMarca.getValue().equals("Otra") && ctrldr.tfMarcaCustom.getText().isBlank()) {
            ctrldr.msgErr.setText("Marca no escrita");

        } else if (ctrldr.cbMaterial.getValue() == null || ctrldr.cbMaterial.getValue().isBlank()) {
            ctrldr.msgErr.setText("Material no seleccionado");

        } else if (ctrldr.cbMaterial.getValue().equals("Otro") && ctrldr.tfMaterialCustom.getText().isBlank()) {
            ctrldr.msgErr.setText("Material no escrito");

        } else { // Guardado de filamento
            ctrldr.msgErr.setText("");
            
            // Se guardan los cambios en un objeto nuevo
            Color c = ctrldr.cpColor.getValue();
            String hex = String.format("#%02X%02X%02X",
                (int)(c.getRed() * 255),
                (int)(c.getGreen() * 255),
                (int)(c.getBlue() * 255));
    
            Filamento filamentoNuevo = new Filamento(
                ctrldr.cbMaterial.getValue(),
                ctrldr.cbMarca.getValue(),
                hex,
                new Especs(
                    Integer.parseInt(ctrldr.tfTempImp.getText()),
                    Integer.parseInt(ctrldr.tfTempCama.getText()),
                    Integer.parseInt(ctrldr.tfVelImp.getText())
                ),
                Integer.parseInt(ctrldr.tfGrDispo.getText())
            );

            // En caso de que se haya marcado un material personalizado se asigna el valor del text filed y no del combo box
            if (ctrldr.cbMaterial.getValue().equals("Otro")) {
                filamentoNuevo.setMaterial(ctrldr.tfMaterialCustom.getText());
            }
            // En caso de que se haya marcado una marca personalizada se asigna el valor del text filed y no del combo box
            if (ctrldr.cbMarca.getValue().equals("Otra")) {
                filamentoNuevo.setMarca(ctrldr.tfMarcaCustom.getText());
            }

            try {
                // Se toma la decision de guardar o actualizar el filamento
                if (ctrldr.editando) {
                    ctrldr.apiClient.actualizarFilamento(ctrldr.filamentoEditando.getId(), filamentoNuevo);

                } else {
                    ctrldr.apiClient.insertarFilamento(filamentoNuevo);
                }

                // Se ejecuta, se actualiza la lista y se cambia de pestaña
                ctrldr.mensajeBreve.setText("Filamento guardado correctamente");
                pest1.actualizarLista();

                ctrldr.tabPane.getSelectionModel().select(ctrldr.pestaniaInventario);
                ctrldr.borrarDatos();
                ctrldr.editando = false;

                System.out.println("Datos actualizados: " + filamentoNuevo);
                
            } catch (IOException | InterruptedException e) {
                ctrldr.mensajeBreve.setText("Fallo al guardar filamento");
            }
        }
    }
}
