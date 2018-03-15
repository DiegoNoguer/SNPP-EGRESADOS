
<%@page import="modelos.Egresados"%>
<%@page import="controladores.TrabajosControlador"%>
<%@page import="modelos.Trabajos"%>
<%@page import="org.json.simple.JSONObject"%>

<%
    int id_egresado = 0;
    if (request.getParameter("id_egresado") != "") {
        id_egresado = Integer.parseInt(request.getParameter("id_egresado"));
    }
    
    
    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";
   
    
    
    Trabajos trabajo = TrabajosControlador.buscarBorraridEgresado2(id_egresado);
    if (trabajo!= null) {
    
        tipo = "success";
        mensaje = "Egresado ya existe en el registro";
        nuevo = "false";
      
    }else{
        
         tipo = "success";
        mensaje = "Enviando datos al Servidor ...";
        nuevo = "true";
        trabajo=new Trabajos();
        Egresados egresado = new Egresados();
        egresado.setId_egresado(id_egresado);
        trabajo.setEgresado(egresado);
        
    }
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);

    
    obj.put("id_egresado", trabajo.getEgresado().getId_egresado());
    System.out.println(trabajo.getEgresado().getId_egresado());
   
    out.print(obj);
    out.flush();
%>