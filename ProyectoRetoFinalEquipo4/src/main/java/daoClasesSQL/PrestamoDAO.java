/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daoClasesSQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelClasesTablas.Prestamo;
import utilsClasesApoyo.ConexionBD;

public class PrestamoDAO {

    
    // 1. MÉTODO PARA PRESTAR MATERIAL
    public boolean registrarPrestamo(Prestamo p) {
        Connection con = ConexionBD.getInstancia().getConexion();
        
        String sqlInsert = "INSERT INTO prestamos (id_material, nombre, descripcion, estado, cantidad, fecha, observaciones) VALUES (?, ?, ?, ?, ?, ?, ?)";
        String sqlUpdateStock = "UPDATE materiales SET cantidad = cantidad - ? WHERE id_material = ?";

        try {
            con.setAutoCommit(false); 

            try (PreparedStatement psInsert = con.prepareStatement(sqlInsert);
                 PreparedStatement psUpdate = con.prepareStatement(sqlUpdateStock)) {
                
                psInsert.setInt(1, p.getId_material());
                psInsert.setString(2, p.getNombre());
                psInsert.setString(3, p.getDescripcion());
                psInsert.setString(4, p.getEstado() != null ? p.getEstado().toString() : "PRESTADO"); 
                psInsert.setInt(5, p.getCantidad());
                psInsert.setObject(6, p.getFecha()); 
                psInsert.setString(7, p.getObservaciones());
                psInsert.executeUpdate();

                psUpdate.setInt(1, p.getCantidad());
                psUpdate.setInt(2, p.getId_material());
                psUpdate.executeUpdate();

                con.commit(); 
                return true;
            } catch (SQLException e) {
                con.rollback(); 
                System.out.println("Error al registrar préstamo: " + e.getMessage());
                return false;
            } finally {
                con.setAutoCommit(true);
            }
        } catch (SQLException e) {
            System.out.println("Error de conexión: " + e.getMessage());
            return false;
        }
    }

    // 2. MÉTODO PARA DEVOLVER MATERIAL (El que te estaba dando error)
    public boolean registrarDevolucion(int idPrestamo) {
        Connection con = ConexionBD.getInstancia().getConexion();
        
        String sqlSelect = "SELECT id_material, cantidad FROM prestamos WHERE id_prestamo = ? AND estado != 'DEVUELTO'";
        String sqlUpdatePrestamo = "UPDATE prestamos SET estado = 'DEVUELTO' WHERE id_prestamo = ?";
        String sqlUpdateStock = "UPDATE materiales SET cantidad = cantidad + ? WHERE id_material = ?";

        try {
            con.setAutoCommit(false); 

            try (PreparedStatement psSelect = con.prepareStatement(sqlSelect);
                 PreparedStatement psUpdateP = con.prepareStatement(sqlUpdatePrestamo);
                 PreparedStatement psUpdateS = con.prepareStatement(sqlUpdateStock)) {
                
                psSelect.setInt(1, idPrestamo);
                try (java.sql.ResultSet rs = psSelect.executeQuery()) {
                    if (rs.next()) {
                        int idMaterial = rs.getInt("id_material");
                        int cantidad = rs.getInt("cantidad");

                        psUpdateP.setInt(1, idPrestamo);
                        psUpdateP.executeUpdate();

                        psUpdateS.setInt(1, cantidad);
                        psUpdateS.setInt(2, idMaterial);
                        psUpdateS.executeUpdate();

                        con.commit(); 
                        return true;
                    } else {
                        return false; 
                    }
                }
            } catch (SQLException e) {
                con.rollback();
                System.out.println("Error al procesar devolución: " + e.getMessage());
                return false;
            } finally {
                con.setAutoCommit(true);
            }
        } catch (SQLException e) {
            return false;
        }
    }

    // 3. MÉTODO PARA LISTAR LOS PRÉSTAMOS QUE AÚN NO SE HAN DEVUELTO
    public List<Object[]> listarPrestamosPendientes() {
        List<Object[]> lista = new ArrayList<>();
        Connection con = ConexionBD.getInstancia().getConexion();
        
        String sql = "SELECT p.id_prestamo, p.cantidad, p.fecha_prestamo " +
                     "FROM prestamos p " +
                     "JOIN materiales m ON p.id_material = m.id_material " +
                     "WHERE p.fecha_devolucion < now()"; 

        try (PreparedStatement ps = con.prepareStatement(sql);
             java.sql.ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                lista.add(new Object[]{
                    rs.getInt("id_prestamo"),
                    rs.getString("prestatario"),
                    rs.getString("material"),
                    rs.getInt("cantidad"),
                    rs.getTimestamp("fecha").toLocalDateTime()
                });
            }
        } catch (SQLException e) {
            System.out.println("Error al listar préstamos pendientes: " + e.getMessage());
        }
        return lista;
    }
    
}