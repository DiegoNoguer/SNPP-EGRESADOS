<%-- 
    Document   : buscarid
    Created on : May 10, 2017, 8:52:52 AM
    Author     : Administrator
--%>





<%@page import="controladores.EspecialidadesControlador"%>
<%@page import="modelos.Especialidades"%>
<%@page import="org.json.simple.JSONObject"%>
<%
 int id_especialidad = 0;
 if (request.getParameter("id_especialidad") != "") {
 id_especialidad = Integer.parseInt(request.getParameter("id_especialidad"));
}
 

 String tipo = "error";
 String mensaje = "Datos no encontrados.";
 String nuevo = "true";
 Especialidades especialidad = new Especialidades();
 especialidad.setId_especialidad(id_especialidad);
 especialidad = EspecialidadesControlador.buscarId(especialidad);
 if (especialidad.getId_especialidad() != 0) {
 tipo = "success";
 
 mensaje = "Datos encontrados";
 nuevo = "false";
}
 JSONObject obj = new JSONObject();
 obj.put("tipo", tipo);
 obj.put("mensaje", mensaje);
 obj.put("nuevo", nuevo);
 obj.put("id_especialidad", especialidad.getId_especialidad());
 obj.put("nombre_especialidad", especialidad.getNombre_especialidad());
 
 
 
 out.print(obj);
 out.flush();
%>
