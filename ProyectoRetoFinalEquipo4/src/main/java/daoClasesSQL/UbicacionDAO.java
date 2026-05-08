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
import modelClasesTablas.Ubicacion;
import utilsClasesApoyo.ConexionBD;

/**
 *
 * @author DAM126
 */
public class UbicacionDAO {
     
    //Metodo que devuelve una lista con todas las ubicaciones de la base de datos
    public List<Ubicacion> listarTodos() {
        
        //Creamos una lista vacia donde guardaremos las ubicaciones
        List<Ubicacion> lista = new ArrayList<>();
        
        //Pedimos la conexion a la clase ConexionDB (Singleton)
        Connection con = ConexionBD.getInstancia().getConexion();
        
        //Creamos un String con la consulta para obtener todas las ubicaciones ordenadas por armario y balda
        String sql = "SELECT id_ubicacion, armario, balda, cajon, descripcion FROM ubicacion ORDER BY armario, balda";
        
        //PreparedStatement protege la consulta SQL y la ejecuta
        try (PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            //Recorremos todas las filas que devuelve la consulta
            while (rs.next()) {
                
                //Por cada fila creamos un objeto Ubicacion con los datos obtenidos
                lista.add(new Ubicacion(
                    rs.getInt("id_ubicacion"),
                    rs.getString("armario"),
                    rs.getString("balda"),
                    rs.getString("cajon"),
                    rs.getString("descripcion")
                ));
            }
            
        } catch (SQLException e) {
            System.out.println("Error al listar ubicaciones: " + e.getMessage());
        }
        
        //Devolvemos la lista con todas las ubicaciones (vacia si no encontro ninguna)
        return lista;
    }
}
