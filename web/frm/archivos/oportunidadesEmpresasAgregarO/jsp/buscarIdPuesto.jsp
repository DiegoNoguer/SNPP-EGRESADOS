<%-- 
    Document   : buscarid
    Created on : May 10, 2017, 8:52:52 AM
    Author     : Administrator
--%>





<%@page import="controladores.PuestosControlador"%>
<%@page import="modelos.Puestos"%>
<%@page import="org.json.simple.JSONObject"%>
<%
 int id_puesto = 0;
 if (request.getParameter("id_puesto") != "") {
 id_puesto = Integer.parseInt(request.getParameter("id_puesto"));
}
 

 String tipo = "error";
 String mensaje = "Datos no encontrados.";
 String nuevo = "true";
 Puestos puesto = new Puestos();
 puesto.setId_puesto(id_puesto);
 puesto = PuestosControlador.buscarId(puesto);
 if (puesto.getId_puesto() != 0) {
 tipo = "success";
 
 mensaje = "Datos encontrados";
 nuevo = "false";
}
 JSONObject obj = new JSONObject();
 obj.put("tipo", tipo);
 obj.put("mensaje", mensaje);
 obj.put("nuevo", nuevo);
 obj.put("id_puesto", puesto.getId_puesto());
 obj.put("nombre_puesto", puesto.getNombre_puesto());
 //obj.put("id_area", puesto.getArea().getId_area());
 obj.put("nombre_area", puesto.getArea().getNombre_area());
 
 
 
 
 out.print(obj);
 out.flush();
%>
