/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package CSV;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author DAM119
 */

public class LeerCSV {

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
