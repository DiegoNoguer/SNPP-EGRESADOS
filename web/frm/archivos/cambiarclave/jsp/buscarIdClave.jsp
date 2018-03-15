
<%@page import="modelos.Usuarios"%>
<%@page import="org.json.simple.JSONObject"%>

<%
    HttpSession sesion = request.getSession();
    
    int id_usuario = 0;
    String login_usuario = "";
    
    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    //String nuevo = "true";

    Usuarios usuarioLogueado = (Usuarios) sesion.getAttribute("usuarioLogueado");
    if (usuarioLogueado != null) {

        id_usuario = usuarioLogueado.getId_usuario();
        login_usuario = usuarioLogueado.getLogin_usuario();

        tipo = "success";
        mensaje = "Datos encontrados";
//        nuevo = "false";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    //  obj.put("nuevo", nuevo);
    obj.put("id_usuario", id_usuario);
    obj.put("login_usuario", login_usuario);
    out.print(obj);
    out.flush();
%>