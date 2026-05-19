/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package CSV;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Clase para leer y mostrar por consola el contenido del archivo CSV generado.
 * Trocea las líneas usando las comas como separador de columnas.
 *
 * * @author Sergio Camacho
 * * @author David Alonso
 */
public class LeerCSV {

    /**
     * Método principal que lee el archivo CSV línea por línea.
     *
     * * @param args argumentos de la línea de comandos.
     */
    public static void main(String[] args) {

        String archivo = "src/inventario.csv";
        String linea;

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {

            while ((linea = br.readLine()) != null) {

                String[] datos = linea.split(",");

                for (String dato : datos) {
                    System.out.print(dato + " | ");
                }

                System.out.println();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
