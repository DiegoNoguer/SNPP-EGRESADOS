/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function buscarIdUsuarioClave() {
    var datosFormulario = $("#formPrograma").serialize();
    //alert(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdClave.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {

            $("#mensajes").html("Enviando datos al Servidor ...");

        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_usuario").val(json.id_usuario);
            $("#login_usuario").val(json.login_usuario);
            $("#claveactual_usuario").focus();
        },
        error: function (e) {
            $("#mensajes").html("No se pudo recuperar los datos.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {

            }
        }
    });
}

function modificarClave() {
    var datosFormulario = $("#formPrograma").serialize();
    //alert(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/modificarClave.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("enviado al servidor...");

        },
        succeess: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#nombre_usuario").focus();
        },
        error: function (e) {
            $("#mensajes").html("No se pudo modificar los datos.");
        },
        complete: function (objeto, exito, error) {
            limpiarFormulario();
            $("#nombre_usuario").focus();
        }
    });
}

function validarFormulario() {
    var valor = true;
    if ($("#claveactual_usuario").val().trim() === "") {
        valor = false;
        $("#mensajes").html("El campo Clave Actual no puede estar vacio.");
        $("#claveactual_usuario").focus();
    } else if ($("#clavenueva_usuario").val().trim() === "") {
        valor = false;
        $("#mensajes").html("El campo Clave nueva no puede estar vacio.");
        $("#clavenueva_usuario").focus();
    }
    return valor;
}
function limpiarFormulario() {
    $("#id_usuario").focus;
    $("#claveactual_usuario").val("");
    $("#claveactual_usuario").focus;
    $("#clavenueva_usuario").val("");
}


function checkIt(evt) {
    evt = (evt) ? evt : window.event;
    var charCode = (evt.which) ? evt.which : evt.keyCode;
    if (charCode > 31 && (charCode < 48 || charCode > 57)) {
        status = "This field accepts numbers only.";
             $("#mensajes").html("ingrese solo numeros.");
        return false;
    }
    status = "";
    return true;

}