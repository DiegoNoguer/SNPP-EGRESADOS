<%-- 
    Document   : eliminar
    Created on : 24/05/2017, 09:09:11 AM
    Author     : CTA
--%>






<%@page import="controladores.EspecialidadesControlador"%>
<%@page import="modelos.Especialidades"%>
<%@page import="org.json.simple.JSONObject"%>

<%@page import="java.sql.ResultSet"%>

<%
int id_especialidad = Integer.parseInt(request.getParameter("id_especialidad"));
String tipo = "error";
String mensaje = "Datos no eliminados";

Especialidades especialidad = new Especialidades();
especialidad.setId_especialidad(id_especialidad);
if (EspecialidadesControlador.eliminar(especialidad)) {
tipo = "success";
mensaje = "Datos eliminados correctamente";
 };
JSONObject obj = new JSONObject();
obj.put("tipo", tipo);
obj.put("mensaje", String.valueOf(mensaje));
out.print(obj);
out.flush();
%>