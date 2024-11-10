package Vista;

import javax.swing.*;
import Controlador.ProductoController;
import Controlador.ClienteController;
import Controlador.VentaController;
import java.awt.BorderLayout;

public class MainFrame extends JFrame {
    private ProductoController productoController;
    private ClienteController clienteController;
    private VentaController ventaController;
    private JPanel mainPanel; // Panel principal para mostrar el contenido

    public MainFrame() {
        productoController = new ProductoController();
        clienteController = new ClienteController();
        ventaController = new VentaController();

        setTitle("Sistema de Ventas de Productos de Computación");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        initUI();
    }

    private void initUI() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menuProductos = new JMenu("Productos");
        JMenu menuClientes = new JMenu("Clientes");
        JMenu menuVentas = new JMenu("Ventas");

        // Agregar opciones de ver
        JMenuItem verClientes = new JMenuItem("Ver Clientes");
        verClientes.addActionListener(e -> mostrarVerClientes());
        menuClientes.add(verClientes);

        JMenuItem verVentas = new JMenuItem("Ver Ventas");
        verVentas.addActionListener(e -> mostrarVerVentas());
        menuVentas.add(verVentas);

        JMenuItem verProductos = new JMenuItem("Ver Productos");
        verProductos.addActionListener(e -> mostrarVerProductos());
        menuProductos.add(verProductos);

        // Agregar acciones a los menús para agregar
        JMenuItem agregarProducto = new JMenuItem("Agregar Producto");
        agregarProducto.addActionListener(e -> mostrarAgregarProducto());
        menuProductos.add(agregarProducto);

        JMenuItem agregarCliente = new JMenuItem("Agregar Cliente");
        agregarCliente.addActionListener(e -> mostrarAgregarCliente());
        menuClientes.add(agregarCliente);

        JMenuItem registrarVenta = new JMenuItem("Registrar Venta");
        registrarVenta.addActionListener(e -> mostrarRegistrarVenta());
        menuVentas.add(registrarVenta);

        menuBar.add(menuProductos);
        menuBar.add(menuClientes);
        menuBar.add(menuVentas);
        setJMenuBar(menuBar);

        add(mainPanel, BorderLayout.CENTER); // Agrega el panel principal al marco
    }

    private void mostrarVerClientes() {
        mainPanel.removeAll(); // Limpia el panel principal
        VerClientesDialog verClientesDialog = new VerClientesDialog(this, clienteController);
        mainPanel.add(verClientesDialog); 
        mainPanel.revalidate(); 
        mainPanel.repaint(); // Repaint para mostrar el nuevo contenido
    }

    private void mostrarVerVentas() {
        mainPanel.removeAll(); 
        VerVentasDialog verVentasDialog = new VerVentasDialog(ventaController); 
        mainPanel.add(verVentasDialog);
        mainPanel.revalidate(); 
        mainPanel.repaint(); 
    }

    private void mostrarVerProductos() {
        mainPanel.removeAll(); 
        VerProductosDialog verProductosDialog = new VerProductosDialog(productoController);
        mainPanel.add(verProductosDialog);
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    private void mostrarAgregarCliente() {
        AgregarClienteDialog agregarClienteDialog = new AgregarClienteDialog(this, clienteController);
        agregarClienteDialog.setVisible(true); // Abre el diálogo para agregar cliente
    }

    private void mostrarAgregarProducto() {
        AgregarProductoDialog agregarProductoDialog = new AgregarProductoDialog(this, productoController);
        agregarProductoDialog.setVisible(true);
    }

    private void mostrarRegistrarVenta() {
        RegistrarVentaDialog registrarVentaDialog = new RegistrarVentaDialog(this, ventaController, clienteController, productoController);
        registrarVentaDialog.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame mainFrame = new MainFrame();
            mainFrame.setVisible(true);
        });
    }
}