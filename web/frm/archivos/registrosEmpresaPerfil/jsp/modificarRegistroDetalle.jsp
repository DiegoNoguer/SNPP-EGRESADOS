
<%@page import="controladores.DetallesRegistrosControlador"%>
<%@page import="modelos.Cursos"%>
<%@page import="modelos.Registros"%>
<%@page import="modelos.DetallesRegistros"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%

    int id_detalleregistro = Integer.parseInt(request.getParameter("id_detalleregistro"));
    int id_registro = Integer.parseInt(request.getParameter("id_registro"));
    int id_curso = Integer.parseInt(request.getParameter("id_curso"));

    String tipo = "error";
    String mensaje = "Datos no modificados.";

    DetallesRegistros detalleregistro = new DetallesRegistros();
    detalleregistro.setId_detalleregistro(id_detalleregistro);
   

    Registros registro = new Registros();
    registro.setId_registro(id_registro);

    Cursos curso = new Cursos();
    curso.setId_curso(id_curso);
    

    detalleregistro.setRegistro(registro);
    detalleregistro.setCurso(curso);

    if (DetallesRegistrosControlador.modificar(detalleregistro)) {
        tipo = "success";
        mensaje = "Datos modificados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();

%>