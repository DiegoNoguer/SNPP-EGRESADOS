<%-- 
    Document   : modificar.jsp
    Created on : May 24, 2017, 7:37:57 AM
    Author     : Administrator
--%>




<%@page import="modelos.Especialidades"%>
<%@page import="controladores.CursosControlador"%>
<%@page import="modelos.Cursos"%>
<%@page import="org.json.simple.JSONObject"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>


<%
int id_curso = Integer.parseInt(request.getParameter("id_curso"));
String nombre_curso = request.getParameter("nombre_curso");
String tipo_curso = request.getParameter("tipo_curso");
String duracion_curso = request.getParameter("duracion_curso");
String año_curso = request.getParameter("año_curso");
int id_especialidad = Integer.parseInt(request.getParameter("id_especialidad"));


String tipo="error";
Cursos curso=new Cursos();
curso.setId_curso(id_curso);


curso.setNombre_curso(nombre_curso);
curso.setTipo_curso(tipo_curso);
curso.setDuracion_curso(duracion_curso);
curso.setAño_curso(año_curso);


Especialidades especialidad = new Especialidades();
    especialidad.setId_especialidad(id_especialidad);
    curso.setEspecialidad(especialidad);


String mensaje="Datos no modificados";
if(CursosControlador.modificar(curso)){
    tipo="sucess";
    mensaje="Datos modificados correctamente";
};
JSONObject obj=new JSONObject();
obj.put("tipo",tipo);
obj.put("mensaje",String.valueOf(mensaje));
out.print(obj);
out.flush();


%>