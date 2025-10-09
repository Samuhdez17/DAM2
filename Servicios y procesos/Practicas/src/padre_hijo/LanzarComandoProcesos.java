package padre_hijo;

import java.io.File;
import java.io.IOException;

public class LanzarComandoProcesos {

    public static void main(String[] args) {

        //windows: "cmd.exe", "/c", "tasklist"
        ProcessBuilder constructorProcesos = new ProcessBuilder("bash", "-c","ps -e");


        try {

            //Redirige la salida del hijo a lo mismo que tenga el padre
         //   constructorProcesos.redirectOutput(ProcessBuilder.Redirect.INHERIT);

            //Redirige la salida del hijo a un fichero
            constructorProcesos.redirectOutput(new File("salidaListaProcesos.txt"));

            constructorProcesos.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
