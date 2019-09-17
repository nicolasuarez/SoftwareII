/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VO;

import java.io.Serializable;

/**
 *
 * @author LUIS
 */
public class Administrador implements Serializable {

    private int id_adm;
    private String usuario;
    private String clave;
    private String nombre;
    private String apellido;

    public Administrador(int id_adm, String usuario, String clave, String nombre, String apellido) {
        this.id_adm = id_adm;
        this.usuario = usuario;
        this.clave = clave;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public Administrador() {
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getId_adm() {
        return id_adm;
    }

    public void setId_adm(int id_adm) {
        this.id_adm = id_adm;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
