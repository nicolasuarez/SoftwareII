package Controllers;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import DAO.EncuestaDAO;
import Model.Encuesta; 
import DAO.UsuarioDao;
import Model.Usuario;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author MACC2
 */
public class EncuestaServlet extends HttpServlet {

    EncuestaDAO encuesta;
    UsuarioDao user;

    public void init() throws ServletException {
        this.encuesta = new EncuestaDAO();
        this.user = new UsuarioDao();
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            //String valop = request.getParameter("valop");
            String usuario = request.getParameter("usuario");
            //Usuario auxen = user.find(usuario);
            Usuario  auxen= new Usuario("maria", "pena", 12341, "mari4a", "124124", "mujer", "mari4ana@use.es", new Date(12));
            int id = auxen.getId();
            String genero = auxen.getSexo();
            int Semestre = Integer.parseInt(request.getParameter("semestre"));
            String habitos_De_Estudio = request.getParameter("habitos");            
            String Edad = request.getParameter("edad");
            boolean trabaja;
            if (request.getParameter("trabaja").compareTo("Si") == 0) {
                trabaja = true;
            } else {
                trabaja = false;
            }
            String hobbies = request.getParameter("hobbies");
            boolean statusCarrera;
            if (request.getParameter("status").compareTo("Al dia") == 0) {
                statusCarrera = true;
            } else {
                statusCarrera = false;
            }
            String nucleoFamiliar = request.getParameter("nucleo");
            String lugarDeOrigen = request.getParameter("origen");
            String Enfermedad = request.getParameter("enfermedad");
            String gusto = request.getParameter("gusto");
            String Carrera = request.getParameter("carrera");
            
            if ( genero != null && Semestre != 0 && habitos_De_Estudio != null) {
                Encuesta aux = new Encuesta(id, genero, Semestre, habitos_De_Estudio, Edad,
                        trabaja, hobbies, statusCarrera, nucleoFamiliar, lugarDeOrigen, Enfermedad,
                        gusto, Carrera);
                encuesta.add(aux);
            } else {
                response.sendRedirect("EncuestaJSP.jsp?error=IngreseDatos");
            }
        } catch (Exception ex) {
            Logger.getLogger(EncuestaServlet.class.getName()).log(Level.SEVERE, null, ex);
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
