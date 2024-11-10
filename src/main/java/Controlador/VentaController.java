package Controlador;

import Modelo.Venta;
import Basedatos.MongoDBConnection;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import Modelo.Producto;
import java.util.ArrayList;
import java.util.List;

public class VentaController {
    private final MongoDatabase database;
    private final MongoCollection<Document> collection;
    private ProductoController productoController;
    
    public VentaController() {
        MongoDBConnection connection = new MongoDBConnection();
        this.database = connection.getDatabase();
        this.productoController = new ProductoController();
        this.collection = database.getCollection("ventas");
    }

    public void registrarVenta(Venta venta) {
        Document doc = new Document("clienteId", venta.getClienteId())
                .append("productoId", venta.getProductoId())
                .append("cantidad", venta.getCantidad())
                .append("fechaVenta", venta.getFechaVenta())
                .append("montoTotal", venta.getMontoTotal());
        collection.insertOne(doc);
    }

    public List<Venta> obtenerVentas() {
        List<Venta> ventas = new ArrayList<>();
        for (Document doc : collection.find()) {
            ventas.add(new Venta(doc.getObjectId("_id").toString(),
                    doc.getString("clienteId"),
                    doc.getString("productoId"),
                    doc.getInteger("cantidad"),
                    doc.getDate("fechaVenta"),
                    doc.getDouble("montoTotal")));
        }
        return ventas;
    }

   public void actualizarVenta(Venta venta) {
        Document doc = new Document("clienteId", venta.getClienteId())
                .append("productoId", venta.getProductoId())
                .append("cantidad", venta.getCantidad())
                .append("fechaVenta", venta.getFechaVenta())
                .append("montoTotal", venta.getMontoTotal());

        // Usar ObjectId para el ID de la venta
        collection.replaceOne(new Document("_id", new org.bson.types.ObjectId(venta.getId())), doc);
    }

    public Venta obtenerVentaPorId(String ventaId) {
        Document doc = collection.find(new Document("_id", new org.bson.types.ObjectId(ventaId))).first();
        if (doc != null) {
            return new Venta(doc.getObjectId("_id").toString(),
                    doc.getString("clienteId"),
                    doc.getString("productoId"),
                    doc.getInteger("cantidad"),
                    doc.getDate("fechaVenta"),
                    doc.getDouble("montoTotal"));
        }
        return null; // Retorna null si no se encuentra la venta
    }

    public double obtenerPrecioProducto(String productoId) {
        Producto producto = productoController.obtenerProductoPorId(productoId);
        if (producto != null) {
            return producto.getPrecioUnitario();
        }
        return 0.0; 
    }
}