<%-- 
    Document   : agregar
    Created on : May 3, 2017, 9:03:37 AM
    Author     : Diego Lima
--%>




<%@page import="modelos.Areas"%>
<%@page import="controladores.PuestosControlador"%>
<%@page import="modelos.Puestos"%>
<%@page import="org.json.simple.JSONObject"%>


<%
  //  int id_puesto =Integer.parseInt(request.getParameter("id_puesto"));
    String nombre_puesto=request.getParameter("nombre_puesto");
    int id_area =Integer.parseInt(request.getParameter("id_area"));
    
 
    String tipo ="error";
    String mensaje= "Datos no agregados"  ;
    
   
    Puestos puesto = new Puestos();
   // puesto.setId_puesto(id_puesto);
    puesto.setNombre_puesto(nombre_puesto);
    
    //Area       
   Areas area = new Areas();
   area.setId_area(id_area);
   puesto.setArea(area);
    
    
    
 if (PuestosControlador.agregar(puesto)){
     tipo ="success";
     mensaje="Datos Agregados.";
 }

JSONObject obj = new JSONObject();
obj.put("tipo",tipo);
obj.put("mensaje",mensaje);
out.print(obj);
out.flush();

%>


