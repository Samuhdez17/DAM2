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
        String marca        = ctrldr.cbMarca.getValue();
        String material     = ctrldr.cbMaterial.getValue();
        String tempImp      = ctrldr.tfTempImp.getText();
        String tempCama     = ctrldr.tfTempCama.getText();
        String vel          = ctrldr.tfVelImp.getText();
        String grDispo      = ctrldr.tfGrDispo.getText();

        // Verificaciones de campos rellenos

        
        if (camposRellenos(marca, material, tempImp, tempCama, vel ,grDispo)) {
            if (camposValidos(tempImp, tempCama, vel ,grDispo)) { 
                // Guardado de filamento
                ctrldr.setMsgErr("");

                // Se guardan los cambios en un objeto nuevo
                Color c = ctrldr.cpColor.getValue();
                String hex = String.format("#%02X%02X%02X",
                    (int)(c.getRed() * 255),
                    (int)(c.getGreen() * 255),
                    (int)(c.getBlue() * 255));

                Filamento filamentoNuevo = new Filamento(
                    material,
                    marca,
                    hex,
                    new Especs(
                        Integer.parseInt(ctrldr.tfTempImp.getText()),
                        Integer.parseInt(ctrldr.tfTempCama.getText()),
                        Integer.parseInt(ctrldr.tfVelImp.getText())
                    ),
                    Integer.parseInt(ctrldr.tfGrDispo.getText())
                );

                // En caso de que se haya marcado un material personalizado se asigna el valor del text filed y no del combo box
                if (material.equals("Otro")) {
                    filamentoNuevo.setMaterial(ctrldr.tfMaterialCustom.getText());
                    ctrldr.guardarMaterial(ctrldr.tfMaterialCustom.getText());

                }

                // En caso de que se haya marcado una marca personalizada se asigna el valor del text filed y no del combo box
                if (marca.equals("Otra")) {
                    filamentoNuevo.setMarca(ctrldr.tfMarcaCustom.getText());
                    ctrldr.guardarMarca(ctrldr.tfMarcaCustom.getText());
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

    private boolean camposRellenos(String marca, String material, String tempImp, String tempCama, String vel, String grDispo) {
        // MARCA
        if (marca == null || marca.isBlank()) {
            ctrldr.setMsgErr("Marca no seleccionada");
            return false;

        } else if (marca.equals("Otra") && ctrldr.tfMarcaCustom.getText().isBlank()) {
            ctrldr.setMsgErr("Marca no escrita");
            return false;

        // MATERIAL    
        } else if (material == null || material.isBlank()) {
            ctrldr.setMsgErr("Material no seleccionado");
            return false;

        } else if (material.equals("Otro") && ctrldr.tfMaterialCustom.getText().isBlank()) {
            ctrldr.setMsgErr("Material no escrito");
            return false;

        // TEMP IMP
        } else if (tempImp == null || tempImp.isBlank()) {
            ctrldr.setMsgErr("Temperatura de impresion no escrita");
            return false;

        // TEMP CAMA
        } else if (tempCama == null || tempCama.isBlank()) {
            ctrldr.setMsgErr("Temperatura de cama no escrita");
            return false;

        // VEL IMP
        } else if (vel == null || vel.isBlank()) {
            ctrldr.setMsgErr("Velocidad de impresion no escrita");
            return false;

        // GR DISPO
        } else if (grDispo == null || grDispo.isBlank()) {
            ctrldr.setMsgErr("Gramos no escritos");
            return false;
        }

        return true;
    }

    private boolean camposValidos(String tempImp, String tempCama, String vel, String grDispo) {
        return 
        esValido(tempImp, "ti") && esValido(tempCama, "tc") && 
        esValido(vel, "v") && esValido(grDispo, "gr");
    }

    private boolean esValido(String valor, String tipoDato) {
        int valorInt;

        // Verificamos que se haya escrito un numero y no letras
        try {
            valorInt = Integer.parseInt(valor);

        } catch (NumberFormatException e) {
            switch (tipoDato) {
                case "ti" -> {
                    ctrldr.setMsgErr("Valor incorrecto en campo Temp. impresión");
                }

                case "tc" -> {
                    ctrldr.setMsgErr("Valor incorrecto en campo Temp. cama");
                }

                case "v" -> {
                    ctrldr.setMsgErr("Valor incorrecto en campo Velocidad");
                }

                case "gr" -> {
                    ctrldr.setMsgErr("Valor incorrecto en campo Gramos disponibles");
                }

                default -> {
                    ctrldr.registrarError("SALTA DEFAULT EN Pestania2:160 > esValido()");
                }
            }

            return false;
        }
        
        // Una vez se verifica que son numeros, se verifica que no sobrepase unos valores
        switch (tipoDato) {
            case "ti" -> {
                if (valorInt > 300) {
                    ctrldr.setMsgErr("La temperatura de impresion no puede ser superior a 300 °C");
                    return false;
                } 
                
                if  (valorInt < 0) {
                    ctrldr.setMsgErr("La temperatura de impresion no puede ser negativa");
                    return false;
                }
            }

            case "tc" -> {
                if (valorInt > 120) {
                    ctrldr.setMsgErr("La temperatura de la cama no puede ser superior a 120 °C");
                    return false;
                } 
                
                if  (valorInt < 0) {
                    ctrldr.setMsgErr("La temperatura de la cama no puede ser negativa");
                    return false;
                }
            }

            case "v" -> {
                if (valorInt > 300) {
                    ctrldr.setMsgErr("La velocidad de impresion no puede ser superior a 300 mm/s");
                    return false;
                } 
                
                if  (valorInt < 0) {
                    ctrldr.setMsgErr("La velocidad de impresion no puede ser negativa");
                    return false;
                }
            }

            case "gr" -> {
                if (valorInt > 50000) {
                    ctrldr.setMsgErr("Los gramos no pueden ser superior a 50000 g");
                    return false;
                }
                
                if  (valorInt < 0) {
                    ctrldr.setMsgErr("Los gramos no pueden ser negativos");
                    return false;
                }
            }

            default -> {
                ctrldr.registrarError("SALTA DEFAULT EN Pestania2:217 > esValido()");
                return false;
            }
        }

        return true;
    }
}
