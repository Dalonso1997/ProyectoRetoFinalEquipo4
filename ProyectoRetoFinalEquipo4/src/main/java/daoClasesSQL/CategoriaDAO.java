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
import modelClasesTablas.Categoria;
import utilsClasesApoyo.ConexionBD;

/**
 * Clase del DAO para gestionar todas las consultas SQL de las categorías.
 * Conecta la base de datos de MySQL con los objetos de la interfaz de usuario.
 *
 * * @author alberto gonzalez
 */
public class CategoriaDAO {

    /**
     * Método que busca y devuelve una lista con todas las categorías guardadas
     * en el sistema.
     *
     * * @return una lista de objetos Categoria con todos los registros de la
     * tabla.
     */
    //Metodo que devuelve una lista con todas las categorias de la base de datos
    public List<Categoria> listarTodos() {

        //Creamos una lista vacia donde guardaremos las categorias
        List<Categoria> lista = new ArrayList<>();

        //Pedimos la conexion a la clase ConexionDB (Singleton)
        Connection con = ConexionBD.getInstancia().getConexion();

        //Creamos un String con la consulta para obtener todas las categorias ordenadas por nombre
        String sql = "SELECT id_categoria, nombre, descripcion FROM categorias ORDER BY nombre";

        //PreparedStatement protege la consulta SQL y la ejecuta
        try (PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            //Recorremos todas las filas que devuelve la consulta
            while (rs.next()) {

                //Por cada fila creamos un objeto Categoria con los datos obtenidos
                lista.add(new Categoria(
                        rs.getInt("id_categoria"),
                        rs.getString("nombre"),
                        rs.getString("descripcion")
                ));
            }

        } catch (SQLException e) {
            System.out.println("Error al listar categorías: " + e.getMessage());
        }

        //Devolvemos la lista con todas las categorias (vacia si no encontro ninguna)
        return lista;
    }

}
