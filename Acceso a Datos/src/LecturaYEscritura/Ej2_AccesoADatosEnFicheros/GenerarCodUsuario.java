package LecturaYEscritura.Ej2_AccesoADatosEnFicheros;

import java.io.*;

public class GenerarCodUsuario {
    private final String ruta;

    public GenerarCodUsuario(String ruta) {
        this.ruta = ruta;
    }

    public String getCodigo() {
        String codigoGenerado = "";
        int numMasAlto = 0;

        try {
            File usuarios = new File(ruta);

            if  (!usuarios.exists()) return "U100";
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(usuarios));

            while (true) {
                Usuario usuario = (Usuario) ois.readObject();

                String codigoUsuario = usuario.getCodigo();
                int numeroUsuario = Integer.parseInt(codigoUsuario.substring(1));

                if (numeroUsuario > numMasAlto) numMasAlto = numeroUsuario;
            }


        } catch (EOFException e) {
            System.out.println("Código generado correctamente");
        } catch (IOException e) {
            System.out.println("Error al generar el código de usuario.");
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            System.out.println("Error al leer usuarios");
            throw new RuntimeException(e);
        }

        codigoGenerado = codigoGenerado + numMasAlto;

        return codigoGenerado;
    }
}
