package PipeLine;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

public class EjercicioPipelines {

    public static final String RUTA_CLASS = "out/production/PipeLine";

    public static void main(String[] args) {

        //Crear los PBs
        ProcessBuilder inicial = new ProcessBuilder("java", Inicial.class.getName(), args[0] /*String.valueOf(5)*/);
        ProcessBuilder sumador = new ProcessBuilder("java", Sumador.class.getName());
        ProcessBuilder multiplicador = new ProcessBuilder("java", Multiplicador.class.getName());

        //Configurar directory a los 3
        File directorioClass = new File(RUTA_CLASS);

        inicial.directory(directorioClass);
        sumador.directory(directorioClass);
        multiplicador.directory(directorioClass);

        //Configuro las redirecciones de error a fichero a los 3
        inicial.redirectError(ProcessBuilder.Redirect.appendTo(new File("erroresPipelineNumeros.txt")));
        sumador.redirectError(ProcessBuilder.Redirect.appendTo(new File("erroresPipelineNumeros.txt")));
        multiplicador.redirectError(ProcessBuilder.Redirect.appendTo(new File("erroresPipelineNumeros.txt")));

        //Lanzar el pipeline
        try {
           List<Process> hijos= ProcessBuilder.startPipeline(Arrays.asList(inicial, sumador, multiplicador));

            try(BufferedReader lector = new BufferedReader(new InputStreamReader(hijos.getLast().getInputStream()))){
                String linea;
                while((linea = lector.readLine()) != null){
                    int numeroResultante = Integer.parseInt(linea);

                    System.out.printf("[FIN DEL PROCESO]: El número resultante es -> %d", numeroResultante);
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
