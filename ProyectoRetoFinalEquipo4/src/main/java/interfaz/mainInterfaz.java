/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package interfaz;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * Clase principal que sirve como punto de entrada de toda la aplicación del
 * taller. Se encarga de iniciar el hilo de ejecución para mostrar la interfaz
 * gráfica de forma segura.
 *
 * * @author Sergio Camacho
 */
public class mainInterfaz extends JFrame {

    /**
     * Método principal (main) que arranca el programa completo del taller de
     * informática. Utiliza SwingUtilities para evitar problemas de
     * sincronización de hilos en ventanas complejas.
     *
     * * @param args los argumentos de la línea de comandos al arrancar el jar.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            login ventana = new login();
            ventana.setVisible(true);
        });

    }

}
