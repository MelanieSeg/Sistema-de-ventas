package Controlador;

import Modelo.Cliente;
import Basedatos.MongoDBConnection;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class ClienteController {
    private final MongoDatabase database;
    private final MongoCollection<Document> collection;

    public ClienteController() {
        MongoDBConnection connection = new MongoDBConnection();
        this.database = connection.getDatabase();
        this.collection = database.getCollection("clientes");
    }

    public void agregarCliente(Cliente cliente) {
        Document doc = new Document("nombreCompleto", cliente.getNombreCompleto())
                .append("correoElectronico", cliente.getCorreoElectronico())
                .append("telefono", cliente.getTelefono())
                .append("direccion", cliente.getDireccion());
        collection.insertOne(doc);
    }

    public List<Cliente> obtenerClientes() {
        List<Cliente> clientes = new ArrayList<>();
        for (Document doc : collection.find()) {
            clientes.add(new Cliente(doc.getObjectId("_id").toString(),
                    doc.getString("nombreCompleto"),
                    doc.getString("correoElectronico"),
                    doc.getString("telefono"),
                    doc.getString("direccion")));
        }
        return clientes;
    }

    public void actualizarCliente(Cliente cliente) {
        Document doc = new Document("nombreCompleto", cliente.getNombreCompleto())
                .append("correoElectronico", cliente.getCorreoElectronico())
                .append("telefono", cliente.getTelefono())
                .append("direccion", cliente.getDireccion());
        collection.replaceOne(new Document("_id", new org.bson.types.ObjectId(cliente.getId())), doc);
    }
    
    public Cliente obtenerClientePorId(String clienteId) {
        Document doc = collection.find(new Document("_id", new org.bson.types.ObjectId(clienteId))).first();
        if (doc != null) {
            return new Cliente(doc.getObjectId("_id").toString(),
                    doc.getString("nombreCompleto"),
                    doc.getString("correoElectronico"),
                    doc.getString("telefono"),
                    doc.getString("direccion"));
        }
        return null; // Retorna null si no se encuentra el cliente
    }

     public List<Cliente> buscarClientesPorNombre(String nombre) {
        List<Cliente> clientes = new ArrayList<>();
        for (Document doc : collection.find(new Document("nombreCompleto", new Document("$regex", nombre).append("$options", "i")))) {
            clientes.add(new Cliente(doc.getObjectId("_id").toString(),
                    doc.getString("nombreCompleto"),
                    doc.getString("correoElectronico"),
                    doc.getString("telefono"),
                    doc.getString("direccion")));
        }
        return clientes;
    }

    public List<Cliente> buscarClientesPorEmail(String email) {
        List<Cliente> clientes = new ArrayList<>();
        for (Document doc : collection.find(new Document("correoElectronico", new Document("$regex", email).append("$options", "i")))) {
            clientes.add(new Cliente(doc.getObjectId("_id").toString(),
                    doc.getString("nombreCompleto"),
                    doc.getString("correoElectronico"),
                    doc.getString("telefono"),
                    doc.getString("direccion")));
        }
        return clientes;
    }
}