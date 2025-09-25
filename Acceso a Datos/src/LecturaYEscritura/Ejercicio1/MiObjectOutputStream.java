package LecturaYEscritura.Ejercicio1;

import java.io.IOException;
import java.io.ObjectOutputStream;

public class MiObjectOutputStream extends ObjectOutputStream {
    public MiObjectOutputStream(ObjectOutputStream oos) throws IOException {
        super(oos);
    }

    public MiObjectOutputStream() throws IOException {
        super();
    }

    @Override
    protected void writeStreamHeader() throws IOException {}
}
