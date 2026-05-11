/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfaz.menuPrincipal;

import daoClasesSQL.MaterialDAO;
import java.awt.BorderLayout;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author DAM119
 */



public class PanelConsultaMateriales extends JPanel {

    private JTable tabla;
    private DefaultTableModel modelo;

    public PanelConsultaMateriales() {

        setLayout(new BorderLayout());

        inicializarTabla();

        cargarMateriales();
    }

    private void inicializarTabla() {

        String[] columnas = {
            "ID",
            "Nombre",
            "Descripción",
            "Categoría",
            "Estado",
            "Ubicación",
            "Cantidad"
        };

        modelo = new DefaultTableModel(columnas, 0) {

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tabla = new JTable(modelo);

        tabla.setRowHeight(25);

        JScrollPane scroll = new JScrollPane(tabla);

        add(scroll, BorderLayout.CENTER);
    }

    private void cargarMateriales() {

        MaterialDAO dao = new MaterialDAO();

        
        List<Object[]> materiales = dao.buscar("", "", "", "");
        
        System.out.println(materiales.size());

        modelo.setRowCount(0);

        if (materiales.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "No hay materiales registrados.",
                    "Información",
                    JOptionPane.INFORMATION_MESSAGE
            );

            return;
        }

        for (Object[] fila : materiales) {
            modelo.addRow(fila);
        }
    }
}
