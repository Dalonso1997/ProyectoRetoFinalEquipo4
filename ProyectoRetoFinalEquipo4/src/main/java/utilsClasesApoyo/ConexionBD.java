/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilsClasesApoyo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author DAM126
 */
public class ConexionBD {
    private static Connection conexion;

    public static Connection getConexion() {
        if (conexion == null) {
            try {
                conexion = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/inventario",
                    "root",
                    "password"
                );
                System.out.println("Conexión OK");
            } catch (SQLException e) {
                System.out.println("Error conexión");
                e.printStackTrace();
            }
        }
        return conexion;
    }
    
}
