package Vista;

import javax.swing.*;
import Controlador.ProductoController;
import Modelo.Producto;
import java.awt.*;

public class EditarProductoDialog extends JDialog {
    private JTextField nombreField;
    private JTextField categoriaField;
    private JTextField marcaField;
    private JTextField cantidadField;
    private JTextField precioField;
    private ProductoController productoController;
    private Producto producto;

    public EditarProductoDialog(ProductoController productoController, Producto producto) {
        this.productoController = productoController;
        this.producto = producto;
        initUI();
    }

    private void initUI() {
        setTitle("Editar Producto");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setModal(true);
        // Usar GridLayout para organizar los componentes
        setLayout(new GridLayout(6, 2));

        // Crear y agregar los componentes
        add(new JLabel("Nombre:"));
        nombreField = new JTextField(producto.getNombre());
        add(nombreField);

        add(new JLabel("Categoría:"));
        categoriaField = new JTextField(producto.getCategoria());
        add(categoriaField);

        add(new JLabel("Marca:"));
        marcaField = new JTextField(producto.getMarca());
        add(marcaField);

        add(new JLabel("Cantidad en Stock:"));
        cantidadField = new JTextField(String.valueOf(producto.getCantidadEnStock()));
        add(cantidadField);

        add(new JLabel("Precio Unitario:"));
        precioField = new JTextField(String.valueOf(producto.getPrecioUnitario()));
        add(precioField);

        JButton guardarButton = new JButton("Guardar");
        guardarButton.addActionListener(e -> guardarProducto());
        add(guardarButton);
        
        JButton cancelarButton = new JButton("Cancelar");
        cancelarButton.addActionListener(e -> dispose());
        add(cancelarButton);
    }

    private void guardarProducto() {
        String nombre = nombreField.getText();
        String categoria = categoriaField.getText();
        String marca = marcaField.getText();
        int cantidad = Integer.parseInt(cantidadField.getText());
        double precio = Double.parseDouble(precioField.getText());
        // Crear un nuevo objeto Producto con los datos editados
        Producto productoActualizado = new Producto(producto.getId(), nombre, categoria, marca, cantidad, precio);

        try {
            productoController.actualizarProducto(productoActualizado);
            JOptionPane.showMessageDialog(this, "Producto actualizado exitosamente.");
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(this, "Error al actualizar el producto: " + e.getMessage());
        }

        dispose();
    }
}