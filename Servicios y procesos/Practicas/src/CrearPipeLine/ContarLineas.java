package CrearPipeLine;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ContarLineas {
    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            ArrayList<String> lineasTotales = new ArrayList<>();
            String linea;
            while ((linea = br.readLine()) != null) lineasTotales.add(linea);

            System.out.println(lineasTotales.size());
        } catch (Exception e) {
            System.out.println("Error al leer el archivo para contar lineas");
        }
    }
}
