<%-- 
    Document   : agregar
    Created on : May 3, 2017, 9:03:37 AM
    Author     : Diego Lima
--%>




<%@page import="controladores.DepartamentosControlador"%>
<%@page import="modelos.Departamentos"%>
<%@page import="org.json.simple.JSONObject"%>


<%
  //  int id_departamento =Integer.parseInt(request.getParameter("id_departamento"));
    String nombre_departamento=request.getParameter("nombre_departamento");
      //int id_color =Integer.parseInt(request.getParameter("id_color"));
      //int id_rubro =Integer.parseInt(request.getParameter("id_rubro"));
    String tipo ="error";
    String mensaje= "Datos no agregados"  ;
    
   
    Departamentos departamento = new Departamentos();
   // departamento.setId_departamento(id_departamento);
    departamento.setNombre_departamento(nombre_departamento);
 if (DepartamentosControlador.agregar(departamento)){
     tipo ="success";
     mensaje="Datos Agregados.";
 }

JSONObject obj = new JSONObject();
obj.put("tipo",tipo);
obj.put("mensaje",mensaje);
out.print(obj);
out.flush();

%>


