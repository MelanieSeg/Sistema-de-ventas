package Modelo;

public class Producto {
    private String id;
    private String nombre;
    private String categoria;
    private String marca;
    private int cantidadEnStock;
    private double precioUnitario;

    public Producto(String id, String nombre, String categoria, String marca, int cantidadEnStock, double precioUnitario) {
        this.id = id;
        this.nombre = nombre;
        this.categoria = categoria;
        this.marca = marca;
        this.cantidadEnStock = cantidadEnStock;
        this.precioUnitario = precioUnitario;
    }

    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public String getCategoria() { return categoria; }
    public String getMarca() { return marca; }
    public int getCantidadEnStock() { return cantidadEnStock; }
    public double getPrecioUnitario() { return precioUnitario; }

    public void setId(String id) { this.id = id; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setCategoria(String categoria) { this.categoria = categoria; }
    public void setMarca(String marca) { this.marca = marca; }
    public void setCantidadEnStock(int cantidadEnStock) { this.cantidadEnStock = cantidadEnStock; }
    public void setPrecioUnitario(double precioUnitario) { this.precioUnitario = precioUnitario; }
}