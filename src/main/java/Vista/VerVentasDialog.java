package Vista;

import javax.swing.*;
import Controlador.VentaController;
import Modelo.Venta;
import java.awt.*;
import java.util.List;

public class VerVentasDialog extends JPanel {
    private VentaController ventaController;
    private JTable ventasTable;
    private JButton editarButton;

    public VerVentasDialog(VentaController ventaController) {
        this.ventaController = ventaController;
        initUI();
    }

    private void initUI() {
        setLayout(new BorderLayout());

        List<Venta> ventas = ventaController.obtenerVentas();

        String[] columnNames = {"ID", "Cliente ID", "Producto ID", "Cantidad", "Fecha", "Monto Total"};
        Object[][] data = new Object[ventas.size()][columnNames.length];

        for (int i = 0; i < ventas.size(); i++) {
            Venta venta = ventas.get(i);
            data[i][0] = venta.getId();
            data[i][1] = venta.getClienteId();
            data[i][2] = venta.getProductoId();
            data[i][3] = venta.getCantidad();
            data[i][4] = venta.getFechaVenta();
            data[i][5] = venta.getMontoTotal();
        }

        ventasTable = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(ventasTable);
        add(scrollPane, BorderLayout.CENTER);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        editarButton = new JButton("Editar");
        editarButton.addActionListener(e -> editarVentaSeleccionada());
        add(editarButton, BorderLayout.SOUTH);
    }

    private void editarVentaSeleccionada() {
        int filaSeleccionada = ventasTable.getSelectedRow();
        if (filaSeleccionada != -1) {
            String idVenta = ventasTable.getValueAt(filaSeleccionada, 0).toString();
            Venta venta = ventaController.obtenerVentaPorId(idVenta);

            if (venta != null) {
                EditarVentaDialog editarDialog = new EditarVentaDialog((JFrame) SwingUtilities.getWindowAncestor(this), venta, ventaController);
                editarDialog.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Venta no encontrada.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Por favor selecciona una venta para editar.");
        }
    }
}