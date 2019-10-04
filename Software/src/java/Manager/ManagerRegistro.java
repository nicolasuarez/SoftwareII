/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Manager;

import com.google.gson.Gson;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import DAO.UsuarioDao;
import Model.Usuario;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author User
 */
public class ManagerRegistro {

    UsuarioDao UsuariDAo = new UsuarioDao();

    public String Hacer(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String attr = request.getParameter("attr");
        String Res = "";
        if (attr.equals("User")) {
            Res = ValidarUser(request, response);
        } else {
            Res = CrearUser(request, response);
        }

        return null;

    }

    public String ValidarUser(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        String val = request.getParameter("valor");
        Usuario a = UsuariDAo.find(val);
        boolean res = (a != null);
        Map<String, Boolean> data = new HashMap<>();
        if (res) {
            data.put("User", false);
        } else {
            data.put("Email", true);
        }
        String json = new Gson().toJson(data);
        return json;

    }

    public String CrearUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String txtValop = request.getParameter("txtValOpe");
        response.setContentType("text/html");
        PrintWriter escritor = response.getWriter();
        String nombre = request.getParameter("exampleFirstName");
        System.out.println(txtValop);
        System.out.println(nombre + "-----------");
        String apellido = request.getParameter("exampleLastName");
        System.out.println(apellido);
        int id = Integer.parseInt(request.getParameter("exampleInputId"));
        String usuario = request.getParameter("exampleInputUser");
        String Correo = request.getParameter("exampleInputEmail");
        String contraseña = request.getParameter("exampleInputPassword");
        String dia = request.getParameter("dia");
        String mes = request.getParameter("mes");
        String año = request.getParameter("año");
        String sexo = request.getParameter("sexo");

        String f = dia + "/" + mes + "/" + año;
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        Date date = formatter.parse(f);
        System.out.println(date);
        System.out.println(formatter.format(date));

        Usuario x = new Usuario();

        x.setId(id);
        x.setUsuario(usuario);
        x.setContraseña(contraseña);
        x.setNombre(nombre);
        x.setApellido(apellido);
        x.setSexo(sexo);

        return null;

    }
}
