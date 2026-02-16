package org.example.DAO;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.example.conexion.ConexionMongo;
import org.example.model.Amigos;
import org.example.model.Estudios;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;

public class AmigosDAO {
    private final MongoCollection<Document> amigos;

    public AmigosDAO() {
        MongoDatabase db = ConexionMongo.getDataBase();
        amigos = db.getCollection("amigos");
    }

    public void insertar(Amigos amigo) {
        ArrayList<Document> estudios = new ArrayList<>();

        for (Estudios estudio : amigo.getEstudios()) {
            estudios.add(new Document("titulo", estudio.getTitulo())
                    .append("centro", estudio.getCentro())
                    .append("anio", estudio.getAnio())
            );
        }

        Document doc = new Document("nombre", amigo.getNombre())
                .append("edad", amigo.getEdad())
                .append("hobbies", amigo.getHobbies())
                .append("telefonos", amigo.getTelefonos())
                .append("estudios", estudios);

        amigos.insertOne(doc);
    }

    public void listar() {
        for (Document d : amigos.find()) {
            String nombre = d.getString("nombre");
            int edad = d.getInteger("edad");
            List<String> hobbies = d.getList("hobbies", String.class);
            List<String> telefonos = d.getList("telefonos", String.class);

            List<Document> estudiosDocs = d.getList("estudios", Document.class);
            List<Estudios> estudiosList = new ArrayList<>();

            if (estudiosDocs != null) {
                for (Document estDoc : estudiosDocs) {
                    estudiosList.add(new Estudios(
                            estDoc.getString("titulo"),
                            estDoc.getString("centro"),
                            estDoc.getInteger("anio")
                    ));
                }
            }

            Amigos amigo = new Amigos(nombre, edad, hobbies, telefonos, estudiosList);
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
