package LecturaYEscritura.Ej2_AccesoADatosEnFicheros;

import java.util.ArrayList;

public class Usuario {
    private ArrayList<String> aficiones;
    private final String codigo;

    public Usuario(String codigo, String[] aficionesS) {
        this.codigo = codigo;
        for (String aficion : aficionesS) aficiones.add(aficion);
    }

    public String getCodigo() {
        return codigo;
    }

    public ArrayList<String> getAficiones() {
        return aficiones;
    }
}
