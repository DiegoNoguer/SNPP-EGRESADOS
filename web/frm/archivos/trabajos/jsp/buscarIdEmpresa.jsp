<%-- 
    Document   : buscarid
    Created on : May 10, 2017, 8:52:52 AM
    Author     : Administrator
--%>





<%@page import="controladores.EmpresasControlador"%>
<%@page import="modelos.Empresas"%>
<%@page import="org.json.simple.JSONObject"%>
<%
 int id_empresa = 0;
 if (request.getParameter("id_empresa") != "") {
 id_empresa = Integer.parseInt(request.getParameter("id_empresa"));
}
 

 String tipo = "error";
 String mensaje = "Datos no encontrados.";
 String nuevo = "true";
 Empresas empresa = new Empresas();
 empresa.setId_empresa(id_empresa);
 empresa = EmpresasControlador.buscarId(empresa);
 if (empresa.getId_empresa() != 0) {
 tipo = "success";
 
 mensaje = "Datos encontrados";
 nuevo = "false";
}
 JSONObject obj = new JSONObject();
 obj.put("tipo", tipo);
 obj.put("mensaje", mensaje);
 obj.put("nuevo", nuevo);
 obj.put("id_empresa", empresa.getId_empresa());
 obj.put("nombre_empresa", empresa.getNombre_empresa());
 obj.put("email_empresa", empresa.getEmail_empresa());
 obj.put("direccion_empresa", empresa.getDireccion_empresa());
 obj.put("telefono_empresa", empresa.getTelefono_empresa());
 obj.put("ruc_empresa", empresa.getRuc_empresa());
 //obj.put("buscamos_empresa", empresa.getBuscamos_empresa());
 //obj.put("turno_empresa", empresa.getTurno_empresa());
 //obj.put("horario_empresa", empresa.getHorario_empresa());
 //obj.put("salario_empresa", empresa.getSalario_empresa());
 //obj.put("vacancia_empresa", empresa.getVacancia_empresa());
 //obj.put("rangoedad_empresa", empresa.getRangoedad_empresa());
 //obj.put("tipogenero_empresa", empresa.getTipogenero_empresa());
 //obj.put("id_ciudad",empresa.getCiudad().getId_ciudad());
 //obj.put("nombre_ciudad",empresa.getCiudad().getNombre_ciudad());
 
 
 
 out.print(obj);
 out.flush();
%>
