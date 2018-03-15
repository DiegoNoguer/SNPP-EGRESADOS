<%-- 
    Document   : agregar
    Created on : May 3, 2017, 9:03:37 AM
    Author     : Diego Lima
--%>




<%@page import="modelos.Egresados"%>
<%@page import="utiles.Utiles"%>
<%@page import="controladores.RegistrosControlador"%>
<%@page import="modelos.Registros"%>
<%@page import="org.json.simple.JSONObject"%>


<%
  //  int id_registro =Integer.parseInt(request.getParameter("id_registro"));
    String nombre_registro=request.getParameter("nombre_registro");
    String sfecha_registro = request.getParameter("fecha_registro");
    java.sql.Date fecha_registro = Utiles.stringToSqlDate(sfecha_registro);
    int id_egresado =Integer.parseInt(request.getParameter("id_egresado"));
    
    String tipo ="error";
    String mensaje= "Datos no agregados"  ;
    
   
    Registros registro = new Registros();
   // registro.setId_registro(id_registro);
    registro.setNombre_registro(nombre_registro);
    registro.setFecha_registro(fecha_registro);
    
    
    //EGRESADO       
   Egresados egresado = new Egresados();
   egresado.setId_egresado(id_egresado);
   registro.setEgresado(egresado);
 if (RegistrosControlador.agregar(registro)){
     tipo ="success";
     mensaje="Datos Agregados.";
 }

JSONObject obj = new JSONObject();
obj.put("tipo",tipo);

    obj.put("id_registro", String.valueOf(registro.getId_registro()));
obj.put("mensaje",mensaje);
out.print(obj);
out.flush();

%>


