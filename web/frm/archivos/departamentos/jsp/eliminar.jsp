<%-- 
    Document   : eliminar
    Created on : 24/05/2017, 09:09:11 AM
    Author     : CTA
--%>






<%@page import="controladores.DepartamentosControlador"%>
<%@page import="modelos.Departamentos"%>
<%@page import="org.json.simple.JSONObject"%>

<%@page import="java.sql.ResultSet"%>

<%
int id_departamento = Integer.parseInt(request.getParameter("id_departamento"));
String tipo = "error";
String mensaje = "Datos no eliminados";

Departamentos departamento = new Departamentos();
departamento.setId_departamento(id_departamento);
if (DepartamentosControlador.eliminar(departamento)) {
tipo = "success";
mensaje = "Datos eliminados correctamente";
 };
JSONObject obj = new JSONObject();
obj.put("tipo", tipo);
obj.put("mensaje", String.valueOf(mensaje));
out.print(obj);
out.flush();
%>