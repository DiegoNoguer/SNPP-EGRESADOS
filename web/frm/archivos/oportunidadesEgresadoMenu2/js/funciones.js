/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function agregarOportunidad
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
            $("#id_oportunidad").val(json.id_oportunidad);

            buscarIdOportunidad();
            $("#id_oportunidad").focus;
            $("#id_oportunidad").select();
        },
        error: function (e) {
            $("#mensajes").html("No se puede modificar los datos.");
        },
        complete: function (objeto, exito, error) {
            $("#id_oportunidad").focus();
        }
    });
}

function buscarIdOportunidad() {
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
            $("#id_oportunidad").val(json.id_oportunidad);
            $("#nombre_oportunidad").val(json.nombre_oportunidad);
            $("#fecha_oportunidad").val(json.fecha_oportunidad);
            $("#id_empresa").val(json.id_empresa);
            $("#nombre_empresa").val(json.nombre_empresa);
            $("#email_empresa").val(json.email_empresa);
            $("#direccion_empresa").val(json.direccion_empresa);
            $("#telefono_empresa").val(json.telefono_empresa);
            $("#ruc_empresa").val(json.ruc_empresa);
            $("#nombre_ciudad").val(json.nombre_ciudad);
            $("#contenidoDetalle").html(json.contenido_detalle);

            if (json.nuevo === "true") {
                $("#botonAgregar").prop('disabled', true);
                $("#botonModificar").prop('disabled', true);
                $("#botonEliminar").prop('disabled', true);
                $("#botonBuscarIdEmpresa").prop('disabled', true);
                $("#id_empresa").prop('disabled', true);
                $("#botonplus").prop('disabled', true);
                
                siguienteCampo("#nombre_oportunidad", "botonModificar", true);
                $("#detalle").prop('hidden', true);
            } else {
                $("#botonAgregar").prop('disabled', true);
                $("#botonModificar").prop('disabled', true);
                $("#botonEliminar").prop('disabled', true);
                $("#botonBuscarIdEmpresa").prop('disabled', true);
                $("#id_empresa").prop('disabled', true);
                $("#botonplus").prop('disabled', true);
                siguienteCampo("#nombre_oportunidad", "botonModificar", true);
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

function buscarNombreOportunidad() {
    var datosFormulario = $("#formBuscar").serialize();
    //alert("rr");
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarNombreOpor.jsp',
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
                $("#id_oportunidad").val(id);
                $("#nombre_oportunidad").focus();
                buscarIdOportunidad();
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


function modificarOportunidad() {
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
            $("#id_oportunidad").focus();
            $("#id_oportunidad").select();
            if (exito === "success") {

            }
        }
    });
}
function eliminarOportunidad() {
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
            $("#id_oportunidad").focus();
            $("#id_oportunidad").select();
            if (exito === "success") {

            }
        }
    });
}
function limpiarCampos() {
    $("#id_oportunidad").val("0");
    $("#nombre_oportunidad").val("");
    $("#fecha_oportunidad").val(0);
    $("#id_empresa").val("0");
    $("#nombre_empresa").val("");
    $("#email_empresa").val("");
    $("#direccion_empresa").val("");
    $("#telefono_empresa").val("");
    $("#ruc_empresa").val("");
    $("#nombre_ciudad").val("");
    $("#id_oportunidad").focus();
}




//EGRESADOS BUSCAR NOMBRE Y BUSCAR ID

function buscarIdEmpresa() {
    var datosFormulario = $("#formPrograma").serialize();
    //alert(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdEmpresa.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_empresa").val(json.id_empresa);
            $("#nombre_empresa").val(json.nombre_empresa);
            $("#email_empresa").val(json.email_empresa);
            $("#direccion_empresa").val(json.direccion_empresa);
            $("#telefono_empresa").val(json.telefono_empresa);
            // $("#fechanaci_empresa").val(json.fechanaci_empresa);
            // $("#cedula_empresa").val(json.cedula_empresa);
            $("#ruc_empresa").val(json.ruc_empresa);
            //$("#nombre_departamento").val(json.nombre_departamento);
            // $("#id_ciudad").val(json.id_ciudad);
            $("#nombre_ciudad").val(json.nombre_ciudad);
            // $("#id_estadocivil").val(json.id_estadocivil);
            //$("#nombre_estadocivil").val(json.nombre_estadocivil);





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

function buscarNombreEmpresa() {
    var datosFormulario = $("#formBuscar").serialize();
    //alert("rr");
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarNombreEmpresa.jsp',
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
                $("#id_empresa").val(id);
                $("#nombre_empresa").focus();
                buscarIdEmpresa();
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
    if ($("#nombre_oportunidad").val().length < 3) {
        valor = false;
        $("#mensajes").html("Nombre Oportunidad no puede estar vacio.");
        $("#nombre_oportunidad").focus();
    }

    if ($("#nombre_empresa").val().length < 2) {
        valor = false;
        $("#mensajes").html("Empresa no puede estar vacio.");
        $("#id_empresa").focus();
    }

    if ($("#nombre_tipooportunidad").val().length < 2) {
        valor = false;
        $("#mensajes").html("Tipo Oportunidad no puede estar vacio.");
        $("#id_tipooportunidad").focus();
    }
    return valor;
}
function limpiarFormulario() {
    $("#id_oportunidad").val("0");
    $("#nombre_empresa").val("");
    $("#id_empresa").val("0");
    $("#fecha_oportunidad").val("0");



}
function agregarLinea() {
    $("#id_detalleoportunidad").val("0");
    $("#id_puesto").val("0");
    $("#nombre_puesto").val("");
    $("#nombre_area").val("");
    $("#vacancia_detalleoportunidad").val("");
    $("#horario_detalleoportunidad").val("");
    $("#rangoedad_detalleoportunidad").val("");
    $("#panelLinea").fadeIn("slow");
    $("#panelPrograma").fadeOut("slow");
    $("#id_puesto").focus();
    $("#id_puesto").select();
    $("#botonAgregarLinea").prop('disabled', false);
    $("#botonModificarLinea").prop('disabled', true);
    $("#botonEliminarLinea").prop('disabled', true);
    siguienteCampo("#horas_detalleoportunidad", "#botonAgregarLinea", true);
}
function editarLinea(id) {
    $("#id_detalleoportunidad").val(id);
    $("#id_puesto").val("0");
    $("#nombre_puesto").val("");
    $("#nombre_area").val("");
    $("#vacancia_detalleoportunidad").val("");
    $("#horario_detalleoportunidad").val("");
    $("#rangoedad_detalleoportunidad").val("");
    $("#panelLinea").fadeIn("slow");
    $("#panelPrograma").fadeOut("slow");
    $("#id_puesto").focus();
    $("#id_puesto").select();
    $("#botonAgregarLinea").prop('disabled', true);
    $("#botonModificarLinea").prop('disabled', false);
    $("#botonEliminarLinea").prop('disabled', false);
    buscarIdOportunidadDetalle();
    siguienteCampo("#cantidad_puestooportunidad", "#botonModificarLinea", true);
}
// oportunidadespuestos
function buscarIdOportunidadDetalle() {
    var datosFormulario = $("#formLinea").serialize();
    //alert(datosFormulario);
    //alert(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdOportunidadDetalle.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_puesto").val(json.id_puesto);
            $("#nombre_puesto").val(json.nombre_puesto);
            $("#nombre_area").val(json.nombre_area);
            $("#vacancia_detalleoportunidad").val(json.vacancia_detalleoportunidad);
            $("#horario_detalleoportunidad").val(json.horario_detalleoportunidad);
            $("#rangoedad_detalleoportunidad").val(json.rangoedad_detalleoportunidad);
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
function agregarOportunidadDetalle() {
    var datosFormulario = $("#formLinea").serialize();
    var id_oportunidad = $("#id_oportunidad").val();
    datosFormulario += "&id_oportunidad=" + id_oportunidad;

    
    $.ajax({
        type: 'POST',
        url: 'jsp/agregarOportunidadDetalle.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#panelLinea").fadeOut("slow");
            $("#panelPrograma").fadeIn("slow");
            buscarIdOportunidad();
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
function modificarOportunidadDetalle() {
    var datosFormulario = $("#formLinea").serialize();
    var id_oportunidad = $("#id_oportunidad").val();
    datosFormulario += "&id_oportunidad=" + id_oportunidad;
    $.ajax({
        type: 'POST',
        url: 'jsp/modificarOportunidadDetalle.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#panelLinea").fadeOut("slow");
            $("#panelPrograma").fadeIn("slow");
            buscarIdOportunidad();
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
function eliminarOportunidadDetalle() {
    var datosFormulario = $("#formLinea").serialize();
    var id_oportunidad = $("#id_oportunidad").val();
    datosFormulario += "&id_oportunidad=" + id_oportunidad;
    $.ajax({
        type: 'POST',
        url: 'jsp/eliminarOportunidadDetalle.jsp',
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
            buscarIdOportunidad();

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
//// puestos
function buscarIdPuesto() {
    var datosFormulario = $("#formLinea").serialize();
    //alert(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdPuesto.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_puesto").val(json.id_puesto);
            $("#nombre_puesto").val(json.nombre_puesto);
            $("#nombre_area").val(json.nombre_area);



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
function buscarNombrePuesto() {
    var datosFormulario = $("#formBuscar").serialize();
    // alert(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarNombrePuesto.jsp',
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
                $("#id_puesto").val(id);
                $("#nombre_puesto").focus();
                buscarIdPuesto();
                $("#buscar").fadeOut("slow");
                $("#panelLinea").fadeIn("slow");
            });
        },
        error: function (e) {
            $("#mensajes").html("No se pudo buscar oportunidades.");
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

function soloNumeros(e){
	var key = window.Event ? e.which : e.keyCode();
	return (key >= 48 && key <= 57);
}

function validarFormularioOportunidad() {

    var valor = true;
    var nom = $("#nombre_empresa").val();
    //var log = $("#login_usuario").val();
    //var nom = $("#nombre_usuario").val();

    if (nom === "") {
        valor = false;
        $("#nombre_empresa").val("");
        $("#id_empresa").focus();
        $("#mensajes").html("La empresa no puede estar vacio.");
        }
        return valor;
        }
        
        
        
   function validarFormularioOpor() {
   var valor = true;
    var nom = $("#nombre_puesto").val();
    var ran = $("#rangoedad_detalleoportunidad").val();
    var hor = $("#horario_detalleoportunidad").val();
    var vac = $("#vacancia_detalleoportunidad").val();
    //var log = $("#login_usuario").val();
    //var nom = $("#nombre_usuario").val();
   
    if (nom === "") {
        valor = false;
        $("#nombre_puesto").val("");
        $("#id_puesto").focus();
        $("#mensajes").html("El puesto laboral no puede estar vacio.");
        }else{
          if (ran === "") {
              valor = false;
              $("#rangoedad_detalleoportunidad").val("");
              $("#rangoedad_detalleoportunidad").focus();
              $("#mensajes").html("El rango de la edad no puede estar vacio.");
        }else{
          if (hor === "") {
              valor = false;
              $("#horario_detalleoportunidad").val("");
              $("#horario_detalleoportunidad").focus();
              $("#mensajes").html("El horario requerido no puede estar vacio.");
        }else{
            if (vac.length <  1) {
              valor = false;
              $("#vacancia_detalleoportunidad").val("");
              $("#vacancia_detalleoportunidad").focus();
              $("#mensajes").html("La vacancia no puede estar vacio.");
        }
        }
        
        
        
        }
        
        
        }
        return valor;
        }