/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelos.Areas;
import modelos.DetallesOportunidades;
import modelos.Oportunidades;
import modelos.Puestos;
import utiles.Conexion;
import utiles.Utiles;

/**
 *
 * @author Usuario
 */
public class DetallesOportunidadesControlador_1 {
    
    
    
    //PARA BUSCARIDREGISTRODETALLE
    public static DetallesOportunidades buscarId(int id) throws SQLException {
        DetallesOportunidades detalleoportunidad = null;
        if (Conexion.conectar()) {
            try {
                String sql = "select * from detallesoportunidades dp "
                        + "left join oportunidades p on p.id_oportunidad=dp.id_oportunidad "
                        + "left join puestos a on a.id_puesto=dp.id_puesto "
                        + "left join areas e on a.id_area=e.id_area "
                        + "where dp.id_detalleoportunidad=?";
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ps.setInt(1, id);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        detalleoportunidad = new DetallesOportunidades();
                        detalleoportunidad.setId_detalleoportunidad(rs.getInt("id_detalleoportunidad"));
                        detalleoportunidad.setVacancia_detalleoportunidad(rs.getInt("vacancia_detalleoportunidad"));
                        detalleoportunidad.setHorario_detalleoportunidad(rs.getString("horario_detalleoportunidad"));
                        
                        detalleoportunidad.setRangoedad_detalleoportunidad(rs.getString("rangoedad_detalleoportunidad"));
                     
                        
                        Areas area = new Areas();
                        area.setNombre_area(rs.getString("nombre_area"));

                        Puestos puesto = new Puestos();
                        puesto.setId_puesto(rs.getInt("id_puesto"));
                        puesto.setNombre_puesto(rs.getString("nombre_puesto"));                       
                        puesto.setArea(area);
                      
                        detalleoportunidad.setPuesto(puesto);

                        Oportunidades oportunidad = new Oportunidades();
                        oportunidad.setId_oportunidad(rs.getInt("id_oportunidad"));
                        detalleoportunidad.setOportunidad(oportunidad);

                    }
                    ps.close();
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return detalleoportunidad;
    }
    
    

     public static boolean agregar(DetallesOportunidades detalleoportunidad) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "insert into detallesoportunidades "
                    + "(id_oportunidad,id_puesto,vacancia_detalleoportunidad,horario_detalleoportunidad,rangoedad_detalleoportunidad) "
                    + "values (?,?,?,?,?)";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, detalleoportunidad.getOportunidad().getId_oportunidad());
                ps.setInt(2, detalleoportunidad.getPuesto().getId_puesto());
                ps.setInt(3, detalleoportunidad.getVacancia_detalleoportunidad());
                ps.setString(4, detalleoportunidad.getHorario_detalleoportunidad());
                ps.setString(5, detalleoportunidad.getRangoedad_detalleoportunidad());
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
    
    
    
    
    
    //DIEGO HASTA ACA TE QUEDASTE PROB EL SELECT SI FUNCIONA
   
    public static String buscarIdOportunidad(int id) throws SQLException {
        String valor = "";
        Puestos puesto = new Puestos();
        if (Conexion.conectar()) {
            try {
                String sql = "select * from detallesoportunidades dv "
                        + "left join oportunidades p on p.id_oportunidad=dv.id_oportunidad "
                        + "left join puestos a on a.id_puesto=dv.id_puesto "
                        + "left join areas e on a.id_area=e.id_area "
                        + "where dv.id_oportunidad= " + id + " "
                        + "order by id_detalleoportunidad";
                System.out.println("buscarid_oportunidad--> " + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    
                    while (rs.next()) {

                     
                        tabla += "<tr>"
                                 //  + "<td>" + rs.getInt("id_detalleoportunidad") + "</td>"
                                  //+ "<td>" + rs.getInt("id_puesto") + "</td>"
                                
                                + "<td>" + rs.getString("nombre_area") + "</td>"
                                + "<td>" + rs.getString("nombre_puesto") + "</td>"                                                             
                                + "<td>" + rs.getString("vacancia_detalleoportunidad") + "</td>"                                                             
                                + "<td>" + rs.getString("horario_detalleoportunidad") + "</td>"                                                                                                                         
                                + "<td>" + rs.getString("rangoedad_detalleoportunidad") + "</td>"                                                             
                                + "<td class='centrado'>"
//                                + "<button onclick='editarLinea(" + rs.getString("id_detalleoportunidad") + ")'"
//                                + " type='button' class='btn btn-primary btn-sm'><span class='glyphicon glyphicon-pencil'>"
//                                + "</span></button></td>"
                                + "</tr>";
                        System.out.println("sqldetalleoportunidad: "+tabla);

                    }
                    if (tabla.equals("")) {
                        tabla = "<tr><td  colspan=7>No existen oportunidades ...</td></tr>";
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
                String sql = "select * from detallesOportunidades dv "
                        + "left join Oportunidades v on v.id_Oportunidad=dv.id_Oportunidad "
                        + "left join puestos p on p.id_puesto=dv.id_puesto "
                        + "where upper(p.nombre_puesto) like '%"
                        + nombre.toUpperCase()
                        + "%' "
                        + "order by id_detalleOportunidad "
                        + "offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;
                System.out.println("--> " + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getInt("id_detalleOportunidad") + "</td>"
                                + "<td>" + rs.getInt("id_Oportunidad") + "</td>"
                                + "<td>" + rs.getInt("id_puesto") + "</td>"
                                + "<td>" + rs.getString("nombre_puesto") + "</td>"
                                + "</tr>";
                    }
                    if (tabla.equals("")) {
                        tabla = "<tr><td  colspan=6>No existen oportunidades ...</td></tr>";
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
    
    public static boolean modificar(DetallesOportunidades detalleoportunidad) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update detallesoportunidades set "
                    + "id_oportunidad=?,"
                    + "id_puesto=?,"
                    + "vacancia_detalleoportunidad=?,"
                    + "horario_detalleoportunidad=?,"
                    + "rangoedad_detalleoportunidad=? "
                    + "where id_detalleoportunidad=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, detalleoportunidad.getOportunidad().getId_oportunidad());
                ps.setInt(2, detalleoportunidad.getPuesto().getId_puesto());
                ps.setInt(3, detalleoportunidad.getVacancia_detalleoportunidad());
                ps.setString(4, detalleoportunidad.getHorario_detalleoportunidad());
                ps.setString(5, detalleoportunidad.getRangoedad_detalleoportunidad());
                ps.setInt(6, detalleoportunidad.getId_detalleoportunidad());             
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
    
    public static boolean eliminar(DetallesOportunidades detalleoportunidad) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from detallesoportunidades where id_detalleoportunidad=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, detalleoportunidad.getId_detalleoportunidad());
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
    
    
}


