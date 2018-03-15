<%-- 
    Document   : agregar
    Created on : May 3, 2017, 9:03:37 AM
    Author     : Diego Lima
--%>




<%@page import="modelos.Areas"%>
<%@page import="modelos.Ciudades"%>
<%@page import="controladores.EmpresasControlador"%>
<%@page import="modelos.Empresas"%>
<%@page import="org.json.simple.JSONObject"%>


<%
  //  int id_empresa =Integer.parseInt(request.getParameter("id_empresa"));
    String nombre_empresa=request.getParameter("nombre_empresa");
    String email_empresa=request.getParameter("email_empresa");
    String direccion_empresa=request.getParameter("direccion_empresa");
    String telefono_empresa=request.getParameter("telefono_empresa");
    String ruc_empresa=request.getParameter("ruc_empresa");
   // String buscamos_empresa=request.getParameter("buscamos_empresa");
   // String turno_empresa=request.getParameter("turno_empresa");
   // String horario_empresa=request.getParameter("horario_empresa");
   // String salario_empresa=request.getParameter("salario_empresa");
   // String vacancia_empresa=request.getParameter("vacancia_empresa");
   // String rangoedad_empresa=request.getParameter("rangoedad_empresa");
   // String tipogenero_empresa=request.getParameter("tipogenero_empresa");
    int id_ciudad =Integer.parseInt(request.getParameter("id_ciudad"));
    
      //int id_color =Integer.parseInt(request.getParameter("id_color"));
      //int id_rubro =Integer.parseInt(request.getParameter("id_rubro"));
    String tipo ="error";
    String mensaje= "Datos no agregados"  ;
    
   
    Empresas empresa = new Empresas();
   // empresa.setId_empresa(id_empresa);
    empresa.setNombre_empresa(nombre_empresa);
    empresa.setEmail_empresa(email_empresa);
    empresa.setDireccion_empresa(direccion_empresa);
    empresa.setTelefono_empresa(telefono_empresa);
    empresa.setRuc_empresa(ruc_empresa);
   // empresa.setBuscamos_empresa(buscamos_empresa);
   //empresa.setTurno_empresa(turno_empresa);
   // empresa.setHorario_empresa(horario_empresa);
   // empresa.setSalario_empresa(salario_empresa);
    //empresa.setVacancia_empresa(vacancia_empresa);
   // empresa.setRangoedad_empresa(rangoedad_empresa);
    //empresa.setTipogenero_empresa(tipogenero_empresa);
    //CIUDAD
    Ciudades ciudad = new Ciudades();
    ciudad.setId_ciudad(id_ciudad);
    empresa.setCiudad(ciudad);
    
    
    
    
 if (EmpresasControlador.agregar(empresa)){
     tipo ="success";
     mensaje="EMPRESA REGISTRADA.";
 }

JSONObject obj = new JSONObject();
obj.put("tipo",tipo);
obj.put("mensaje",mensaje);
out.print(obj);
out.flush();

%>


