/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Basedatos;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;
/**
 *
 * @author melit
 */
public class MongoDBConnection {
    private final MongoClient mongoClient;
    private final MongoDatabase database;

    public MongoDBConnection() {
        MongoClientURI uri = new MongoClientURI("mongodb://localhost:27017"); // Cambia si es necesario
        mongoClient = new MongoClient(uri);
        database = mongoClient.getDatabase("SistemaVentas");
    }

    public MongoDatabase getDatabase() {
        return database;
    }

    public void close() {
        mongoClient.close();
    }
}
