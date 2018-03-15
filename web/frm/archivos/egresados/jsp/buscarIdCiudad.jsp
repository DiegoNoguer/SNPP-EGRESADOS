<%-- 
    Document   : buscarid
    Created on : May 10, 2017, 8:52:52 AM
    Author     : Administrator
--%>





<%@page import="controladores.CiudadesControlador"%>
<%@page import="modelos.Ciudades"%>
<%@page import="org.json.simple.JSONObject"%>
<%
 int id_ciudad = 0;
 if (request.getParameter("id_ciudad") != "") {
 id_ciudad = Integer.parseInt(request.getParameter("id_ciudad"));
}
 

 String tipo = "error";
 String mensaje = "Datos no encontrados.";
 String nuevo = "true";
 Ciudades ciudad = new Ciudades();
 ciudad.setId_ciudad(id_ciudad);
 ciudad = CiudadesControlador.buscarId(ciudad);
 if (ciudad.getId_ciudad() != 0) {
 tipo = "success";
 
 mensaje = "Datos encontrados";
 nuevo = "false";
}
 JSONObject obj = new JSONObject();
 obj.put("tipo", tipo);
 obj.put("mensaje", mensaje);
 obj.put("nuevo", nuevo);
 obj.put("id_ciudad", ciudad.getId_ciudad());
 obj.put("nombre_ciudad", ciudad.getNombre_ciudad());
 obj.put("id_departamento",ciudad.getDepartamento().getId_departamento());
 obj.put("nombre_departamento",ciudad.getDepartamento().getNombre_departamento());

 
 
 
 out.print(obj);
 out.flush();
%>
