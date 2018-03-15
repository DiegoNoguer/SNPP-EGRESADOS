<%-- 
    Document   : modificar.jsp
    Created on : May 24, 2017, 7:37:57 AM
    Author     : Administrator
--%>




<%@page import="modelos.Egresados"%>
<%@page import="utiles.Utiles"%>
<%@page import="controladores.RegistrosControlador"%>
<%@page import="modelos.Registros"%>
<%@page import="org.json.simple.JSONObject"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>


<%
int id_registro = Integer.parseInt(request.getParameter("id_registro"));
String nombre_registro = request.getParameter("nombre_registro");
String sFecha_registro = request.getParameter("fecha_registro");
java.sql.Date Fecha_registro = Utiles.stringToSqlDate(sFecha_registro);
int id_egresado = Integer.parseInt(request.getParameter("id_egresado"));


String tipo="error";
Registros registro=new Registros();
registro.setId_registro(id_registro);
registro.setNombre_registro(nombre_registro);
registro.setFecha_registro(Fecha_registro);

//EGRESADOS  
    Egresados egresado = new Egresados();
    egresado.setId_egresado(id_egresado);
    registro.setEgresado(egresado);



String mensaje="Datos no modificados";
if(RegistrosControlador.modificar(registro)){
    tipo="sucess";
    mensaje="Datos modificados correctamente";
};
JSONObject obj=new JSONObject();
obj.put("tipo",tipo);
obj.put("mensaje",String.valueOf(mensaje));
out.print(obj);
out.flush();


%>