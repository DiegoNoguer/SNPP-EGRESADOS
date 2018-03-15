<%-- 
    Document   : eliminar
    Created on : 24/05/2017, 09:09:11 AM
    Author     : CTA
--%>






<%@page import="controladores.EmpresasControlador"%>
<%@page import="modelos.Empresas"%>
<%@page import="org.json.simple.JSONObject"%>

<%@page import="java.sql.ResultSet"%>

<%
int id_empresa = Integer.parseInt(request.getParameter("id_empresa"));
String tipo = "error";
String mensaje = "Datos no eliminados";

Empresas empresa = new Empresas();
empresa.setId_empresa(id_empresa);
if (EmpresasControlador.eliminar(empresa)) {
tipo = "success";
mensaje = "Datos eliminados correctamente";
 };
JSONObject obj = new JSONObject();
obj.put("tipo", tipo);
obj.put("mensaje", String.valueOf(mensaje));
out.print(obj);
out.flush();
%>