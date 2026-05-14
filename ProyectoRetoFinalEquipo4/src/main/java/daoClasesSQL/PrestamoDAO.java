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
import modelClasesTablas.Prestamo;
import utilsClasesApoyo.ConexionBD;

public class PrestamoDAO {

    
  
    public boolean registrarPrestamo(modelClasesTablas.Prestamo p, int idUsuario) {
    Connection con = ConexionBD.getInstancia().getConexion();
    
    // El orden aquí es: 1.cantidad, 2.observaciones, 3.id_material, 4.id_usuario
    String sqlInsert = "INSERT INTO prestamos (cantidad, fecha_prestamo, observaciones, id_material, id_usuario) VALUES (?, NOW(), ?, ?, ?)";
    String sqlUpdateStock = "UPDATE materiales SET cantidad = cantidad - ? WHERE id_material = ?";

    try {
        con.setAutoCommit(false); 

        try (PreparedStatement psInsert = con.prepareStatement(sqlInsert);
             PreparedStatement psUpdate = con.prepareStatement(sqlUpdateStock)) {
            
            // --- INSERTAR PRÉSTAMO ---
            // 1er '?' es cantidad (int)
            psInsert.setInt(1, p.getCantidad());
            // 2do '?' es observaciones (String) -> AQUÍ IRÁ EL "si" Y NO DARÁ ERROR
            psInsert.setString(2, p.getObservaciones());
            // 3er '?' es id_material (int)
            psInsert.setInt(3, p.getId_material());
            // 4to '?' es id_usuario (int)
            psInsert.setInt(4, idUsuario); 
            
            psInsert.executeUpdate();

            // --- ACTUALIZAR STOCK ---
            psUpdate.setInt(1, p.getCantidad());
            psUpdate.setInt(2, p.getId_material());
            psUpdate.executeUpdate();

            con.commit(); 
            return true;
        } catch (SQLException e) {
            con.rollback(); 
            System.out.println("Error en la transacción: " + e.getMessage());
            return false;
        } finally {
            con.setAutoCommit(true);
        }
    } catch (SQLException e) {
        System.out.println("Error de conexión: " + e.getMessage());
        return false;
    }
}

    // En PrestamoDAO.java
    public List<Object[]> listarTodosLosPrestamos() {
        List<Object[]> lista = new ArrayList<>();
        Connection con = ConexionBD.getInstancia().getConexion();

        // Traemos la fecha_devolucion para saber si está entregado o no
        String sql = "SELECT p.id_prestamo, u.nombre as usuario, m.nombre as material, p.cantidad, p.fecha_devolucion " +
                     "FROM prestamos p " +
                     "JOIN materiales m ON p.id_material = m.id_material " +
                     "JOIN usuarios u ON p.id_usuario = u.id_usuario " +
                     "ORDER BY p.id_prestamo DESC"; // Los más nuevos arriba

        try (PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(new Object[]{
                    rs.getInt("id_prestamo"),
                    rs.getString("usuario"),
                    rs.getString("material"),
                    rs.getInt("cantidad"),
                    rs.getObject("fecha_devolucion") // Guardamos la fecha (puede ser null)
                });
            }
        } catch (SQLException e) {
            System.out.println("Error al listar: " + e.getMessage());
        }
        return lista;
    }

    // 2. Método para registrar la devolución (con Transacción)
    public boolean registrarDevolucion(int idPrestamo) {
        Connection con = ConexionBD.getInstancia().getConexion();

        // SQLs necesarios
        String sqlCheck = "SELECT id_material, cantidad FROM prestamos WHERE id_prestamo = ? AND fecha_devolucion IS NULL";
        String sqlUpdatePrestamo = "UPDATE prestamos SET fecha_devolucion = NOW() WHERE id_prestamo = ?";
        String sqlUpdateStock = "UPDATE materiales SET cantidad = cantidad + ? WHERE id_material = ?";

        try {
            con.setAutoCommit(false);
            try (PreparedStatement psCheck = con.prepareStatement(sqlCheck)) {
                psCheck.setInt(1, idPrestamo);
                ResultSet rs = psCheck.executeQuery();

                if (rs.next()) {
                    int idMaterial = rs.getInt("id_material");
                    int cantidad = rs.getInt("cantidad");

                    // Marcar como devuelto
                    try (PreparedStatement ps1 = con.prepareStatement(sqlUpdatePrestamo)) {
                        ps1.setInt(1, idPrestamo);
                        ps1.executeUpdate();
                    }

                    // Devolver stock al material
                    try (PreparedStatement ps2 = con.prepareStatement(sqlUpdateStock)) {
                        ps2.setInt(1, cantidad);
                        ps2.setInt(2, idMaterial);
                        ps2.executeUpdate();
                    }

                    con.commit();
                    return true;
                }
            }
            con.rollback();
            return false;
        } catch (SQLException e) {
            try { con.rollback(); } catch (SQLException ex) {}
            return false;
        } finally {
            try { con.setAutoCommit(true); } catch (SQLException ex) {}
        }
    }
    
}