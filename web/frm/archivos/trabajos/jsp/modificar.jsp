<%-- 
    Document   : modificar.jsp
    Created on : May 24, 2017, 7:37:57 AM
    Author     : Administrator
--%>




<%@page import="modelos.Egresados"%>
<%@page import="utiles.Utiles"%>
<%@page import="controladores.TrabajosControlador"%>
<%@page import="modelos.Trabajos"%>
<%@page import="org.json.simple.JSONObject"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>


<%
int id_trabajo = Integer.parseInt(request.getParameter("id_trabajo"));
String nombre_trabajo = request.getParameter("nombre_trabajo");
int id_egresado = Integer.parseInt(request.getParameter("id_egresado"));


String tipo="error";
Trabajos trabajo=new Trabajos();
trabajo.setId_trabajo(id_trabajo);
trabajo.setNombre_trabajo(nombre_trabajo);
//trabajo.setFechainicio_trabajo(Fechainicio_trabajo);
//trabajo.setFechafin_trabajo(Fechafin_trabajo);

//EGRESADOS  
    Egresados egresado = new Egresados();
    egresado.setId_egresado(id_egresado);
    trabajo.setEgresado(egresado);



String mensaje="Datos no modificados";
if(TrabajosControlador.modificar(trabajo)){
    tipo="sucess";
    mensaje="Datos modificados correctamente";
};
JSONObject obj=new JSONObject();
obj.put("tipo",tipo);
obj.put("mensaje",String.valueOf(mensaje));
out.print(obj);
out.flush();


%>