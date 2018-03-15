<%-- 
    Document   : modificar.jsp
    Created on : May 24, 2017, 7:37:57 AM
    Author     : Administrator
--%>




<%@page import="controladores.EspecialidadesControlador"%>
<%@page import="modelos.Especialidades"%>
<%@page import="org.json.simple.JSONObject"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>


<%
int id_especialidad = Integer.parseInt(request.getParameter("id_especialidad"));
String nombre_especialidad = request.getParameter("nombre_especialidad");



String tipo="error";
Especialidades especialidad=new Especialidades();
especialidad.setId_especialidad(id_especialidad);


especialidad.setNombre_especialidad(nombre_especialidad);



String mensaje="Datos no modificados";
if(EspecialidadesControlador.modificar(especialidad)){
    tipo="sucess";
    mensaje="Datos modificados correctamente";
};
JSONObject obj=new JSONObject();
obj.put("tipo",tipo);
obj.put("mensaje",String.valueOf(mensaje));
out.print(obj);
out.flush();


%>