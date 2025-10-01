package LecturaYEscritura.Ej2_AccesoADatosEnFicheros;

import java.io.*;

public class GenerarCodUsuario {
    private final File fichero;

    public GenerarCodUsuario(File fichero) {
        this.fichero = fichero;
    }

    public String getCodigo() {
        if (!fichero.exists()) return "U100";

        String codigoGenerado = "U";
        int numMasAlto = 0;

        try (BufferedReader br = new BufferedReader (new FileReader(fichero))) {
            String lineaUsuario;

            while ((lineaUsuario = br.readLine()) != null) {
                Usuario usuario = new Usuario(lineaUsuario);
                String codigoUsuario = usuario.getCodigo();
                int numeroUsuario = Integer.parseInt(codigoUsuario.substring(1));

                if (numeroUsuario > numMasAlto) numMasAlto = numeroUsuario;
            }

        } catch (EOFException e) {
            System.out.println("Código generado correctamente");
        } catch (IOException e) {
            System.out.println("Error al generar el código de usuario.");
            throw new RuntimeException(e);
        }

        codigoGenerado = codigoGenerado + (numMasAlto + 1);

        return codigoGenerado;
    }
}
