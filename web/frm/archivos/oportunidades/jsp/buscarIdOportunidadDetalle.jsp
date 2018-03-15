




<%@page import="controladores.DetallesOportunidadesControlador"%>
<%@page import="modelos.Puestos"%>
<%@page import="modelos.Oportunidades"%>
<%@page import="modelos.DetallesOportunidades"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_detalleoportunidad = Integer.parseInt(request.getParameter("id_detalleoportunidad"));

    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";

    DetallesOportunidades detalleoportunidad = DetallesOportunidadesControlador.buscarId(id_detalleoportunidad);
    if (detalleoportunidad != null) {
        tipo = "success";
        mensaje = "Datos encontrados.";
        nuevo = "false";
    } else {
        detalleoportunidad = new DetallesOportunidades();
        detalleoportunidad.setId_detalleoportunidad(0);
        
        Oportunidades oportunidad = new Oportunidades();
        oportunidad.setId_oportunidad(0);
        detalleoportunidad.setOportunidad(oportunidad);
        
        Puestos puesto = new Puestos();
        puesto.setId_puesto(0);
        detalleoportunidad.setPuesto(puesto);
        
   
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);

    obj.put("id_detalleoportunidad", String.valueOf(detalleoportunidad.getId_detalleoportunidad()));
    obj.put("id_oportunidad", String.valueOf(detalleoportunidad.getOportunidad().getId_oportunidad()));
    obj.put("id_puesto", String.valueOf(detalleoportunidad.getPuesto().getId_puesto()));
    obj.put("nombre_puesto", detalleoportunidad.getPuesto().getNombre_puesto());
    obj.put("nombre_area", detalleoportunidad.getPuesto().getArea().getNombre_area());
    obj.put("vacancia_detalleoportunidad", detalleoportunidad.getVacancia_detalleoportunidad());
    obj.put("horario_detalleoportunidad", detalleoportunidad.getHorario_detalleoportunidad());
    obj.put("rangoedad_detalleoportunidad", detalleoportunidad.getRangoedad_detalleoportunidad());
    out.print(obj);
    out.flush();
%>