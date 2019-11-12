/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import DAO.UsuarioDao;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author User
 */
public class ManagerInicioSesion {

    public static Sesion IniciarSesion(HttpServletRequest request) throws SQLException, Exception {

        UsuarioDao user = new UsuarioDao();
        String correo = request.getParameter("correo");
        String password = request.getParameter("password");
        Sesion sesion = new Sesion();
        System.out.println(correo);
        System.out.println("-------------------");
        Usuario aux = user.find(correo);
        System.out.println("----------");
        String pass = "";
        pass = sha256(password);
        System.out.println(aux.getContraseña());
        if (aux != null) {

            if (aux.getContraseña().equals(pass)) {
                String[] data = user.comprobarPerfil(aux);
                if (data[0] != null) {
                    //ACA VAN LOS METODOS QUE TRBAJAN CON EL TOKEN           
                    sesion.setValidacion("Inicio de sesion valido");
                    sesion.setMensaje("El correo y la clave son validos y corresponden");
                    sesion.setTipoUsuario(data[0]);
                    sesion.setCorreo(data[1]);
                    sesion.setIdUsuario(data[2]);
                    String c = RSAtoken.crearToken(sesion);
                    Cookie gat = new Cookie("Usap.Yourhome", c);
                    gat.setMaxAge(365 * 24 * 60 * 60);
                    sesion.setGalleta(gat);

                }
            } else {
                sesion.setValidacion("Inicio de sesion no valido");
                sesion.setMensaje("La clave y el usuario no corresponden");
            }
        } else {
            sesion.setValidacion("Inicio de sesion no valido");
            sesion.setMensaje("El correo no es valido");
        }
        return sesion;
    }

    static String sha256(String input) throws NoSuchAlgorithmException {
        MessageDigest mDigest = MessageDigest.getInstance("SHA256");
        byte[] result = mDigest.digest(input.getBytes());
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < result.length; i++) {
            sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
        }

        return sb.toString();
    }

}
