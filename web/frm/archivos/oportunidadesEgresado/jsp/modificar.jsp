<%-- 
    Document   : modificar.jsp
    Created on : May 24, 2017, 7:37:57 AM
    Author     : Administrator
--%>




<%@page import="modelos.Empresas"%>
<%@page import="utiles.Utiles"%>
<%@page import="controladores.OportunidadesControlador"%>
<%@page import="modelos.Oportunidades"%>
<%@page import="org.json.simple.JSONObject"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>


<%
int id_oportunidad = Integer.parseInt(request.getParameter("id_oportunidad"));
String nombre_oportunidad = request.getParameter("nombre_oportunidad");
String sFecha_oportunidad = request.getParameter("fecha_oportunidad");
java.sql.Date Fecha_oportunidad = Utiles.stringToSqlDate(sFecha_oportunidad);
int id_empresa = Integer.parseInt(request.getParameter("id_empresa"));


String tipo="error";
Oportunidades oportunidad=new Oportunidades();
oportunidad.setId_oportunidad(id_oportunidad);
oportunidad.setNombre_oportunidad(nombre_oportunidad);
oportunidad.setFecha_oportunidad(Fecha_oportunidad);

//EGRESADOS  
    Empresas empresa = new Empresas();
    empresa.setId_empresa(id_empresa);
    oportunidad.setEmpresa(empresa);



String mensaje="Datos no modificados";
if(OportunidadesControlador.modificar(oportunidad)){
    tipo="sucess";
    mensaje="Datos modificados correctamente";
};
JSONObject obj=new JSONObject();
obj.put("tipo",tipo);
obj.put("mensaje",String.valueOf(mensaje));
out.print(obj);
out.flush();


%>