/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import modelos.Ciudades;
import modelos.Egresados;
import modelos.Registros;
import utiles.Conexion;
import utiles.Utiles;

/**
 *
 * @author Administrator
 */
public class RegistrosControladorEmpresa {
    public static boolean agregar(Registros registro){
     boolean valor = false;
     if (Conexion.conectar()){
        String sql = "insert into registros(nombre_registro,fecha_registro,id_egresado) "
                    + "values('" + registro.getNombre_registro() + "','"
                    + registro.getFecha_registro() + "','"
                    + registro.getEgresado().getId_egresado() + "')";
        
         
     try {
                Conexion.getSt().executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
                ResultSet keyset = Conexion.getSt().getGeneratedKeys();
                if (keyset.next()) {
                    int id_registro = keyset.getInt(1);
                    registro.setId_registro(id_registro);
                    Conexion.getConn().commit();
                }
                valor = true;
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
            Conexion.cerrar();
        }

    return valor;   
  }   
   
    public static Registros buscarId(Registros registro){
            if (Conexion.conectar()){
                String sql ="select * from registros,egresados,ciudades where registros.id_egresado=egresados.id_egresado "
                        + "and egresados.id_ciudad=ciudades.id_ciudad and id_registro='" + registro.getId_registro() + "'";
                try {
                     ResultSet rs = Conexion.getSt().executeQuery(sql);
                     if (rs.next()){
                            registro.setNombre_registro(rs.getString("nombre_registro"));
                            registro.setFecha_registro(rs.getDate("fecha_registro"));
                            
                            //CIUDADES  
                            Ciudades ciudad= new Ciudades();
                            ciudad.setNombre_ciudad(rs.getString("nombre_ciudad"));
                            
                            
                            //EGRESADOS 
                            Egresados egresado = new Egresados();
                            egresado.setId_egresado(rs.getInt("id_egresado"));
                            egresado.setNombre_egresado(rs.getString("nombre_egresado"));
                            egresado.setApellido_egresado(rs.getString("apellido_egresado"));
                            egresado.setCedula_egresado(rs.getInt("cedula_egresado"));
                            egresado.setTelefono_egresado(rs.getString("telefono_egresado"));
                            egresado.setEdad_egresado(rs.getInt("edad_egresado"));
                            egresado.setCiudad(ciudad);
                            registro.setEgresado(egresado);
                            
                     }else {
                             registro.setId_registro(0);
                             registro.setNombre_registro("");
                             java.sql.Date fecha_registro = new java.sql.Date(new java.util.Date().getTime());
                             registro.setFecha_registro(fecha_registro);
                             
                             //CIUDADES  
                            Ciudades ciudad= new Ciudades();
                            ciudad.setNombre_ciudad("");
                             
                             //EGRESADOS 
                            Egresados egresado = new Egresados();
                            egresado.setId_egresado(0);
                            egresado.setNombre_egresado("");
                            egresado.setApellido_egresado("");
                            egresado.setCedula_egresado(0);
                            egresado.setTelefono_egresado("");
                            egresado.setEdad_egresado(0);
                            egresado.setCiudad(ciudad);
                            registro.setEgresado(egresado);
                     }
               } catch (SQLException ex) {
                     System.err.println("Error controlador - buscar id: " + ex);
               }
}
return registro;
}

    public static String buscaNombre(String nombre, int pagina) {
        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()){
            try{
                String sql = "select * from registros,egresados,ciudades where registros.id_egresado=egresados.id_egresado and egresados.id_ciudad=ciudades.id_ciudad and  cedula_egresado = '" + nombre.toUpperCase() + "'" + "order by id_registro offset " + offset + "limit " + Utiles.REGISTROS_PAGINA;
                System.out.println("--->" + sql);
                try(PreparedStatement ps= Conexion.getConn().prepareStatement(sql)){
                    ResultSet rs = ps.executeQuery();
                    String tabla="";
                    while (rs.next()) {
                        tabla+= "<tr>"
                                + "<td>" + rs.getString("id_registro")+ "</td>"
                                //+ "<td>" + rs.getString("nombre_curso")+ "</td>"
                                + "<td>" +rs.getDate("fecha_registro")+ "</td>"
                                + "<td>" +rs.getString("nombre_egresado")+ "</td>"
                                + "<td>" +rs.getString("apellido_egresado")+ "</td>"
                                + "<td>" +rs.getString("cedula_egresado")+ "</td>"
                                + "<td>" +rs.getString("telefono_egresado")+ "</td>"
                                + "<td>" +rs.getString("edad_egresado")+ "</td>"
                                + "<td>" +rs.getString("nombre_ciudad")+ "</td>"+"</tr>"; 
                               
                    }
                    if(tabla.equals("")){
                        tabla = "<tr><td colspan =2>No Existen Registros...</td><tr>";
                    }
                    ps.close();
                    valor = tabla;
                    
                }catch(SQLException ex){
                    System.err.println("Error:" + ex );
                }
                Conexion.cerrar();
                
                
            }catch (Exception ex){
                System.err.println("Error: " + ex);
            }         
        }
       Conexion.cerrar();
       return valor;
    }

  public static boolean modificar(Registros registro) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update registros set id_egresado=" + registro.getEgresado().getId_egresado() + ","
                    + "fecha_registro='" + registro.getFecha_registro() + "'"
                    + " where id_registro=" + registro.getId_registro();
            try {

                Conexion.getSt().executeUpdate(sql);

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
  
  
  public static boolean eliminar(Registros registro) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from registros where id_registro=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, registro.getId_registro());
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
  
  
  
  

  
     public static Registros buscarBorraridEgresado3(int id) {
        Registros registro = null;
        if (Conexion.conectar()) {
            String sql = "select* from registros where id_egresado='" + id + "'";

            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()) {
                    registro = new Registros();

                    Egresados egresado = new Egresados();
                    egresado.setId_egresado(0);
                    //egresado.setNombre_egresado("");
                   // egresado.setApellido_egresado("");
                  // egresado.setCedula_egresado(0);
                   //egresado.setTelefono_egresado("");
                    registro.setEgresado(egresado);
                    System.out.println(egresado);

                }

            } catch (SQLException ex) {
                System.err.println("Error:" + ex);
            }
        }
        return registro;
    }
  
     
     
     
     public static String buscarNombreCurso(String nombre, int pagina) throws SQLException {
        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from detallesregistros dv "
                        + "left join registros v on v.id_registro=dv.id_registro "
                        + "left join egresados e on e.id_egresado=v.id_egresado "
                        + "left join cursos p on p.id_curso=dv.id_curso "
                       // + "left join especialidades es on p.id_especialidad=es.id_especialidad "
                        + "where upper(p.nombre_curso) like '%"
                        + nombre.toUpperCase()
                        + "%' "
                        + "order by dv.id_registro "
                        + "offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;
                System.out.println("--> " + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                
                               + "<td>" + rs.getInt("id_Registro") + "</td>"
                               // + "<td>" + rs.getInt("id_curso") + "</td>"                              
                                + "<td>" + rs.getString("nombre_curso") + "</td>"
                                + "<td>" + rs.getString("nombre_egresado") + "</td>"
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
     
    
     
     
  
}
