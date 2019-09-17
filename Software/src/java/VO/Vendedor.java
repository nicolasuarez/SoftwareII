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
public class Vendedor implements Serializable {

    private String nombre;
    private String apellido;
    private Integer id_vendedor;
    private String usuario;
    private String clave;
    private int productos_vend;
    private Double porcentaje;

    public Double getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(Double porcentaje) {
        this.porcentaje = porcentaje;
    }

    public Vendedor(String nombre, String apellido, Integer id_vendedor, String usuario, String clave, int productos_vend) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.id_vendedor = id_vendedor;
        this.usuario = usuario;
        this.clave = clave;
        this.productos_vend = productos_vend;
    }

    public Integer getId_vendedor() {
        return id_vendedor;
    }

    public void setId_vendedor(Integer id_vendedor) {
        this.id_vendedor = id_vendedor;
    }

    public Vendedor() {
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

    public int getProductos_vend() {
        return productos_vend;
    }

    public void setProductos_vend(int productos_vend) {
        this.productos_vend = productos_vend;
    }

}
