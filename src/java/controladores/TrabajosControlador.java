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
import modelos.Trabajos;
import utiles.Conexion;
import utiles.Utiles;

/**
 *
 * @author Usuario
 */
public class TrabajosControlador {
    public static boolean agregar(Trabajos trabajo){
     boolean valor = false;
     if (Conexion.conectar()){
        String sql = "insert into trabajos(nombre_trabajo,id_egresado) "
                    + "values('" + trabajo.getNombre_trabajo() + "','"
                    + trabajo.getEgresado().getId_egresado() + "')";
        
         
     try {
                Conexion.getSt().executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
                ResultSet keyset = Conexion.getSt().getGeneratedKeys();
                if (keyset.next()) {
                    int id_trabajo = keyset.getInt(1);
                    trabajo.setId_trabajo(id_trabajo);
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
   
    public static Trabajos buscarId(Trabajos trabajo){
            if (Conexion.conectar()){
                String sql ="select * from trabajos,egresados,ciudades where trabajos.id_egresado=egresados.id_egresado and egresados.id_ciudad=ciudades.id_ciudad and id_trabajo='" + trabajo.getId_trabajo() + "'";
                try {
                     ResultSet rs = Conexion.getSt().executeQuery(sql);
                     if (rs.next()){
                            trabajo.setNombre_trabajo(rs.getString("nombre_trabajo"));
                
                            
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
                            trabajo.setEgresado(egresado);
                            
                     }else {
                             trabajo.setId_trabajo(0);
                             trabajo.setNombre_trabajo("");

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
                            trabajo.setEgresado(egresado);
                     }
               } catch (SQLException ex) {
                     System.err.println("Error controlador - buscar id: " + ex);
               }
}
return trabajo;
}

    public static String buscaNombre(String nombre, int pagina) {
        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()){
            try{
                String sql = "select * from trabajos,egresados,ciudades where trabajos.id_egresado=egresados.id_egresado and egresados.id_ciudad=ciudades.id_ciudad and cedula_egresado = '" + nombre.toUpperCase() + "'" + "order by id_trabajo offset " + offset + "limit " + Utiles.REGISTROS_PAGINA;
                System.out.println("--->" + sql);
                try(PreparedStatement ps= Conexion.getConn().prepareStatement(sql)){
                    ResultSet rs = ps.executeQuery();
                    String tabla="";
                    while (rs.next()) {
                        tabla+= "<tr>"
                                + "<td>" + rs.getString("id_trabajo")+ "</td>"
                                + "<td>" +rs.getString("nombre_egresado")+ "</td>"
                                + "<td>" +rs.getString("apellido_egresado")+ "</td>"
                                + "<td>" +rs.getString("cedula_egresado")+ "</td>"
                                + "<td>" +rs.getString("nombre_ciudad")+ "</td>"+"</tr>"; 
                               
                    }
                    if(tabla.equals("")){
                        tabla = "<tr><td colspan =2>No Existen Trabajos...</td><tr>";
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

  public static boolean modificar(Trabajos trabajo) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update trabajos set id_egresado=" + trabajo.getEgresado().getId_egresado() + ","
                    + " where id_trabajo=" + trabajo.getId_trabajo();
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
  
  
  public static boolean eliminar(Trabajos trabajo) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from trabajos where id_trabajo=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, trabajo.getId_trabajo());
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
  
  
     public static Trabajos buscarBorraridEgresado2(int id) {
        Trabajos trabajo = null;
        if (Conexion.conectar()) {
            String sql = "select* from trabajos where id_egresado='" + id + "'";

            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()) {
                    trabajo = new Trabajos();

                    Egresados egresado = new Egresados();
                    egresado.setId_egresado(0);
                    //egresado.setNombre_egresado("");
                   // egresado.setApellido_egresado("");
                  // egresado.setCedula_egresado(0);
                   //egresado.setTelefono_egresado("");
                    trabajo.setEgresado(egresado);
                    System.out.println(egresado);

                }

            } catch (SQLException ex) {
                System.err.println("Error:" + ex);
            }
        }
        return trabajo;
    }
    
}
