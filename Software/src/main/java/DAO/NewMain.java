/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import controladores.*;
import entidades.*;
import java.util.Scanner;

/**
 *
 * @author User
 */
public class NewMain {

    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception {

        UsuariosJpaController em = new UsuariosJpaController();
        em.findUsuarios(1);
        Scanner sc=new Scanner(System.in);
        System.out.println("------------");
        String a=sc.nextLine();
        Usuarios findUsuariosU;
        findUsuariosU = em.findUsuariosU(a);

    }
}
