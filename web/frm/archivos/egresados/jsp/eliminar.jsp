<%-- 
    Document   : eliminar
    Created on : 24/05/2017, 09:09:11 AM
    Author     : CTA
--%>






<%@page import="controladores.EgresadosControlador"%>
<%@page import="modelos.Egresados"%>
<%@page import="org.json.simple.JSONObject"%>

<%@page import="java.sql.ResultSet"%>

<%
int id_egresado = Integer.parseInt(request.getParameter("id_egresado"));
String tipo = "error";
String mensaje = "Datos no eliminados";

Egresados egresado = new Egresados();
egresado.setId_egresado(id_egresado);
if (EgresadosControlador.eliminar(egresado)) {
tipo = "success";
mensaje = "Datos eliminados correctamente";
 };
JSONObject obj = new JSONObject();
obj.put("tipo", tipo);
obj.put("mensaje", String.valueOf(mensaje));
out.print(obj);
out.flush();
%>