<%-- 
    Document   : buscarid
    Created on : May 10, 2017, 8:52:52 AM
    Author     : Administrator
--%>





<%@page import="utiles.Utiles"%>
<%@page import="controladores.EgresadosControlador"%>
<%@page import="modelos.Egresados"%>
<%@page import="org.json.simple.JSONObject"%>
<%
 int id_egresado = 0;
 if (request.getParameter("id_egresado") != "") {
 id_egresado = Integer.parseInt(request.getParameter("id_egresado"));
}
 

 String tipo = "error";
 String mensaje = "Datos no encontrados.";
 String nuevo = "true";
 Egresados egresado = new Egresados();
 egresado.setId_egresado(id_egresado);
 egresado = EgresadosControlador.buscarId(egresado);
 if (egresado.getId_egresado() != 0) {
 tipo = "success";
 
 mensaje = "Datos encontrados";
 nuevo = "false";
}
 JSONObject obj = new JSONObject();
 obj.put("tipo", tipo);
 obj.put("mensaje", mensaje);
 obj.put("nuevo", nuevo);
 obj.put("id_egresado", egresado.getId_egresado());
 obj.put("nombre_egresado", egresado.getNombre_egresado());
 obj.put("apellido_egresado", egresado.getApellido_egresado());
 obj.put("telefono_egresado", egresado.getTelefono_egresado());
 obj.put("cedula_egresado", egresado.getCedula_egresado());
 obj.put("fechanaci_egresado", Utiles.sqlDateToString(egresado.getFechanaci_egresado()));
 obj.put("edad_egresado", egresado.getEdad_egresado());
 obj.put("id_ciudad",egresado.getCiudad().getId_ciudad());
 obj.put("nombre_ciudad",egresado.getCiudad().getNombre_ciudad());
 obj.put("id_estadocivil",egresado.getEstadocivil().getId_estadocivil());
 obj.put("nombre_estadocivil",egresado.getEstadocivil().getNombre_estadocivil());
 obj.put("nombre_departamento",egresado.getCiudad().getDepartamento().getNombre_departamento());
 obj.put("otroestudio_egresado",egresado.getOtroestudio_egresado());
 obj.put("nivelacademico_egresado",egresado.getNivelacademico_egresado());
 obj.put("aptitud_egresado",egresado.getAptitud_egresado());
 obj.put("manejoidioma_egresado",egresado.getManejoidioma_egresado());
 obj.put("tipogenero_egresado",egresado.getTipogenero_egresado());
 
 
 
 
 
 
 
 out.print(obj);
 out.flush();
%>
