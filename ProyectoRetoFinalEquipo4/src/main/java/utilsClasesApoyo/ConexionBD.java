/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilsClasesApoyo;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Clase que gestiona la conexión centralizada con la base de datos de MySQL.
 * Implementa el patrón de diseño Singleton para garantizar que todo el programa
 * utilice un canal único y seguro de comunicación.
 *
 * @author David Alonso
 * @author Alberto Gonzalez
 */
public class ConexionBD {

    //variable estática de la misma clase para almacenar la única instancia del objeto
    private static ConexionBD instancia;

    //variable nativa de jdbc encargada de mantener la sesión activa con la base de datos
    private Connection conexion;

    /**
     * Constructor privado del patrón Singleton. Se encarga de abrir el archivo
     * config.properties externo y configurar de forma dinámica el destino de la
     * conexión (local o el servidor de AWS).
     */
    private ConexionBD() {
        //objeto para leer el archivo de configuración creado de manera externa con las propiedades para la conexion
        Properties props = new Properties();

        //usamos getResourceAsStream para que funcione dentro del JAR
        try (InputStream fis = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            if (fis == null) {
                System.out.println(" ERROR: No se encuentra el archivo config.properties.");
                return;
            }
            //cargamos el archivo en memoria
            props.load(fis);

            //leemos qué entorno queremos usar (local o aws)
            String entorno = props.getProperty("entorno");
            String url, user, pass;

            //si el entorno encontrado es igual a "aws"
            if ("aws".equalsIgnoreCase(entorno)) {
                //recogemos la url, el usuario y la contraseña ubicadas en el archivo properties
                url = props.getProperty("aws.url");
                user = props.getProperty("aws.user");
                pass = props.getProperty("aws.pass");
                System.out.println("Iniciando conexión a AWS...");
            } else {
                //en caso contrario recogemos los datos para la conexion local
                url = props.getProperty("local.url");
                user = props.getProperty("local.user");
                pass = props.getProperty("local.pass");
                System.out.println("Iniciando conexión a LOCALHOST...");
            }

            //establecemos la conexión con los datos seguros
            this.conexion = DriverManager.getConnection(url, user, pass);
            System.out.println("Conexion establecida con éxito.");

        } catch (IOException e) {
            System.out.println("⚠️ ERROR: No se encuentra el archivo config.properties. ¿Lo has creado en la raíz del proyecto?");
        } catch (SQLException e) {
            System.out.println("⚠️ Error al conectar: " + e.getMessage());
        }
    }

    /**
     * Método de acceso global para recuperar la única instancia de la conexión.
     * Si es la primera vez que se solicita, invoca al constructor privado para
     * crearla.
     *
     * @return la instancia única de tipo ConexionBD encargada del enlace
     * relacional.
     */
    public static ConexionBD getInstancia() {
        if (instancia == null) {
            instancia = new ConexionBD();
        }
        return instancia;
    }

    /**
     * Devuelve el objeto Connection activo para que pueda ser utilizado por los
     * diferentes DAOs del taller.
     *
     * @return el objeto Connection nativo de JDBC que representa la sesión
     * activa.
     */
    public Connection getConexion() {
        return this.conexion;
    }
}
