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


        <script src="http://code.jquery.com/jquery-latest.js"></script>
        <script>
            $(document).ready(function () {
                $('#enviar').click(function (event) {
                    var idvar = $('#ide').val();
                    var generovar = $('#genero').val();
                    var semestrevar = $('#semestre').val();
                    var habitosvar = $('#habitos').val();
                    var edadvar = $('edad').val();
                    var combo = document.getElementById("trabaja");                    
                    var trabajavar = combo.options[combo.selectedIndex].text;
                    var hobbiesvar = $('hobbies').val();
                    var combos = document.getElementById("status"); 
                    var statusvar = combos.options[combos.selectedIndex].text;
                    var nucleovar = $('nucleo').val();
                    var origenvar = $('origen').val();
                    var enfermedadvar = $('enfermedad').val();
                    var gustovar = $('gusto').val();
                    var carreravar = $('carrera').val();
                    $.post('EncuestaServlet', {
                        ide: idvar,
                        genero: generovar,
                        semestre: semestrevar,
                        habitos: habitosvar,
                        edad: edadvar,
                        trabaja: trabajavar,
                        hobbies: hobbiesvar,
                        status: statusvar,
                        nucleo: nucleovar,
                        origen: origenvar,
                        enfermedad: enfermedadvar,
                        gusto: gustovar,
                        carrera: carreravar
                    });
                });
            });
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
        <p> <input type="button" id="enviar" ></button></p>
</body>
</html>
