package CrearPipeLine;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

public class ProcesoPipeLine {
    public static final String RUTA_CLASS = "out/production/Practicas";

    public static void main(String[] args) {
        ProcessBuilder tasklist = new ProcessBuilder("tasklist");
        ProcessBuilder find = new ProcessBuilder("findstr", "/I", "chrome");
        ProcessBuilder contarLineas = new ProcessBuilder("java", "CrearPipeLine.ContarLineas");


        File directorioClass = new File(RUTA_CLASS);
        tasklist.directory(directorioClass);
        find.directory(directorioClass);
        contarLineas.directory(directorioClass);

        tasklist.redirectError(ProcessBuilder.Redirect.appendTo(new File("erroresPipeLineTasklist.txt")));
        contarLineas.redirectError(ProcessBuilder.Redirect.appendTo(new File("erroresPipeLineTasklist.txt")));

        try {
            List<Process> hijos = ProcessBuilder.startPipeline(Arrays.asList(tasklist, find, contarLineas));

            try(BufferedReader lector = new BufferedReader(new InputStreamReader(hijos.getLast().getInputStream()))){
                String linea;
                while((linea = lector.readLine()) != null){
                    int numeroResultante = Integer.parseInt(linea);

                    System.out.printf("[FIN DEL PROCESO]: El número resultante es -> %d", numeroResultante);
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
