package org.example.DAO;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.example.conexion.ConexionMongo;

public class EstudiosDAO {
    private final MongoCollection<Document> estudios;

    public EstudiosDAO() {
        MongoDatabase db = ConexionMongo.getDataBase();
        estudios = db.getCollection("estudios");
    }
}
