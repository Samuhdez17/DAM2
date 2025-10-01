package LecturaYEscritura.Ej2_AccesoADatosEnFicheros;

import java.util.ArrayList;
import java.util.Collections;

public class Usuario {
    private final ArrayList<String> aficiones = new ArrayList<>();
    private final String codigo;

    public Usuario(String codigo, String[] aficionesArray) {
        gestionarAficiones(aficionesArray);
        this.codigo = codigo;
    }

    public Usuario (String linea) {
        String[] fraseSeccionada = linea.split(" ");
        this.codigo = fraseSeccionada[0];
    }

    public String getCodigo() {
        return codigo;
    }

    public ArrayList<String> getAficiones() {
        return aficiones;
    }

    private void gestionarAficiones(String[] aficionesArray) {
        for (String aficion : aficionesArray) {
            StringBuilder aficionCorregida = new StringBuilder();

            for (int letra = 0; letra < aficion.length(); letra++) {
                if (aficion.charAt(letra) == ' ') {
                    if (letra != 0 && letra != aficion.length() - 1) aficionCorregida.append('_');


                } else aficionCorregida.append(aficion.charAt(letra));
            }

            aficiones.add(aficionCorregida.toString());
            Collections.sort(aficiones);
        }
    }

    public String toString() {
        StringBuilder stringSalida = new StringBuilder();

        stringSalida.append(codigo + " ");
        for (int i = 0 ; i < aficiones.size() ; i++) {
            stringSalida.append(aficiones.get(i));
            if (i != aficiones.size() - 1) stringSalida.append(" ");
        }

        return stringSalida.toString();
    }
}
