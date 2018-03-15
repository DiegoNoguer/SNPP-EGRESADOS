<%-- 
    Document   : buscarNombre
    Created on : May 10, 2017, 10:34:19 AM
    Author     : Diego Lima
--%>






<%@page import="controladores.CiudadesControlador"%>
<%@page import="controladores.EmpresasControlador"%>
<%@page import="org.json.simple.JSONObject"%>

<%@page import="java.sql.ResultSet"%>

<%
    String nombre_ciudad = request.getParameter("bnombre_ciudad");
    int pagina = Integer.parseInt(request.getParameter("bpagina1"));
    
    String mensaje = "Busqueda exitosa.";
    String contenido = EmpresasControlador.buscaNombre2(nombre_ciudad,pagina);
    
    JSONObject obj = new JSONObject();
    obj.put("mensaje",mensaje);
    obj.put("contenido",contenido);
         System.out.println("--->" + contenido);
    
    out.print(obj);
    out.flush();
    
%>