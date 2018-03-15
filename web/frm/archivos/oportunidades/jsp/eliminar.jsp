<%-- 
    Document   : eliminar
    Created on : 24/05/2017, 09:09:11 AM
    Author     : CTA
--%>






<%@page import="controladores.OportunidadesControlador"%>
<%@page import="modelos.Oportunidades"%>
<%@page import="org.json.simple.JSONObject"%>

<%@page import="java.sql.ResultSet"%>

<%
int id_oportunidad = Integer.parseInt(request.getParameter("id_oportunidad"));
String tipo = "error";
String mensaje = "Datos no eliminados";

Oportunidades oportunidad = new Oportunidades();
oportunidad.setId_oportunidad(id_oportunidad);
if (OportunidadesControlador.eliminar(oportunidad)) {
tipo = "success";
mensaje = "Datos eliminados correctamente";
 };
JSONObject obj = new JSONObject();
obj.put("tipo", tipo);
obj.put("mensaje", String.valueOf(mensaje));
out.print(obj);
out.flush();
%>