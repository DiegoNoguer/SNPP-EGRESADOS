/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelos.DetallesTrabajos;
import modelos.Empresas;
import modelos.Trabajos;
import utiles.Conexion;
import utiles.Utiles;

/**
 *
 * @author Usuario
 */
public class DetallesTrabajosControlador {

    //PARA BUSCARIDREGISTRODETALLE
    public static DetallesTrabajos buscarId(int id) throws SQLException {
        DetallesTrabajos detalletrabajo = null;
        if (Conexion.conectar()) {
            try {
                String sql = " select * from detallestrabajos dp "
                        + " left join trabajos p on p.id_trabajo=dp.id_trabajo "
                        + " left join empresas a on a.id_empresa=dp.id_empresa"
                        + " where dp.id_detalletrabajo=?";
                System.out.println("sql" + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ps.setInt(1, id);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        detalletrabajo = new DetallesTrabajos();
                        detalletrabajo.setId_detalletrabajo(rs.getInt("id_detalletrabajo"));
                     
                        detalletrabajo.setFechainicio_detalletrabajo(rs.getDate("fechainicio_detalletrabajo"));
                        detalletrabajo.setFechafin_detalletrabajo(rs.getDate("fechafin_detalletrabajo"));

                        Empresas empresa = new Empresas();
                        empresa.setId_empresa(rs.getInt("id_empresa"));
                        empresa.setNombre_empresa(rs.getString("nombre_empresa"));
                        empresa.setDireccion_empresa(rs.getString("direccion_empresa"));
                        empresa.setTelefono_empresa(rs.getString("telefono_empresa"));
                        empresa.setRuc_empresa(rs.getString("ruc_empresa"));                    
                        detalletrabajo.setEmpresa(empresa);

                        Trabajos trabajo = new Trabajos();
                        trabajo.setId_trabajo(rs.getInt("id_trabajo"));
                        detalletrabajo.setTrabajo(trabajo);

                    }
                    ps.close();
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return detalletrabajo;
    }

    public static boolean agregar(DetallesTrabajos detalletrabajo) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "insert into detallestrabajos "
                    + "(id_trabajo,id_empresa,fechainicio_detalletrabajo,fechafin_detalletrabajo) "
                    + "values (?,?,?,?)";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, detalletrabajo.getTrabajo().getId_trabajo());
                ps.setInt(2, detalletrabajo.getEmpresa().getId_empresa());
                ps.setDate(3, detalletrabajo.getFechainicio_detalletrabajo());
                ps.setDate(4, detalletrabajo.getFechafin_detalletrabajo());
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

    public static String buscarIdTrabajo(int id) throws SQLException {
        String valor = "";
        Empresas empresa = new Empresas();
        if (Conexion.conectar()) {
            try {
                String sql = "select * from detallestrabajos dv "
                        + "left join trabajos p on p.id_trabajo=dv.id_trabajo "
                        + "left join empresas a on a.id_empresa=dv.id_empresa "
                        + "where dv.id_trabajo=" + id + " "
                        + "order by id_detalletrabajo";
                System.out.println("buscarid_trabajo--> " + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";

                    while (rs.next()) {

                        tabla += "<tr>"
                                //   + "<td>" + rs.getInt("id_detalletrabajo") + "</td>"
                                //  + "<td>" + rs.getInt("id_empresa") + "</td>"

                                + "<td>" + rs.getString("nombre_empresa") + "</td>"
                                + "<td>" + rs.getDate("fechainicio_detalletrabajo") + "</td>"
                                + "<td>" + rs.getDate("fechafin_detalletrabajo") + "</td>"
                                + "<td class='centrado'>"
                                + "<button onclick='editarLinea(" + rs.getString("id_detalletrabajo") + ")'"
                                + " type='button' class='btn btn-primary btn-sm'><span class='glyphicon glyphicon-pencil'>"
                                + "</span></button></td>"
                                + "</tr>";
                        System.out.println("sqldetalletrabajo: " + tabla);

                    }
                    if (tabla.equals("")) {
                        tabla = "<tr><td  colspan=7>No existen trabajos ...</td></tr>";
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
                String sql = "select * from detallesTrabajos dv "
                        + "left join Trabajos v on v.id_Trabajo=dv.id_Trabajo "
                        + "left join empresas p on p.id_empresa=dv.id_empresa "
                        + "where upper(p.nombre_empresa) like '%"
                        + nombre.toUpperCase()
                        + "%' "
                        + "order by id_detalleTrabajo "
                        + "offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;
                System.out.println("--> " + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getInt("id_detalleTrabajo") + "</td>"
                                + "<td>" + rs.getInt("id_Trabajo") + "</td>"
                                + "<td>" + rs.getInt("id_empresa") + "</td>"
                                + "<td>" + rs.getString("nombre_empresa") + "</td>"
                                + "</tr>";
                    }
                    if (tabla.equals("")) {
                        tabla = "<tr><td  colspan=6>No existen trabajos ...</td></tr>";
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

    public static boolean eliminar(DetallesTrabajos detalletrabajo) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from detallestrabajos where id_detalletrabajo=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, detalletrabajo.getId_detalletrabajo());
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

    public static boolean modificar(DetallesTrabajos detalletrabajo) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update detallestrabajos set "
                    + "id_trabajo=?,"
                    + "id_empresa=?,"
                    + "fechainicio_detalletrabajo=?,"
                    + "fechafin_detalletrabajo=? "
                    + "where id_detalletrabajo=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, detalletrabajo.getTrabajo().getId_trabajo());
                ps.setInt(2, detalletrabajo.getEmpresa().getId_empresa());
                ps.setDate(3, detalletrabajo.getFechainicio_detalletrabajo());
                ps.setDate(4, detalletrabajo.getFechafin_detalletrabajo());
                ps.setInt(5, detalletrabajo.getId_detalletrabajo());
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

