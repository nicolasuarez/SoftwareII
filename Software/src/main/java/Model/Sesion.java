/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;
import javax.servlet.http.Cookie;
/**
 *
 * @author User
 */

    public class Sesion {
    
    String validacion;
    String mensaje;
    String tipoUsuario;
    String correo;
    String idUsuario;
    private Cookie galleta;   
    

    public Sesion(String validacion, String mensaje, String tipoUsuario, String correo, String idUsuario) {
        this.validacion = validacion;
        this.mensaje = mensaje;
        this.tipoUsuario = tipoUsuario;
        this.correo = correo;
        this.idUsuario = idUsuario;
    }

    public Sesion() {
    }
    
    
 
    public String getValidacion() {
        return validacion;
    }

    public void setValidacion(String validacion) {
        this.validacion = validacion;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Cookie getGalleta() {
        return galleta;
    }

    public void setGalleta(Cookie galleta) {
        this.galleta = galleta;
    }

}
