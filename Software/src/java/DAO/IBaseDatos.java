/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;
import java.sql.SQLException;
import java.util.List;
/**
 *
 * @author LUIS
 */
public interface IBaseDatos <T>{
    List<T> findAll() throws SQLException;
    boolean insert(T t)throws SQLException;
    boolean update(T t)throws SQLException;
    boolean delete(T t)throws SQLException;
}
