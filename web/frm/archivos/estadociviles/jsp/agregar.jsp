<%-- 
    Document   : agregar
    Created on : May 3, 2017, 9:03:37 AM
    Author     : Diego Lima
--%>




<%@page import="controladores.EstadocivilesControlador"%>
<%@page import="modelos.Estadociviles"%>
<%@page import="org.json.simple.JSONObject"%>


<%
  //  int id_estadocivil =Integer.parseInt(request.getParameter("id_estadocivil"));
    String nombre_estadocivil=request.getParameter("nombre_estadocivil");
    
    
      //int id_color =Integer.parseInt(request.getParameter("id_color"));
      //int id_rubro =Integer.parseInt(request.getParameter("id_rubro"));
      
    String tipo ="error";
    String mensaje= "Datos no agregados"  ;
    
   
    Estadociviles estadocivil = new Estadociviles();
   // estadocivil.setId_estadocivil(id_estadocivil);
    estadocivil.setNombre_estadocivil(nombre_estadocivil);
    
    
    

 if (EstadocivilesControlador.agregar(estadocivil)){
     tipo ="success";
     mensaje="Datos Agregados.";
 }

JSONObject obj = new JSONObject();
obj.put("tipo",tipo);
obj.put("mensaje",mensaje);
out.print(obj);
out.flush();

%>


