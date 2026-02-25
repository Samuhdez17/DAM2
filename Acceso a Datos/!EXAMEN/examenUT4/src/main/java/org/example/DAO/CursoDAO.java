package org.example.DAO;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.DeleteOptions;
import com.mongodb.client.model.UpdateOptions;
import org.bson.Document;
import org.example.model.Alumno;
import org.example.model.Curso;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;

public class CursoDAO {
    MongoCollection<Document> cursos;

    public CursoDAO(MongoDatabase cliente) {
        cursos = cliente.getCollection("cursos");
    }

    public void listarCursos() {
//        List<Document> listaCursosDoc = cursos.find();
//        List<Document> listaAlumnosDoc = cursos.find("alumnos");
//
//        ArrayList<Alumno> listaAlumnos = new ArrayList<>();
//        ArrayList<Curso> listaCursos = new ArrayList<>();
//
//        for (Document docAlumno : listaAlumnosDoc) {
//            listaAlumnos.add(new Alumno(
//                    docAlumno.getString("codigo"),
//                    docAlumno.getString("nombre"),
//                    docAlumno.getString("email"),
//                    docAlumno.getDouble("nota")
//            ));
//        }
//
//        for (Document docCurso : listaCursosDoc) {
//            listaCursos.add(new Curso(
//                    docCurso.getString("codigo"),
//                    docCurso.getString("nombre"),
//                    docCurso.getInteger("horas"),
//                    docCurso.getBoolean("activo"),
//                    listaAlumnos
//            ));
//        }
//
//        leerListaCursos(listaCursos);
    }

    public void listarCursosActivos() {
//        List<Document> listaCursosDoc = cursos.find();
//        List<Document> listaAlumnosDoc = cursos.find("alumnos");
//
//        ArrayList<Alumno> listaAlumnos = new ArrayList<>();
//        ArrayList<Curso> listaCursos = new ArrayList<>();
//
//        for (Document docAlumno : listaAlumnosDoc) {
//            listaAlumnos.add(new Alumno(
//                    docAlumno.getString("codigo"),
//                    docAlumno.getString("nombre"),
//                    docAlumno.getString("email"),
//                    docAlumno.getDouble("nota")
//            ));
//        }
//
//        for (Document docCurso : listaCursosDoc) {
//            if (docCurso.getBoolean("activo"))
//                listaCursos.add(new Curso(
//                    docCurso.getString("codigo"),
//                    docCurso.getString("nombre"),
//                    docCurso.getInteger("horas"),
//                    docCurso.getBoolean("activo"),
//                    listaAlumnos
//            ));
//        }
//
//        leerListaCursos(listaCursos);
    }

    public void mostrarAlumnosPorCurso(String curso) {
//        List<Document> listaCursosDoc = cursos.find(eq("codigo", curso));
//        List<Document> listaAlumnosDoc = cursos.find("alumnos");
//
//        ArrayList<Alumno> listaAlumnos = new ArrayList<>();
//        ArrayList<Curso> listaCursos = new ArrayList<>();
//
//        for (Document docAlumno : listaAlumnosDoc) {
//            listaAlumnos.add(new Alumno(
//                    docAlumno.getString("codigo"),
//                    docAlumno.getString("nombre"),
//                    docAlumno.getString("email"),
//                    docAlumno.getDouble("nota")
//            ));
//        }
//
//        for (Document docCurso : listaCursosDoc) {
//            listaCursos.add(new Curso(
//                    docCurso.getString("codigo"),
//                    docCurso.getString("nombre"),
//                    docCurso.getInteger("horas"),
//                    docCurso.getBoolean("activo"),
//                    listaAlumnos
//            ));
//        }
//
//        for (Curso c : listaCursos)
//            for (Alumno a : c.getAlumnos())
//                System.out.println(a);
    }

    public void listarCursosConAlumnoSupA9() {
//        List<Document> listaCursosDoc = cursos.find();
//        List<Document> listaAlumnosDoc = cursos.find("alumnos");
//
//        ArrayList<Alumno> listaAlumnos = new ArrayList<>();
//        ArrayList<Curso> listaCursos = new ArrayList<>();
//        ArrayList<Curso> listaCursosAptos = new ArrayList<>();
//
//        for (Document docAlumno : listaAlumnosDoc) {
//            listaAlumnos.add(new Alumno(
//                    docAlumno.getString("codigo"),
//                    docAlumno.getString("nombre"),
//                    docAlumno.getString("email"),
//                    docAlumno.getDouble("nota")
//            ));
//        }
//
//        for (Document docCurso : listaCursosDoc) {
//            listaCursos.add(new Curso(
//                    docCurso.getString("codigo"),
//                    docCurso.getString("nombre"),
//                    docCurso.getInteger("horas"),
//                    docCurso.getBoolean("activo"),
//                    listaAlumnos
//            ));
//        }
//
//        for (Curso c : listaCursos)
//            for (Alumno a : c.getAlumnos())
//                if (a.getNota() > 9) {
//                    listaCursosAptos.add(c);
//                    break;
//                }
//
//        leerListaCursos(listaCursosAptos);
    }

    public void insertarCurso(Curso curso) {
        List<Document> alumnos = new ArrayList<>();

        for (Alumno alumno : curso.getAlumnos()) {
            alumnos.add(new Document("dni", alumno.getDni())
                    .append("nombre", alumno.getNombre())
                    .append("email", alumno.getEmail())
                    .append("nota", alumno.getNota())
            );
        }

        Document cursoDoc = new Document("codigo", curso.getCodigo())
                .append("nombre", curso.getNombre())
                .append("horas", curso.getHoras())
                .append("activo", curso.isActivo())
                .append("alumnos", alumnos);

        cursos.insertOne(cursoDoc);
    }

    public void cambiarHoras(String curso, int nuevasHoras) {
        cursos.updateOne(eq("codigo", curso), set("horas", nuevasHoras));
    }

    public void cambiarEstado(String curso, boolean estado) {
        cursos.updateOne(eq("codigo", curso), set("estado", estado));
    }

    public void cambiarNotaAlumno(String curso, String dni, double nota) {
        cursos.updateOne(eq("codigo", curso), set("nota", nota), (UpdateOptions) eq("alumnos.dni", dni));
    }

    public void eliminarAlumno(String curso, String dni) {
        cursos.deleteOne(eq("codigo", curso), (DeleteOptions) eq("alumno.dni", dni));
    }

    public void eliminarCurso(String curso) {
        cursos.deleteOne(eq("codigo", curso));
    }

    private static void leerListaCursos(ArrayList<Curso> listaCursos) {
        for (Curso c : listaCursos)
            System.out.println(c);
    }
}
