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
 * @author David Alonso, Alberto Gonzalez
 */
public class ConexionBD {
    
    // Variable estatica de la misma clase
    private static ConexionBD instancia;
    
    // Variable para la conexion SQL
    private Connection conexion;
    
    private ConexionBD(){
        
        // Objeto para leer el archivo de configuración creado de manera externa con las propiedades para la conexion
        Properties props = new Properties();
        
        try (FileInputStream fis = new FileInputStream("config.properties")) {
            
            // Cargamos el archivo
            props.load(fis);
            
            // Leemos qué entorno queremos usar (local o aws)
            String entorno = props.getProperty("entorno");
            String url, user, pass;
            
            //Si el entorno enconrado es igual a "aws"
            if ("aws".equalsIgnoreCase(entorno)) {
                //Recogemos la url, el usuario y la contrasena ubicadas en el archivo properties
                url = props.getProperty("aws.url");
                user = props.getProperty("aws.user");
                pass = props.getProperty("aws.pass");
                System.out.println("Iniciando conexión a AWS...");
            } else {
                //En caso contrario recogemos los datos para la conexion local
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
    
    // Aqui reside el metodo singleton, este sirve para que exista unica y exclusivamente una instancia de la conexion
    //De esta manera aseguramos que el canal por el que se conecta a la base de datos sera unico y seguro
    public static ConexionBD getInstancia(){
        if (instancia == null){
            instancia = new ConexionBD();
        }
        return instancia;
    }
    
    //Devuelve la conexion activa para poder ser usada por los DAOs
    public Connection getConexion(){
        return this.conexion;
    } 
}
