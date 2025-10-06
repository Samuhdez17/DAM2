package CrearPipeLine;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class ProcesoPipeLine {
    public static final String RUTA_CLASS = "out/production/Practicas";

    public static void main(String[] args) {
        ProcessBuilder taskList = new ProcessBuilder("tasklist");
        ProcessBuilder find = new ProcessBuilder("find", "chrome");
        ProcessBuilder contarLineas = new ProcessBuilder(); // Hay que hacer el pipe line con el cmd y con la clase contarlineas meterlo en la lista y que haga lo mismo que wc -l

        File directorioClass = new File(RUTA_CLASS);
        contarLineas.directory(directorioClass);
        find.directory(directorioClass);

        contarLineas.redirectError(ProcessBuilder.Redirect.appendTo(new File("erroresPipeLine.txt")));
        find.redirectError(ProcessBuilder.Redirect.appendTo(new File("erroresPipeLine.txt")));

        try {
            List<Process> hijos = ProcessBuilder.startPipeline(Arrays.asList(taskList, find, contarLineas));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
