<%-- 
    Document   : buscarNombre
    Created on : May 10, 2017, 10:34:19 AM
    Author     : Diego Lima
--%>






<%@page import="controladores.RegistrosControlador"%>
<%@page import="org.json.simple.JSONObject"%>

<%@page import="java.sql.ResultSet"%>

<%
    String cedula_registro = request.getParameter("bcedula_egresado");
    int pagina = Integer.parseInt(request.getParameter("bpagina"));
    
    String mensaje = "Busqueda exitosa.";
    String contenido = RegistrosControlador.buscaNombre(cedula_registro,pagina);
    
    JSONObject obj = new JSONObject();
    obj.put("mensaje",mensaje);
    obj.put("contenido",contenido);
         System.out.println("--->" + contenido);
    
    out.print(obj);
    out.flush();
    
%>