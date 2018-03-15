<%-- 
    Document   : eliminar
    Created on : 24/05/2017, 09:09:11 AM
    Author     : CTA
--%>






<%@page import="controladores.PuestosControlador"%>
<%@page import="modelos.Puestos"%>
<%@page import="org.json.simple.JSONObject"%>

<%@page import="java.sql.ResultSet"%>

<%
int id_puesto = Integer.parseInt(request.getParameter("id_puesto"));
String tipo = "error";
String mensaje = "Datos no eliminados";

Puestos puesto = new Puestos();
puesto.setId_puesto(id_puesto);
if (PuestosControlador.eliminar(puesto)) {
tipo = "success";
mensaje = "Datos eliminados correctamente";
 };
JSONObject obj = new JSONObject();
obj.put("tipo", tipo);
obj.put("mensaje", String.valueOf(mensaje));
out.print(obj);
out.flush();
%>