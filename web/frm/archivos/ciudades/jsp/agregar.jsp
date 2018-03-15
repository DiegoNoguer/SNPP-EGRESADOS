<%-- 
    Document   : agregar
    Created on : May 3, 2017, 9:03:37 AM
    Author     : Diego Lima
--%>




<%@page import="modelos.Departamentos"%>
<%@page import="controladores.CiudadesControlador"%>
<%@page import="modelos.Ciudades"%>
<%@page import="org.json.simple.JSONObject"%>


<%
  //  int id_ciudad =Integer.parseInt(request.getParameter("id_ciudad"));
    String nombre_ciudad=request.getParameter("nombre_ciudad");
    int id_departamento = Integer.parseInt(request.getParameter("id_departamento"));
    
      //int id_color =Integer.parseInt(request.getParameter("id_color"));
      //int id_rubro =Integer.parseInt(request.getParameter("id_rubro"));
    String tipo ="error";
    String mensaje= "Datos no agregados"  ;
    
   
    Ciudades ciudad = new Ciudades();
    ciudad.setNombre_ciudad(nombre_ciudad);
   // ciudad.setId_ciudad(id_ciudad);
   
   //DEPARTAMENTO       
   Departamentos departamento = new Departamentos();
   departamento.setId_departamento(id_departamento);
   ciudad.setDepartamento(departamento);
  
     
 if (CiudadesControlador.agregar(ciudad)){
     tipo ="success";
     mensaje="Datos Agregados.";
 }

JSONObject obj = new JSONObject();
obj.put("tipo",tipo);
obj.put("mensaje",mensaje);
out.print(obj);
out.flush();

%>


