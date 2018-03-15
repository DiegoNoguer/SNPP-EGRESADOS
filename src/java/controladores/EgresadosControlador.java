/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelos.Ciudades;
import modelos.Departamentos;
import modelos.Egresados;
//import modelos.Especialidades;
import modelos.Estadociviles;
import utiles.Conexion;
import utiles.Utiles;

/**
 *
 * @author Usuario
 */
public class EgresadosControlador {
    public static boolean agregar(Egresados egresado){
     boolean valor = false;
     if (Conexion.conectar()){
        String sql = "insert into egresados (nombre_egresado,apellido_egresado,telefono_egresado,cedula_egresado,fechanaci_egresado,edad_egresado,id_ciudad,id_estadocivil,otroestudio_egresado,nivelacademico_egresado,aptitud_egresado,manejoidioma_egresado,tipogenero_egresado)"               
                + "values('" + egresado.getNombre_egresado() + "','"
                    + egresado.getApellido_egresado() + "','"
                    + egresado.getTelefono_egresado() + "','"
                    + egresado.getCedula_egresado() + "','"
                    + egresado.getFechanaci_egresado() + "','"
                    + egresado.getEdad_egresado() + "','"
                    + egresado.getCiudad().getId_ciudad() + "','"
                    + egresado.getEstadocivil().getId_estadocivil() + "','" 
                    + egresado.getOtroestudio_egresado() + "','"  
                    + egresado.getNivelacademico_egresado() + "','"   
                    + egresado.getAptitud_egresado() + "','"  
                    + egresado.getManejoidioma_egresado() + "','"  
                    + egresado.getTipogenero_egresado() + "')";  
        
         
        try {
           Conexion.getSt().executeUpdate(sql);
           valor=true; 
        } catch (SQLException ex){
           System.err.println("Error:" + ex); 
        }
     }
    return valor;   
  }   
   
    public static Egresados buscarId(Egresados egresado){
            if (Conexion.conectar()){
                //String sql ="select * from egresados,ciudades  where egresados.id_ciudad=ciudades.id_ciudad and id_egresado='" + egresado.getId_egresado() + "'";
                String sql = "select * from egresados,ciudades,estadociviles,departamentos where "
            + "ciudades.id_ciudad=egresados.id_ciudad and estadociviles.id_estadocivil " +
            "=egresados.id_estadocivil  and departamentos.id_departamento=ciudades.id_departamento and id_egresado='" + egresado.getId_egresado() + "'";
                
                System.out.println("sql"+sql);
                try {
                     ResultSet rs = Conexion.getSt().executeQuery(sql);
                     if (rs.next()){
                            egresado.setNombre_egresado(rs.getString("nombre_egresado"));
                            egresado.setApellido_egresado(rs.getString("apellido_egresado"));
                            egresado.setTelefono_egresado(rs.getString("telefono_egresado"));
                            egresado.setCedula_egresado(rs.getInt("cedula_egresado"));
                            
                            Departamentos departamento = new Departamentos();
                            departamento.setNombre_departamento(rs.getString("nombre_departamento"));
                            
                            
                            Ciudades ciudad = new Ciudades();
                            ciudad.setId_ciudad(rs.getInt("id_ciudad"));
                            ciudad.setNombre_ciudad(rs.getString("nombre_ciudad"));
                            ciudad.setDepartamento(departamento);
                            egresado.setCiudad(ciudad);
                            
                            
                            Estadociviles estadocivil = new Estadociviles();
                            estadocivil.setId_estadocivil(rs.getInt("id_estadocivil"));
                            estadocivil.setNombre_estadocivil(rs.getString("nombre_estadocivil"));
                            egresado.setEstadocivil(estadocivil);
                            egresado.setFechanaci_egresado(rs.getDate("fechanaci_egresado"));
                            egresado.setEdad_egresado(rs.getInt("edad_egresado"));
                            
                            egresado.setOtroestudio_egresado(rs.getString("otroestudio_egresado"));
                            egresado.setNivelacademico_egresado(rs.getString("nivelacademico_egresado"));
                            egresado.setAptitud_egresado(rs.getString("aptitud_egresado"));
                            egresado.setManejoidioma_egresado(rs.getString("manejoidioma_egresado"));
                            egresado.setTipogenero_egresado(rs.getString("tipogenero_egresado"));
                            
                            
                            
                            
                            
                            
                     }else {
                             egresado.setId_egresado(0);
                             egresado.setNombre_egresado("");
                             egresado.setApellido_egresado("");
                             egresado.setTelefono_egresado("");
                             egresado.setCedula_egresado(0);
                             
                             Departamentos departamento = new Departamentos();
                            departamento.setNombre_departamento("");
                            
                             Ciudades ciudad = new Ciudades();
                             ciudad.setId_ciudad(0);
                             ciudad.setNombre_ciudad("");
                             ciudad.setDepartamento(departamento);
                             egresado.setCiudad(ciudad);
                             
                             
                             Estadociviles estadocivil = new Estadociviles();
                             estadocivil.setId_estadocivil(0);
                             estadocivil.setNombre_estadocivil("");
                             egresado.setEstadocivil(estadocivil);
                             java.sql.Date fechanaci_egresado = new java.sql.Date(new java.util.Date().getTime());
                             egresado.setFechanaci_egresado(fechanaci_egresado);
                             egresado.setEdad_egresado(0);
                             
                             
                             egresado.setOtroestudio_egresado("");
                            egresado.setNivelacademico_egresado("Tecnico en Informatica");
                            egresado.setAptitud_egresado("Proactivo");
                            egresado.setManejoidioma_egresado("");
                            egresado.setTipogenero_egresado("Masculino");
                             
                            
                             
                             
                             
                     }
               } catch (SQLException ex) {
                     System.err.println("Error controlador - buscar id: " + ex);
               }
}
return egresado;
}

   public static String buscaNombre(String nombre, int pagina) {
        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()){
            try{
                //String sql = "select * from egresados ,ciudades where egresados.id_ciudad=ciudades.id_ciudad and upper(nombre_egresado) like '%" + nombre.toUpperCase() + "%'" + "order by id_egresado offset " + offset + "limit " + Utiles.REGISTROS_PAGINA;
                String sql = "select * from egresados ,ciudades where egresados.id_ciudad=ciudades.id_ciudad and cedula_egresado = '" + nombre.toUpperCase() + "'" + "order by id_egresado offset " + offset + "limit " + Utiles.REGISTROS_PAGINA;
                System.out.println("--->" + sql);
                try(PreparedStatement ps= Conexion.getConn().prepareStatement(sql)){
                    ResultSet rs = ps.executeQuery();
                    String tabla="";
                    while (rs.next()) {
                        tabla+= "<tr>"
                                + "<td>" + rs.getString("id_egresado")+ "</td>"
                                + "<td>" +rs.getString("nombre_egresado")+ "</td>"
                                + "<td>" +rs.getString("apellido_egresado")+ "</td>"
                                + "<td>" +rs.getString("telefono_egresado")+ "</td>"
                                + "<td>" +rs.getInt("cedula_egresado")+ "</td>"
                                + "<td>" +rs.getDate("fechanaci_egresado")+ "</td>"
                                + "<td>" +rs.getInt("edad_egresado")+ "</td>"+"</tr>"; 
                               
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

  
  public static boolean modificar(Egresados egresado) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update egresados set nombre_egresado='" + egresado.getNombre_egresado()                
                    + "',apellido_egresado='" + egresado.getApellido_egresado() + ""
                    + "',telefono_egresado='" + egresado.getTelefono_egresado() + ""
                    + "',cedula_egresado='" + egresado.getCedula_egresado() + ""
                    + "',fechanaci_egresado='" + egresado.getFechanaci_egresado() + ""
                    + "',edad_egresado='" + egresado.getEdad_egresado() + ""
                    + "',id_ciudad='" + egresado.getCiudad().getId_ciudad() + ""
                    + "',id_estadocivil='" + egresado.getEstadocivil().getId_estadocivil() + ""
                    + "',otroestudio_egresado='" + egresado.getOtroestudio_egresado() + ""
                    + "',nivelacademico_egresado='" + egresado.getNivelacademico_egresado() + ""
                    + "',aptitud_egresado='" + egresado.getAptitud_egresado() + ""
                    + "',manejoidioma_egresado='" + egresado.getManejoidioma_egresado() + ""
                    + "',tipogenero_egresado='" + egresado.getTipogenero_egresado() + ""
                    + "' where id_egresado=" + egresado.getId_egresado();
      
                    
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
  
  
  public static boolean eliminar (Egresados egresado) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from egresados where id_egresado=" + egresado.getId_egresado();
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
                String sql = "select * from egresados where upper(nombre_egresado) like '%" + nombre.toUpperCase() + "%'" + "order by id_egresado offset " + offset + "limit " + Utiles.REGISTROS_PAGINA;
                System.out.println("--->" + sql);
                try(PreparedStatement ps= Conexion.getConn().prepareStatement(sql)){
                    ResultSet rs = ps.executeQuery();
                    String tabla="";
                    while (rs.next()) {
                        tabla+= "<tr>"
                                + "<td>" + rs.getString("id_egresado")+ "</td>"
                                + "<td>" +rs.getString("nombre_egresado")+ "</td>"
                                + "<td>" + rs.getString("apellido_egresado")+ "</td>"
                                + "<td>" + rs.getString("telefono_egresado")+ "</td>"
                                + "<td>" + rs.getString("cedula_egresado")+ "</td>"
                                + "<td>" + rs.getDate("fechanaci_egresado")+ "</td>"
                                + "<td>" + rs.getString("edad_egresado")+ "</td>"+"</tr>"; 
                               
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
 
 
 
 
 
 
 
 
 
 public static Egresados buscarCedula(int id) {
        Egresados egresado = null;
        if (Conexion.conectar()) {
            String sql = "select* from egresados where cedula_egresado='" + id + "'";

            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()) {
                    egresado = new Egresados();
                    egresado.setCedula_egresado(0);

                }

            } catch (SQLException ex) {
                System.err.println("Error:" + ex);
            }
        }
        return egresado;
    }


 
 
 
 
 
 
 
 
 
 
 
 
 
 
}