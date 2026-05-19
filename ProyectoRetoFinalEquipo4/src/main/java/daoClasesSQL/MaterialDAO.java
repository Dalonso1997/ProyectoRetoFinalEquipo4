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
 * Clase central del DAO para gestionar el inventario de materiales en la base
 * de datos. Centraliza las altas, bajas, modificaciones y búsquedas con filtros
 * avanzados del taller.
 *
 * @author equipo 4
 */
public class MaterialDAO {

    /**
     * Busca materiales aplicando filtros opcionales de forma dinámica. Junta
     * las tablas de categorías, estados y ubicaciones devolviendo la
     * información formateada.
     *
     * @param texto busca por coincidencia en el nombre o la descripción (LIKE).
     * @param categoriaNombre filtra por el nombre de la categoría.
     * @param estado filtra por estado (disponible, prestado, reparación, baja).
     * @param ubicacionStr filtra por el nombre del armario (formato "Armario -
     * Cajón X").
     * @return una lista de arrays de objetos lista para pintar directamente en
     * el JTable.
     * @author alberto gonzalez
     */
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
                parametros.add(partes[1].replaceAll("[^0-9]", "").trim());
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

    /**
     * Inserta un registro completo de material nuevo en las tablas del taller
     * de informática.
     *
     * @param m objeto Material que contiene los atributos rellenos desde la
     * ventana visual.
     * @return true si la inserción se ejecutó de forma correcta en mysql, false
     * si falló.
     * @author david alonso
     */
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

    /**
     * Modifica las propiedades principales de un material guardado
     * identificándolo mediante su ID.
     *
     * @param idMaterial identificador numérico de la fila original en la tabla
     * materiales.
     * @param nombreMat nuevo nombre asignado al componente.
     * @param descriMat texto de descripción con los nuevos detalles del
     * material.
     * @param cantMat número de unidades actualizadas en el stock físico.
     * @param estaMat identificador de clave foránea al estado nuevo.
     * @param cateMat identificador de clave foránea a la categoría nueva.
     * @return true si la consulta afectó con éxito a alguna fila de la base de
     * datos, false si dio error.
     * @author david alonso
     */
    public boolean modificarMaterial(int idMaterial, String nombreMat, String descriMat, int cantMat, int estaMat, int cateMat) {

        Connection con = ConexionBD.getInstancia().getConexion();
        String sql = "UPDATE materiales SET nombre=?, descripcion=?, cantidad=?, id_estado=?, id_categoria=? WHERE id_material=?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, nombreMat);
            ps.setString(2, descriMat);
            ps.setInt(3, cantMat);
            ps.setInt(4, estaMat);
            ps.setInt(5, cateMat);
            ps.setInt(6, idMaterial); // El ID para el WHERE

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al modificar material: " + e.getMessage());
            return false;
        }
    }

    /**
     * Realiza una búsqueda exacta de un material a través de su nombre.
     *
     * @param nombre cadena con el nombre de la herramienta que queremos buscar.
     * @return un objeto de tipo Material con todos sus atributos rellenos, o
     * null si no se encuentra coincidencia.
     * @author david alonso
     */
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

    /**
     * Da de baja lógica un material del taller asignándole el ID de estado de
     * bajas (ID 4).
     *
     * @param idMaterial el identificador numérico de la fila que queremos
     * retirar.
     * @return true si el estado cambió correctamente en la base de datos de
     * AWS, false si falló.
     * @author adrian gonzalez
     */
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

    /**
     * Actualiza el identificador de ubicación de un material moviéndolo de
     * armario o de cajón de destino.
     *
     * @param idUbicacion la id numérica del nuevo registro de destino en la
     * tabla ubicacion.
     * @param idMaterial la id numérica del material que vamos a trasladar
     * físicamente.
     * @return true si la base de datos guardó la modificación del traslado con
     * éxito, false en caso contrario.
     * @author adrian gonzalez
     */
    public boolean cambioUbicacionMaterial(int idUbicacion, int idMaterial) {
        //Llamamos a la instancia de la conexion de la base de datos
        Connection con = ConexionBD.getInstancia().getConexion();

        //cambiamos la ubicacion por id de ubicacion que seleccionen
        String sql = "UPDATE materiales SET id_ubicacion = ? WHERE id_material=?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idUbicacion);
            ps.setInt(2, idMaterial);
            //si devuelve distinto a cero se ha ejecutado correctamente
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al actualizar ubicacion: " + e.getMessage());
            return false;
        }
    }

    /**
     * Devuelve una lista de objetos Material filtrados exclusivamente por el
     * nombre del estado indicado.
     *
     * @param estado cadena de texto con el nombre del estado (ej: 'disponible',
     * 'baja').
     * @return una lista ordenada con todas las entidades Material que cumplen
     * esa propiedad.
     * @author david alonso
     */
    public List<Material> buscarPorEstado(String estado) {
        List<Material> lista = new ArrayList<>();
        Connection con = ConexionBD.getInstancia().getConexion();
        String sql = "SELECT * FROM materiales WHERE id_estado = (SELECT id_estado FROM estado WHERE nombre = ?)";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, estado);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Material m = new Material(
                        rs.getInt("id_material"),
                        rs.getString("nombre"),
                        rs.getString("descripcion"),
                        rs.getInt("cantidad"),
                        rs.getInt("id_estado"),
                        rs.getTimestamp("fecha_alta").toLocalDateTime(),
                        rs.getInt("id_categoria"),
                        rs.getInt("id_ubicacion")
                );
                lista.add(m);
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar por estado: " + e.getMessage());
        }
        return lista;
    }

    /**
     * Devuelve de alta de forma rápida un material que estaba retirado
     * revirtiendo su id_estado a disponible (ID 1).
     *
     * @param idMaterial identificador numérico de la fila del componente que
     * vuelve a estar operativo en el aula.
     * @author david alonso
     */
    public void darDeAlta(int idMaterial) {
        Connection con = ConexionBD.getInstancia().getConexion();
        String sql = "UPDATE materiales SET id_estado = 1 WHERE id_material = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idMaterial);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al dar de alta el material: " + e.getMessage());
        }
    }

}
