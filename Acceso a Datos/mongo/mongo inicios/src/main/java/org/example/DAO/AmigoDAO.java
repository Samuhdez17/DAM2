package org.example.DAO;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.example.conexion.ConexionMongo;
import org.example.model.Amigos;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;

public class AmigoDAO {
    private MongoCollection<Document> amigos;

    public AmigoDAO() {
        MongoDatabase db = ConexionMongo.getDataBase();
        amigos = db.getCollection("amigos");
    }

    public void insertar(Amigos amigo) {
        Document doc = new Document("nombre", amigo.getNombre())
                .append("edad", amigo.getEdad())
                .append("telefono", amigo.getTelefono());

        amigos.insertOne(doc);
    }

    public void listar() {
        for (Document d : amigos.find()) {
            System.out.println(d.toJson());
        }
    }

    public void actualizar(String nombre, int edad) {
        amigos.updateMany(eq("nombre", nombre), set("edad", edad));
    }

    public void eliminar(String nombre) {
        amigos.deleteOne(eq("nombre", nombre));
    }
}
