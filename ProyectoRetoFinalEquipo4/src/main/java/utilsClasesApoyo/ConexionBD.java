/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilsClasesApoyo;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author DAM126
 */
public class ConexionBD {
    
    // Variable estatica de la misma clase
    private static ConexionBD instancia;
    
    // Variable para la conexion SQL
    private Connection conexion;
    
    private ConexionBD(){
        
        // Objeto para leer el archivo de configuración externo
        Properties props = new Properties();
        
        try (FileInputStream fis = new FileInputStream("config.properties")) {
            
            // Cargamos el archivo
            props.load(fis);
            
            // Leemos qué entorno queremos usar (local o aws)
            String entorno = props.getProperty("entorno");
            String url, user, pass;
            
            // Elegimos las credenciales según el entorno
            if ("aws".equalsIgnoreCase(entorno)) {
                url = props.getProperty("aws.url");
                user = props.getProperty("aws.user");
                pass = props.getProperty("aws.pass");
                System.out.println("Iniciando conexión a AWS...");
            } else {
                url = props.getProperty("local.url");
                user = props.getProperty("local.user");
                pass = props.getProperty("local.pass");
                System.out.println("Iniciando conexión a LOCALHOST...");
            }
            
            // Establecemos la conexión con los datos seguros
            this.conexion = DriverManager.getConnection(url, user, pass);
            System.out.println("Conexion establecida con éxito.");
            
        } catch (IOException e) {
            System.out.println("⚠️ ERROR: No se encuentra el archivo config.properties. ¿Lo has creado en la raíz del proyecto?");
        } catch (SQLException e){
            System.out.println("⚠️ Error al conectar: " + e.getMessage());
        }
        
    }
    
    // Metodo static que devuelve la instancia
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
