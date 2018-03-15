<%-- 
    Document   : modificar.jsp
    Created on : May 24, 2017, 7:37:57 AM
    Author     : Administrator
--%>




<%@page import="controladores.EstadocivilesControlador"%>
<%@page import="modelos.Estadociviles"%>
<%@page import="org.json.simple.JSONObject"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>


<%
int id_estadocivil = Integer.parseInt(request.getParameter("id_estadocivil"));
String nombre_estadocivil = request.getParameter("nombre_estadocivil");



String tipo="error";
Estadociviles estadocivil=new Estadociviles();
estadocivil.setId_estadocivil(id_estadocivil);


estadocivil.setNombre_estadocivil(nombre_estadocivil);



String mensaje="Datos no modificados";
if(EstadocivilesControlador.modificar(estadocivil)){
    tipo="sucess";
    mensaje="Datos modificados correctamente";
};
JSONObject obj=new JSONObject();
obj.put("tipo",tipo);
obj.put("mensaje",String.valueOf(mensaje));
out.print(obj);
out.flush();


%>