<%-- 
    Document   : agregar
    Created on : May 3, 2017, 9:03:37 AM
    Author     : Diego Lima
--%>




<%@page import="modelos.Empresas"%>
<%@page import="utiles.Utiles"%>
<%@page import="controladores.OportunidadesControlador"%>
<%@page import="modelos.Oportunidades"%>
<%@page import="org.json.simple.JSONObject"%>


<%
  //  int id_oportunidad =Integer.parseInt(request.getParameter("id_oportunidad"));
    String nombre_oportunidad=request.getParameter("nombre_oportunidad");
    String sfecha_oportunidad = request.getParameter("fecha_oportunidad");
    java.sql.Date fecha_oportunidad = Utiles.stringToSqlDate(sfecha_oportunidad);
    int id_empresa =Integer.parseInt(request.getParameter("id_empresa"));
    
    String tipo ="error";
    String mensaje= "Datos no agregados"  ;
    
   
    Oportunidades oportunidad = new Oportunidades();
   // oportunidad.setId_oportunidad(id_oportunidad);
    oportunidad.setNombre_oportunidad(nombre_oportunidad);
    oportunidad.setFecha_oportunidad(fecha_oportunidad);
    
    
    //EGRESADO       
   Empresas empresa = new Empresas();
   empresa.setId_empresa(id_empresa);
   oportunidad.setEmpresa(empresa);
 if (OportunidadesControlador.agregar(oportunidad)){
     tipo ="success";
     mensaje="Datos Agregados.";
 }

JSONObject obj = new JSONObject();
obj.put("tipo",tipo);

    obj.put("id_oportunidad", String.valueOf(oportunidad.getId_oportunidad()));
obj.put("mensaje",mensaje);
out.print(obj);
out.flush();

%>


