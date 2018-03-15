<%-- 
    Document   : agregar
    Created on : May 3, 2017, 9:03:37 AM
    Author     : Diego Lima
--%>




<%@page import="modelos.Especialidades"%>
<%@page import="controladores.CursosControlador"%>
<%@page import="modelos.Cursos"%>
<%@page import="org.json.simple.JSONObject"%>


<%
  //  int id_curso =Integer.parseInt(request.getParameter("id_curso"));
      String nombre_curso=request.getParameter("nombre_curso");
      String tipo_curso=request.getParameter("tipo_curso");
      String duracion_curso=request.getParameter("duracion_curso");
      String año_curso=request.getParameter("año_curso");
      int id_especialidad =Integer.parseInt(request.getParameter("id_especialidad"));
      //int id_color =Integer.parseInt(request.getParameter("id_color"));
      //int id_rubro =Integer.parseInt(request.getParameter("id_rubro"));
    String tipo ="error";
    String mensaje= "Datos no agregados"  ;
    
   
    Cursos curso = new Cursos();
   // curso.setId_curso(id_curso);
      curso.setNombre_curso(nombre_curso);
      curso.setTipo_curso(tipo_curso);
      curso.setDuracion_curso(duracion_curso);
      curso.setAño_curso(año_curso);
      
   Especialidades especialidad = new Especialidades();
   especialidad.setId_especialidad(id_especialidad);
   curso.setEspecialidad(especialidad);
      
      
      
 if (CursosControlador.agregar(curso)){
     tipo ="success";
     mensaje="Datos Agregados.";
 }

JSONObject obj = new JSONObject();
obj.put("tipo",tipo);
obj.put("mensaje",mensaje);
out.print(obj);
out.flush();

%>


