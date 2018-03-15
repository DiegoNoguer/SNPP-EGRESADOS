

<%@page import="controladores.UsuariosControlador"%>
<%@page import="modelos.Roles"%>
<%@page import="modelos.Usuarios"%>
<%@page import="utiles.Utiles"%>
<%@page import="org.json.simple.JSONObject"%>
<%
    HttpSession sesion = request.getSession();

    int id_usuario = 0;
    //int id_persona = 0;
    String login_usuario = "";
    String password_usuario = "";
    String claveactual_usuario = Utiles.md5(request.getParameter("claveactual_usuario"));
    String clavenueva_usuario = request.getParameter("clavenueva_usuario");
    int id_rol = 0;
    //int id_titulo = 0;

    String tipo = "error";
    String mensaje = "Datos no modificados";

    Usuarios usuarioLogueado = (Usuarios) sesion.getAttribute("usuarioLogueado");
    if (usuarioLogueado != null) {

        id_usuario = usuarioLogueado.getId_usuario();
        login_usuario = usuarioLogueado.getLogin_usuario();
        password_usuario = usuarioLogueado.getPassword_usuario();
        //id_persona = usuarioLogueado.getPersona().getId_persona();
        id_rol = usuarioLogueado.getRol().getId_rol();
        //id_titulo = usuarioLogueado.getTitulo().getId_titulo();
        System.out.println("usuarioLogueado - clave (jsp-actual): " + password_usuario);
        System.out.println("del formulario - clave actual: " + claveactual_usuario);

        if (claveactual_usuario.equals(password_usuario)) {
            Usuarios usuario = new Usuarios();
            usuario.setId_usuario(id_usuario);
            usuario.setLogin_usuario(login_usuario);
            usuario.setPassword_usuario(clavenueva_usuario);

            //Personas persona = new Personas();
            //persona.setId_persona(id_persona);

            //usuario.setPersona(persona);

            Roles rol = new Roles();
            rol.setId_rol(id_rol);

            usuario.setRol(rol);

            //Titulos titulo = new Titulos();
            //titulo.setId_titulo(id_titulo);

            //usuario.setTitulo(titulo);

            if (UsuariosControlador.modificar(usuario)) {
                tipo = "success";
                mensaje = "Datos modificados correctamente";
                System.out.println("Cambiado!!");
            }

        } else {
            tipo = "error";
            mensaje = "Clave actual incorrecta";
        }

    }

    JSONObject obj = new JSONObject();
    obj.put("mensaje", mensaje);
    obj.put("tipo", tipo);
    obj.put("id_usuario", id_usuario);
    obj.put("login_usuario", login_usuario);
    out.print(obj);
    out.flush();
%>