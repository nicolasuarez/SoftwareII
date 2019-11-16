/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author LUIS
 */
public class Conexion {

    private  Connection CONEXION ;

    public Connection getCONEXION() {
        return CONEXION;
    }

    public void  getConnection() throws Exception {
       
            try {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                 
                this.CONEXION = DriverManager.getConnection("jdbc:mysql://localhost:3306/desercion", "root", "root");
               if(this.CONEXION!=null){
                   System.out.println("entro");
               }else{
                   System.out.println("no entro");
               }
                //Integracion Log4J
            } catch (Exception e) {
               
            }
            //Integracion Log4J
            //Integracion Log4J
    }
            

        
    
    

    public  void closeConnection() throws Exception {
        try {
            if (this.CONEXION != null) {
                if(this.CONEXION.isClosed()!=true)
                this.CONEXION.close();
              
            }

        } catch (Exception e) {
            //Integracion Log4J
            
        }

    }
}



