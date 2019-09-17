/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VO;

import java.io.Serializable;
import javafx.collections.ObservableList;

/**
 *
 * @author LUIS
 */
public class Caja implements Serializable {

    private int id_caja;
    private double Dinero;
    private int id_sucursal;

    public Caja(double Dinero, int id_sucursal) {
        this.Dinero = Dinero;
        this.id_sucursal = id_sucursal;
    }

    public int getId_sucursal() {
        return id_sucursal;
    }

    public void setId_sucursal(int id_sucursal) {
        this.id_sucursal = id_sucursal;
    }

    public Caja() {
    }

    public Caja(int id_caja, double Dinero) {
        this.id_caja = id_caja;
        this.Dinero = Dinero;
    }

    public int getId_caja() {
        return id_caja;
    }

    public void setId_caja(int id_caja) {
        this.id_caja = id_caja;
    }

    public double getDinero() {
        return Dinero;
    }

    public void setDinero(double Dinero) {
        this.Dinero = Dinero;
    }

    public double calP(Double p, int c) {
        double precio = p * c;
        return precio;
    }

    public double calS(ObservableList<Producto> inven) {
        double sub = 0.0;
        for (int i = 0; i < inven.size(); i++) {
            sub = sub + inven.get(i).getPrecio();
        }
        return sub;
    }

    public double calI(double sub) {
        double iva = (sub * 0.18);
        return iva;
    }

    public double calT(double sub, double iva) {
        double total = sub + iva;
        return total;
    }

    public double calC(double P, double t) {
        double cambio = P - t;
        return P;
    }

    public double Agd(double d, double d1) {
        double dolares = d + d1;
        return dolares;
    }
}
