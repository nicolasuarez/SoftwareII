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
import DAO.UsuarioDao;
import Model.Usuario;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;


public class Register extends HttpServlet {

    UsuarioDao user = new UsuarioDao();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("yololooooo");
        request.getRequestDispatcher("register.jsp").forward(request, response);
        doPost(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

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
        String contraseña = "";
        try {
            contraseña = sha256(contra);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            user.find(usuario);
        } catch (SQLException ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
        }
        Usuario x = new Usuario();
        x.setId(id);
        x.setUsuario(usuario);
        x.setContraseña(contraseña);
        x.setNombre(nombre);
        x.setApellido(apellido);
        x.setSexo("oter");
        x.setCorreo(Correo);

        try {
            user.insert(x);
            System.out.println("-----------");
        } catch (SQLException ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
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

 static String sha256(String input) throws NoSuchAlgorithmException {
        MessageDigest mDigest = MessageDigest.getInstance("SHA256");
        byte[] result = mDigest.digest(input.getBytes());
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < result.length; i++) {
            sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
        }

        return sb.toString();
    }
}
