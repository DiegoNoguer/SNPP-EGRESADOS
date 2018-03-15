
<%@page import="controladores.DetallesRegistrosControlador"%>
<%@page import="modelos.DetallesRegistros"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_detalleregistro = Integer.parseInt(request.getParameter("id_detalleregistro"));

    String tipo = "error";
    String mensaje = "Datos no eliminados.";

    DetallesRegistros detalleregistro = new DetallesRegistros();
    detalleregistro.setId_detalleregistro(id_detalleregistro);

    if (DetallesRegistrosControlador.eliminar(detalleregistro)) {
        tipo = "success";
        mensaje = "Datos eliminados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>