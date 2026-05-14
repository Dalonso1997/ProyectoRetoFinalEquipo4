/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daoClasesSQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelClasesTablas.Usuario;
import utilsClasesApoyo.ConexionBD;

/**
 *
 * @author DAM126
 */
public class UsuarioDAO {
    
    //Metodo que recibe lo que el usuario introduce en la pantalla de login
    public Usuario login(String email, String password){
        
        //creamos esta variable para que el usuario logueado no exista en un pricipio.
        Usuario usuarioLogueado = null;
        
        //Pedimos la conexion a la clase ConexionDB (Singleton)
        Connection con = ConexionBD.getInstancia().getConexion();
        
        //Creamos un String con la consulta que necesita MySQL para recibir los datos del usuario introducido
        String sql = "SELECT * FROM usuarios WHERE email = ? AND password = ?";
        
        
        //Prepared Statement es el objeto de java que protege la consulta es decir valida que lo que se recoge en la variable sql es una consulta valida que no crea problemas en la base de datos
        try (PreparedStatement ps = con.prepareStatement(sql)){
            
            //Sustituimos los ? de la consulta en la varibale sql por los datos introducidos por el usuario
            //Por defecto el propio metodo lee las ? y las asigna un numero comenzando por 1 y ademas le ponemos la variable contenedora del email y la password del usuario
            ps.setString(1, email);
            ps.setString(2, password);
            
            //Ejecutamos la consulta
            try (ResultSet rs = ps.executeQuery()){
                
                //Si rs.next es true, MySql encontro del usuario
                if(rs.next()){
                    
                    //Creamos el usuario vacio usando el usuario que creamos en inexistente al principio del metodo
                    usuarioLogueado = new Usuario();
                    
                    
                    //Usamos los sets para meter los datos al usuario con los datos que nos ha devuelto la base de datos con la consulta
                    usuarioLogueado.setId_usuario(rs.getInt("id_usuario"));
                    usuarioLogueado.setNombre(rs.getString("nombre"));
                    usuarioLogueado.setApellidos(rs.getString("apellidos"));
                    usuarioLogueado.setEmail(rs.getString("email"));
                    
                    
                    //Obtenemos el rol que tiene el usuario que se loguea
                    usuarioLogueado.setRol(rs.getString("rol"));
                    
                }
                
            }
            
            
        } catch (SQLException e){
            System.out.println("Error al consultar la base de datos: "+e.getMessage());
        }
        
        //Devolvemos el usuario (Con sus datos si era correcto, null si no lo es)
        return usuarioLogueado;
    }
   
}
