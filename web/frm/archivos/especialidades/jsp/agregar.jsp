<%-- 
    Document   : agregar
    Created on : May 3, 2017, 9:03:37 AM
    Author     : Diego Lima
--%>




<%@page import="controladores.EspecialidadesControlador"%>
<%@page import="modelos.Especialidades"%>
<%@page import="org.json.simple.JSONObject"%>


<%
  //  int id_especialidad =Integer.parseInt(request.getParameter("id_especialidad"));
    String nombre_especialidad=request.getParameter("nombre_especialidad");
      //int id_color =Integer.parseInt(request.getParameter("id_color"));
      //int id_rubro =Integer.parseInt(request.getParameter("id_rubro"));
    String tipo ="error";
    String mensaje= "Datos no agregados"  ;
    
   
    Especialidades especialidad = new Especialidades();
   // especialidad.setId_especialidad(id_especialidad);
    especialidad.setNombre_especialidad(nombre_especialidad);
 if (EspecialidadesControlador.agregar(especialidad)){
     tipo ="success";
     mensaje="Datos Agregados.";
 }

JSONObject obj = new JSONObject();
obj.put("tipo",tipo);
obj.put("mensaje",mensaje);
out.print(obj);
out.flush();

%>


