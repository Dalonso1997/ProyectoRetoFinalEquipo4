/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daoClasesSQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelClasesTablas.Estado;
import utilsClasesApoyo.ConexionBD;

public class EstadoDAO {

    public List<Estado> listarTodos() {
        List<Estado> listaEstados = new ArrayList<>();
        String sql = "SELECT id_estado, nombre FROM estado";

        // IMPORTANTE: Cambia "ConexionBD.getConnection()" por tu clase real de conexión Singleton
        try (Connection con = ConexionBD.getInstancia().getConexion(); 
             PreparedStatement ps = con.prepareStatement(sql); 
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
