
<%@page import="controladores.DetallesOportunidadesControlador_2"%>
<%@page import="controladores.DetallesOportunidadesControlador"%>
<%@page import="modelos.DetallesOportunidades"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_detalleoportunidad = Integer.parseInt(request.getParameter("id_detalleoportunidad"));

    String tipo = "error";
    String mensaje = "Datos no eliminados.";

    DetallesOportunidades detalleoportunidad = new DetallesOportunidades();
    detalleoportunidad.setId_detalleoportunidad(id_detalleoportunidad);

    if (DetallesOportunidadesControlador_2.eliminar(detalleoportunidad)) {
        tipo = "success";
        mensaje = "Datos eliminados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>