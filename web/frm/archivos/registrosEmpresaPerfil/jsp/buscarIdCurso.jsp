<%-- 
    Document   : buscarid
    Created on : May 10, 2017, 8:52:52 AM
    Author     : Administrator
--%>





<%@page import="controladores.CursosControlador"%>
<%@page import="modelos.Cursos"%>
<%@page import="org.json.simple.JSONObject"%>
<%
 int id_curso = 0;
 if (request.getParameter("id_curso") != "") {
 id_curso = Integer.parseInt(request.getParameter("id_curso"));
}
 

 String tipo = "error";
 String mensaje = "Datos no encontrados.";
 String nuevo = "true";
 Cursos curso = new Cursos();
 curso.setId_curso(id_curso);
 curso = CursosControlador.buscarId(curso);
 if (curso.getId_curso() != 0) {
 tipo = "success";
 
 mensaje = "Datos encontrados";
 nuevo = "false";
}
 JSONObject obj = new JSONObject();
 obj.put("tipo", tipo);
 obj.put("mensaje", mensaje);
 obj.put("nuevo", nuevo);
 obj.put("id_curso", curso.getId_curso());
 obj.put("nombre_curso", curso.getNombre_curso());
 obj.put("tipo_curso", curso.getTipo_curso());
 obj.put("duracion_curso", curso.getDuracion_curso());
 obj.put("año_curso", curso.getAño_curso());
 obj.put("id_especialidad",curso.getEspecialidad().getId_especialidad());
 obj.put("nombre_especialidad",curso.getEspecialidad().getNombre_especialidad());
 
 
 out.print(obj);
 out.flush();
%>
