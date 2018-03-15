


<%@page import="controladores.DetallesOportunidadesControlador"%>
<%@page import="modelos.Puestos"%>
<%@page import="modelos.Oportunidades"%>
<%@page import="modelos.DetallesOportunidades"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    
    int id_detalleoportunidad = Integer.parseInt(request.getParameter("id_detalleoportunidad"));
   int id_oportunidad = Integer.parseInt(request.getParameter("id_oportunidad"));
    int id_puesto = Integer.parseInt(request.getParameter("id_puesto")); 
    int vacancia_detalleoportunidad = Integer.parseInt(request.getParameter("vacancia_detalleoportunidad"));
    String horario_detalleoportunidad = request.getParameter("horario_detalleoportunidad");  
    String rangoedad_detalleoportunidad = request.getParameter("rangoedad_detalleoportunidad"); 
    
     
    
    String tipo = "error";
    String mensaje = "Datos no modificados.";
    
    DetallesOportunidades detalleoportunidad = new DetallesOportunidades();
    detalleoportunidad.setId_detalleoportunidad(id_detalleoportunidad);
    detalleoportunidad.setVacancia_detalleoportunidad(vacancia_detalleoportunidad);
    detalleoportunidad.setHorario_detalleoportunidad(horario_detalleoportunidad);
    detalleoportunidad.setRangoedad_detalleoportunidad(rangoedad_detalleoportunidad);
 
    
    Oportunidades oportunidad = new Oportunidades();
    oportunidad.setId_oportunidad(id_oportunidad);
   
    Puestos puesto = new Puestos();
    puesto.setId_puesto(id_puesto);
    
    detalleoportunidad.setOportunidad(oportunidad);
    detalleoportunidad.setPuesto(puesto);
      
    if (DetallesOportunidadesControlador.modificar(detalleoportunidad)) {
        tipo = "success";
        mensaje = "Datos modificados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
    
%>