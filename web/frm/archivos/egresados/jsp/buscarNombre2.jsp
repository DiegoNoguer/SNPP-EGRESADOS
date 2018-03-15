<%-- 
    Document   : buscarNombre
    Created on : May 10, 2017, 10:34:19 AM
    Author     : Diego Lima
--%>






<%@page import="controladores.EgresadosControlador"%>
<%@page import="org.json.simple.JSONObject"%>

<%@page import="java.sql.ResultSet"%>

<%
    String nombre_egresado = request.getParameter("bnombre_egresado");
    int pagina = Integer.parseInt(request.getParameter("bpagina1"));
    
    String mensaje = "Busqueda exitosa.";
    String contenido = EgresadosControlador.buscaNombre2(nombre_egresado,pagina);
    
    JSONObject obj = new JSONObject();
    obj.put("mensaje",mensaje);
    obj.put("contenido",contenido);
         System.out.println("--->" + contenido);
    
    out.print(obj);
    out.flush();
    
%>