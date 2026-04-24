package com.example.crud_srpingboot.interfaz;

import java.io.IOException;
import java.util.ArrayList;

import com.example.crud_srpingboot.DAO.model.Amigo;
import com.example.crud_srpingboot.DAO.model.Estudio;

import javafx.scene.control.Alert;

public class Pestania1 {
    private final Controlador controlador;

    public Pestania1(Controlador controlador) {
        this.controlador = controlador;
    }

    protected void actualizarLista() {
        System.out.println("Actualizando lista"); // log

        try {
            controlador.lista = controlador.apiClient.listarAmigos(controlador.tipoOrdenacion);
            controlador.mensajeBreve.setText("Lista actualizada");
            System.out.println("Tamanio lista: " + controlador.lista.size());// log
            
        } catch (IOException | InterruptedException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error de conexión");
            alert.setContentText("No se pudo conectar con el servidor.");
            alert.showAndWait();

            System.out.println(e);
        }

        controlador.tablaAmigos.getItems().setAll(controlador.lista);
    }

    protected void cambiarOrdenacion() {
        String tipo = controlador.cbOrdenacion.getValue();

        switch (tipo) {
            case "Nombre ASC"      -> { controlador.tipoOrdenacion = "nombremM";    }
            case "Nombre DES"      -> { controlador.tipoOrdenacion = "nombreMm";    }
            case "Edad ASC"        -> { controlador.tipoOrdenacion = "edadmM";      }
            case "Edad DES"        -> { controlador.tipoOrdenacion = "edadMm";      }
            case "Hobbies ASC"     -> { controlador.tipoOrdenacion = "hobbiesmM";   }
            case "Hobbies DES"     -> { controlador.tipoOrdenacion = "hobbiesMm";   }
            case "Telefonos ASC"   -> { controlador.tipoOrdenacion = "telefonosmM"; }
            case "Telefonos DES"   -> { controlador.tipoOrdenacion = "telefonosMm"; }
            case "Estudios ASC"    -> { controlador.tipoOrdenacion = "estudiosmM";  }
            case "Estudios DES"    -> { controlador.tipoOrdenacion = "estudiosMm";  }
            default -> System.out.println("SALTA DEFAULT");
        }

        actualizarLista();
    }

    protected void editarAmigo() {
        borrarDatos();

        controlador.editando = true;
        controlador.amigoEditando = controlador.tablaAmigos.getSelectionModel().getSelectedItem();
        
        controlador.tfNombre.setText(controlador.amigoEditando.getNombre());
        controlador.tfEdad.  setText(String.valueOf(controlador.amigoEditando.getEdad()));

        controlador.hobbies = new ArrayList<>(controlador.amigoEditando.getHobbies());

        controlador.telefonos = new ArrayList<>(controlador.amigoEditando.getTelefonos());

        controlador.estudios = new ArrayList<>(controlador.amigoEditando.getEstudios());

        for (String hobbie : controlador.amigoEditando.getHobbies()) {
            controlador.listaHobbies.getChildren().add(controlador.crearFilaHobbie(hobbie));
        }

        for (String tel : controlador.amigoEditando.getTelefonos()) {
            controlador.listaTelefonos.getChildren().add(controlador.crearFilaTelefono(tel));
        }

        for (Estudio est : controlador.amigoEditando.getEstudios()) {
            controlador.listaEstudios.getChildren().add(controlador.crearFilaEstudio(est));
        }

        // Cambiamos de pestaña
        controlador.tabAmigos.getSelectionModel().select(controlador.pestaniaAgregar);
    }

    private void borrarDatos() {
        controlador.tfHobbie.clear();
        controlador.tfTelefono.clear();

        controlador.tfTitulo.clear();
        controlador.tfCentro.clear();
        controlador.tfAnio.clear();

        controlador.listaEstudios.getChildren().clear();
        controlador.listaHobbies.getChildren().clear();
        controlador.listaTelefonos.getChildren().clear();

        controlador.hobbies.clear();
        controlador.telefonos.clear();
        controlador.estudios.clear();
    }

    protected void borrarAmigo() {
        Amigo seleccionado = controlador.tablaAmigos.getSelectionModel().getSelectedItem();

        try {
            controlador.apiClient.borrarAmigo(seleccionado.getId());
            actualizarLista();
            controlador.mensajeBreve.setText("Amigo eliminado");

        } catch (IOException | InterruptedException e) {
            System.out.println("Error al eliminar el amigo. " + e); // Logerr
        }
    }
}
