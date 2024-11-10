package Vista;

import javax.swing.*;
import Controlador.ClienteController;
import Modelo.Cliente;

public class AgregarClienteDialog extends JDialog {
    private JTextField nombreField;
    private JTextField emailField;
    private JTextField telefonoField;
    private JTextField direccionField;
    private ClienteController clienteController;
    private JFrame parent; 

    public AgregarClienteDialog(JFrame parent, ClienteController clienteController) {
        super(parent, "Agregar Cliente", true);
        this.parent = parent;
        this.clienteController = clienteController;
        initUI();
    }

    private void initUI() {
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        nombreField = new JTextField();
        emailField = new JTextField();
        telefonoField = new JTextField();
        direccionField = new JTextField();

        add(new JLabel("Nombre:"));
        add(nombreField);
        add(new JLabel("Email:"));
        add(emailField);
        add(new JLabel("Teléfono:"));
        add(telefonoField);
        add(new JLabel("Dirección:"));
        add(direccionField);

        JButton agregarButton = new JButton("Agregar");
        agregarButton.addActionListener(e -> agregarCliente());
        add(agregarButton);

        pack();
        setLocationRelativeTo(parent); 
    }

    private void agregarCliente() {
        String nombre = nombreField.getText();
        String email = emailField.getText();
        String telefono = telefonoField.getText();
        String direccion = direccionField.getText(); 

        Cliente cliente = new Cliente(null, nombre, email, telefono, direccion);
        clienteController.agregarCliente(cliente);
        JOptionPane.showMessageDialog(this, "Cliente agregado exitosamente.");
        dispose();
    }
}