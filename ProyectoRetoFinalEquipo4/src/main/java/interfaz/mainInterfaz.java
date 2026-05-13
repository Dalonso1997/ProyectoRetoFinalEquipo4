/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package interfaz;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 *
 * @author DAM119
 */
public class mainInterfaz extends JFrame {

   
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            login ventana = new login();
            ventana.setVisible(true);
        });
        
    }
    
}
