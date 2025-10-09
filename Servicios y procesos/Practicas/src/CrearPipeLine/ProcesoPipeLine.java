package CrearPipeLine;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

public class ProcesoPipeLine {
    public static final String RUTA_CLASS = "out/production/Practicas";

    public static void main(String[] args) {
        File directorioClass = new File(RUTA_CLASS);
        String palabraClave = "chrome";

        ProcessBuilder tasklist = new ProcessBuilder("tasklist");
        ProcessBuilder find = new ProcessBuilder("findstr", "/I", palabraClave);
        ProcessBuilder contarLineas = new ProcessBuilder("java", ContarLineas.class.getName());
        ProcessBuilder multiplcarPor2 = new ProcessBuilder("java", "-cp", RUTA_CLASS, Multiplicar.class.getName());

        tasklist.directory(directorioClass);
        find.directory(directorioClass);
        contarLineas.directory(directorioClass);

        File erroresTxt = new File("CrearPipeLine/erroresPipeLineTasklist.txt");
        tasklist.redirectError(ProcessBuilder.Redirect.appendTo(erroresTxt));
        find.redirectError(ProcessBuilder.Redirect.appendTo(erroresTxt));
        contarLineas.redirectError(ProcessBuilder.Redirect.appendTo(erroresTxt));
        multiplcarPor2.redirectError(ProcessBuilder.Redirect.appendTo(erroresTxt));


        try {
            List<Process> hijos = ProcessBuilder.startPipeline(Arrays.asList(tasklist, find, contarLineas, multiplcarPor2));

            try(BufferedReader lector = new BufferedReader(new InputStreamReader(hijos.getLast().getInputStream()))){
                String linea;
                while((linea = lector.readLine()) != null){
                    int numeroResultante = Integer.parseInt(linea);

                    System.out.printf("El número de procesos de %s es: %d", palabraClave, numeroResultante);
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
