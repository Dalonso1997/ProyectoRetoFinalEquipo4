/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package viewFormularios;

import interfaz.login;
import javax.swing.SwingUtilities;

/**
 *
 * @author DAM119
 */
public class mainInterfazBusqueda {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            VentanaBusqueda ventana1 = new VentanaBusqueda();
            ventana1.setVisible(true);
        });

    }

}
