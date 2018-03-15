/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function agregarPuesto
() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/agregar.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("enviando datos al servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            limpiarFormulario();
            $("#id_puesto").focus();
            $("#id_puesto").select();
        },
        error: function (e) {
            $("#mensajes").html("No se puede modificar los datos.");
        },
        complete: function (objeto, exito, error) {
            $("#id_puesto").focus();
        }
    });
}

function buscarIdPuesto() {
    var datosFormulario = $("#formPrograma").serialize();
    //alert(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarId.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_puesto").val(json.id_puesto);
            $("#nombre_puesto").val(json.nombre_puesto);
            $("#id_area").val(json.id_area);
            $("#nombre_area").val(json.nombre_area);
            
            
            
            
      

            if (json.nuevo === "true") {
                $("#botonAgregar").prop('disabled', false);
                $("#botonModificar").prop('disabled', true);
                $("#botonEliminar").prop('disabled', true);
                siguienteCampo("#nombre_puesto", "botonModificar", true);
            } else {
                $("#botonAgregar").prop('disabled', true);
                $("#botonModificar").prop('disabled', false);
                $("#botonEliminar").prop('disabled', false);
                siguienteCampo("#nombre_puesto", "botonModificar", true);
            }
        },
        error: function (e) {
            $("#mensajes").html("No se puede recuperar los datos.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });

}

function buscarNombrePuesto() {
    var datosFormulario = $("#formBuscar").serialize();
   //alert("rr");
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarNombre.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al servidor ...");
            $("#contenidoBusqueda").css("display", "none");
        },
        success: function (json) {

            $("#mensajes").html(json.mensaje);
            $("#contenidoBusqueda").html(json.contenido);
            $("#contenidoBusqueda").fadeIn("slow");
            $("tbody tr").on("click", function () {
                var id = $(this).find("td:first").html();
                $("#panelBuscar").html("");
                $("#id_puesto").val(id);
                $("#nombre_puesto").focus();
                buscarIdPuesto();
                $("#buscar").fadeOut("slow");
                $("#panelDiseño").fadeIn("slow");
            });
        },
        error: function (e) {
            $("#mensajes").html("No se pudo buscar regisros.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }


    });
}


function modificarPuesto() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/modificar.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al servidor...");
        },
        sucess: function (json) {
            $("#mensajes").html(json.mensaje);
        },
        error: function (e) {
            $("#mensajes").html("No se puede modificar los datos error" + e.status);
        },
        complete: function (objeto, exito, error) {
            //limpiarCampos();
            $("#id_puesto").focus();
            $("#id_puesto").select();
            if (exito === "success") {

            }
        }
    });
}
function eliminarPuesto() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/eliminar.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al servidor...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
        },
        error: function (e) {
            $("#mensajes").html("No se pudo modificar los datos error" + e.status);
        },
        complete: function (objeto, exito, error) {
            // limpiarCampos();
            $("#id_puesto").focus();
            $("#id_puesto").select();
            if (exito === "success") {

            }
        }
    });
}
function limpiarCampos() {
    $("#id_puesto").val("0");
    $("#nombre_puesto").val("");
    $("#id_area").val("0");
    $("#nombre_area").val("");
    
    
}



function buscarIdArea() {
    var datosFormulario = $("#formPrograma").serialize();
    //alert(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdArea.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_area").val(json.id_area);
            $("#nombre_area").val(json.nombre_area);
            
            
      

            
        },
        error: function (e) {
            $("#mensajes").html("No se puede recuperar los datos.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });

}

function buscarNombreArea() {
    var datosFormulario = $("#formBuscar").serialize();
   //alert("rr");
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarNombreArea.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al servidor ...");
            $("#contenidoBusqueda").css("display", "none");
        },
        success: function (json) {

            $("#mensajes").html(json.mensaje);
            $("#contenidoBusqueda").html(json.contenido);
            $("#contenidoBusqueda").fadeIn("slow");
            $("tbody tr").on("click", function () {
                var id = $(this).find("td:first").html();
                $("#panelBuscar").html("");
                $("#id_area").val(id);
                $("#nombre_area").focus();
                buscarIdArea();
                $("#buscar").fadeOut("slow");
                $("#panelDiseño").fadeIn("slow");
            });
        },
        error: function (e) {
            $("#mensajes").html("No se pudo buscar regisros.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }


    });
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


function validarFormularioPuesto() {

    var valor = true;
    var nom = $("#nombre_puesto").val();
    var are = $("#nombre_area").val();

    if (nom === "") {
        valor = false;
        $("#nombre_puesto").val("");
        $("#nombre_puesto").focus();
        $("#mensajes").html("El puesto no puede estar vacio.");

    } else {
        if (are === "") {
            valor = false;

            $("#nombre_area").val("");
            $("#nombre_area").focus();
            $("#mensajes").html("El Area no debe estar vacia.");
        }
    }
    return valor;
}