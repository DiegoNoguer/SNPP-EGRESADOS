/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function agregarEgresado
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
            $("#id_egresado").focus();
            $("#id_egresado").select();
        },
        error: function (e) {
            $("#mensajes").html("No se puede modificar los datos.");
        },
        complete: function (objeto, exito, error) {
            $("#id_egresado").focus();
        }
    });
}

function buscarIdEgresado() {
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
            $("#id_egresado").val(json.id_egresado);
            $("#nombre_egresado").val(json.nombre_egresado);
            $("#apellido_egresado").val(json.apellido_egresado);
            $("#telefono_egresado").val(json.telefono_egresado);
            $("#fechanaci_egresado").val(json.fechanaci_egresado);
            $("#cedula_egresado").val(json.cedula_egresado);
            $("#edad_egresado").val(json.edad_egresado);
            $("#nombre_departamento").val(json.nombre_departamento);
            $("#id_ciudad").val(json.id_ciudad);
            $("#nombre_ciudad").val(json.nombre_ciudad);
            $("#id_estadocivil").val(json.id_estadocivil);
            $("#nombre_estadocivil").val(json.nombre_estadocivil);
            $("#otroestudio_egresado").val(json.otroestudio_egresado);
            $("#nivelacademico_egresado").val(json.nivelacademico_egresado);
            $("#aptitud_egresado").val(json.aptitud_egresado);
            $("#manejoidioma_egresado").val(json.manejoidioma_egresado);
            $("#tipogenero_egresado").val(json.tipogenero_egresado);
          
            
      

            if (json.nuevo === "true") {
                $("#botonAgregar").prop('disabled', false);
                $("#botonModificar").prop('disabled', true);
                $("#botonEliminar").prop('disabled', true);
                //$("#cedula_egresado").prop('disabled', false);
                siguienteCampo("#nombre_egresado", "botonModificar", true);
            } else {
                $("#botonAgregar").prop('disabled', true);
                $("#botonModificar").prop('disabled', false);
                $("#botonEliminar").prop('disabled', false);
               // $("#cedula_egresado").prop('disabled', true);
                siguienteCampo("#nombre_egresado", "botonModificar", true);
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

function buscarNombreEgresado() {
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
                $("#id_egresado").val(id);
                $("#cedula_egresado").focus();
                buscarIdEgresado();
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
function buscarNombreEgresado2() {
    var datosFormulario = $("#formBuscar1").serialize();
   //alert("rr");
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarNombre2.jsp',
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
                $("#nombre_egresado").focus();
                buscarIdEgresado();
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



function modificarEgresado() {
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
            $("#id_egresado").focus();
            $("#id_egresado").select();
            if (exito === "success") {

            }
        }
    });
}
function eliminarEgresado() {
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
            $("#id_egresado").focus();
            $("#id_egresado").select();
            if (exito === "success") {

            }
        }
    });
}
function limpiarCampos() {
    $("#id_egresado").val("0");
    $("#nombre_egresado").val("");
    $("#apellido_egresado").val("");
    $("#telefono_egresado").val("");
    $("#cedula_egresado").val("0");
    $("#fechanaci_egresado").val("");
    $("#edad_egresado").val("0");
    $("#id_ciudad").val("0");
    $("#nombre_ciudad").val("");
    $("#id_estadocivil").val("0");
    $("#nombre_estadocivil").val("");
    $("#otroestudio_egresado").val("");
    $("#nivelacademico_egresado").val("");
    $("#aptitud_egresado").val("");
    $("#manejoidioma_egresado").val("");
    $("#tipogenero_egresado").val("");
    $("#id_egresado").focus();
}

function buscarCedulas() {
    var datosFormulario = $("#formPrograma").serialize();
    //alert(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarcedula.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
         
            $("#cedula_egresado").val(json.cedula_egresado);
            if (json.nuevo === "false"){
                 $("#cedula_egresado").val("");
                // $("#cedula_egresado").focus();     para que no se quede ahi 
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


//CIUDAD
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
            $("#id_departamento").val(json.id_departamento);
            $("#nombre_departamento").val(json.nombre_departamento);
            
    
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
            $("#id_departamento").val(json.id_departamento);
            $("#nombre_departamento").val(json.nombre_departamento);
            
            
            
      

            
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


function buscarIdEstadocivil() {
    var datosFormulario = $("#formPrograma").serialize();
    //alert(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdEstadocivil.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_estadocivil").val(json.id_estadocivil);
            $("#nombre_estadocivil").val(json.nombre_estadocivil);
            
            
      

        
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

function buscarNombreEstadocivil() {
    var datosFormulario = $("#formBuscar").serialize();
   //alert("rr");
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarNombreEstadocivil.jsp',
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
                $("#id_estadocivil").val(id);
                $("#nombre_estadocivil").focus();
                buscarIdEstadocivil();
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


function soloLetras(e) {
    key = e.keyCode || e.which;
    tecla = String.fromCharCode(key).toLowerCase();
    letras = " áéíóúabcdefghijklmnñopqrstuvwxyz";
    especiales = "8-37-39-46";

    tecla_especial = false;
    for (var i in especiales) {
        if (key === especiales[i]) {
            tecla_especial = true;
            break;
        }
    }

    if (letras.indexOf(tecla) === -1 && !tecla_especial) {
        return false;
    }
}

function validarFormularioEgresado() {

    var valor = true;
    var nom = $("#nombre_egresado").val();
    var ape = $("#apellido_egresado").val();
    var tel = $("#telefono_egresado").val();
    var edad = $("#edad_egresado").val();
    var ciu = $("#nombre_ciudad").val();
    var est = $("#nombre_estadocivil").val();
    var niv = $("#nivelacademico_egresado").val();
    var otr = $("#otroestudio_egresado").val();
    var man = $("#manejoidioma_egresado").val();
    var apt = $("#aptitud_egresado").val();
  

    if (nom === "") {
        valor = false;
        $("#nombre_egresado").val("");
        $("#nombre_egresado").focus();
        $("#mensajes").html("El nombre no debe estar vacio.");

    } else {
        if (ape === "") {
            valor = false;

            $("#apellido_egresado").val("");
            $("#apellido_egresado").focus();
            $("#mensajes").html("El apellido no debe estar vacio.");
        }  else {
        if (tel === "") {
            valor = false;

            $("#telefono_egresado").val("");
            $("#telefono_egresado").focus();
            $("#mensajes").html("El telefono no debe estar vacio.");
        } 
        else {
         if (edad.length < 2) {
            valor = false;
            
            $("#edad_egresado").val("0");
            $("#edad_egresado").focus();
            $("#mensajes").html("La edad no debe estar vacio.");
        }else {
            if (ciu === "") {
            valor = false;
            
            $("#nombre_ciudad").val("");
            $("#nombre_ciudad").focus();
            $("#mensajes").html("El id Ciudad  no debe estar vacio.");
        }else {
           if (est === "") {
            valor = false;
            
            $("#nombre_estadocivil").val("");
            $("#nombre_estadocivil").focus();
            $("#mensajes").html("El id Estadocivil no debe estar vacio.");
 
            
        } else {
           if (niv === "") {
            valor = false;
            
            $("#nivelacademico_egresado").val("");
            $("#nivelacademico_egresado").focus();
            $("#mensajes").html("El nivel academico no debe estar vacio.");
        
        } else {
            
          if (otr === "") {
            valor = false;
            
            $("#otroestudio_egresado").val("");
            $("#otroestudio_egresado").focus();
            $("#mensajes").html("Otros Estudios no debe estar vacio.");
       
        
        } else {
            if (man === "") {
            valor = false;
            
            $("#manejoidioma_egresado").val("");
            $("#manejoidioma_egresado").focus();
            $("#mensajes").html("Manejo de Idiomas no debe estar vacio.");
       
        
        }else {
           if (apt === "") {
            valor = false;
            
            $("#aptitud_egresado").val("");
            $("#aptitud_egresado").focus();
            $("#mensajes").html("Aptitud no debe estar vacio.");
       
        
        } 
        }
        
        }
   
        }
 
        }
  
        }
  
        }
  
        }

       }  
    } 
   
    return valor;
}



function calcularEdad() {
    var sfecha = $('*[name=fechanaci_egresado]').val(),
            inicio = 0,
            fin = 10,
            fecha = sfecha.substring(inicio, fin);
    var values = fecha.split("/");
    var dia = values[0];
    var mes = values[1];
    var ano = values[2];
 
    // cogemos los valores actuales
    var fecha_hoy = new Date();
    var ahora_ano = fecha_hoy.getYear();
    var ahora_mes = fecha_hoy.getMonth() + 1;
    var ahora_dia = fecha_hoy.getDate();
 
    // realizamos el calculo
    var edad = (ahora_ano + 1900) - ano;
    if (ahora_mes < mes)
    {
        edad--;
    }
    if ((mes === ahora_mes) && (ahora_dia < dia))
    {
        edad--;
    }
    if (edad > 1900)
    {
        edad -= 1900;
    }
 
    // calculamos los meses
    var meses = 0;
    if (ahora_mes > mes)
        meses = ahora_mes - mes;
    if (ahora_mes < mes)
        meses = 12 - (mes - ahora_mes);
    if (ahora_mes === mes && dia > ahora_dia)
        meses = 11;
 
    // calculamos los dias
    var dias = 0;
    if (ahora_dia > dia)
        dias = ahora_dia - dia;
    if (ahora_dia < dia)
    {
        ultimoDiaMes = new Date(ahora_ano, ahora_mes, 0);
        dias = ultimoDiaMes.getDate() - (dia - ahora_dia);
    }
//        document.getElementById("result").innerHTML="Tienes "+edad+" años, "+meses+" meses y "+dias+" días";
//    }
//    else{ document.getElementById("result").innerHTML="La fecha "+fecha+" es incorrecta";
//    }
//document.getElementById("edad_persona").innerHTML=edad+" años";
    $("#edad_egresado").val(edad);
}