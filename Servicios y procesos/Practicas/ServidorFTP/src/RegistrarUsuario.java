import java.io.*;

// Clase para agregar usuarios al .txt en formato UTF
public class RegistrarUsuario {
    private static final File usuariosBD = new File("src/files/usuariosBD.txt");
    private static final String nombre = "";
    private static final String contra = "";

    static void main() throws IOException {
        new DataOutputStream(
                new FileOutputStream(usuariosBD))
                    .writeUTF(String.format("%s´´%s", nombre, contra));
    }
}
