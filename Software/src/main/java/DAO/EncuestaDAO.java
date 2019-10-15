package DAO;


import java.util.ArrayList;
import Model.Encuesta;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author MACC2
 */
public class EncuestaDAO {
    ArrayList<Encuesta> lista = new ArrayList<>();
    
    
    public void add (Encuesta en){
        lista.add(en);
    }
    
    public Encuesta serach (int id){        
        for (int i = 0; i < lista.size(); i++) {            
            if (lista.get(i).getId()==id){
                return lista.get(i);
            }
        }
        return null;
    }
    
    
    
}
