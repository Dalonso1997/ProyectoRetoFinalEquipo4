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

        //Creamos una lista vacia donde guardaremos los resultados
        List<Object[]> resultados = new ArrayList<>();

        //Pedimos la conexion a la clase ConexionDB (Singleton)
        Connection conexion = ConexionBD.getInstancia().getConexion();
        //Usamos StringBuilder para construir la consulta SQL de forma dinamica
        StringBuilder sql = new StringBuilder();

        //Consulta base que obtiene los datos del material junto con el nombre de la categoria y la ubicacion
        sql.append("SELECT m.id_material, m.nombre, m.descripcion, c.nombre AS categoria, ")
                .append("m.estado, CONCAT(u.armario, ' - ', u.balda) AS ubicacion, m.cantidad ")
                .append("FROM materiales m ")
                .append("JOIN categorias c ON m.id_categoria = c.id_categoria ")
                .append("JOIN ubicacion u ON m.id_ubicacion = u.id_ubicacion ")
                .append("WHERE 1=1 "); //El 1=1 es para poder añadir condiciones con AND sin comprobar si es la primera
        //Lista para guardar los parametros que sustituiran a las ? en la consulta
        List<Object> parametros = new ArrayList<>();
        //Si el usuario introdujo texto, añadimos filtro por nombre o descripcion (LIKE)
        if (texto != null && !texto.trim().isEmpty()) {
            sql.append("AND (m.nombre LIKE ? OR m.descripcion LIKE ?) ");
            parametros.add("%" + texto.trim() + "%");
            parametros.add("%" + texto.trim() + "%");
        }

        //Si el usuario selecciono una categoria, añadimos filtro por nombre de categoria
        if (categoriaNombre != null && !categoriaNombre.trim().isEmpty()) {
            sql.append("AND c.nombre = ? ");
            parametros.add(categoriaNombre.trim());
        }

        //Si el usuario selecciono un estado, añadimos filtro por estado
        if (estado != null && !estado.trim().isEmpty()) {
            sql.append("AND m.estado = ? ");
            parametros.add(estado.trim().toLowerCase());
        }

        //Si el usuario selecciono una ubicacion, añadimos filtro por armario y balda
        if (ubicacionStr != null && !ubicacionStr.trim().isEmpty()) {
            //Dividimos el string "Armario - Balda" en dos partes
            String[] partes = ubicacionStr.split(" - ");
            sql.append("AND u.armario = ? AND u.balda = ? ");
            parametros.add(partes[0].trim());
            parametros.add(partes.length > 1 ? partes[1].trim() : "");
        }
        //Ordenamos los resultados por nombre del material
        sql.append("ORDER BY m.nombre");
        //PreparedStatement protege la consulta SQL y la ejecuta
        try (PreparedStatement ps = conexion.prepareStatement(sql.toString())) {

            //Sustituimos cada ? por su parametro correspondiente
            for (int i = 0; i < parametros.size(); i++) {
                ps.setObject(i + 1, parametros.get(i));
            }

            //Ejecutamos la consulta
            try (ResultSet rs = ps.executeQuery()) {

                //Recorremos todas las filas que devuelve la consulta
                while (rs.next()) {

                    //Creamos un array con los datos de cada fila para mostrarlos en la tabla
                    //El orden de las columnas es: id, nombre, descripcion, categoria, estado, ubicacion, cantidad
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
            System.out.println("Error al buscar materiales: " + e.getMessage());
        }

        //Devolvemos la lista con los resultados (vacia si no encontro ninguno)
        return resultados;
    }
}
