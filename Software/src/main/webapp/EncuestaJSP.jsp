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
                    var uservar = $('#usuario').val();
                    var semestrevar = $('#semestre').val();
                    var combo2 = document.getElementById("habitos")
                    var habitosvar = combo2.options[combo2.selectedIndex].text;
                    var edadvar = $('edad').val();
                    var combo = document.getElementById("trabaja");
                    var trabajavar = combo.options[combo.selectedIndex].text;
                    var hobbiesvar = $('hobbies').val();
                    var combos = document.getElementById("status");
                    var statusvar = combos.options[combos.selectedIndex].text;
                    var combo3 = document.getElementById("nucleo");
                    var nucleovar = combo3.options[combo3.selectedIndex].text;
                    var origenvar = $('origen').val();
                    var combo4 = document.getElementById("enfermedad");
                    var enfermedadvar = combo4.options[combo4.selectedIndex].text;
                    var gustovar = $('gusto').val();
                    var carreravar = $('carrera').val();
                    $.post('EncuestaServlet', {
                        usuario: uservar,
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
        <p> Usuario <input  id="usuario" type="text"> </p>
        <p> Semestre: <input  id="semestre" type="text"> </p>
        <p> Habitos de estudio: <select id="habitos">
                <option value="antelacion">Estudia con antelacion</option>
                <option value="estudiatar">Estudia poco antes de las pruebas</option>
                <option value="noestudia">No estudia o no lo sufuciente</option>                
            </select></p>
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
        <p> Nucleo Familiar: <select id="nucleo"> 
                <option value="padremadre">Padre y Madre</option>
                <option value="padre">Padre</option>
                <option value="madre">Madre</option>
                <option value="familiar">Familiar(es)</option>
                <option value="unipersonal">Unipersonal</option>
            </select> </p>
        <p> Ciudad de origen: <input  id="origen" type="text"> </p>
        <p> Enfermedad: <select id="enfermedad">
                <option value="si">Si</option>
                <option value="no">No</option>  
            </select> </p>
        <p> Gusto: <input  id="gusto" type="text"> </p>
        <p> Carrera: <input  id="carrera" type="text"> </p>
        <p> <input type="button" id="enviar" >Enviar</button></p>
</body>
</html>
