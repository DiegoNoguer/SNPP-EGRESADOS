




<%@page import="controladores.DetallesRegistrosControlador_1"%>
<%@page import="modelos.Cursos"%>
<%@page import="modelos.Registros"%>
<%@page import="controladores.DetallesRegistrosControlador"%>
<%@page import="modelos.DetallesRegistros"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_detalleregistro = Integer.parseInt(request.getParameter("id_detalleregistro"));

    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";

    DetallesRegistros detalleregistro = DetallesRegistrosControlador.buscarId(id_detalleregistro);
    if (detalleregistro != null) {
        tipo = "success";
        mensaje = "Datos encontrados.";
        nuevo = "false";
    } else {
        detalleregistro = new DetallesRegistros();
        detalleregistro.setId_detalleregistro(0);
        
        Registros registro = new Registros();
        registro.setId_registro(0);
        detalleregistro.setRegistro(registro);
        
        Cursos curso = new Cursos();
        curso.setId_curso(0);
        detalleregistro.setCurso(curso);
        
   
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);

    obj.put("id_detalleregistro", String.valueOf(detalleregistro.getId_detalleregistro()));
    obj.put("id_registro", String.valueOf(detalleregistro.getRegistro().getId_registro()));
    obj.put("id_curso", String.valueOf(detalleregistro.getCurso().getId_curso()));
    obj.put("nombre_curso", detalleregistro.getCurso().getNombre_curso());
    obj.put("tipo_curso", detalleregistro.getCurso().getTipo_curso());
    obj.put("duracion_curso", detalleregistro.getCurso().getDuracion_curso());
    obj.put("año_curso", detalleregistro.getCurso().getAño_curso());
    obj.put("nombre_especialidad", detalleregistro.getCurso().getEspecialidad().getNombre_especialidad());   
    out.print(obj);
    out.flush();
%>