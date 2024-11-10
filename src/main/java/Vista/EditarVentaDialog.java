package Vista;

import javax.swing.*;
import Controlador.VentaController;
import Modelo.Venta;
import java.awt.*;
import java.util.Date;

public class EditarVentaDialog extends JDialog {
    private JTextField clienteField;
    private JTextField productoField;
    private JTextField cantidadField;
    private JTextField precioField;
    private VentaController ventaController;
    private Venta venta;

    public EditarVentaDialog(JFrame parent, Venta venta, VentaController ventaController) {
        super(parent, "Editar Venta", true);
        this.venta = venta;
        this.ventaController = ventaController;
        initUI();
    }

    private void initUI() {
        setTitle("Editar Venta");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setModal(true);
        setLayout(new GridLayout(4, 2));

        add(new JLabel("Cliente:"));
        clienteField = new JTextField(venta.getClienteId()); 
        add(clienteField);

        add(new JLabel("Producto:"));
        productoField = new JTextField(venta.getProductoId());
        add(productoField);

        add(new JLabel("Cantidad:"));
        cantidadField = new JTextField(String.valueOf(venta.getCantidad()));
        add(cantidadField);

        add(new JLabel("Precio:"));
        precioField = new JTextField(String.valueOf(venta.getMontoTotal()));
        add(precioField);

        JButton guardarButton = new JButton("Guardar");
        guardarButton.addActionListener(e -> guardarVenta());
        add(guardarButton);

        JButton cancelarButton = new JButton("Cancelar");
        cancelarButton.addActionListener(e -> dispose());
        add(cancelarButton);
    }

    private void guardarVenta() {
        String cliente = clienteField.getText();
        String producto = productoField.getText();
        int cantidad;
        double precio;

        // Validación de entrada
        if (cliente.isEmpty() || producto.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, completa todos los campos.");
            return;
        }

        try {
            cantidad = Integer.parseInt(cantidadField.getText());
            precio = Double.parseDouble(precioField.getText());

            if (cantidad <= 0 || precio < 0) {
                JOptionPane.showMessageDialog(this, "La cantidad y el precio deben ser mayores que 0.");
                return;
            }

            // Crear un nuevo objeto Venta con los datos editados
            Venta ventaActualizada = new Venta(venta.getId(), cliente, producto, cantidad, venta.getFechaVenta(), precio); // Mantener la fecha original

            ventaController.actualizarVenta(ventaActualizada);
            JOptionPane.showMessageDialog(this, "Venta actualizada exitosamente.");
            dispose(); // Cerrar el diálogo solo después de una actualización exitosa
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor, ingresa valores válidos para cantidad y precio.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al actualizar la venta: " + e.getMessage());
        }
        dispose();
    }
}