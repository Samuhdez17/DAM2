package CrearPipeLine;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class ProcesoPipeLine {
    public static final String RUTA_CLASS = "out/production/Practicas";

    public static void main(String[] args) {
        ProcessBuilder taskList = new ProcessBuilder("java", TaskList.class.getName());
        ProcessBuilder find = new ProcessBuilder("java", Find.class.getName());

        File directorioClass = new File(RUTA_CLASS);
        taskList.directory(directorioClass);
        find.directory(directorioClass);

        taskList.redirectError(ProcessBuilder.Redirect.appendTo(new File("erroresPipeLine.txt")));
        find.redirectError(ProcessBuilder.Redirect.appendTo(new File("erroresPipeLine.txt")));

        try {
            List<Process> hijos = ProcessBuilder.startPipeline(Arrays.asList(taskList, find));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
