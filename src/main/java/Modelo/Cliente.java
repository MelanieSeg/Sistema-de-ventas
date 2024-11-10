package Modelo;

public class Cliente {
    private String id;
    private String nombreCompleto;
    private String correoElectronico;
    private String telefono;
    private String direccion;

    public Cliente(String id, String nombreCompleto, String correoElectronico, String telefono, String direccion) {
        this.id = id;
        this.nombreCompleto = nombreCompleto;
        this.correoElectronico = correoElectronico;
        this.telefono = telefono;
        this.direccion = direccion;
    }

    public String getId() { return id; }
    public String getNombreCompleto() { return nombreCompleto; }
    public String getCorreoElectronico() { return correoElectronico; }
    public String getTelefono() { return telefono; }
    public String getDireccion() { return direccion; }

    public void setId(String id) { this.id = id; }
    public void setNombreCompleto(String nombreCompleto) { this.nombreCompleto = nombreCompleto; }
    public void setCorreoElectronico(String correoElectronico) { this.correoElectronico = correoElectronico; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
}