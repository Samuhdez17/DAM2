package ficherosxml;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class CrearDat {
    public static void main(String[] args) {
        String fichero = "ficherosxml/AleatorioEmple.dat";

        int[] ids = {1, 2, 3, 4};
        String[] apellidos = {"Lopez", "Pérez", "Villoria", "Garcia"};
        int[] deptos = {10, 20, 10, 30};
        double[] salarios = {1200.5, 1300.89, 1100.00, 720.6};

        try(RandomAccessFile raf = new RandomAccessFile(fichero, "rw")) {
            raf.setLength(0);
            for (int i = 0; i < ids.length; i++) {
                raf.writeInt(ids[i]);
                raf.writeUTF(apellidos[i]);
                raf.writeInt(deptos[i]);
                raf.writeDouble(salarios[i]);
            }

            System.out.println("Fichero creado correctamente");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
