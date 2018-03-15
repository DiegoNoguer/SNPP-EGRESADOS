
<%@page import="controladores.EgresadosControlador"%>
<%@page import="modelos.Egresados"%>
<%@page import="org.json.simple.JSONObject"%>

<%
    int cedula_egresado = 0;
    if (request.getParameter("cedula_egresado") != "") {
        cedula_egresado = Integer.parseInt(request.getParameter("cedula_egresado"));
    }
    
    
    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";
   
    
    
    Egresados egresado = EgresadosControlador.buscarCedula(cedula_egresado);
    if (egresado!= null) {
    
        tipo = "success";
        mensaje = "cedula ya existe";
        nuevo = "false";
    }else{
        
         tipo = "success";
        mensaje = "Agregar Cedula";
        nuevo = "true";
        egresado=new Egresados();
        egresado.setCedula_egresado(cedula_egresado);
        
    }
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);

    
    obj.put("cedula_egresado", egresado.getCedula_egresado());
   
    out.print(obj);
    out.flush();
%>