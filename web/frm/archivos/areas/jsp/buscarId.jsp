<%-- 
    Document   : buscarid
    Created on : May 10, 2017, 8:52:52 AM
    Author     : Administrator
--%>





<%@page import="controladores.AreasControlador"%>
<%@page import="modelos.Areas"%>
<%@page import="org.json.simple.JSONObject"%>
<%
 int id_area = 0;
 if (request.getParameter("id_area") != "") {
 id_area = Integer.parseInt(request.getParameter("id_area"));
}
 

 String tipo = "error";
 String mensaje = "Datos no encontrados.";
 String nuevo = "true";
 Areas area = new Areas();
 area.setId_area(id_area);
 area = AreasControlador.buscarId(area);
 if (area.getId_area() != 0) {
 tipo = "success";
 
 mensaje = "Datos encontrados";
 nuevo = "false";
}
 JSONObject obj = new JSONObject();
 obj.put("tipo", tipo);
 obj.put("mensaje", mensaje);
 obj.put("nuevo", nuevo);
 obj.put("id_area", area.getId_area());
 obj.put("nombre_area", area.getNombre_area());
 
 
 
 out.print(obj);
 out.flush();
%>
