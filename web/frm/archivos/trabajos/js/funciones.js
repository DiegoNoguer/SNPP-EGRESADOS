/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function agregarTrabajo
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
            $("#botonAgregar").prop('disabled', true);
            $("#detalle").prop('hidden', false);
            $("#id_trabajo").val(json.id_trabajo);

            buscarIdTrabajo();
            $("#id_trabajo").focus;
            $("#id_trabajo").select();
        },
        error: function (e) {
            $("#mensajes").html("No se puede modificar los datos.");
        },
        complete: function (objeto, exito, error) {
            $("#id_trabajo").focus();
        }
    });
}

function buscarIdTrabajo() {
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
            $("#id_trabajo").val(json.id_trabajo);
            $("#nombre_trabajo").val(json.nombre_trabajo);
            $("#id_egresado").val(json.id_egresado);
            $("#nombre_egresado").val(json.nombre_egresado);
            $("#apellido_egresado").val(json.apellido_egresado);
            $("#cedula_egresado").val(json.cedula_egresado);
            $("#telefono_egresado").val(json.telefono_egresado);
            $("#edad_egresado").val(json.edad_egresado);
            $("#nombre_ciudad").val(json.nombre_ciudad);
            $("#contenidoDetalle").html(json.contenido_detalle);

            if (json.nuevo === "true") {
                $("#botonAgregar").prop('disabled', false);
                $("#botonModificar").prop('disabled', true);
                $("#botonEliminar").prop('disabled', true);
                $("#botonBuscarIdEgresado").prop('disabled', false);
                $("#id_egresado").prop('disabled', false);
                siguienteCampo("#nombre_trabajo", "botonModificar", true);
                $("#detalle").prop('hidden', true);
            } else {
                $("#botonAgregar").prop('disabled', true);
                $("#botonModificar").prop('disabled', false);
                $("#botonEliminar").prop('disabled', false);
                $("#botonBuscarIdEgresado").prop('disabled', true);
                $("#id_egresado").prop('disabled', true);
                siguienteCampo("#nombre_trabajo", "botonModificar", true);
                $("#detalle").prop('hidden', false);
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

function buscarNombreTrabajo() {
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
                $("#id_trabajo").val(id);
                $("#cedula_egresado").focus();
                buscarIdTrabajo();
                $("#buscar").fadeOut("slow");
                $("#panelPrograma").fadeIn("slow");
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


function modificarTrabajo() {
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
            $("#id_trabajo").focus();
            $("#id_trabajo").select();
            if (exito === "success") {

            }
        }
    });
}
function eliminarTrabajo() {
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
            $("#id_trabajo").focus();
            $("#id_trabajo").select();
            if (exito === "success") {

            }
        }
    });
}
function limpiarCampos() {
    $("#id_trabajo").val("0");
    $("#nombre_trabajo").val("");
    $("#id_egresado").val("0");
    $("#nombre_egresado").val("");
    $("#apellido_egresado").val("");
    $("#cedula_egresado").val("0");
    $("#telefono_egresado").val("");
    $("#edad_egresado").val("0");
    $("#nombre_ciudad").val("0");
    $("#id_trabajo").focus();
}

function buscarBorraridegresado2() {
    var datosFormulario = $("#formPrograma").serialize();
    //alert(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarBorraridegresado2.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
         
            $("#id_egresado").val(json.id_egresado);
            
            console.log(json.id_egresado);
            if (json.nuevo === "false"){
                //alert(datosFormulario);
                
                 limpiarCampos();
               
            }else {
                 buscarIdEgresado();
            }
            
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



//EGRESADOS BUSCAR NOMBRE Y BUSCAR ID

function buscarIdEgresado() {
    var datosFormulario = $("#formPrograma").serialize();
    //alert(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdEgresado.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_egresado").val(json.id_egresado);
            $("#nombre_egresado").val(json.nombre_egresado);
            $("#apellido_egresado").val(json.apellido_egresado);
            $("#cedula_egresado").val(json.cedula_egresado);
            $("#telefono_egresado").val(json.telefono_egresado);
           $("#edad_egresado").val(json.edad_egresado);
           $("#nombre_ciudad").val(json.nombre_ciudad);






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

function buscarNombreEgresado() {
    var datosFormulario = $("#formBuscar").serialize();
    //alert("rr");
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarNombreEgresado.jsp',
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
                $("#id_egresado").val(id);
                $("#cedula_egresado").focus();
                buscarBorraridegresado2();
                $("#buscar").fadeOut("slow");
                $("#panelPrograma").fadeIn("slow");
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

function validarFormulario() {
    var valor = true;
    if ($("#nombre_trabajo").val().length < 3) {
        valor = false;
        $("#mensajes").html("Nombre Trabajo no puede estar vacio.");
        $("#nombre_trabajo").focus();
    }

    if ($("#nombre_egresado").val().length < 2) {
        valor = false;
        $("#mensajes").html("Egresado no puede estar vacio.");
        $("#id_egresado").focus();
    }

    if ($("#nombre_tipotrabajo").val().length < 2) {
        valor = false;
        $("#mensajes").html("Tipo Trabajo no puede estar vacio.");
        $("#id_tipotrabajo").focus();
    }
    return valor;
}
function limpiarFormulario() {
    $("#id_trabajo").val("0");
    $("#nombre_egresado").val("");
    $("#id_egresado").val("0");
    $("#apellido_egresado").val("0");
    $("#cedula_egresado").val("0");
    $("#telefono_egresado").val("0");
    $("#telefono_egresado").val("0");
    $("#edad_trabajo").val("0");
    $("#fechafin_trabajo").val("0");



}
function agregarLinea() {
    $("#id_detalletrabajo").val("0");
    $("#id_empresa").val("0");
    $("#nombre_empresa").val("");
    $("#direccion_empresa").val("");
    $("#telefono_empresa").val("");
    $("#ruc_empresa").val(""); 
    $("#fechainicio_detalletrabajo").val("0"); 
    $("#fechafin_detalletrabajo").val("0"); 
    $("#panelLinea").fadeIn("slow");
    $("#panelPrograma").fadeOut("slow");
    $("#id_empresa").focus();
    $("#id_empresa").select();
    $("#botonAgregarLinea").prop('disabled', false);
    $("#botonModificarLinea").prop('disabled', true);
    $("#botonEliminarLinea").prop('disabled', true);
    siguienteCampo("#horas_detalletrabajo", "#botonAgregarLinea", true);
}
function editarLinea(id) {
    $("#id_detalletrabajo").val(id);
  $("#id_empresa").val("0");
    $("#nombre_empresa").val("");
    $("#direccion_empresa").val("");
    $("#telefono_empresa").val("");
    $("#ruc_empresa").val("");
    $("#fechainicio_detalletrabajo").val("0"); 
    $("#fechafin_detalletrabajo").val("0"); 
    $("#panelLinea").fadeIn("slow");
    $("#panelPrograma").fadeOut("slow");
    $("#id_empresa").focus();
    $("#id_empresa").select();
    $("#botonAgregarLinea").prop('disabled', true);
    $("#botonModificarLinea").prop('disabled', false);
    $("#botonEliminarLinea").prop('disabled', false);
    buscarIdTrabajoDetalle();
    siguienteCampo("#cantidad_empresatrabajo", "#botonModificarLinea", true);
}
// trabajosempresas
function buscarIdTrabajoDetalle() {
    var datosFormulario = $("#formLinea").serialize();
  // alert(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdTrabajoDetalle.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_empresa").val(json.id_empresa);
            $("#nombre_empresa").val(json.nombre_empresa);
            $("#direccion_empresa").val(json.direccion_empresa);
            $("#telefono_empresa").val(json.telefono_empresa);
            $("#ruc_empresa").val(json.ruc_empresa);
            $("#fechainicio_detalletrabajo").val(json.fechainicio_detalletrabajo);
            $("#fechafin_detalletrabajo").val(json.fechafin_detalletrabajo);
        
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
function agregarTrabajoDetalle() {
    var datosFormulario = $("#formLinea").serialize();
    var id_trabajo = $("#id_trabajo").val();
    datosFormulario += "&id_trabajo=" + id_trabajo;

    //alert(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/agregarTrabajoDetalle.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#panelLinea").fadeOut("slow");
            $("#panelPrograma").fadeIn("slow");
            buscarIdTrabajo();
        },
        error: function (e) {
            $("#mensajes").html("No se pudo agregar los datos.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}
function modificarTrabajoDetalle() {
    var datosFormulario = $("#formLinea").serialize();
    var id_trabajo = $("#id_trabajo").val();
    datosFormulario += "&id_trabajo=" + id_trabajo;
    $.ajax({
        type: 'POST',
        url: 'jsp/modificarTrabajoDetalle.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#panelLinea").fadeOut("slow");
            $("#panelPrograma").fadeIn("slow");
            buscarIdTrabajo();
        },
        error: function (e) {
            $("#mensajes").html("No se pudo modificar los datos.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}
function eliminarTrabajoDetalle() {
    var datosFormulario = $("#formLinea").serialize();
    var id_trabajo = $("#id_trabajo").val();
    datosFormulario += "&id_trabajo=" + id_trabajo;
    $.ajax({
        type: 'POST',
        url: 'jsp/eliminarTrabajoDetalle.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json)
        {
            $("#mensajes").html(json.mensaje);
            $("#panelLinea").fadeOut("slow");
            $("#panelPrograma").fadeIn("slow");
            buscarIdTrabajo();

        },
        error: function (e) {
            $("#mensajes").html("No se pudo modificar los datos.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}
//// empresas
function buscarIdEmpresa() {
    var datosFormulario = $("#formLinea").serialize();
    //alert(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdEmpresa.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_empresa").val(json.id_empresa);
            $("#nombre_empresa").val(json.nombre_empresa);
            $("#direccion_empresa").val(json.direccion_empresa);
            $("#telefono_empresa").val(json.telefono_empresa);
            $("#ruc_empresa").val(json.ruc_empresa);
            



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
function buscarNombreEmpresa() {
    var datosFormulario = $("#formBuscar").serialize();
    // alert(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarNombreEmpresa.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
            $("#contenidoBusqueda").css("display", "none");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#contenidoBusqueda").html(json.contenido);
            $("#contenidoBusqueda").fadeIn("slow");
            $("tbody tr").on("click", function () {
                var id = $(this).find("td:first").html();
                $("#panelBuscar").html("");
                $("#id_empresa").val(id);
                $("#nombre_empresa").focus();
                buscarIdEmpresa();
                $("#buscar").fadeOut("slow");
                $("#panelLinea").fadeIn("slow");
            });
        },
        error: function (e) {
            $("#mensajes").html("No se pudo buscar trabajos.");
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




function validarFormularioTrabajo() {

    var valor = true;
    var nom = $("#nombre_egresado").val();
    //var log = $("#login_usuario").val();
    //var nom = $("#nombre_usuario").val();

    if (nom === "") {
        valor = false;
        $("#nombre_egresado").val("");
        $("#id_egresado").focus();
        $("#mensajes").html("El id del egresado no puede estar vacio.");
        }
        return valor;
        }
        
        
        
        
        function validarFormularioDeTrabajo() {

    var valor = true;
    var nom = $("#nombre_empresa").val();
    //var log = $("#login_usuario").val();
    //var nom = $("#nombre_usuario").val();

    if (nom === "") {
        valor = false;
        $("#nombre_empresa").val("");
        $("#id_empresa").focus();
        $("#mensajes").html("La Empresa no puede estar vacio.");
        }
        return valor;
        }