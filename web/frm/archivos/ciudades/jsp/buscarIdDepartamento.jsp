<%-- 
    Document   : buscarid
    Created on : May 10, 2017, 8:52:52 AM
    Author     : Administrator
--%>





<%@page import="controladores.DepartamentosControlador"%>
<%@page import="modelos.Departamentos"%>
<%@page import="org.json.simple.JSONObject"%>
<%
 int id_departamento = 0;
 if (request.getParameter("id_departamento") != "") {
 id_departamento = Integer.parseInt(request.getParameter("id_departamento"));
}
 

 String tipo = "error";
 String mensaje = "Datos no encontrados.";
 String nuevo = "true";
 Departamentos departamento = new Departamentos();
 departamento.setId_departamento(id_departamento);
 departamento = DepartamentosControlador.buscarId(departamento);
 if (departamento.getId_departamento() != 0) {
 tipo = "success";
 
 mensaje = "Datos encontrados";
 nuevo = "false";
}
 JSONObject obj = new JSONObject();
 obj.put("tipo", tipo);
 obj.put("mensaje", mensaje);
 obj.put("nuevo", nuevo);
 obj.put("id_departamento", departamento.getId_departamento());
 obj.put("nombre_departamento", departamento.getNombre_departamento());
 
 
 
 out.print(obj);
 out.flush();
%>
