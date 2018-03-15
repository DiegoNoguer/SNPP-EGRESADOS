<%-- 
    Document   : buscarNombre
    Created on : May 10, 2017, 10:34:19 AM
    Author     : Diego Lima
--%>






<%@page import="controladores.TrabajosControlador"%>
<%@page import="org.json.simple.JSONObject"%>

<%@page import="java.sql.ResultSet"%>

<%
    String cedula_trabajo = request.getParameter("bcedula_egresado");
    int pagina = Integer.parseInt(request.getParameter("bpagina"));
    
    String mensaje = "Busqueda exitosa.";
    String contenido = TrabajosControlador.buscaNombre(cedula_trabajo,pagina);
    
    JSONObject obj = new JSONObject();
    obj.put("mensaje",mensaje);
    obj.put("contenido",contenido);
         System.out.println("--->" + contenido);
    
    out.print(obj);
    out.flush();
    
%>