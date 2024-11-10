/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.sistemaventas;

import Vista.MainFrame; // Asegúrate de importar la clase MainFrame

public class SistemaVentas {

    public static void main(String[] args) {
        // Iniciar la interfaz gráfica
        javax.swing.SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame(); // Crear una instancia de MainFrame
            frame.setVisible(true); //Hacer visible el marco
        });
    }
}