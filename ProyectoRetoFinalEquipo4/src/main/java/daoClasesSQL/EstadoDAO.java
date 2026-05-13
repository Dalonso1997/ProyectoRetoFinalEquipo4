package daoClasesSQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelClasesTablas.Estado;
import utilsClasesApoyo.ConexionBD;

/**
 * Clase DAO para gestionar los estados de los materiales (Disponible, Prestado, etc.)
 * interactuando con la tabla 'estado' de la base de datos.
 * @author DAM126
 */
public class EstadoDAO {

    /**
     * Recupera el listado de todos los estados posibles desde la base de datos.
     * @return List de objetos Estado.
     */
    public List<Estado> listarTodos() {
        List<Estado> listaEstados = new ArrayList<>();
        String sql = "SELECT id_estado, nombre FROM estado";

        // 1. Pedimos la conexión al Singleton
        Connection con = ConexionBD.getInstancia().getConexion(); 

        // 2. Solo el PreparedStatement y el ResultSet se cierran automáticamente
        try (PreparedStatement ps = con.prepareStatement(sql); 
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Estado estado = new Estado(
                    rs.getInt("id_estado"),
                    rs.getString("nombre")
                );
                listaEstados.add(estado);
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener los estados: " + e.getMessage());
        }

        return listaEstados;
    }
}