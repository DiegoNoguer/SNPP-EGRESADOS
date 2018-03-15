
<%@page import="controladores.DetallesRegistrosControlador_1"%>

<%@page import="modelos.DetallesRegistros"%>
<%@page import="modelos.Cursos"%>
<%@page import="modelos.Registros"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%

    int id_detalleregistro = Integer.parseInt(request.getParameter("id_detalleregistro"));
    
    int id_registro = Integer.parseInt(request.getParameter("id_registro"));
    int id_curso = Integer.parseInt(request.getParameter("id_curso"));
    // int total_registro= con + 0; 
   
    
    //int con=total_detalleregistro;

    String tipo = "error";
    String mensaje = "Datos no agregados.";

    DetallesRegistros detalleregistro = new DetallesRegistros();
    detalleregistro.setId_detalleregistro(id_detalleregistro);
   
    Registros registro = new Registros();
    registro.setId_registro(id_registro);
    Cursos curso = new Cursos();
    curso.setId_curso(id_curso);
    
    detalleregistro.setRegistro(registro);
    detalleregistro.setCurso(curso);
    
  


    if (DetallesRegistrosControlador_1.agregar(detalleregistro)) {
        tipo = "success";
        mensaje = "Datos agregados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();

%>