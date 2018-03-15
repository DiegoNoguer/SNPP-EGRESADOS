
<%@page import="controladores.DetallesTrabajosControlador"%>
<%@page import="modelos.DetallesTrabajos"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_detalletrabajo = Integer.parseInt(request.getParameter("id_detalletrabajo"));

    String tipo = "error";
    String mensaje = "Datos no eliminados.";

    DetallesTrabajos detalletrabajo = new DetallesTrabajos();
    detalletrabajo.setId_detalletrabajo(id_detalletrabajo);

    if (DetallesTrabajosControlador.eliminar(detalletrabajo)) {
        tipo = "success";
        mensaje = "Datos eliminados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>