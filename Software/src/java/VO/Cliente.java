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
public class Cliente implements Serializable{
    private String nombre;
    private int Id;
    private Double dinero;

    public Cliente(String nombre,  int Id, Double dinero) {
        this.nombre = nombre;
       
        this.Id = Id;
        this.dinero = dinero;
    }

    public Cliente() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public Double getDinero() {
        return dinero;
    }

    public void setDinero(Double dinero) {
        this.dinero = dinero;
    }
    
}
