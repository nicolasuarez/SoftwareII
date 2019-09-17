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
public class Item_vent {
    private int id_item;
    private int cantidad;
    private double valor;
    private Producto producto;
    private Venta venta;

    public Item_vent(int id_item, int cantidad, double valor, Producto producto, Venta venta) {
        this.id_item = id_item;
        this.cantidad = cantidad;
        this.valor = valor;
        this.producto = producto;
        this.venta = venta;
    }

    public Item_vent() {
    }

    public int getId_item() {
        return id_item;
    }

    public void setId_item(int id_item) {
        this.id_item = id_item;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }
    
    
    
    
}
