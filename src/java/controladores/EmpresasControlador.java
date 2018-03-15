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
import modelos.Ciudades;
import modelos.Empresas;
import utiles.Conexion;
import utiles.Utiles;

/**
 *
 * @author Usuario
 */
public class EmpresasControlador {
    public static boolean agregar(Empresas empresa){
     boolean valor = false;
     if (Conexion.conectar()){
        String sql = "insert into empresas (nombre_empresa,email_empresa,direccion_empresa,telefono_empresa,ruc_empresa,id_ciudad)"               
                + "values('" + empresa.getNombre_empresa() + "','"
                    + empresa.getEmail_empresa() + "','"
                    + empresa.getDireccion_empresa() + "','"
                    + empresa.getTelefono_empresa() + "','"
                    + empresa.getRuc_empresa() + "','"
                    + empresa.getCiudad().getId_ciudad() + "')";               
                                 
                                
                             
                     
         
        try {
           Conexion.getSt().executeUpdate(sql);
           valor=true; 
        } catch (SQLException ex){
           System.err.println("Error:" + ex); 
        }
     }
    return valor;   
  }   
   
    public static Empresas buscarId(Empresas empresa){
            if (Conexion.conectar()){
                String sql ="select * from empresas,ciudades,areas where ciudades.id_ciudad=empresas.id_ciudad and id_empresa='" + empresa.getId_empresa() + "'";
                try {
                     ResultSet rs = Conexion.getSt().executeQuery(sql);
                     if (rs.next()){
                            empresa.setNombre_empresa(rs.getString("nombre_empresa"));
                            empresa.setEmail_empresa(rs.getString("email_empresa"));
                            empresa.setDireccion_empresa(rs.getString("direccion_empresa"));
                            empresa.setTelefono_empresa(rs.getString("telefono_empresa"));
                            empresa.setRuc_empresa(rs.getString("ruc_empresa"));
                            //empresa.setBuscamos_empresa(rs.getString("buscamos_empresa"));
                           // empresa.setTurno_empresa(rs.getString("turno_empresa"));
                           // empresa.setHorario_empresa(rs.getString("horario_empresa"));
                            //empresa.setSalario_empresa(rs.getString("salario_empresa"));
                            //empresa.setVacancia_empresa(rs.getString("vacancia_empresa"));
                            //empresa.setRangoedad_empresa(rs.getString("rangoedad_empresa"));
                            //empresa.setTipogenero_empresa(rs.getString("tipogenero_empresa"));
                            
                            Ciudades ciudad = new Ciudades();
                            ciudad.setId_ciudad(rs.getInt("id_ciudad"));
                            ciudad.setNombre_ciudad(rs.getString("nombre_ciudad"));
                            empresa.setCiudad(ciudad);
                            
                            
                            
                     }else {
                             empresa.setId_empresa(0);
                             empresa.setNombre_empresa("");
                             empresa.setEmail_empresa("");
                             empresa.setDireccion_empresa("");
                             empresa.setTelefono_empresa("");
                             empresa.setRuc_empresa("");
                            // empresa.setBuscamos_empresa("");
                             //empresa.setTurno_empresa("");
                            // empresa.setHorario_empresa("");
                            // empresa.setSalario_empresa("");
                            // empresa.setVacancia_empresa("");
                            // empresa.setRangoedad_empresa("");
                            // empresa.setTipogenero_empresa("");
                             
                             Ciudades ciudad = new Ciudades();
                             ciudad.setId_ciudad(0);
                             ciudad.setNombre_ciudad("");
                             empresa.setCiudad(ciudad);
                             
                             
                             
                     }
               } catch (SQLException ex) {
                     System.err.println("Error controlador - buscar id: " + ex);
               }
}
return empresa;
}

    public static String buscaNombre(String nombre, int pagina) {
        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()){
            try{
                String sql = "select * from empresas,ciudades where empresas.id_ciudad=ciudades.id_ciudad and upper(nombre_empresa) like '%" + nombre.toUpperCase() + "%'" + "order by id_empresa offset " + offset + "limit " + Utiles.REGISTROS_PAGINA;
                System.out.println("--->" + sql);
                try(PreparedStatement ps= Conexion.getConn().prepareStatement(sql)){
                    ResultSet rs = ps.executeQuery();
                    String tabla="";
                    while (rs.next()) {
                        tabla+= "<tr>"
                                + "<td>" + rs.getString("id_empresa")+ "</td>"
                                + "<td>" +rs.getString("nombre_empresa")+ "</td>"
                                + "<td>" + rs.getString("email_empresa")+ "</td>"
                                + "<td>" + rs.getString("direccion_empresa")+ "</td>"
                                + "<td>" + rs.getString("telefono_empresa")+ "</td>"
                                + "<td>" + rs.getString("ruc_empresa")+ "</td>"
                                + "<td>" + rs.getString("nombre_ciudad")+ "</td>"+"</tr>"; 
                               
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

  
  public static boolean modificar(Empresas empresa) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update empresas set nombre_empresa='" + empresa.getNombre_empresa()
                    + "',email_empresa='" + empresa.getEmail_empresa() + ""
                    + "',direccion_empresa='" + empresa.getDireccion_empresa() + ""
                    + "',telefono_empresa='" + empresa.getTelefono_empresa() + ""
                    + "',ruc_empresa='" + empresa.getRuc_empresa() + ""
                    + "',id_ciudad='" + empresa.getCiudad().getId_ciudad() + ""
                    + "' where id_empresa=" + empresa.getId_empresa();
                    
            System.out.println(sql);
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            } catch (SQLException ex) {
                System.err.println("Error" + ex);
            }
        }
        return valor;
    }
  
  
  public static boolean eliminar (Empresas empresa) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from empresas where id_empresa=" + empresa.getId_empresa();
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            } catch (SQLException ex) {
                System.err.println("Error" + ex);
            }
        }
        return valor;
    }
  
  
   public static String buscaNombre2(String nombre, int pagina) {
        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()){
            try{
                String sql = "select * from empresas,ciudades where empresas.id_ciudad=ciudades.id_ciudad and upper(nombre_ciudad) like '%" + nombre.toUpperCase() + "%'" + "order by id_empresa offset " + offset + "limit " + Utiles.REGISTROS_PAGINA;
                System.out.println("--->" + sql);
                try(PreparedStatement ps= Conexion.getConn().prepareStatement(sql)){
                    ResultSet rs = ps.executeQuery();
                    String tabla="";
                    while (rs.next()) {
                        tabla+= "<tr>"
                                + "<td>" + rs.getString("id_empresa")+ "</td>"
                                + "<td>" +rs.getString("nombre_empresa")+ "</td>"
                                + "<td>" + rs.getString("email_empresa")+ "</td>"
                                + "<td>" + rs.getString("direccion_empresa")+ "</td>"
                                + "<td>" + rs.getString("telefono_empresa")+ "</td>"
                                + "<td>" + rs.getString("ruc_empresa")+ "</td>"
                                + "<td>" + rs.getString("nombre_ciudad")+ "</td>"+"</tr>"; 
                               
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
  
}
