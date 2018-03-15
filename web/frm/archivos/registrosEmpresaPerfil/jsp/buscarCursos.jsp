<%-- 
    Document   : buscarNombre
    Created on : May 10, 2017, 10:34:19 AM
    Author     : Diego Lima
--%>






<%@page import="controladores.RegistrosControlador"%>
<%@page import="controladores.CiudadesControlador"%>
<%@page import="org.json.simple.JSONObject"%>

<%@page import="java.sql.ResultSet"%>

<%
    String nombre_curso = request.getParameter("bnombre_curso");
    int pagina = Integer.parseInt(request.getParameter("bpagina1"));
    
    String mensaje = "Busqueda exitosa.";
    String contenido = RegistrosControlador.buscaCurso(nombre_curso,pagina);
    
    JSONObject obj = new JSONObject();
    obj.put("mensaje",mensaje);
    obj.put("contenido",contenido);
         System.out.println("--->" + contenido);
    
    out.print(obj);
    out.flush();
    
%>