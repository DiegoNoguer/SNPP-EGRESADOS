<%-- 
    Document   : modificar.jsp
    Created on : May 24, 2017, 7:37:57 AM
    Author     : Administrator
--%>




<%@page import="utiles.Utiles"%>
<%@page import="modelos.Departamentos"%>
<%@page import="controladores.CiudadesControlador"%>
<%@page import="modelos.Ciudades"%>
<%@page import="org.json.simple.JSONObject"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>


<%
int id_ciudad = Integer.parseInt(request.getParameter("id_ciudad"));
String nombre_ciudad = request.getParameter("nombre_ciudad");
int id_departamento = Integer.parseInt(request.getParameter("id_departamento"));




String tipo="error";
Ciudades ciudad=new Ciudades();
ciudad.setId_ciudad(id_ciudad);
ciudad.setNombre_ciudad(nombre_ciudad);

//Departamentos 
    Departamentos departamento = new Departamentos();
    departamento.setId_departamento(id_departamento);
    ciudad.setDepartamento(departamento);





String mensaje="Datos no modificados";
if(CiudadesControlador.modificar(ciudad)){
    tipo="sucess";
    mensaje="Datos modificados correctamente";
};
JSONObject obj=new JSONObject();
obj.put("tipo",tipo);
obj.put("mensaje",String.valueOf(mensaje));
out.print(obj);
out.flush();


%>