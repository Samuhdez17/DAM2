package LecturaYEscritura.Ejercicio1;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class MiObjectOutputStream extends ObjectOutputStream {
    public MiObjectOutputStream(OutputStream oos) throws IOException {
        super(oos);
    }

    @Override
    protected void writeStreamHeader() throws IOException {}
}
