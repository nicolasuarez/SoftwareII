package Controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import entidades.*;
import controladores.*;

public class Register extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("yololooooo");
        request.getRequestDispatcher("register.jsp").forward(request, response);
        doPost(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String txtValop = request.getParameter("txtValOpe");
            response.setContentType("text/html");
            PrintWriter escritor = response.getWriter();
            String nombre = request.getParameter("exampleFirstName");
            System.out.println(txtValop);
            System.out.println(nombre + "-----------");
            String apellido = request.getParameter("exampleLastName");
            System.out.println(apellido);
            int id = Integer.parseInt(request.getParameter("exampleInputId"));
            boolean valido = true;
            String usuario = request.getParameter("exampleInputUser");
            String Correo = request.getParameter("exampleInputEmail");
            String contra = request.getParameter("exampleInputPassword");
            String dia = request.getParameter("dia");
            String mes = request.getParameter("mes");
            String año = request.getParameter("año");
            String sexo = request.getParameter("sexo");
            
            System.out.println(contra);
            System.out.println("asdasdasdasdasdasdasdsaad");
            String f = dia + "/" + mes + "/" + año;
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MMMM/yyyy");
            System.out.println(f);
            Date date = null;
            try {
                date = formatter.parse(f);
                System.out.println(date);
                System.out.println("----");
                System.out.println(formatter.format(date));
                
            } catch (ParseException ex) {
                Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
            }
            Usuarios x = new Usuarios(id, usuario, contra, nombre, apellido, "Estudiante", "M", Correo);
          
            UsuariosJpaController em = new UsuariosJpaController();
            em.create(x);
            
        } catch (Exception ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
