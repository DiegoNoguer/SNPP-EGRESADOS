<%-- 
    Document   : modificar.jsp
    Created on : May 24, 2017, 7:37:57 AM
    Author     : Administrator
--%>




<%@page import="modelos.Areas"%>
<%@page import="controladores.PuestosControlador"%>
<%@page import="modelos.Puestos"%>
<%@page import="org.json.simple.JSONObject"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>


<%
int id_puesto = Integer.parseInt(request.getParameter("id_puesto"));
String nombre_puesto = request.getParameter("nombre_puesto");
int id_area = Integer.parseInt(request.getParameter("id_area"));




String tipo="error";
Puestos puesto=new Puestos();
puesto.setId_puesto(id_puesto);


puesto.setNombre_puesto(nombre_puesto);


//AREAS 
    Areas area = new Areas();
    area.setId_area(id_area);
    puesto.setArea(area);



String mensaje="Datos no modificados";
if(PuestosControlador.modificar(puesto)){
    tipo="sucess";
    mensaje="Datos modificados correctamente";
};
JSONObject obj=new JSONObject();
obj.put("tipo",tipo);
obj.put("mensaje",String.valueOf(mensaje));
out.print(obj);
out.flush();


%>