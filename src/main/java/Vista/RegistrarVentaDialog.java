package Vista;

import javax.swing.*;
import Controlador.VentaController;
import Controlador.ClienteController;
import Controlador.ProductoController;
import Modelo.Venta;
import Modelo.Cliente;
import Modelo.Producto;

import java.util.List;

public class RegistrarVentaDialog extends JDialog {
    private VentaController ventaController;
    private ClienteController clienteController;
    private ProductoController productoController;

    private JComboBox<Cliente> clienteComboBox;
    private JComboBox<Producto> productoComboBox;
    private JTextField cantidadField;

    public RegistrarVentaDialog(JFrame parent, VentaController ventaController, ClienteController clienteController, ProductoController productoController) {
        super(parent, "Registrar Venta", true);
        this.ventaController = ventaController;
        this.clienteController = clienteController;
        this.productoController = productoController;

        initUI();
        setLocationRelativeTo(parent);
        pack();
        setVisible(true);
    }

    private void initUI() {
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        // ComboBox para seleccionar cliente
        clienteComboBox = new JComboBox<>();
        List<Cliente> clientes = clienteController.obtenerClientes(); // Método que obtenga todos los clientes
        for (Cliente cliente : clientes) {
            clienteComboBox.addItem(cliente);
        }
        add(new JLabel("Cliente:"));
        add(clienteComboBox);

        // ComboBox para seleccionar producto
        productoComboBox = new JComboBox<>();
        List<Producto> productos = productoController.obtenerProductos();
        for (Producto producto : productos) {
            productoComboBox.addItem(producto);
        }
        add(new JLabel("Producto:"));
        add(productoComboBox);

        cantidadField = new JTextField();
        add(new JLabel("Cantidad:"));
        add(cantidadField);

        JButton registrarButton = new JButton("Registrar venta");
        registrarButton.addActionListener(e -> registrarVenta());
        add(registrarButton);
    }

    private void registrarVenta() {
        Cliente clienteSeleccionado = (Cliente) clienteComboBox.getSelectedItem();
        Producto productoSeleccionado = (Producto) productoComboBox.getSelectedItem();
        int cantidad = Integer.parseInt(cantidadField.getText());

        // Calcular monto total
        double montoTotal = productoSeleccionado.getPrecioUnitario() * cantidad;

        // Crear y guardar la venta
        Venta venta = new Venta(null, clienteSeleccionado.getId(), productoSeleccionado.getId(), cantidad, new java.util.Date(), montoTotal);
        ventaController.registrarVenta(venta); // Método que guarda la venta en la base de datos

        JOptionPane.showMessageDialog(this, "Venta registrada exitosamente.");
        dispose();
    }
}