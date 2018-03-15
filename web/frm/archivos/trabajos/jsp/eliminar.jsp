<%-- 
    Document   : eliminar
    Created on : 24/05/2017, 09:09:11 AM
    Author     : CTA
--%>






<%@page import="controladores.TrabajosControlador"%>
<%@page import="modelos.Trabajos"%>
<%@page import="org.json.simple.JSONObject"%>

<%@page import="java.sql.ResultSet"%>

<%
int id_trabajo = Integer.parseInt(request.getParameter("id_trabajo"));
String tipo = "error";
String mensaje = "Datos no eliminados";

Trabajos trabajo = new Trabajos();
trabajo.setId_trabajo(id_trabajo);
if (TrabajosControlador.eliminar(trabajo)) {
tipo = "success";
mensaje = "Datos eliminados correctamente";
 };
JSONObject obj = new JSONObject();
obj.put("tipo", tipo);
obj.put("mensaje", String.valueOf(mensaje));
out.print(obj);
out.flush();
%>