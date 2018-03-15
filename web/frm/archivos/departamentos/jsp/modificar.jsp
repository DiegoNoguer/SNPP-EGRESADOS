<%-- 
    Document   : modificar.jsp
    Created on : May 24, 2017, 7:37:57 AM
    Author     : Administrator
--%>




<%@page import="controladores.DepartamentosControlador"%>
<%@page import="modelos.Departamentos"%>
<%@page import="org.json.simple.JSONObject"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>


<%
int id_departamento = Integer.parseInt(request.getParameter("id_departamento"));
String nombre_departamento = request.getParameter("nombre_departamento");



String tipo="error";
Departamentos departamento=new Departamentos();
departamento.setId_departamento(id_departamento);


departamento.setNombre_departamento(nombre_departamento);



String mensaje="Datos no modificados";
if(DepartamentosControlador.modificar(departamento)){
    tipo="sucess";
    mensaje="Datos modificados correctamente";
};
JSONObject obj=new JSONObject();
obj.put("tipo",tipo);
obj.put("mensaje",String.valueOf(mensaje));
out.print(obj);
out.flush();


%>