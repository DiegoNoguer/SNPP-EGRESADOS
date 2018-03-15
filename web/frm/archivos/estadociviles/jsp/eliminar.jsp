<%-- 
    Document   : eliminar
    Created on : 24/05/2017, 09:09:11 AM
    Author     : CTA
--%>






<%@page import="controladores.EstadocivilesControlador"%>
<%@page import="modelos.Estadociviles"%>
<%@page import="org.json.simple.JSONObject"%>

<%@page import="java.sql.ResultSet"%>

<%
int id_estadocivil = Integer.parseInt(request.getParameter("id_estadocivil"));
String tipo = "error";
String mensaje = "Datos no eliminados";

Estadociviles estadocivil = new Estadociviles();
estadocivil.setId_estadocivil(id_estadocivil);
if (EstadocivilesControlador.eliminar(estadocivil)) {
tipo = "success";
mensaje = "Datos eliminados correctamente";
 };
JSONObject obj = new JSONObject();
obj.put("tipo", tipo);
obj.put("mensaje", String.valueOf(mensaje));
out.print(obj);
out.flush();
%>