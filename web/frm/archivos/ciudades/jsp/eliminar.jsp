<%-- 
    Document   : eliminar
    Created on : 24/05/2017, 09:09:11 AM
    Author     : CTA
--%>






<%@page import="controladores.CiudadesControlador"%>
<%@page import="modelos.Ciudades"%>
<%@page import="org.json.simple.JSONObject"%>

<%@page import="java.sql.ResultSet"%>

<%
int id_ciudad = Integer.parseInt(request.getParameter("id_ciudad"));
String tipo = "error";
String mensaje = "Datos no eliminados";

Ciudades ciudad = new Ciudades();
ciudad.setId_ciudad(id_ciudad);
if (CiudadesControlador.eliminar(ciudad)) {
tipo = "success";
mensaje = "Datos eliminados correctamente";
 };
JSONObject obj = new JSONObject();
obj.put("tipo", tipo);
obj.put("mensaje", String.valueOf(mensaje));
out.print(obj);
out.flush();
%>