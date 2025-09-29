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

    public String toString() {
        StringBuilder stringSalida = new StringBuilder();

        stringSalida.append(codigo + " ");
        for (int i = 0 ; i < aficiones.size() ; i++) {
            stringSalida.append(aficiones.get(i));
            if (i != aficiones.size() - 1) stringSalida.append(", ");
        }

        return stringSalida.toString();
    }
}
