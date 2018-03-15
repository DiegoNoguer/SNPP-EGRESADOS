<%-- 
    Document   : modificar.jsp
    Created on : May 24, 2017, 7:37:57 AM
    Author     : Administrator
--%>





<%@page import="modelos.Ciudades"%>
<%@page import="controladores.EmpresasControlador"%>
<%@page import="modelos.Empresas"%>
<%@page import="org.json.simple.JSONObject"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>


<%
int id_empresa = Integer.parseInt(request.getParameter("id_empresa"));
String nombre_empresa = request.getParameter("nombre_empresa");
String email_empresa = request.getParameter("email_empresa");
String direccion_empresa = request.getParameter("direccion_empresa");
String telefono_empresa = request.getParameter("telefono_empresa");
String ruc_empresa = request.getParameter("ruc_empresa");
int id_ciudad = Integer.parseInt(request.getParameter("id_ciudad"));




String tipo="error";
Empresas empresa=new Empresas();
empresa.setId_empresa(id_empresa);


empresa.setNombre_empresa(nombre_empresa);
empresa.setEmail_empresa(email_empresa);
empresa.setDireccion_empresa(direccion_empresa);
empresa.setTelefono_empresa(telefono_empresa);
empresa.setRuc_empresa(ruc_empresa);

//CIUDADES
Ciudades ciudad = new Ciudades();
ciudad.setId_ciudad(id_ciudad);
empresa.setCiudad(ciudad);




String mensaje="Datos no modificados";
if(EmpresasControlador.modificar(empresa)){
    tipo="sucess";
    mensaje="Datos modificados correctamente";
};
JSONObject obj=new JSONObject();
obj.put("tipo",tipo);
obj.put("mensaje",String.valueOf(mensaje));
out.print(obj);
out.flush();


%>