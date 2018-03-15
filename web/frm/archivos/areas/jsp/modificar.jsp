<%-- 
    Document   : modificar.jsp
    Created on : May 24, 2017, 7:37:57 AM
    Author     : Administrator
--%>




<%@page import="controladores.AreasControlador"%>
<%@page import="modelos.Areas"%>
<%@page import="org.json.simple.JSONObject"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>


<%
int id_area = Integer.parseInt(request.getParameter("id_area"));
String nombre_area = request.getParameter("nombre_area");



String tipo="error";
Areas area=new Areas();
area.setId_area(id_area);


area.setNombre_area(nombre_area);



String mensaje="Datos no modificados";
if(AreasControlador.modificar(area)){
    tipo="sucess";
    mensaje="Datos modificados correctamente";
};
JSONObject obj=new JSONObject();
obj.put("tipo",tipo);
obj.put("mensaje",String.valueOf(mensaje));
out.print(obj);
out.flush();


%>