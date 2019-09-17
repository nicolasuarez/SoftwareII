package Controllers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;

public class Register extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("yololooooo");
        request.getRequestDispatcher("register.jsp").forward(request, response);
        doPost(request, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println(" asdasdasdasdasdasdasd");
        System.out.println(" asdasdasdasdasdasdasd");
        System.out.println(" asdasdasdasdasdasdasd");
        String txtValop = request.getParameter("txtValOpe");
        response.setContentType("text/html");
        PrintWriter escritor = response.getWriter();
        String nombre = request.getParameter("exampleFirstName");
        String apellido = request.getParameter("exampleLastName");
        String id = request.getParameter("exampleInputId");
        String usuario = request.getParameter("exampleInputUser");
        String Correo = request.getParameter("exampleInputEmail");
        String contraseña = request.getParameter("exampleInputPassword");

        if (txtValop != null) {
            if (txtValop.equalsIgnoreCase("GU")) {
                System.out.println(nombre);
            }
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
