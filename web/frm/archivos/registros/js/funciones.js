/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function agregarRegistro
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
            $("#id_registro").val(json.id_registro);

            buscarIdRegistro();
            $("#id_registro").focus;
            $("#id_registro").select();
        },
        error: function (e) {
            $("#mensajes").html("No se puede modificar los datos.");
        },
        complete: function (objeto, exito, error) {
            $("#id_registro").focus();
        }
    });
}

function buscarIdRegistro() {
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
            $("#id_registro").val(json.id_registro);
            $("#nombre_registro").val(json.nombre_registro);
            $("#fecha_registro").val(json.fecha_registro);
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
                siguienteCampo("#nombre_registro", "botonModificar", true);
                $("#detalle").prop('hidden', true);
            } else {
                $("#botonAgregar").prop('disabled', true);
                $("#botonModificar").prop('disabled', false);
                $("#botonEliminar").prop('disabled', false);
                $("#botonBuscarIdEgresado").prop('disabled', true);
                $("#id_egresado").prop('disabled', true);
                siguienteCampo("#nombre_registro", "botonModificar", true);
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

function buscarNombreRegistro() {
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
                $("#id_registro").val(id);
                $("#cedula_egresado").focus();
                buscarIdRegistro();
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






function modificarRegistro() {
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
            $("#id_registro").focus();
            $("#id_registro").select();
            if (exito === "success") {

            }
        }
    });
}
function eliminarRegistro() {
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
            $("#id_registro").focus();
            $("#id_registro").select();
            if (exito === "success") {

            }
        }
    });
}
function limpiarCampos() {
    $("#id_registro").val("0");
    $("#nombre_registro").val("");
    $("#id_egresado").val("0");
    $("#nombre_egresado").val("");
    $("#apellido_egresado").val("");
    $("#cedula_egresado").val("0");
    $("#telefono_egresado").val("");
    $("#edad_egresado").val("0");
    $("#nombre_ciudad").val("");
    $("#id_registro").focus();
}





function buscarBorraridegresado3() {
    var datosFormulario = $("#formPrograma").serialize();
    //alert(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarBorraridegresado3.jsp',
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
            // $("#fechanaci_egresado").val(json.fechanaci_egresado);
            // $("#cedula_egresado").val(json.cedula_egresado);
            $("#edad_egresado").val(json.edad_egresado);
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
                buscarBorraridegresado3();
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
    if ($("#nombre_registro").val().length < 3) {
        valor = false;
        $("#mensajes").html("Nombre Registro no puede estar vacio.");
        $("#nombre_registro").focus();
    }

    if ($("#nombre_egresado").val().length < 2) {
        valor = false;
        $("#mensajes").html("Egresado no puede estar vacio.");
        $("#id_egresado").focus();
    }

    if ($("#nombre_tiporegistro").val().length < 2) {
        valor = false;
        $("#mensajes").html("Tipo Registro no puede estar vacio.");
        $("#id_tiporegistro").focus();
    }
    return valor;
}
function limpiarFormulario() {
    $("#id_registro").val("0");

    $("#nombre_egresado").val("");
    $("#id_egresado").val("0");
    $("#apellido_egresado").val("0");
    $("#fecha_registro").val("0");



}
function agregarLinea() {
    $("#id_detalleregistro").val("0");
    $("#id_curso").val("0");
    $("#nombre_curso").val("");
    $("#tipo_curso").val("");
    $("#duracion_curso").val("");
    $("#año_curso").val("");
    $("#nombre_especialidad").val(""); 
    $("#panelLinea").fadeIn("slow");
    $("#panelPrograma").fadeOut("slow");
    $("#id_curso").focus();
    $("#id_curso").select();
    $("#botonAgregarLinea").prop('disabled', false);
    $("#botonModificarLinea").prop('disabled', true);
    $("#botonEliminarLinea").prop('disabled', true);
    siguienteCampo("#horas_detalleregistro", "#botonAgregarLinea", true);
}
function editarLinea(id) {
    $("#id_detalleregistro").val(id);
    $("#id_curso").val("0");
    $("#nombre_curso").val("");
    $("#tipo_curso").val("");
    $("#duracion_curso").val("");
    $("#año_curso").val("");
    $("#nombre_especialidad").val("");
    $("#panelLinea").fadeIn("slow");
    $("#panelPrograma").fadeOut("slow");
    $("#id_curso").focus();
    $("#id_curso").select();
    $("#botonAgregarLinea").prop('disabled', true);
    $("#botonModificarLinea").prop('disabled', false);
    $("#botonEliminarLinea").prop('disabled', false);
    buscarIdRegistroDetalle();
    siguienteCampo("#cantidad_cursoregistro", "#botonModificarLinea", true);
}
// registroscursos
function buscarIdRegistroDetalle() {
    var datosFormulario = $("#formLinea").serialize();
   //alert(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdRegistroDetalle.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
         $("#id_curso").val(json.id_curso);
          $("#nombre_curso").val(json.nombre_curso);
            $("#tipo_curso").val(json.tipo_curso);
            $("#duracion_curso").val(json.duracion_curso);
            $("#año_curso").val(json.año_curso);
         $("#nombre_especialidad").val(json.nombre_especialidad);
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
function agregarRegistroDetalle() {
    var datosFormulario = $("#formLinea").serialize();
    var id_registro = $("#id_registro").val();
    datosFormulario += "&id_registro=" + id_registro;

    //alert(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/agregarRegistroDetalle.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#panelLinea").fadeOut("slow");
            $("#panelPrograma").fadeIn("slow");
            buscarIdRegistro();
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
function modificarRegistroDetalle() {
    var datosFormulario = $("#formLinea").serialize();
    var id_registro = $("#id_registro").val();
    datosFormulario += "&id_registro=" + id_registro;
    $.ajax({
        type: 'POST',
        url: 'jsp/modificarRegistroDetalle.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#panelLinea").fadeOut("slow");
            $("#panelPrograma").fadeIn("slow");
            buscarIdRegistro();
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
function eliminarRegistroDetalle() {
    var datosFormulario = $("#formLinea").serialize();
    var id_registro = $("#id_registro").val();
    datosFormulario += "&id_registro=" + id_registro;
    $.ajax({
        type: 'POST',
        url: 'jsp/eliminarRegistroDetalle.jsp',
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
            buscarIdRegistro();

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
//// cursos
function buscarIdCurso() {
    var datosFormulario = $("#formLinea").serialize();
    //alert(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdCurso.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_curso").val(json.id_curso);
            $("#nombre_curso").val(json.nombre_curso);
            $("#tipo_curso").val(json.tipo_curso);
            $("#duracion_curso").val(json.duracion_curso);
            $("#año_curso").val(json.año_curso);
            $("#nombre_especialidad").val(json.nombre_especialidad);



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
function buscarNombreCurso() {
    var datosFormulario = $("#formBuscar").serialize();
    // alert(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarNombreCurso.jsp',
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
                $("#id_curso").val(id);
                $("#nombre_curso").focus();
                buscarIdCurso();
                $("#buscar").fadeOut("slow");
                $("#panelLinea").fadeIn("slow");
            });
        },
        error: function (e) {
            $("#mensajes").html("No se pudo buscar registros.");
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






function validarFormularioRegistro() {

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
        
        
        function validarFormularioRegistro1() {

    var valor = true;
    var nomb = $("#nombre_curso").val();
    //var log = $("#login_usuario").val();
    //var nom = $("#nombre_usuario").val();

    if (nomb === "") {
        valor = false;
        $("#nombre_curso").val("");
        $("#nombre_curso").focus();
        $("#mensajes").html("El curso no puede estar vacio.");
        }
        return valor;
        }