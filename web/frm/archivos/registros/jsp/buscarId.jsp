<%-- 
    Document   : buscarid
    Created on : May 10, 2017, 8:52:52 AM
    Author     : Administrator
--%>





<%@page import="controladores.DetallesRegistrosControlador"%>
<%@page import="controladores.RegistrosControlador"%>
<%@page import="modelos.Registros"%>
<%@page import="org.json.simple.JSONObject"%>
<%
 int id_registro = 0;
 if (request.getParameter("id_registro") != "") {
 id_registro = Integer.parseInt(request.getParameter("id_registro"));
}
 

 String tipo = "error";
 String mensaje = "Datos no encontrados.";
 String nuevo = "true";
 Registros registro = new Registros();
 registro.setId_registro(id_registro);
 registro = RegistrosControlador.buscarId(registro);
 if (registro.getId_registro() != 0) {
 tipo = "success";
 
 mensaje = "Datos encontrados";
 nuevo = "false";
}
   // 
 //String contenido_detalle = DetallesRegistrosControlador.buscarIdRegistro1(id_registro);
String contenido_detalle = DetallesRegistrosControlador.buscarIdRegistro(id_registro);
 JSONObject obj = new JSONObject();
 obj.put("tipo", tipo);
 obj.put("mensaje", mensaje);
 obj.put("nuevo", nuevo);
 obj.put("id_registro", registro.getId_registro());
 obj.put("nombre_registro", registro.getNombre_registro());
 obj.put("fecha_registro", String.valueOf(registro.getFecha_registro()));
 obj.put("id_egresado", registro.getEgresado().getId_egresado());
 obj.put("nombre_egresado", registro.getEgresado().getNombre_egresado());
 obj.put("apellido_egresado", registro.getEgresado().getApellido_egresado());
 obj.put("cedula_egresado", registro.getEgresado().getCedula_egresado());
 obj.put("telefono_egresado", registro.getEgresado().getTelefono_egresado());
 obj.put("edad_egresado", registro.getEgresado().getEdad_egresado());
 obj.put("nombre_ciudad", registro.getEgresado().getCiudad().getNombre_ciudad());
 obj.put("contenido_detalle", contenido_detalle);

 
 
 
 out.print(obj);
 out.flush();
%>
