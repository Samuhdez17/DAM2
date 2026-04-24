package com.example.crud_srpingboot.interfaz;

import java.io.IOException;
import java.time.LocalDate;

import com.example.crud_srpingboot.DAO.model.Amigo;
import com.example.crud_srpingboot.DAO.model.Estudio;

public class Pestania2 {
    private final Controlador controlador;
    private final Pestania1 pest1;

    public Pestania2(Controlador controlador, Pestania1 pest1) {
        this.controlador = controlador;
        this.pest1 = pest1;
    }

    protected void agregarHobbie() {
        String hobbie = controlador.tfHobbie.getText();

        if (!hobbie.isEmpty()) {
            controlador.listaHobbies.getChildren().add(controlador.crearFilaHobbie(hobbie));
            controlador.tfHobbie.clear();

            controlador.hobbies.add(hobbie);
        }

        System.out.println(controlador.hobbies.size());
    }

    protected void agregarTelefono() {
        String telefono = controlador.tfTelefono.getText();
        
        if (!telefono.isEmpty()) {
            controlador.listaTelefonos.getChildren().add(controlador.crearFilaTelefono(telefono));
            controlador.tfTelefono.clear();

            controlador.telefonos.add(telefono);
        }

        System.out.println(controlador.telefonos.size());
    }

    protected void agregarEstudio() {
        if (
            !controlador.tfTitulo.getText().isEmpty() &&
            !controlador.tfCentro.getText().isEmpty() &&
            !controlador.tfAnio.getText().isEmpty()
        ) {
            int anio;

            try {
                anio = verificarAnioEstudio();
            } catch (NumberFormatException e) {
                controlador.msgErr.setText("El año introducido no es un numero");
                return;
            }

            if (anio < 1900 || anio > LocalDate.now().getYear()) {
                controlador.msgErr.setText("El año introducido no es valido");
                return;
            }

            Estudio estudio = new Estudio(controlador.tfTitulo.getText(), controlador.tfCentro.getText(), anio);

            controlador.listaEstudios.getChildren().add(controlador.crearFilaEstudio(estudio));
            controlador.estudios.add(estudio);

            controlador.tfTitulo.clear();
            controlador.tfCentro.clear();
            controlador.tfAnio.clear();

            System.out.println(controlador.estudios.size());
        }
    }

    private int verificarAnioEstudio() {
        controlador.msgErr.setText("");
        return Integer.parseInt(controlador.tfAnio.getText());
    }

    protected void guardarAmigo() {
        if (controlador.tfNombre.getText().isBlank()) {
            controlador.msgErr.setText("Campo de nombre vacio");

        } else if (controlador.tfEdad.getText().isBlank()) {
            controlador.msgErr.setText("Campo de edad vacio");

        } else {
            controlador.msgErr.setText("");
            
            // Se guardan los cambios
            Amigo amigoNuevo = new Amigo(
                controlador.tfNombre.getText(),
                Integer.parseInt(controlador.tfEdad.getText()),
                controlador.hobbies,
                controlador.telefonos,
                controlador.estudios
            );

            try {
                if (controlador.editando) {
                    controlador.apiClient.actualizarAmigo(controlador.amigoEditando.getId(), amigoNuevo);
    
                } else {
                    controlador.apiClient.insertarAmigo(amigoNuevo);
                }

                controlador.mensajeBreve.setText("Amigo agregado correctamente");
                
            } catch (IOException | InterruptedException e) {
                controlador.mensajeBreve.setText("Fallo al agregar amigo");
                // meter en log el error

            } finally {
                pest1.actualizarLista();
                controlador.editando = false;

                // Cambiamos de pestaña
                controlador.tabAmigos.getSelectionModel().select(controlador.pestaniaAmigos);
                borrarDatos();
            }
        }
    }

    private void borrarDatos() {
        controlador.tfNombre.clear();
        controlador.tfEdad.clear();

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
}
