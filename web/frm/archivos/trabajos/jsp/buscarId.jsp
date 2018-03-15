<%-- 
    Document   : buscarid
    Created on : May 10, 2017, 8:52:52 AM
    Author     : Administrator
--%>





<%@page import="controladores.DetallesTrabajosControlador"%>
<%@page import="controladores.TrabajosControlador"%>
<%@page import="modelos.Trabajos"%>
<%@page import="org.json.simple.JSONObject"%>
<%
 int id_trabajo = 0;
 if (request.getParameter("id_trabajo") != "") {
 id_trabajo = Integer.parseInt(request.getParameter("id_trabajo"));
}
 

 String tipo = "error";
 String mensaje = "Datos no encontrados.";
 String nuevo = "true";
 Trabajos trabajo = new Trabajos();
 trabajo.setId_trabajo(id_trabajo);
 trabajo = TrabajosControlador.buscarId(trabajo);
 if (trabajo.getId_trabajo() != 0) {
 tipo = "success";
 
 mensaje = "Datos encontrados";
 nuevo = "false";
}
   // 
 //String contenido_detalle = DetallesTrabajosControlador.buscarIdTrabajo1(id_trabajo);
String contenido_detalle = DetallesTrabajosControlador.buscarIdTrabajo(id_trabajo);
 JSONObject obj = new JSONObject();
 obj.put("tipo", tipo);
 obj.put("mensaje", mensaje);
 obj.put("nuevo", nuevo);
 obj.put("id_trabajo", trabajo.getId_trabajo());
 obj.put("nombre_trabajo", trabajo.getNombre_trabajo());
 //obj.put("fechainicio_trabajo", String.valueOf(trabajo.getFechainicio_trabajo()));
 //obj.put("fechafin_trabajo", String.valueOf(trabajo.getFechafin_trabajo()));
 obj.put("id_egresado", trabajo.getEgresado().getId_egresado());
 obj.put("nombre_egresado", trabajo.getEgresado().getNombre_egresado());
 obj.put("apellido_egresado", trabajo.getEgresado().getApellido_egresado());
 obj.put("cedula_egresado", trabajo.getEgresado().getCedula_egresado());
 obj.put("telefono_egresado", trabajo.getEgresado().getTelefono_egresado());
 obj.put("edad_egresado", trabajo.getEgresado().getEdad_egresado());
 obj.put("nombre_ciudad", trabajo.getEgresado().getCiudad().getNombre_ciudad());
 obj.put("contenido_detalle", contenido_detalle);

 
 
 
 out.print(obj);
 out.flush();
%>
