package org.example.DAO;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.example.conexion.ConexionMongo;
import org.example.model.Amigo;
import org.example.model.Estudio;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;

public class AmigosDAO {
    private final MongoCollection<Document> amigos;

    public AmigosDAO() {
        MongoDatabase db = ConexionMongo.getDataBase();
        amigos = db.getCollection("si");
    }

    public void insertar(Amigo amigo) {
        ArrayList<Document> estudios = new ArrayList<>();

        for (Estudio estudio : amigo.getEstudios()) {
            estudios.add(new Document("titulo", estudio.getTitulo())
                    .append("centro", estudio.getCentro())
                    .append("anio", estudio.getAnio())
            );
        }

        Document docAmigo = new Document("nombre", amigo.getNombre())
                .append("edad", amigo.getEdad())
                .append("hobbies", amigo.getHobbies())
                .append("telefonos", amigo.getTelefonos())
                .append("estudios", estudios);

        amigos.insertOne(docAmigo);
    }

    public void listar() {
        for (Document d : amigos.find()) {
            String nombre = d.getString("nombre");
            int edad = d.getInteger("edad");

            List<String> hobbies = d.getList("hobbies", String.class);
            List<String> telefonos = d.getList("telefonos", String.class);

            List<Document> estudiosDoc = d.getList("estudios", Document.class);
            List<Estudio> estudiosList = new ArrayList<>();

            if (estudiosDoc != null) {
                for (Document estudio : estudiosDoc) {
                    estudiosList.add(new Estudio(
                            estudio.getString("titulo"),
                            estudio.getString("centro"),
                            estudio.getInteger("anio")
                    ));
                }
            }

            Amigo amigo = new Amigo(nombre, edad, hobbies, telefonos, estudiosList);

            System.out.println(amigo);
        }
    }

    public void actualizar(String nombre, int edad) {
        amigos.updateOne(eq("nombre", nombre), set("edad", edad));
    }

    public void eliminar(String nombre) {
        amigos.deleteOne(eq("nombre", nombre));
    }
}
