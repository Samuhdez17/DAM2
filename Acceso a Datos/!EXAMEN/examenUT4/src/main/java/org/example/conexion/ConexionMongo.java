package org.example.conexion;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class ConexionMongo {
    private static final MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");;

    public static MongoDatabase getDataBase() {
        return mongoClient.getDatabase("academia");
    }
}
