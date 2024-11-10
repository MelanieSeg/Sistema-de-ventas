package Vista;

import javax.swing.*;
import Controlador.ProductoController;
import Modelo.Producto;
import java.awt.*;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class VerProductosDialog extends JPanel {
    private ProductoController productoController;
    private JTable productosTable;
    private JButton editarButton;
    private JTextField buscarNombreField;
    private JTextField buscarCategoriaField;
    private JTextField buscarMarcaField;
    private String[] columnNames = {"ID", "Nombre", "Categoría", "Marca", "Cantidad en Stock", "Precio Unitario"};

    public VerProductosDialog(ProductoController productoController) {
        this.productoController = productoController;
        initUI();
    }

    private void initUI() {
        setLayout(new BorderLayout());
        // Panel de búsqueda
        JPanel buscarPanel = new JPanel();
        buscarPanel.setLayout(new FlowLayout());

        buscarNombreField = new JTextField(10);
        buscarCategoriaField = new JTextField(10);
        buscarMarcaField = new JTextField(10);
        JButton buscarButton = new JButton("Buscar");

        buscarButton.addActionListener(e -> buscarProductos());

        buscarPanel.add(new JLabel("Nombre:"));
        buscarPanel.add(buscarNombreField);
        buscarPanel.add(new JLabel("Categoría:"));
        buscarPanel.add(buscarCategoriaField);
        buscarPanel.add(new JLabel("Marca:"));
        buscarPanel.add(buscarMarcaField);
        buscarPanel.add(buscarButton);

        add(buscarPanel, BorderLayout.NORTH);

        List<Producto> productos = productoController.obtenerProductos();

        String[] columnNames = {"ID", "Nombre", "Categoría", "Marca", "Cantidad en Stock", "Precio Unitario"};
        Object[][] data = new Object[productos.size()][columnNames.length];

        for (int i = 0; i < productos.size(); i++) {
            Producto producto = productos.get(i);
            data[i][0] = producto.getId();
            data[i][1] = producto.getNombre();
            data[i][2] = producto.getCategoria();
            data[i][3] = producto.getMarca();
            data[i][4] = producto.getCantidadEnStock();
            data[i][5] = producto.getPrecioUnitario();
        }

        productosTable = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(productosTable);
        add(scrollPane, BorderLayout.CENTER);
        // Botón de editar
        editarButton = new JButton("Editar");
        editarButton.addActionListener(e -> editarProducto());
        add(editarButton, BorderLayout.SOUTH);
    }

    private void editarProducto() {
        int selectedRow = productosTable.getSelectedRow();
        if (selectedRow >= 0) {

            String productoId = (String) productosTable.getValueAt(selectedRow, 0);
            Producto producto = productoController.obtenerProductoPorId(productoId);

            EditarProductoDialog editarDialog = new EditarProductoDialog(productoController, producto);
            editarDialog.setVisible(true);
            initUI(); //Vuelve a cargar la UI para reflejar los cambios
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, selecciona un producto para editar.");
        }
    }

    private void buscarProductos() {
        String nombre = buscarNombreField.getText();
        String categoria = buscarCategoriaField.getText();
        String marca = buscarMarcaField.getText();

        List<Producto> productos;

        if (!nombre.isEmpty()) {
            productos = productoController.buscarProductosPorNombre(nombre);
        } else if (!categoria.isEmpty()) {
            productos = productoController.buscarProductosPorCategoria(categoria);
        } else if (!marca.isEmpty()) {
            productos = productoController.buscarProductosPorMarca(marca);
        } else {
            productos = productoController.obtenerProductos(); // Si no hay criterios, mostrar todos
        }
        actualizarTabla(productos);
    }

    private void actualizarTabla(List<Producto> productos) {
        Object[][] data = new Object[productos.size()][6];
        for (int i = 0; i < productos.size(); i++) {
            Producto producto = productos.get(i);
            data[i][0] = producto.getId();
            data[i][1] = producto.getNombre();
            data[i][2] = producto.getCategoria();
            data[i][3] = producto.getMarca();
            data[i][4] = producto.getCantidadEnStock();
            data[i][5] = producto.getPrecioUnitario();
        }
        productosTable.setModel(new DefaultTableModel(data, columnNames));
    }
}