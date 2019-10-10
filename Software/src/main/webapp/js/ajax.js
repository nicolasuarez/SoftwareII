/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function () {
    //Agregar intereses a la lista
    $(".add").click(function () {
        var botonUsado = $(this).attr("lista");
        var opcion = $("#select" + botonUsado).val();
        if (opcion !== null) {
            var id = $("[value='" + opcion + "']").attr("id");
            console.log(id);
            var Contenido;
            Contenido = $("#" + opcion).text();
            $("#" + id).hide();
            $("#Default" + botonUsado).show();
            $("#select" + botonUsado).val("null");
            var item = $("#" + botonUsado + " li").toArray().length;
            $("#" + botonUsado).append("<li id='" + item + id + "' sel= '" + id + "' item='" + opcion + "'>" + Contenido + "</li>");
        }
    });
});
var removerLista = function () {
    var item = $(this).attr("sel");
    $("#" + item).show();
    $(this).remove();
};
$(document).on("click", "#Listas li", removerLista);
//Verificar El Email
$(document).on("change", "#Email", function () {
    $("#Email").blur(function () {
        var valor = $(this).val();
        var attr = "Email";
        var Valido = false;
        $.ajax({
            url: "ServletRegistroEstudiante",
            type: 'GET',
            dataType: "json",
            data: {
                valor: valor,
                attr: attr
            },
            success: function (a) {
                Valido = a.Email;
                if (!Valido) {
                    $("#Email").val(null);
                    $("#Email").attr("placeholder", "Email no valido");
                }
            },
            error: function () {
                console.log("No se ha podido obtener la información");
            }
        });
    });
});
var validar = function (campo) {
    return !(campo == "" || campo == null);
};
$(document).on("click", "#contact-submit", function () {
    var valido = true;
    $(".input100").each(function () {
        var esto = $(this).val();
        console.log(esto);
        if (!($(this).attr("id") === "selectIntereses" || $(this).attr("id") === "selectTransporte")) {
            valido = validar(esto);
        }
    });
    
    var nombre = $("#Nombre").val();
    var edad = $("#Edad").val();
    var correo = $("#Email").val();
    var passw = $("#Password").val();
    var presupuesto = $("#Presupuesto").val();
    var universidad = $("#Universidad").val();
    var identificacion = $("#Identificacion").val();

    var Intereses = "";
    var Transporte = "";

    $("#Intereses  li").each(function () {
        if ($(this).attr("item") == "CentrosComerciales") {
            Intereses += "Centros Comerciales,"
        } else {
            Intereses += $(this).attr("item") + ",";
        }
    });
    $("#Transporte  li").each(function () {
        Transporte += $(this).attr("item") + ",";
    });
    console.log(Transporte);
    valido = (validar(Intereses) && validar(Transporte));
    console.log(Intereses);
    console.log(Transporte);
    if (valido) {
        $.ajax({
            url: "ServletRegistroEstudiante",
            type: 'POST',
            dataType: "json",
            data: {
                attr: "Datos",
                nombre: nombre,
                edad: Number.parseInt(edad),
                correo: correo,
                identificacion: identificacion,
                passw: passw,
                presupuesto: presupuesto,
                universidad: universidad,
                Transporte: Transporte,
                Gustos: Intereses
            },
            success: function (a) {
                console.log(a);
                location.href = "./index.jsp";
            },
            error: function () {
                console.log("No se ha podido obtener la información");
            }
        });
    } else {
        alert("Datos No Validos");
    }
});