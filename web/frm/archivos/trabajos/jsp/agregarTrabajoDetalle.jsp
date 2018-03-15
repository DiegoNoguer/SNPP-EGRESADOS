
<%@page import="utiles.Utiles"%>
<%@page import="utiles.Utiles"%>
<%@page import="controladores.DetallesTrabajosControlador"%>
<%@page import="modelos.DetallesTrabajos"%>
<%@page import="modelos.Empresas"%>
<%@page import="modelos.Trabajos"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%

    int id_detalletrabajo = Integer.parseInt(request.getParameter("id_detalletrabajo"));
    
    int id_trabajo = Integer.parseInt(request.getParameter("id_trabajo"));
    int id_empresa = Integer.parseInt(request.getParameter("id_empresa"));
    // int total_trabajo= con + 0; 
   
    String sfechainicio_trabajo = request.getParameter("fechainicio_detalletrabajo");
    java.sql.Date fechainicio_detalletrabajo = Utiles.stringToSqlDate(sfechainicio_trabajo);
    String sfechafin_trabajo = request.getParameter("fechafin_detalletrabajo");
    java.sql.Date fechafin_detalletrabajo = Utiles.stringToSqlDate(sfechafin_trabajo);
    
    
    //int con=total_detalletrabajo;

    String tipo = "error";
    String mensaje = "Datos no agregados.";

    DetallesTrabajos detalletrabajo = new DetallesTrabajos();
    detalletrabajo.setId_detalletrabajo(id_detalletrabajo);
    detalletrabajo.setFechafin_detalletrabajo(fechafin_detalletrabajo);
    detalletrabajo.setFechainicio_detalletrabajo(fechainicio_detalletrabajo);
   
    Trabajos trabajo = new Trabajos();
    trabajo.setId_trabajo(id_trabajo);
    Empresas empresa = new Empresas();
    empresa.setId_empresa(id_empresa);
    
    detalletrabajo.setTrabajo(trabajo);
    detalletrabajo.setEmpresa(empresa);
    
  


    if (DetallesTrabajosControlador.agregar(detalletrabajo)) {
        tipo = "success";
        mensaje = "Datos agregados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();

%>