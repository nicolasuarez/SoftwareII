package Controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Model.ManagerInicioSesion;
import Model.Sesion;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.utils;




public class Login extends HttpServlet {

@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
         
            Sesion lol =   ManagerInicioSesion.IniciarSesion(request);
            
            if(lol.getGalleta()!=null){
                lol.getGalleta().setHttpOnly(true);
                 response.addCookie(lol.getGalleta());              
            }
           lol.setGalleta(null);
            String respuestaInicioSesion = utils.toJson(lol);
            response.setContentType("application/json;charset=utf-8");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().print(respuestaInicioSesion);
        } catch (IOException ex) {
        } catch (SQLException ex) {
        Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
    } catch (Exception ex) {
        Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
    
    
    
    

    
}
