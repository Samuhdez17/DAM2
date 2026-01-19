package service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.Curso;

import java.io.InputStreamReader;

public class CursoService {
    public String generarJson(Curso curso) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(curso);
    }

    public Curso cargarCurso() {
        Gson gson = new Gson();
        return gson.fromJson(new InputStreamReader(getClass().getResourceAsStream("/alumnos.json")), Curso.class);
    }
}
