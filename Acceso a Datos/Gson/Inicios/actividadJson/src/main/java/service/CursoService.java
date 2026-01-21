package service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.Curso;

import java.io.*;

public class CursoService {
    public String generarJson(Curso curso) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(curso);
    }

    public Curso cargarCurso() {
        Gson gson = new Gson();
        return gson.fromJson(new InputStreamReader(getClass().getResourceAsStream("/alumnos.json")), Curso.class);
    }

    public void actualizarCurso(Curso curso) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try {
            FileWriter out = new FileWriter("src/main/java");
            gson.toJson(curso, out);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
