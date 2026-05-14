/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daoClasesSQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import modelClasesTablas.Material;
import utilsClasesApoyo.ConexionBD;

/**
 *
 * @author DAM126
 */
public class MaterialDAO {
    //Metodo que busca materiales aplicando filtros opcionales
    //texto: busca por nombre o descripcion (LIKE)
    //categoriaNombre: filtra por el nombre de la categoria
    //estado: filtra por estado (disponible, prestado, reparacion, baja)
    //ubicacionStr: filtra por armario y balda (formato "Armario - Balda")
    //Devuelve una lista de arrays con los datos para mostrar en la tabla

    public List<Object[]> buscar(String texto, String categoriaNombre, String estado, String ubicacionStr) {
            List<Object[]> resultados = new ArrayList<>();
            Connection conexion = ConexionBD.getInstancia().getConexion();
            StringBuilder sql = new StringBuilder();

            sql.append("SELECT m.id_material, m.nombre, m.descripcion, c.nombre AS categoria, ")
               .append("e.nombre AS estado_nombre, ") // Obtenemos el nombre del estado
               .append("CASE ")
               .append("  WHEN u.tipo = 'mesa' THEN u.nombre ")
               .append("  ELSE CONCAT(u.nombre, ' - Cajón ', u.cajon) ")
               .append("END AS ubicacion, m.cantidad ")
               .append("FROM materiales m ")
               .append("JOIN categorias c ON m.id_categoria = c.id_categoria ")
               .append("JOIN estado e ON m.id_estado = e.id_estado ") 
               .append("JOIN ubicacion u ON m.id_ubicacion = u.id_ubicacion ")
               .append("WHERE 1=1 ");

            List<Object> parametros = new ArrayList<>();

            if (texto != null && !texto.trim().isEmpty()) {
                sql.append("AND (m.nombre LIKE ? OR m.descripcion LIKE ?) ");
                parametros.add("%" + texto.trim() + "%");
                parametros.add("%" + texto.trim() + "%");
            }

            if (categoriaNombre != null && !categoriaNombre.trim().isEmpty()) {
                sql.append("AND c.nombre = ? ");
                parametros.add(categoriaNombre.trim());
            }

            // CORRECCIÓN: Filtramos por el NOMBRE del estado en la tabla 'e'
            if (estado != null && !estado.trim().isEmpty()) {
                sql.append("AND e.nombre = ? "); 
                parametros.add(estado.trim()); 
            }

            if (ubicacionStr != null && !ubicacionStr.trim().isEmpty()) {
                String[] partes = ubicacionStr.split(" - ");
                sql.append("AND u.nombre = ? "); // Asumiendo que u.nombre es el nombre del armario/ubicación
                parametros.add(partes[0].trim());
                if (partes.length > 1) {
                    sql.append("AND u.cajon = ? ");
                    parametros.add(partes[1].replace("Cajón ", "").trim());
                }
            }

            sql.append("ORDER BY m.nombre");

            try (PreparedStatement ps = conexion.prepareStatement(sql.toString())) {
                for (int i = 0; i < parametros.size(); i++) {
                    ps.setObject(i + 1, parametros.get(i));
                }

                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        resultados.add(new Object[]{
                            rs.getInt("id_material"),
                            rs.getString("nombre"),
                            rs.getString("descripcion"),
                            rs.getString("categoria"),
                            rs.getString("estado_nombre"), // Usamos el alias de la consulta
                            rs.getString("ubicacion"),
                            rs.getInt("cantidad")
                        });
                    }
                }
            } catch (SQLException e) {
                System.out.println("Error al buscar materiales: " + e.getMessage());
            }
            return resultados;
        }

    public boolean insertarMaterial(Material m) {
        Connection con = ConexionBD.getInstancia().getConexion();
        // 6 parámetros (?) ya que fecha_alta usa NOW()
        String sql = "INSERT INTO materiales (nombre, descripcion, cantidad, id_estado, id_categoria, id_ubicacion, fecha_alta) VALUES (?,?,?,?,?,?,NOW())";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, m.getNombre());
            ps.setString(2, m.getDescripcion());
            ps.setInt(3, m.getCantidad());
            ps.setInt(4, m.getId_estado());    // Estado (ID numérico)
            ps.setInt(5, m.getId_categoria()); // Categoría (ID numérico)
            ps.setInt(6, m.getId_ubicacion()); // Ubicación (ID numérico)

            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error al insertar: " + e.getMessage());
            return false;
        }
    }

    public boolean modificarMaterial(Material m) {
        Connection con = ConexionBD.getInstancia().getConexion();
        String sql = "UPDATE materiales SET nombre=?, descripcion=?, cantidad=?, id_estado=?, id_categoria=?, id_ubicacion=? WHERE id_material=?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, m.getNombre());
            ps.setString(2, m.getDescripcion());
            ps.setInt(3, m.getCantidad());
            ps.setInt(4, m.getId_estado());
            ps.setInt(5, m.getId_categoria());
            ps.setInt(6, m.getId_ubicacion());
            ps.setInt(7, m.getId_material()); // El ID para el WHERE

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al modificar material: " + e.getMessage());
            return false;
        }
    }

    public Material buscarPorNombre(String nombre) {

        Connection con = ConexionBD.getInstancia().getConexion();

        String sql = "SELECT * FROM materiales WHERE nombre = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, nombre);

            try (ResultSet rs = ps.executeQuery()) {
                LocalDateTime fechaAlta
                        = rs.getTimestamp("fecha_alta").toLocalDateTime();

                if (rs.next()) {

                    return new Material(
                            rs.getInt("id_material"),
                            rs.getString("nombre"),
                            rs.getString("descripcion"),
                            rs.getInt("cantidad"),
                            rs.getInt("estado"),
                            fechaAlta,
                            rs.getInt("id_categoria"),
                            rs.getInt("id_ubicacion")
                    );
                }
            }

        } catch (SQLException e) {

            System.out.println(e.getMessage());
        }

        return null;
    }

    public boolean bajaMaterial(int idMaterial) {
        //Llamamos a la instancia de la conexion de la base de datos
        Connection con = ConexionBD.getInstancia().getConexion();

        //cambiamos el estado a baja
        String sql = "UPDATE materiales SET id_estado = 4 WHERE id_material=?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idMaterial);
            //si devuelve distinto a cero se ha ejecutado correctamente
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al dar de baja el material: " + e.getMessage());
            return false;
        }
    }

}
