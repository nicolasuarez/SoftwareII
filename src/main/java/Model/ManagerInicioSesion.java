/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;


import java.sql.SQLException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import controladores.*;
import entidades.*;

/**
 *
 * @author User
 */

public class ManagerInicioSesion {

    public static Sesion IniciarSesion(HttpServletRequest request) throws SQLException, Exception {

        UsuariosJpaController user = new UsuariosJpaController();
        String correo = request.getParameter("correo");
        String password = request.getParameter("password");
        Sesion sesion = new Sesion();
        System.out.println(password);
        System.out.println(correo);
        System.out.println("-------------------");
        Usuarios aux = user.findUsuariosU(correo);
        System.out.println("----------");
        String pass = "";

        System.out.println(aux.getContreseña());
        if (aux != null) {
            System.out.println("----1");
            
            System.out.println(aux.getContreseña());
            System.out.println("------------------");
            System.out.println("----------passs");
            System.out.println(password);
            if (aux.getContreseña().equals(password)) {
                String[] data = user.comprobarPerfil(aux);
                if (data[0] != null) {
                    System.out.println("-------2");
                    //ACA VAN LOS METODOS QUE TRBAJAN CON EL TOKEN           
                    sesion.setValidacion("Inicio de sesion valido");
                    sesion.setMensaje("El correo y la clave son validos y corresponden");
                    sesion.setTipoUsuario(data[0]);
                    sesion.setCorreo(data[1]);
                    sesion.setIdUsuario(data[2]);
                    String c = RSAtoken.crearToken(sesion);
                    Cookie gat = new Cookie("TK.USAP", c);
                    gat.setMaxAge(365 * 24 * 60 * 60);
                    sesion.setGalleta(gat);

                }
            } else {
                System.out.println("------3");
                sesion.setValidacion("Inicio de sesion no valido");
                sesion.setMensaje("La clave y el usuario no corresponden");
            }
        } else {
            System.out.println("------3");
            sesion.setValidacion("Inicio de sesion no valido");
            sesion.setMensaje("El correo no es valido");
        }
        return sesion;
    }

}
