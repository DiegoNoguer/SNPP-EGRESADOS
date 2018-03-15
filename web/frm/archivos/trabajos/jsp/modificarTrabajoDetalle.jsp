
<%@page import="utiles.Utiles"%>
<%@page import="controladores.DetallesTrabajosControlador"%>
<%@page import="modelos.Empresas"%>
<%@page import="modelos.Trabajos"%>
<%@page import="modelos.DetallesTrabajos"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%

    int id_detalletrabajo = Integer.parseInt(request.getParameter("id_detalletrabajo"));
    int id_trabajo = Integer.parseInt(request.getParameter("id_trabajo"));
    int id_empresa = Integer.parseInt(request.getParameter("id_empresa"));
    String sFechainicio_detalletrabajo = request.getParameter("fechainicio_detalletrabajo");
java.sql.Date Fechainicio_detalletrabajo = Utiles.stringToSqlDate(sFechainicio_detalletrabajo);
String sFechafin_detalletrabajo = request.getParameter("fechafin_detalletrabajo");
java.sql.Date Fechafin_detalletrabajo = Utiles.stringToSqlDate(sFechafin_detalletrabajo);

    String tipo = "error";
    String mensaje = "Datos no modificados.";

    DetallesTrabajos detalletrabajo = new DetallesTrabajos();
    detalletrabajo.setId_detalletrabajo(id_detalletrabajo);
    detalletrabajo.setFechainicio_detalletrabajo(Fechainicio_detalletrabajo);
detalletrabajo.setFechafin_detalletrabajo(Fechafin_detalletrabajo);
   

    Trabajos trabajo = new Trabajos();
    trabajo.setId_trabajo(id_trabajo);

    Empresas empresa = new Empresas();
    empresa.setId_empresa(id_empresa);
    

    detalletrabajo.setTrabajo(trabajo);
    detalletrabajo.setEmpresa(empresa);

    if (DetallesTrabajosControlador.modificar(detalletrabajo)) {
        tipo = "success";
        mensaje = "Datos modificados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();

%>