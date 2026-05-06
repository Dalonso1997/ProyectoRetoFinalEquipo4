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
    
    //Variable estatica de la misma clase
    private static ConexionBD instancia;
    
    //Varibale para la conexion SQL
    private Connection conexion;
    
    private ConexionBD(){
        
        try {
            
            //Datos provisionales para localhost
            String url = "jdbc:mysql://18.209.239.238:/inventario_taller"; 
            String user = "admin";
            String pass = "Grupo4"; 
            
            this.conexion = DriverManager.getConnection(url,user,pass);
            System.out.println("Conexion establecida.");
            
        } catch (SQLException e){
            System.out.println("Error al conectar: " + e.getMessage());
        }
        
    }
    
    //Metodo static que devuelve la instancia
    public static ConexionBD getInstancia(){
        if (instancia == null){
            instancia = new ConexionBD();
        }
        return instancia;
    }
    
    public Connection getConexion(){
        return this.conexion;
    } 
}
