package com.example.crud_srpingboot.interfaz;

import java.io.IOException;

import javafx.scene.control.Alert;

public class Pestania1 extends Controlador {
    // public void actualizarLista() {
    //     System.out.println("Actualizando lista"); // log

    //     try {
    //         lista = apiClient.listarAmigos(tipoOrdenacion);
    //         mensajeBreve.setText("Lista actualizada");
    //         System.out.println("Tamanio lista: " + lista.size());// log
    //         System.out.println("Contenido: " + lista);// log
            
    //     } catch (IOException | InterruptedException e) {
    //         Alert alert = new Alert(Alert.AlertType.ERROR);
    //         alert.setTitle("Error de conexión");
    //         alert.setContentText("No se puede conectar con el servidor.");
    //         alert.showAndWait();
    //     }

    //     tablaAmigos.getItems().setAll(lista);

    //     System.out.printf("""
    //     Lista actualziada:

    //         Value ComboBox: %s
    //         Tipo ordenacion: %s

    //     """, cbOrdenacion.getValue(), tipoOrdenacion);
    // }

    // public void cambiarOrdenacion() {
    //     String tipo = cbOrdenacion.getValue();

    //     switch (tipo) {
    //         case "Nombre ASC"      -> { tipoOrdenacion = "nombremM";    }
    //         case "Nombre DES"      -> { tipoOrdenacion = "nombreMm";    }
    //         case "Edad ASC"        -> { tipoOrdenacion = "edadmM";      }
    //         case "Edad DES"        -> { tipoOrdenacion = "edadMm";      }
    //         case "Hobbies ASC"     -> { tipoOrdenacion = "hobbiesmM";   }
    //         case "Hobbies DES"     -> { tipoOrdenacion = "hobbiesMm";   }
    //         case "Telefonos ASC"   -> { tipoOrdenacion = "telefonosmM"; }
    //         case "Telefonos DES"   -> { tipoOrdenacion = "telefonosMm"; }
    //         case "Estudios ASC"    -> { tipoOrdenacion = "estudiosmM";  }
    //         case "Estudios DES"    -> { tipoOrdenacion = "estudiosMm";  }
    //         default -> System.out.println("SALTA DEFAULT");
    //     }

    //     actualizarLista();
    // }
}
