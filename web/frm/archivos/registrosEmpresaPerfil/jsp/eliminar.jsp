<%-- 
    Document   : eliminar
    Created on : 24/05/2017, 09:09:11 AM
    Author     : CTA
--%>






<%@page import="controladores.RegistrosControlador"%>
<%@page import="modelos.Registros"%>
<%@page import="org.json.simple.JSONObject"%>

<%@page import="java.sql.ResultSet"%>

<%
int id_registro = Integer.parseInt(request.getParameter("id_registro"));
String tipo = "error";
String mensaje = "Datos no eliminados";

Registros registro = new Registros();
registro.setId_registro(id_registro);
if (RegistrosControlador.eliminar(registro)) {
tipo = "success";
mensaje = "Datos eliminados correctamente";
 };
JSONObject obj = new JSONObject();
obj.put("tipo", tipo);
obj.put("mensaje", String.valueOf(mensaje));
out.print(obj);
out.flush();
%>