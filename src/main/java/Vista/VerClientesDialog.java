package Vista;

import javax.swing.*;
import Controlador.ClienteController;
import Modelo.Cliente;
import java.awt.*;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class VerClientesDialog extends JPanel {
    private ClienteController clienteController;
    private JTable clientesTable;
    private JButton editarButton;
    private JTextField buscarNombreField;
    private JTextField buscarEmailField;
    private String[] columnNames = {"ID", "Nombre", "Email", "Teléfono"}; //para buscar

    public VerClientesDialog(JFrame parent, ClienteController clienteController) {
        this.clienteController = clienteController;
        initUI();
    }

    private void initUI() {
        setLayout(new BorderLayout());

        // Panel de búsqueda
        JPanel buscarPanel = new JPanel();
        buscarPanel.setLayout(new FlowLayout());

        buscarNombreField = new JTextField(10);
        buscarEmailField = new JTextField(10);
        JButton buscarButton = new JButton("Buscar");

        buscarButton.addActionListener(e -> buscarClientes());

        buscarPanel.add(new JLabel("Nombre:"));
        buscarPanel.add(buscarNombreField);
        buscarPanel.add(new JLabel("Email:"));
        buscarPanel.add(buscarEmailField);
        buscarPanel.add(buscarButton);

        add(buscarPanel, BorderLayout.NORTH);

        List<Cliente> clientes = clienteController.obtenerClientes();

        //crear la tabla para mostrar los clientes
        clientesTable = new JTable();
        actualizarTabla(clientes); //cargar la tabla inicialmente
        JScrollPane scrollPane = new JScrollPane(clientesTable);
        add(scrollPane, BorderLayout.CENTER);

        editarButton = new JButton("Editar");
        editarButton.addActionListener(e -> editarCliente());
        add(editarButton, BorderLayout.SOUTH);
    }

    private void editarCliente() {
        int selectedRow = clientesTable.getSelectedRow();
        if (selectedRow >= 0) {
            //cliente seleccionado
            String clienteId = (String) clientesTable.getValueAt(selectedRow, 0);
            Cliente cliente = clienteController.obtenerClientePorId(clienteId); //obtener el cliente por ID
            EditarClienteDialog editarDialog = new EditarClienteDialog(clienteController, cliente);
            editarDialog.setVisible(true);
            buscarClientes(); //Actualiza la lista de clientes
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, selecciona un cliente para editar.");
        }
    }

    private void buscarClientes() {
        String nombre = buscarNombreField.getText();
        String email = buscarEmailField.getText();

        List<Cliente> clientes;

        if (!nombre.isEmpty()) {
            clientes = clienteController.buscarClientesPorNombre(nombre);
        } else if (!email.isEmpty()) {
            clientes = clienteController.buscarClientesPorEmail(email);
        } else {
            clientes = clienteController.obtenerClientes(); //si no hay criterios, mostrar todos
        }
        //Actualizar la tabla con los clientes encontrados
        actualizarTabla(clientes);
    }

    private void actualizarTabla(List<Cliente> clientes) {
        Object[][] data = new Object[clientes.size()][columnNames.length];
        for (int i = 0; i < clientes.size(); i++) {
            Cliente cliente = clientes.get(i);
            data[i][0] = cliente.getId();
            data[i][1] = cliente.getNombreCompleto();
            data[i][2] = cliente.getCorreoElectronico();
            data[i][3] = cliente.getTelefono();
        }
        clientesTable.setModel(new DefaultTableModel(data, columnNames));
    }
}