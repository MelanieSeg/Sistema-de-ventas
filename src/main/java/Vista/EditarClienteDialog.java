package Vista;

import javax.swing.*;
import Controlador.ClienteController;
import Modelo.Cliente;
import java.awt.*;

public class EditarClienteDialog extends JDialog {
    private JTextField nombreField;
    private JTextField emailField;
    private JTextField telefonoField;
    private ClienteController clienteController;
    private Cliente cliente;

    public EditarClienteDialog(ClienteController clienteController, Cliente cliente) {
        this.clienteController = clienteController;
        this.cliente = cliente;
        initUI();
    }

    private void initUI() {
        setTitle("Editar Cliente");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setModal(true);
        setLayout(new GridLayout(4, 2));

        add(new JLabel("Nombre:"));
        nombreField = new JTextField(cliente.getNombreCompleto());
        add(nombreField);

        add(new JLabel("Email:"));
        emailField = new JTextField(cliente.getCorreoElectronico());
        add(emailField);

        add(new JLabel("Teléfono:"));
        telefonoField = new JTextField(cliente.getTelefono());
        add(telefonoField);

        JButton guardarButton = new JButton("Guardar");
        guardarButton.addActionListener(e -> guardarCliente());
        add(guardarButton);

        JButton cancelarButton = new JButton("Cancelar");
        cancelarButton.addActionListener(e -> dispose());
        add(cancelarButton);
    }

    private void guardarCliente() {
        String nombre = nombreField.getText();
        String email = emailField.getText();
        String telefono = telefonoField.getText();
        // Crear un nuevo objeto Cliente con los datos editados
        Cliente clienteActualizado = new Cliente(cliente.getId(), nombre, email, telefono, cliente.getDireccion());
        
        try {
            clienteController.actualizarCliente(clienteActualizado);
            JOptionPane.showMessageDialog(this, "Cliente actualizado exitosamente.");
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(this, "Error al actualizar el cliente: " + e.getMessage());
        }

        dispose();
    }
}