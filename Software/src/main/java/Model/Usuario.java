/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;
/**
 *
 * @author Luis
 */
public class Usuario {

    private String tipo;
    private String nombre;
    private String apellido;
    private int id;
    private String usuario;
    private String contraseña;
    private String Sexo;
    private String correo;
    private Date fechaN;

    private double porcentaje;


    public Usuario(String nombre, String apellido, int id, String usuario, String contraseña, String Sexo, String correo, Date fechaN) {

        this.nombre = nombre;
        this.apellido = apellido;
        this.id = id;
        this.usuario = usuario;
        this.contraseña = contraseña;
        this.Sexo = Sexo;
        this.correo = correo;
        this.fechaN = fechaN;
    }

    public Usuario() {
    }

    public Date getFechaN() {
        return fechaN;
    }

    public void setFechaN(Date fechaN) {
        this.fechaN = fechaN;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getSexo() {
        return Sexo;
    }

    public void setSexo(String Sexo) {
        this.Sexo = Sexo;
    }

    public double getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(double porcentaje) {
        this.porcentaje = porcentaje;
    }


}
