




<%@page import="modelos.Empresas"%>
<%@page import="modelos.Trabajos"%>
<%@page import="controladores.DetallesTrabajosControlador"%>
<%@page import="modelos.DetallesTrabajos"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    
    int id_detalletrabajo = 0;
 if (request.getParameter("id_detalletrabajo") != "") {
 id_detalletrabajo = Integer.parseInt(request.getParameter("id_detalletrabajo"));
}
   // int id_detalletrabajo = Integer.parseInt(request.getParameter("id_detalletrabajo"));

    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";

    DetallesTrabajos detalletrabajo = DetallesTrabajosControlador.buscarId(id_detalletrabajo);
    if (detalletrabajo != null) {
        tipo = "success";
        mensaje = "Datos encontrados.";
        nuevo = "false";
    } else {
        detalletrabajo = new DetallesTrabajos();
        detalletrabajo.setId_detalletrabajo(0);
        
        Trabajos trabajo = new Trabajos();
        trabajo.setId_trabajo(0);
        detalletrabajo.setTrabajo(trabajo);
        
        Empresas empresa = new Empresas();
        empresa.setId_empresa(0);
        detalletrabajo.setEmpresa(empresa);
        
   
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);

    obj.put("id_detalletrabajo", String.valueOf(detalletrabajo.getId_detalletrabajo()));
    obj.put("id_trabajo", String.valueOf(detalletrabajo.getTrabajo().getId_trabajo()));
    obj.put("id_empresa", String.valueOf(detalletrabajo.getEmpresa().getId_empresa()));
    obj.put("nombre_empresa", detalletrabajo.getEmpresa().getNombre_empresa());
    obj.put("direccion_empresa", detalletrabajo.getEmpresa().getDireccion_empresa());
    obj.put("telefono_empresa", detalletrabajo.getEmpresa().getTelefono_empresa());
    System.out.println("telefono"+detalletrabajo.getEmpresa().getTelefono_empresa());
    obj.put("ruc_empresa", detalletrabajo.getEmpresa().getRuc_empresa());
    System.out.println("ruc"+detalletrabajo.getEmpresa().getRuc_empresa());
    obj.put("fechainicio_detalletrabajo", String.valueOf(detalletrabajo.getFechainicio_detalletrabajo()));
    obj.put("fechafin_detalletrabajo", String.valueOf(detalletrabajo.getFechafin_detalletrabajo()));
  
    out.print(obj);
    out.flush();
%>