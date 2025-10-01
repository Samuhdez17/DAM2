package LecturaYEscritura.Ej2_AccesoADatosEnFicheros;

import java.io.*;

public class GenerarCodUsuario {
    private final String ruta;

    public GenerarCodUsuario(String ruta) {
        this.ruta = ruta;
    }

    public String getCodigo() {
        File usuarios = new File(ruta);

        if (!usuarios.exists()) return "U100";

        String codigoGenerado = "U";
        int numMasAlto = 0;

        try (BufferedReader br = new BufferedReader (new FileReader(usuarios))) {
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
