
<%@page import="modelos.Egresados"%>
<%@page import="controladores.RegistrosControlador"%>
<%@page import="modelos.Registros"%>
<%@page import="org.json.simple.JSONObject"%>

<%
    int id_egresado = 0;
    if (request.getParameter("id_egresado") != "") {
        id_egresado = Integer.parseInt(request.getParameter("id_egresado"));
    }
    
    
    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";
   
    
    
    Registros registro = RegistrosControlador.buscarBorraridEgresado3(id_egresado);
    if (registro!= null) {
    
        tipo = "success";
        mensaje = "Egresado ya existe en el registro";
        nuevo = "false";
      
    }else{
        
         tipo = "success";
        mensaje = "Enviando datos al Servidor ...";
        nuevo = "true";
        registro=new Registros();
        Egresados egresado = new Egresados();
        egresado.setId_egresado(id_egresado);
        registro.setEgresado(egresado);
        
    }
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);

    
    obj.put("id_egresado", registro.getEgresado().getId_egresado());
    System.out.println(registro.getEgresado().getId_egresado());
   
    out.print(obj);
    out.flush();
%>