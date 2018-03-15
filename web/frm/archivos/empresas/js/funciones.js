/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function agregarEmpresa
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
            limpiarCampos();
            $("#id_empresa").focus();
            $("#id_empresa").select();
        },
        error: function (e) {
            $("#mensajes").html("No se puede modificar los datos.");
        },
        complete: function (objeto, exito, error) {
            $("#id_empresa").focus();
        }
    });
}

function buscarIdEmpresa() {
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
            $("#id_empresa").val(json.id_empresa);
            $("#nombre_empresa").val(json.nombre_empresa);
            $("#email_empresa").val(json.email_empresa);
            $("#direccion_empresa").val(json.direccion_empresa);
            $("#telefono_empresa").val(json.telefono_empresa);
            $("#ruc_empresa").val(json.ruc_empresa);
            $("#id_ciudad").val(json.id_ciudad);
            $("#nombre_ciudad").val(json.nombre_ciudad);
            
            
      

            if (json.nuevo === "true") {
                $("#botonAgregar").prop('disabled', false);
                $("#botonModificar").prop('disabled', true);
                $("#botonEliminar").prop('disabled', true);
                siguienteCampo("#nombre_empresa", "botonModificar", true);
            } else {
                $("#botonAgregar").prop('disabled', true);
                $("#botonModificar").prop('disabled', false);
                $("#botonEliminar").prop('disabled', false);
                siguienteCampo("#nombre_empresa", "botonModificar", true);
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

function buscarNombreEmpresa() {
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
                $("#id_empresa").val(id);
                $("#nombre_empresa").focus();
                buscarIdEmpresa();
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

function buscarNombreCiudad2() {
    var datosFormulario = $("#formBuscar1").serialize();
   //alert("rr");
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarCiudad2.jsp',
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
                $("#nombre_ciudad").focus();
                buscarIdEmpresa();
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


function modificarEmpresa() {
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
            $("#id_empresa").focus();
            $("#id_empresa").select();
            if (exito === "success") {

            }
        }
    });
}
function eliminarEmpresa() {
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
            $("#id_empresa").focus();
            $("#id_empresa").select();
            if (exito === "success") {

            }
        }
    });
}
function limpiarCampos() {
    $("#id_empresa").val("0");
    $("#nombre_empresa").val("");
    $("#email_empresa").val("");
    $("#direccion_empresa").val("");
    $("#telefono_empresa").val("");
    $("#ruc_empresa").val("");
    $("#id_ciudad").val("0");
    $("#nombre_ciudad").val("");
    $("#id_empresa").focus();
}



function buscarIdCiudad() {
    var datosFormulario = $("#formPrograma").serialize();
    //alert(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdCiudad.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_ciudad").val(json.id_ciudad);
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

function buscarNombreCiudad() {
    var datosFormulario = $("#formBuscar").serialize();
   //alert("rr");
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarNombreCiudad.jsp',
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
                $("#id_ciudad").val(id);
                $("#nombre_ciudad").focus();
                buscarIdCiudad();
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




function validarFormularioEmpresa() {

    var valor = true;
    var nom = $("#nombre_empresa").val();
    var ema = $("#email_empresa").val();
    var dir = $("#direccion_empresa").val();
    var tel = $("#telefono_empresa").val();
    var ruc = $("#ruc_empresa").val();
    var ciu = $("#nombre_ciudad").val();

  

    if (nom === "") {
        valor = false;
        $("#nombre_empresa").val("");
        $("#nombre_empresa").focus();
        $("#mensajes").html("El nombre de empresa no debe estar vacio.");

    } else {
        if (ema === "") {
            valor = false;

            $("#email_empresa").val("");
            $("#email_empresa").focus();
            $("#mensajes").html("El email no debe estar vacio.");
        }  else {
        if (dir === "") {
            valor = false;

            $("#direccion_empresa").val("");
            $("#direccion_empresa").focus();
            $("#mensajes").html("La direccion no debe estar vacio.");
        }  else {
            if (tel === "") {
            valor = false;
            
            $("#telefono_empresa").val("");
            $("#telefono_empresa").focus();
            $("#mensajes").html("El Telefono no debe estar vacio.");
        }else {
           if (ruc === "") {
            valor = false;
            
            $("#ruc_empresa").val("");
            $("#ruc_empresa").focus();
            $("#mensajes").html("El Ruc no debe estar vacio.");
 
            
        } else {
           if (ciu === "") {
            valor = false;
            
            $("#nombre_ciudad").val("");
            $("#nombre_ciudad").focus();
            $("#mensajes").html("El Id ciudad no debe estar vacio.");       
         }  
        } 
        }
  
        }
  
        

       }  
    } 
   
    return valor;
}