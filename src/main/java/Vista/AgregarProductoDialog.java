package Vista;

import javax.swing.*;
import Controlador.ProductoController;
import Modelo.Producto;

public class AgregarProductoDialog extends JDialog {
    private JTextField nombreField;
    private JTextField categoriaField;
    private JTextField marcaField;
    private JTextField cantidadField;
    private JTextField precioField;
    private ProductoController productoController;
    private JFrame parent; 

    public AgregarProductoDialog(JFrame parent, ProductoController productoController) {
        super(parent, "Agregar Producto", true);
        this.parent = parent; 
        this.productoController = productoController;
        initUI();
    }

    private void initUI() {
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        nombreField = new JTextField();
        categoriaField = new JTextField();
        marcaField = new JTextField();
        cantidadField = new JTextField();
        precioField = new JTextField();

        add(new JLabel("Nombre:"));
        add(nombreField);
        add(new JLabel("Categoría:"));
        add(categoriaField);
        add(new JLabel("Marca:"));
        add(marcaField);
        add(new JLabel("Cantidad en Stock:"));
        add(cantidadField);
        add(new JLabel("Precio Unitario:"));
        add(precioField);

        JButton agregarButton = new JButton("Agregar");
        agregarButton.addActionListener(e -> agregarProducto());
        add(agregarButton);

        pack();
        setLocationRelativeTo(parent); // Ahora parent es accesible
    }

    private void agregarProducto() {
        String nombre = nombreField.getText();
        String categoria = categoriaField.getText();
        String marca = marcaField.getText();
        int cantidad = Integer.parseInt(cantidadField.getText());
        double precio = Double.parseDouble(precioField.getText());

        Producto producto = new Producto(null, nombre, categoria, marca, cantidad, precio);
        productoController.agregarProducto(producto);
        JOptionPane.showMessageDialog(this, "Producto agregado exitosamente.");
        dispose();
    }
}