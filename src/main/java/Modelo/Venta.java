package Modelo;

import java.util.Date;

public class Venta {
    private String id;
    private String clienteId;
    private String productoId;
    private int cantidad;
    private Date fechaVenta;
    private double montoTotal;

    public Venta(String id, String clienteId, String productoId, int cantidad, Date fechaVenta, double montoTotal) {
        this.id = id;
        this.clienteId = clienteId;
        this.productoId = productoId;
        this.cantidad = cantidad;
        this.fechaVenta = fechaVenta;
        this.montoTotal = montoTotal;
    }

    public String getId() { return id; }
    public String getClienteId() { return clienteId; }
    public String getProductoId() { return productoId; }
    public int getCantidad() { return cantidad; }
    public Date getFechaVenta() { return fechaVenta; }
    public double getMontoTotal() { return montoTotal; }

    public void setId(String id) { this.id = id; }
    public void setClienteId(String clienteId) { this.clienteId = clienteId; }
    public void setProductoId(String productoId) { this.productoId = productoId; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }
    public void setFechaVenta(Date fechaVenta ) { this.fechaVenta = fechaVenta; }
    public void setMontoTotal(double montoTotal) { this.montoTotal = montoTotal; }
}