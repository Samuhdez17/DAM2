package servidor;

import cliente.ClienteFTP;

import java.io.*;
import java.net.Socket;
import java.util.HashMap;

public class HiloServidor implements Runnable {
    private final Socket cliente;
    private final String raiz;
    private final HashMap<String, String> usuarios;
    private boolean sesionIniciada = false;

    public HiloServidor(Socket cliente, String raiz, HashMap<String, String> usuarios) {
        this.cliente = cliente;
        this.raiz = raiz;
        this.usuarios = usuarios;
    }

    @Override
    public void run() {
        try {
            BufferedReader entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            PrintWriter salida = new PrintWriter(new OutputStreamWriter(cliente.getOutputStream()), true);

            String comando;
            while ((comando = entrada.readLine()) != null) {
                String[] linea = comando.split("``");

                switch (linea[0]) {
                    case "HELLO" -> salida.println("Bienvenido al servidor FTP ");

                    case "LOGIN" -> {
                        if (sesionIniciada) {
                            salida.println("500 Sesion ya iniciada");
                            continue;
                        }

                        String contrasenia = usuarios.get(linea[1]);

                        if (contrasenia == null) {
                            salida.println("401 Usuario o contraseña invalidos");

                        } else {
                            if (contrasenia.equals(linea[2])) {
                                sesionIniciada = true;
                                salida.println("200 Sesion iniciada");

                            } else {
                                salida.println("401 Usuario o contraseña invalidos");
                            }
                        }

                    }

                    case "LS" -> {
                        if (!sesionIniciada) {
                            salida.println("401 Sesion no iniciada");
                            return;
                        }

                        File directorio = new File(raiz);
                        File[] archivos = directorio.listFiles();

                        if (archivos == null) {
                            salida.println("550 El directorio no existe");

                        } else {
                            salida.println("150 Inicio lista");

                            for (File archivo : archivos) {
                                if (archivo.isFile()) {
                                    salida.println(archivo.getName());
                                }
                            }

                            salida.println("226 Fin lista");
                        }
                    }

                    case "GET" -> {
                        if (!sesionIniciada) {
                            salida.println("401 Sesion no iniciada");
                            return;
                        }

                        if (linea.length < 2) {
                            salida.println("550 Comando mal formado, faltan datos");
                            return;
                        }

                        String nombreArchivo = linea[1];
                        File archivo = new File(raiz, nombreArchivo);

                        if (!archivo.exists() || !archivo.isFile()) {
                            salida.println("550 Archivo no existe");

                        } else {
                            salida.println("150 Inicio lista");

                            // Tamaño del archivo
                            DataOutputStream dos = new DataOutputStream(cliente.getOutputStream());
                            dos.writeLong(archivo.length());

                            // Contenido del archivo
                            FileInputStream fis = new FileInputStream(archivo);
                            byte[] buffer = new byte[8192];
                            int leidos;

                            while ((leidos = fis.read(buffer)) != -1) {
                                dos.write(buffer, 0, leidos);
                            }

                            fis.close();
                            dos.flush();
                            salida.println("226 Fin lista");
                        }
                    }

                    case "PUT" -> {
                        if (!sesionIniciada) {
                            salida.println("401 Sesion no iniciada");
                            return;
                        }

                        if (linea.length < 3) {
                            salida.println("550 Comando mal formado, faltan datos");
                            return;
                        }

                        String nombreArchivo = linea[1];
                        long tamanio = Long.parseLong(linea[2]);

                        salida.println("150 Inicio lista");

                        // Recibir archivo
                        DataInputStream dis = new DataInputStream(cliente.getInputStream());
                        File archivoDestino = new File(raiz, nombreArchivo);
                        FileOutputStream fos = new FileOutputStream(archivoDestino);

                        byte[] buffer = new byte[8192];
                        long restantes = tamanio;
                        int leidos;

                        while (restantes > 0) {
                            int porLeer = (int) Math.min(buffer.length, restantes);
                            leidos = dis.read(buffer, 0, porLeer);
                            if (leidos == -1) break;
                            fos.write(buffer, 0, leidos);
                            restantes -= leidos;
                        }

                        fos.close();
                        salida.println("226 Fin lista");
                    }

                    case "EXIT" -> {
                        salida.println("221 Adios");
                        entrada.close();
                        salida.close();
                        cliente.close();
                        return;
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);

        } finally {
            try {
                cliente.close();
                ClienteFTP.cerrar();
                ServidorFTP.eliminarCliente();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
