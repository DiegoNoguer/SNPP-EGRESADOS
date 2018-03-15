package controladores;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelos.Cursos;
import modelos.DetallesRegistros;
import modelos.Especialidades;
import modelos.Registros;
import utiles.Conexion;
import utiles.Utiles;

public class DetallesRegistrosControlador_1 {

    //PARA BUSCARIDREGISTRODETALLE
    public static DetallesRegistros buscarId(int id) throws SQLException {
        DetallesRegistros detalleregistro = null;
        if (Conexion.conectar()) {
            try {
                String sql = " select * from detallesregistros dp "
                        + " left join registros p on p.id_registro=dp.id_registro "
                        + " left join cursos a on a.id_curso=dp.id_curso"
                        + " left join especialidades e on a.id_especialidad=e.id_especialidad   "
                        + " where dp.id_detalleregistro=?";
                System.out.println("sql" + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ps.setInt(1, id);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        detalleregistro = new DetallesRegistros();
                        detalleregistro.setId_detalleregistro(rs.getInt("id_detalleregistro"));

                        Especialidades especialidad = new Especialidades();
                        //especialidad.setId_especialidad(rs.getInt("id_especialidad"));
                        especialidad.setNombre_especialidad(rs.getString("nombre_especialidad"));

                        Cursos curso = new Cursos();
                        curso.setId_curso(rs.getInt("id_curso"));
                        curso.setNombre_curso(rs.getString("nombre_curso"));
                        curso.setTipo_curso(rs.getString("tipo_curso"));
                        curso.setDuracion_curso(rs.getString("duracion_curso"));
                        curso.setAño_curso(rs.getString("año_curso"));
                        curso.setEspecialidad(especialidad);
                        detalleregistro.setCurso(curso);

                        Registros registro = new Registros();
                        registro.setId_registro(rs.getInt("id_registro"));
                        detalleregistro.setRegistro(registro);

                    }
                    ps.close();
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return detalleregistro;
    }

    public static boolean agregar(DetallesRegistros detalleregistro) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "insert into detallesregistros "
                    + "(id_registro,id_curso) "
                    + "values (?,?)";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, detalleregistro.getRegistro().getId_registro());
                ps.setInt(2, detalleregistro.getCurso().getId_curso());
                ps.executeUpdate();
                ps.close();
                Conexion.getConn().commit();
                valor = true;
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
                try {
                    Conexion.getConn().rollback();
                } catch (SQLException ex1) {
                    System.out.println("--> " + ex1.getLocalizedMessage());
                }
            }
        }
        Conexion.cerrar();
        return valor;
    }

    public static String buscarIdRegistro(int id) throws SQLException {
        String valor = "";
        Cursos curso = new Cursos();
        if (Conexion.conectar()) {
            try {
                String sql = "select * from detallesregistros dv "
                        + "left join registros p on p.id_registro=dv.id_registro "
                        + "left join cursos a on a.id_curso=dv.id_curso "
                        + "left join especialidades e on a.id_especialidad=e.id_especialidad "
                        + "where dv.id_registro=" + id + " "
                        + "order by id_detalleregistro";
                System.out.println("buscarid_registro--> " + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";

                    while (rs.next()) {

                        tabla += "<tr>"
                                //   + "<td>" + rs.getInt("id_detalleregistro") + "</td>"
                                //  + "<td>" + rs.getInt("id_curso") + "</td>"

                                + "<td>" + rs.getString("tipo_curso") + "</td>"
                                + "<td>" + rs.getString("nombre_curso") + "</td>"
                                + "<td>" + rs.getString("nombre_especialidad") + "</td>"
                                + "<td>" + rs.getString("duracion_curso") + "</td>"
                                + "<td>" + rs.getString("año_curso") + "</td>"
                                + "<td class='centrado'>"
                                //+ "<button onclick='editarLinea(" + rs.getString("id_detalleregistro") + ")'"
                                //+ " type='button' class='btn btn-primary btn-sm'><span class='glyphicon glyphicon-pencil'>"
                               // + "</span></button></td>"
                                + "</tr>";
                        System.out.println("sqldetalleregistro: " + tabla);

                    }
                    if (tabla.equals("")) {
                        tabla = "<tr><td  colspan=7>No existen registros ...</td></tr>";
                    }
                    ps.close();
                    valor = tabla;
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return valor;
    }

    public static String buscarNombre(String nombre, int pagina) throws SQLException {
        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from detallesRegistros dv "
                        + "left join Registros v on v.id_Registro=dv.id_Registro "
                        + "left join cursos p on p.id_curso=dv.id_curso "
                        + "where upper(p.nombre_curso) like '%"
                        + nombre.toUpperCase()
                        + "%' "
                        + "order by id_detalleRegistro "
                        + "offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;
                System.out.println("--> " + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getInt("id_detalleRegistro") + "</td>"
                                + "<td>" + rs.getInt("id_Registro") + "</td>"
                                + "<td>" + rs.getInt("id_curso") + "</td>"
                                + "<td>" + rs.getString("nombre_curso") + "</td>"
                                + "</tr>";
                    }
                    if (tabla.equals("")) {
                        tabla = "<tr><td  colspan=6>No existen registros ...</td></tr>";
                    }
                    ps.close();
                    valor = tabla;
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return valor;
    }

    public static boolean eliminar(DetallesRegistros detalleregistro) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from detallesregistros where id_detalleregistro=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, detalleregistro.getId_detalleregistro());
                ps.executeUpdate();
                ps.close();
                Conexion.getConn().commit();
                valor = true;
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
                try {
                    Conexion.getConn().rollback();
                } catch (SQLException ex1) {
                    System.out.println("--> " + ex1.getLocalizedMessage());
                }
            }
        }
        Conexion.cerrar();
        return valor;
    }

    public static boolean modificar(DetallesRegistros detalleregistro) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update detallesregistros set "
                    + "id_registro=?,"
                    + "id_curso=? "
                    + "where id_detalleregistro=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, detalleregistro.getRegistro().getId_registro());
                ps.setInt(2, detalleregistro.getCurso().getId_curso());
                ps.setInt(3, detalleregistro.getId_detalleregistro());
                ps.executeUpdate();
                ps.close();
                Conexion.getConn().commit();
                System.out.println("--> Grabado");
                valor = true;
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
                try {
                    Conexion.getConn().rollback();
                } catch (SQLException ex1) {
                    System.out.println("--> " + ex1.getLocalizedMessage());
                }
            }
        }
        Conexion.cerrar();
        return valor;
    }

}
