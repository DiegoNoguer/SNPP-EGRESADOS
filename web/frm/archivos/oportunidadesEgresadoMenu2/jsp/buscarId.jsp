<%-- 
    Document   : buscarid
    Created on : May 10, 2017, 8:52:52 AM
    Author     : Administrator
--%>





<%@page import="controladores.DetallesOportunidadesControlador_1"%>
<%@page import="controladores.OportunidadesControlador"%>
<%@page import="modelos.Oportunidades"%>
<%@page import="org.json.simple.JSONObject"%>
<%
 int id_oportunidad = 0;
 if (request.getParameter("id_oportunidad") != "") {
 id_oportunidad = Integer.parseInt(request.getParameter("id_oportunidad"));
}
 

 String tipo = "error";
 String mensaje = "Datos no encontrados.";
 String nuevo = "true";
 Oportunidades oportunidad = new Oportunidades();
 oportunidad.setId_oportunidad(id_oportunidad);
 
 oportunidad = OportunidadesControlador.buscarId(oportunidad);
 
 if (oportunidad.getId_oportunidad() != 0) {
 tipo = "success";
 
 mensaje = "Datos encontrados";
 nuevo = "false";
}
   // 
 //String contenido_detalle = DetallesOportunidadesControlador.buscarIdOportunidad1(id_oportunidad);
String contenido_detalle = DetallesOportunidadesControlador_1.buscarIdOportunidad(id_oportunidad);
 JSONObject obj = new JSONObject();
 obj.put("tipo", tipo);
 obj.put("mensaje", mensaje);
 obj.put("nuevo", nuevo);
 obj.put("id_oportunidad", oportunidad.getId_oportunidad());
 obj.put("nombre_oportunidad", oportunidad.getNombre_oportunidad());
 obj.put("fecha_oportunidad", String.valueOf(oportunidad.getFecha_oportunidad()));
 obj.put("id_empresa", oportunidad.getEmpresa().getId_empresa());
 obj.put("nombre_empresa", oportunidad.getEmpresa().getNombre_empresa());
 obj.put("email_empresa", oportunidad.getEmpresa().getEmail_empresa());
 obj.put("direccion_empresa", oportunidad.getEmpresa().getDireccion_empresa());
 obj.put("telefono_empresa", oportunidad.getEmpresa().getTelefono_empresa());
 obj.put("ruc_empresa", oportunidad.getEmpresa().getRuc_empresa());
 obj.put("nombre_ciudad", oportunidad.getEmpresa().getCiudad().getNombre_ciudad());
 obj.put("contenido_detalle", contenido_detalle);

 
 
 
 out.print(obj);
 out.flush();
%>
