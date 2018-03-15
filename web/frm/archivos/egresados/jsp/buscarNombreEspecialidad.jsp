<%-- 
    Document   : buscarNombre
    Created on : May 10, 2017, 10:34:19 AM
    Author     : Diego Lima
--%>






<%@page import="controladores.EspecialidadesControlador"%>
<%@page import="org.json.simple.JSONObject"%>

<%@page import="java.sql.ResultSet"%>

<%
    String nombre_especialidad = request.getParameter("bnombre_especialidad");
    int pagina = Integer.parseInt(request.getParameter("bpagina"));
    
    String mensaje = "Busqueda exitosa.";
    String contenido = EspecialidadesControlador.buscaNombre(nombre_especialidad,pagina);
    
    JSONObject obj = new JSONObject();
    obj.put("mensaje",mensaje);
    obj.put("contenido",contenido);
         System.out.println("--->" + contenido);
    
    out.print(obj);
    out.flush();
    
%>