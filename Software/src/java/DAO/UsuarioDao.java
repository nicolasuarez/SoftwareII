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
public class UsuarioDao implements IBaseDatos<Usuario>  {

    @Override
    public List<Usuario> findAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean insert(Usuario t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        boolean result = false;
        Connection connection = Conexion.getConnection();
        String query = "Usuarios values (IdUsu,Usu,Contreseña,Nombre,Apellido,Tipo,genero), values (?,?,?,?,?,?) ";
        PreparedStatement preparedStmt = null;
        try {
            preparedStmt = connection.prepareStatement(query);
            preparedStmt.setInt(1, t.getId());
            preparedStmt.setString(2, t.getUsuario());
            preparedStmt.setString(3, t.getContraseña());
            preparedStmt.setString(4, t.getNombre());
            preparedStmt.setString(5, t.getApellido());
            preparedStmt.setString(6, "Estudiante");
            preparedStmt.setString(7, t.getSexo());
            result = preparedStmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;

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
