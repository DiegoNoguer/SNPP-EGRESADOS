<%-- 
    Document   : agregar
    Created on : May 3, 2017, 9:03:37 AM
    Author     : Diego Lima
--%>




<%@page import="modelos.Egresados"%>
<%@page import="utiles.Utiles"%>
<%@page import="controladores.TrabajosControlador"%>
<%@page import="modelos.Trabajos"%>
<%@page import="org.json.simple.JSONObject"%>


<%
  //  int id_trabajo =Integer.parseInt(request.getParameter("id_trabajo"));
    String nombre_trabajo=request.getParameter("nombre_trabajo");
  //  String sfechainicio_trabajo = request.getParameter("fechainicio_trabajo");
  //  java.sql.Date fechainicio_trabajo = Utiles.stringToSqlDate(sfechainicio_trabajo);
  //  String sfechafin_trabajo = request.getParameter("fechafin_trabajo");
  //  java.sql.Date fechafin_trabajo = Utiles.stringToSqlDate(sfechafin_trabajo);
    
    int id_egresado =Integer.parseInt(request.getParameter("id_egresado"));
    
    String tipo ="error";
    String mensaje= "Datos no agregados"  ;
    
   
    Trabajos trabajo = new Trabajos();
   // trabajo.setId_trabajo(id_trabajo);
    trabajo.setNombre_trabajo(nombre_trabajo);
 //   trabajo.setFechainicio_trabajo(fechainicio_trabajo);
   // trabajo.setFechafin_trabajo(fechafin_trabajo);
    
    
    //EGRESADO       
   Egresados egresado = new Egresados();
   egresado.setId_egresado(id_egresado);
   trabajo.setEgresado(egresado);
 if (TrabajosControlador.agregar(trabajo)){
     tipo ="success";
     mensaje="Datos Agregados.";
 }

JSONObject obj = new JSONObject();
obj.put("tipo",tipo);

    obj.put("id_trabajo", String.valueOf(trabajo.getId_trabajo()));
obj.put("mensaje",mensaje);
out.print(obj);
out.flush();

%>


