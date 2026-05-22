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
import modelClasesTablas.Usuario;
import utilsClasesApoyo.ConexionBD;
import org.mindrot.jbcrypt.BCrypt;

/**
 * Clase del DAO para gestionar todas las consultas SQL relacionadas con los
 * usuarios. Se encarga de comprobar las credenciales del login y listar los
 * usuarios del taller.
 *
 * * @author alberto gonzalez
 */
public class UsuarioDAO {

    /**
     * Comprueba el email y la contraseña de un usuario en la base de datos para
     * permitir el acceso.
     *
     * * @param email el correo electronico que introduce el usuario.
     * @param password la contraseña asociada a esa cuenta de correo.
     * @return el objeto Usuario con sus datos cargados si coincide, o null si
     * las credenciales fallan.
     */
    //Metodo que recibe lo que el usuario introduce en la pantalla de login
    public Usuario login(String email, String password) {

        //Creamos esta variable para que el usuario logueado no exista en un principio.
        Usuario usuarioLogueado = null;

        //Pedimos la conexion a la clase ConexionDB (Singleton)
        Connection con = ConexionBD.getInstancia().getConexion();

        //Ahora solo buscamos el usuario por email
        String sql = "SELECT * FROM usuarios WHERE email = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            //Sustituimos el ? por el email
            ps.setString(1, email);

            //Ejecutamos la consulta
            try (ResultSet rs = ps.executeQuery()) {

                //Si existe el usuario
                if (rs.next()) {

                    //Obtenemos el hash guardado en la BD
                    String hashGuardado = rs.getString("password");

                    //BCrypt compara la contraseña escrita con el hash
                    if (BCrypt.checkpw(password, hashGuardado)) {

                        usuarioLogueado = new Usuario();

                        usuarioLogueado.setId_usuario(rs.getInt("id_usuario"));
                        usuarioLogueado.setNombre(rs.getString("nombre"));
                        usuarioLogueado.setApellidos(rs.getString("apellidos"));
                        usuarioLogueado.setEmail(rs.getString("email"));
                        usuarioLogueado.setRol(rs.getString("rol"));
                    }
                }

            }

        } catch (SQLException e) {
            System.out.println("Error al consultar la base de datos: " + e.getMessage());
        }

        return usuarioLogueado;
    }

    /**
     * Obtiene una lista simplificada de todos los usuarios registrados en el
     * sistema del taller. Trae el identificador, el nombre y el rol asignado de
     * cada uno para pintarlos en la interfaz.
     *
     * * @return una lista de arrays de objetos con los datos de las columnas
     * elegidas.
     */
    public List<Object[]> listarUsuarios() {
        List<Object[]> lista = new ArrayList<>();
        Connection con = ConexionBD.getInstancia().getConexion();
        String sql = "SELECT id_usuario, nombre, rol FROM usuarios";
        try (PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(new Object[]{rs.getInt("id_usuario"), rs.getString("nombre"), rs.getString("rol")});
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return lista;
    }

}
