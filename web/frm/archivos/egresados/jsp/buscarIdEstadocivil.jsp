<%-- 
    Document   : buscarid
    Created on : May 10, 2017, 8:52:52 AM
    Author     : Administrator
--%>





<%@page import="controladores.EstadocivilesControlador"%>
<%@page import="modelos.Estadociviles"%>
<%@page import="org.json.simple.JSONObject"%>
<%
 int id_estadocivil = 0;
 if (request.getParameter("id_estadocivil") != "") {
 id_estadocivil = Integer.parseInt(request.getParameter("id_estadocivil"));

}
 

 String tipo = "error";
 String mensaje = "Datos no encontrados.";
 String nuevo = "true";
 Estadociviles estadocivil = new Estadociviles();
 estadocivil.setId_estadocivil(id_estadocivil);
 
 estadocivil = EstadocivilesControlador.buscarId(estadocivil);
 if (estadocivil.getId_estadocivil() != 0) {
 tipo = "success";
 
 mensaje = "Datos encontrados";
 nuevo = "false";
}
 JSONObject obj = new JSONObject();
 obj.put("tipo", tipo);
 obj.put("mensaje", mensaje);
 obj.put("nuevo", nuevo);
 obj.put("id_estadocivil", estadocivil.getId_estadocivil());
 obj.put("nombre_estadocivil", estadocivil.getNombre_estadocivil());
 
 
 
 out.print(obj);
 out.flush();
%>
