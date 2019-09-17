<%-- 
    Document   : Encuesta
    Created on : 16-sep-2019, 15:18:32
    Author     : MACC2
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript" src="js/jquery-1.9.1.js"></script>
        <script type="text/javascript" src="js/jquery.ui.core.js"></script>
        <script type="text/javascript" src="js/jquery.ui.widget.js"></script>
        <script type="text/javascript" src="js/jquery.ui.button.js"></script>
        <script type="text/javascript" src="js/jquery.ui.dialog.js"></script>
        <script type="text/javascript" src="js/jquery.ui.tabs.js"></script>
        <script type="text/javascript" src="js/jquery.dataTables.js"></script>
        <script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
        <link href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css" rel="stylesheet">
        <script >
            function procesarOperacion(valop) {
                var datos;
                if(valop == 'go'){
                    console.log();
                    datos = "valop=" + valop + "&ide=" + $("#ide").val() + "&genero=" + $("#genero").val()
                            + "semestre=" + $("#semestre").val() + "habitos=" + $("#habitos").val();
                    $.ajax({
                        url: "EncuestaServlet",
                        type: 'POST',
                        data: datos,
                        succes: function (data) {
                            console.log(data);
                        }
                    });
                }
            }
        </script>
        <title>Encuesta</title>
    </head>
    <body>
        <p> Id: <input  id="ide" type="text"> </p>
        <p> Genero: <input  id="genero" type="text"> </p>
        <p> Semestre: <input  id="semestre" type="text"> </p>
        <p> Habitos de estudio: <input  id="habitos" type="text"> </p>
        <p> Edad: <input  id="edad" type="text"> </p>
        <p> Trabaja: <select id="trabaja">
                <option value="si">Si</option>
                <option value="no">No</option>                
            </select> </p>
        <p> Hobbies: <input  id="hobbies" type="text"> </p>
        <p> Status: <select id="status">
                <option value="si">Al dia</option>
                <option value="no">Atrasado</option>                
            </select> </p>
        <p> Nucleo Familiar: <input  id="nucleo" type="text"> </p>
        <p> Ciudad de origen: <input  id="origen" type="text"> </p>
        <p> Enfermedad: <input  id="enfermedad" type="text"> </p>
        <p> Gusto: <input  id="gusto" type="text"> </p>
        <p> Carrera: <input  id="carrera" type="text"> </p>
        <p> <button id="enviar" onclick="procesarOperacion('go')">Enviar</button></p>
    </body>
</html>
