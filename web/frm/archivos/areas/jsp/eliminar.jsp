<%-- 
    Document   : eliminar
    Created on : 24/05/2017, 09:09:11 AM
    Author     : CTA
--%>






<%@page import="controladores.AreasControlador"%>
<%@page import="modelos.Areas"%>
<%@page import="org.json.simple.JSONObject"%>

<%@page import="java.sql.ResultSet"%>

<%
int id_area = Integer.parseInt(request.getParameter("id_area"));
String tipo = "error";
String mensaje = "Datos no eliminados";

Areas area = new Areas();
area.setId_area(id_area);
if (AreasControlador.eliminar(area)) {
tipo = "success";
mensaje = "Datos eliminados correctamente";
 };
JSONObject obj = new JSONObject();
obj.put("tipo", tipo);
obj.put("mensaje", String.valueOf(mensaje));
out.print(obj);
out.flush();
%>