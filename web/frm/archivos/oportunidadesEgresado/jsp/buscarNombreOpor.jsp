<%-- 
    Document   : buscarNombre
    Created on : May 10, 2017, 10:34:19 AM
    Author     : Diego Lima
--%>






<%@page import="controladores.OportunidadesControladorEgresado"%>
<%@page import="controladores.OportunidadesControlador"%>
<%@page import="org.json.simple.JSONObject"%>

<%@page import="java.sql.ResultSet"%>

<%
    String nombre_puesto = request.getParameter("bnombre_puesto");
    int pagina = Integer.parseInt(request.getParameter("bpagina"));
    
    String mensaje = "Busqueda exitosa.";
    String contenido = OportunidadesControladorEgresado.buscarNombrePuesto(nombre_puesto,pagina);
    
    JSONObject obj = new JSONObject();
    obj.put("mensaje",mensaje);
    obj.put("contenido",contenido);
         System.out.println("--->" + contenido);
    
    out.print(obj);
    out.flush();
    
%>