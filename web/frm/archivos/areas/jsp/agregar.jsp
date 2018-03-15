<%-- 
    Document   : agregar
    Created on : May 3, 2017, 9:03:37 AM
    Author     : Diego Lima
--%>




<%@page import="controladores.AreasControlador"%>
<%@page import="modelos.Areas"%>
<%@page import="org.json.simple.JSONObject"%>


<%
  //  int id_area =Integer.parseInt(request.getParameter("id_area"));
    String nombre_area=request.getParameter("nombre_area");
      //int id_color =Integer.parseInt(request.getParameter("id_color"));
      //int id_rubro =Integer.parseInt(request.getParameter("id_rubro"));
    String tipo ="error";
    String mensaje= "Datos no agregados"  ;
    
   
    Areas area = new Areas();
   // area.setId_area(id_area);
    area.setNombre_area(nombre_area);
 if (AreasControlador.agregar(area)){
     tipo ="success";
     mensaje="Datos Agregados.";
 }

JSONObject obj = new JSONObject();
obj.put("tipo",tipo);
obj.put("mensaje",mensaje);
out.print(obj);
out.flush();

%>


