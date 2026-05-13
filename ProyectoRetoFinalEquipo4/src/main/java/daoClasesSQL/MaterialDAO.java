package daoClasesSQL;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import modelClasesTablas.Material;
import utilsClasesApoyo.ConexionBD;

/**
 * Clase DAO para la gestión de materiales en el taller de informática.
 * Proporciona métodos CRUD y búsqueda avanzada con filtros.
 * @author DAM126
 */
public class MaterialDAO {

    /**
     * Busca materiales aplicando filtros dinámicos.
     * @param texto Filtro por nombre o descripción.
     * @param categoriaNombre Nombre de la categoría.
     * @param estadoNombre Nombre del estado (ej: 'disponible').
     * @param ubicacionStr Nombre de la ubicación (ej: 'Mesa 1' o 'ArmarioA - Cajón 1').
     * @return Lista de Object[] para cargar directamente en la JTable.
     */
    public List<Object[]> buscar(String texto, String categoriaNombre, String estadoNombre, String ubicacionStr) {
        List<Object[]> resultados = new ArrayList<>();
        Connection conexion = ConexionBD.getInstancia().getConexion();
        StringBuilder sql = new StringBuilder();

        // Consulta base con los JOINs corregidos para la nueva estructura
        sql.append("SELECT m.id_material, m.nombre, m.descripcion, c.nombre AS categoria, ")
           .append("e.nombre AS estado, ")
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

        if (estadoNombre != null && !estadoNombre.trim().isEmpty()) {
            sql.append("AND e.nombre = ? "); // Filtramos por el nombre en la tabla 'estado'
            parametros.add(estadoNombre.trim());
        }

        if (ubicacionStr != null && !ubicacionStr.trim().isEmpty()) {
            // Ajustamos la lógica de filtrado de ubicación para que coincida con el nombre
            if (ubicacionStr.contains(" - Cajón ")) {
                String[] partes = ubicacionStr.split(" - Cajón ");
                sql.append("AND u.nombre = ? AND u.cajon = ? ");
                parametros.add(partes[0].trim());
                parametros.add(partes[1].trim());
            } else {
                sql.append("AND u.nombre = ? ");
                parametros.add(ubicacionStr.trim());
            }
        }

        sql.append(" ORDER BY m.nombre");

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
                        rs.getString("estado"),
                        rs.getString("ubicacion"),
                        rs.getInt("cantidad")
                    });
                }
            }
        } catch (SQLException e) {
            System.err.println("Error en búsqueda: " + e.getMessage());
        }
        return resultados;
    }

    /**
     * Inserta un nuevo material en el inventario.
     * @param m Objeto Material con los datos.
     * @return true si la operación tuvo éxito.
     */
    public boolean insertarMaterial(Material m) {
        Connection con = ConexionBD.getInstancia().getConexion();
        // Cambiado 'estado' (String) por 'id_estado' (INT) según el script SQL
        String sql = "INSERT INTO materiales (nombre, descripcion, cantidad, id_estado, id_categoria, id_ubicacion, fecha_alta) "
                   + "VALUES (?, ?, ?, ?, ?, ?, (CURRENT_DATE))";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, m.getNombre());
            ps.setString(2, m.getDescripcion());
            ps.setInt(3, m.getCantidad());
            ps.setInt(4, m.getId_estado()); // Ahora es un INT
            ps.setInt(5, m.getId_categoria());
            ps.setInt(6, m.getId_ubicacion());
            
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al insertar: " + e.getMessage());
            return false;
        }
    }

    /**
     * Modifica los datos de un material existente.
     */
    public boolean modificarMaterial(Material m) {
        Connection con = ConexionBD.getInstancia().getConexion();
        String sql = "UPDATE materiales SET nombre=?, descripcion=?, cantidad=?, id_estado=?, "
                   + "id_categoria=?, id_ubicacion=? WHERE id_material=?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, m.getNombre());
            ps.setString(2, m.getDescripcion());
            ps.setInt(3, m.getCantidad());
            ps.setInt(4, m.getId_estado()); 
            ps.setInt(5, m.getId_categoria());
            ps.setInt(6, m.getId_ubicacion());
            ps.setInt(7, m.getId_material());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al modificar: " + e.getMessage());
            return false;
        }
    }

    /**
     * Cambia el estado de un material a 'baja' de forma lógica.
     * @param idMaterial ID del material a desactivar.
     * @return true si se actualizó correctamente.
     */
    public boolean bajaMaterial(int idMaterial) {
        Connection con = ConexionBD.getInstancia().getConexion();
        // Buscamos el ID del estado cuya descripción sea 'baja'
        String sql = "UPDATE materiales SET id_estado = (SELECT id_estado FROM estado WHERE nombre = 'baja') "
                   + "WHERE id_material = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idMaterial);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error en baja lógica: " + e.getMessage());
            return false;
        }
    }
    
    /**
 * Busca un material específico por su nombre exacto.
 * @param nombre Nombre del material a buscar.
 * @return Objeto Material o null si no se encuentra.
 */
public Material buscarPorNombre(String nombre) {
    Connection con = utilsClasesApoyo.ConexionBD.getInstancia().getConexion();
    String sql = "SELECT id_material, nombre, descripcion, cantidad, id_estado, fecha_alta, id_categoria, id_ubicacion FROM materiales WHERE nombre = ?";

    try (PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setString(1, nombre);
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                // Convertimos el Timestamp de la BD a LocalDateTime
                java.time.LocalDateTime fecha = rs.getTimestamp("fecha_alta").toLocalDateTime();

                return new modelClasesTablas.Material(
                    rs.getInt("id_material"),
                    rs.getString("nombre"),
                    rs.getString("descripcion"),
                    rs.getInt("cantidad"),
                    rs.getInt("id_estado"), // El nuevo ID numérico
                    fecha,
                    rs.getInt("id_categoria"),
                    rs.getInt("id_ubicacion")
                );
            }
        }
    } catch (SQLException e) {
        System.err.println("Error al buscar por nombre: " + e.getMessage());
    }
    return null;
}
}