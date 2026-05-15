package daoClasesSQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelClasesTablas.Ubicacion;
import utilsClasesApoyo.ConexionBD;

/**
 * Clase DAO para la gestión de las ubicaciones (mesas y armarios).
 * Interactúa con la tabla 'ubicacion' de la base de datos en AWS.
 * @author DAM126
 */
public class UbicacionDAO {

    /**
     * Recupera todas las ubicaciones registradas en el taller.
     * @return List de objetos Ubicacion con sus datos completos.
     */
    public List<Ubicacion> listarTodos() {
        List<Ubicacion> lista = new ArrayList<>();
        
        // 1. Obtenemos la conexión del Singleton (FUERA del try para no cerrarla)
        Connection con = ConexionBD.getInstancia().getConexion();

        // 2. SQL: Cambiamos 'ubicacion' por 'nombre' en el ORDER BY para que coincida con la BD
        String sql = "SELECT id_ubicacion, tipo, nombre, cajon, descripcion FROM ubicacion ORDER BY nombre ASC, cajon ASC";

        // 3. Usamos try-with-resources para cerrar ps y rs automáticamente
        try (PreparedStatement ps = con.prepareStatement(sql); 
             ResultSet rs = ps.executeQuery()) {

            // ¡IMPORTANTE! Necesitas el while para recorrer las filas del ResultSet
            while (rs.next()) {
                
                // Creamos el objeto mapeando las columnas de la BD al constructor
                Ubicacion ubi = new Ubicacion(
                    rs.getInt("id_ubicacion"),
                    rs.getString("tipo"),
                    rs.getString("nombre"), // Columna 'nombre' de la BD va a 'ubicacion' en Java
                    (rs.getObject("cajon") != null) ? rs.getInt("cajon") : null, // Manejo de nulos para mesas
                    rs.getString("descripcion")
                );
                
                // ¡IMPORTANTE! Añadimos el objeto a la lista
                lista.add(ubi);
            }

        } catch (SQLException e) {
            System.err.println("Error al listar ubicaciones: " + e.getMessage());
        }

        return lista;
    }
    
    public Ubicacion SelecionarTipo(String tipo,String nombre) throws SQLException{
        Connection con = ConexionBD.getInstancia().getConexion();

        String sql = "SELECT nombre FROM ubicacion WHERE nombre";
        
        
            try (PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
                
            }catch (SQLException e) {
            
        }
        return null;
        
    }
    
}