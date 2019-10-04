/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.*;
import Model.Usuario;
import java.util.List;

/**
 *
 * @author User
 */
public class UsuarioDao implements IBaseDatos<Usuario> {

    @Override
    public List<Usuario> findAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean insert(Usuario t) throws SQLException {
        boolean result;
        result = false;
        Connection connection = Conexion.getConnection();
        String query = "insert into Usuarios  (IdUsu,Usu,Contreseña,Nombre,Apellido,Tipo,sexo,Correo) values (?,?,?,?,?,?,?,?) ";
        PreparedStatement preparedStmt = null;
        try {
            System.out.println("sdsdsdsd");
            preparedStmt = connection.prepareStatement(query);
            preparedStmt.setInt(1, t.getId());
            preparedStmt.setString(2, t.getUsuario());
            preparedStmt.setString(3, t.getContraseña());
            preparedStmt.setString(4, t.getNombre());
            preparedStmt.setString(5, t.getApellido());
            preparedStmt.setString(6, "Estudiante");
            preparedStmt.setString(7, t.getSexo());
            preparedStmt.setString(8, t.getCorreo());
            result = preparedStmt.execute();
            System.out.println(result);
        } catch (SQLException e) {
               e.printStackTrace();
        }

        return result;

    }

    public Usuario find(String user) throws SQLException {
        Usuario resultado = null;
        System.out.println(user);

        String query = "Select * from Usuarios Where Usu = '" + user + "'";

        System.out.println(query);
        System.out.println("----");
        Connection connection = Conexion.getConnection();
        try {
            System.out.println("entro");
            try (Statement st = connection.createStatement()) {
                ResultSet rs = st.executeQuery(query);
                int id = 0;
                String nombre = null, apellido = null;
                String usuario = null, clave = null, correo = null;
                String tipo = null, sexo = null;
                if (rs.next()) {
                    id = rs.getInt("idUSu");
                    usuario = rs.getString("Usu");
                    clave = rs.getString("Contreseña");
                    nombre = rs.getString("Nombre");
                    apellido = rs.getString("Apellido");
                    tipo = rs.getString("Tipo");
                    sexo = rs.getString("Sexo");
                    correo = rs.getString("Correo");
                    System.out.println(id);
                    resultado = new Usuario();
                }
            }
        } catch (SQLException e) {
            System.out.println("Problemas al obtener persona");
        }

        return resultado;
    }

    @Override
    public boolean update(Usuario t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(Usuario t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
