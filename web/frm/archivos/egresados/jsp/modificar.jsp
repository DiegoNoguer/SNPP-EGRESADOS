<%-- 
    Document   : modificar.jsp
    Created on : May 24, 2017, 7:37:57 AM
    Author     : Administrator
--%>




<%@page import="utiles.Utiles"%>
<%@page import="modelos.Especialidades"%>
<%@page import="modelos.Estadociviles"%>
<%@page import="modelos.Ciudades"%>
<%@page import="controladores.EgresadosControlador"%>
<%@page import="modelos.Egresados"%>
<%@page import="org.json.simple.JSONObject"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>


<%
int id_egresado = Integer.parseInt(request.getParameter("id_egresado"));
String nombre_egresado = request.getParameter("nombre_egresado");
String apellido_egresado = request.getParameter("apellido_egresado");
String telefono_egresado = request.getParameter("telefono_egresado");
int cedula_egresado = Integer.parseInt(request.getParameter("cedula_egresado"));
String sfechanaci_egresado = request.getParameter("fechanaci_egresado");
java.sql.Date fechanaci_egresado = Utiles.stringToSqlDate(sfechanaci_egresado);
int edad_egresado = Integer.parseInt(request.getParameter("edad_egresado"));
int id_ciudad = Integer.parseInt(request.getParameter("id_ciudad"));
int id_estadocivil = Integer.parseInt(request.getParameter("id_estadocivil"));
String otroestudio_egresado = request.getParameter("otroestudio_egresado");
String nivelacademico_egresado = request.getParameter("nivelacademico_egresado");
String aptitud_egresado = request.getParameter("aptitud_egresado");
String manejoidioma_egresado = request.getParameter("manejoidioma_egresado");
String tipogenero_egresado = request.getParameter("tipogenero_egresado");



String tipo="error";
Egresados egresado=new Egresados();
egresado.setId_egresado(id_egresado);


egresado.setNombre_egresado(nombre_egresado);
egresado.setApellido_egresado(apellido_egresado);
egresado.setTelefono_egresado(telefono_egresado);
egresado.setCedula_egresado(cedula_egresado);
egresado.setFechanaci_egresado(fechanaci_egresado);
egresado.setEdad_egresado(edad_egresado);
egresado.setOtroestudio_egresado(otroestudio_egresado);
egresado.setNivelacademico_egresado(nivelacademico_egresado);
egresado.setAptitud_egresado(aptitud_egresado);
egresado.setManejoidioma_egresado(manejoidioma_egresado);
egresado.setTipogenero_egresado(tipogenero_egresado);
//CIUDADES
Ciudades ciudad = new Ciudades();
ciudad.setId_ciudad(id_ciudad);
egresado.setCiudad(ciudad);

//ESTADOCIVILES
Estadociviles estadocivil = new Estadociviles();
estadocivil.setId_estadocivil(id_estadocivil);
egresado.setEstadocivil(estadocivil);


    

String mensaje="Datos no modificados";
if(EgresadosControlador.modificar(egresado)){
    tipo="sucess";
    mensaje="Datos modificados correctamente";
};
JSONObject obj=new JSONObject();
obj.put("tipo",tipo);
obj.put("mensaje",String.valueOf(mensaje));
out.print(obj);
out.flush();


%>