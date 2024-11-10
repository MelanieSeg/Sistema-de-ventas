package Controlador;

import Modelo.Producto;
import Basedatos.MongoDBConnection;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class ProductoController {
    private final MongoDatabase database;
    private final MongoCollection<Document> collection;

    public ProductoController() {
        MongoDBConnection connection = new MongoDBConnection();
        this.database = connection.getDatabase();
        this.collection = database.getCollection("productos");
    }

    public void agregarProducto(Producto producto) {
        Document doc = new Document("nombre", producto.getNombre())
                .append("categoria", producto.getCategoria())
                .append("marca", producto.getMarca())
                .append("cantidadEnStock", producto.getCantidadEnStock())
                .append("precioUnitario", producto.getPrecioUnitario());
        collection.insertOne(doc);
    }

    public List<Producto> obtenerProductos() {
        List<Producto> productos = new ArrayList<>();
        for (Document doc : collection.find()) {
            productos.add(new Producto(doc.getObjectId("_id").toString(),
                    doc.getString("nombre"),
                    doc.getString("categoria"),
                    doc.getString("marca"),
                    doc.getInteger("cantidadEnStock"),
                    doc.getDouble("precioUnitario")));
        }
        return productos;
    }

    public Producto obtenerProductoPorId(String productoId) {
        Document doc = collection.find(new Document("_id", new org.bson.types.ObjectId(productoId))).first();
        if (doc != null) {
            return new Producto(doc.getObjectId("_id").toString(),
                    doc.getString("nombre"),
                    doc.getString("categoria"),
                    doc.getString("marca"),
                    doc.getInteger("cantidadEnStock"),
                    doc.getDouble("precioUnitario"));
        }
        return null;
    }

    public void actualizarProducto(Producto producto) {
        Document doc = new Document("nombre", producto.getNombre())
                .append("categoria", producto.getCategoria())
                .append("marca", producto.getMarca())
                .append("cantidadEnStock", producto.getCantidadEnStock())
                .append("precioUnitario", producto.getPrecioUnitario());
        collection.replaceOne(new Document("_id", new org.bson.types.ObjectId(producto.getId())), doc);
    }

    public List<Producto> buscarProductosPorNombre(String nombre) {
        List<Producto> productos = new ArrayList<>();
        for (Document doc : collection.find(new Document("nombre", nombre))) {
            productos.add(new Producto(doc.getObjectId("_id").toString(),
                    doc.getString("nombre"),
                    doc.getString("categoria"),
                    doc.getString("marca"),
                    doc.getInteger("cantidadEnStock"),
                    doc.getDouble("precioUnitario")));
        }
        return productos;
    }

    public List<Producto> buscarProductosPorCategoria(String categoria) {
        List<Producto> productos = new ArrayList<>();
        for (Document doc : collection.find(new Document("categoria", categoria))) {
            productos.add(new Producto(doc.getObjectId("_id").toString(),
                    doc.getString("nombre"),
                    doc.getString("categoria"),
                    doc.getString("marca"),
                    doc.getInteger("cantidadEnStock"),
                    doc.getDouble("precioUnitario")));
        }
        return productos;
    }

    public List<Producto> buscarProductosPorMarca(String marca) {
        List<Producto> productos = new ArrayList<>();
        for (Document doc : collection.find(new Document("marca", marca))) {
            productos.add(new Producto(doc.getObjectId("_id").toString(),
                    doc.getString("nombre"),
                    doc.getString("categoria"),
                    doc.getString("marca"),
                    doc.getInteger("cantidadEnStock"),
                    doc.getDouble("precioUnitario")));
        }
        return productos;
    }

}