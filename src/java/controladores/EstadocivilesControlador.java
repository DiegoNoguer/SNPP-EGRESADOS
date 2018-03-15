/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelos.Estadociviles;
import utiles.Conexion;
import utiles.Utiles;

/**
 *
 * @author Usuario
 */
public class EstadocivilesControlador {
    public static boolean agregar(Estadociviles estadocivil){
     boolean valor = false;
     if (Conexion.conectar()){
        String sql = "insert into estadociviles (nombre_estadocivil)" + "values('" + estadocivil.getNombre_estadocivil()+"')";  
        
         
        try {
           Conexion.getSt().executeUpdate(sql);
           valor=true; 
        } catch (SQLException ex){
           System.err.println("Error:" + ex); 
        }
     }
    return valor;   
  }   
   
    public static Estadociviles buscarId(Estadociviles estadocivil){
            if (Conexion.conectar()){
                String sql ="select * from estadociviles where id_estadocivil='" + estadocivil.getId_estadocivil() + "'";
                try {
                     ResultSet rs = Conexion.getSt().executeQuery(sql);
                     if (rs.next()){
                            estadocivil.setNombre_estadocivil(rs.getString("nombre_estadocivil"));
                            
                     }else {
                             estadocivil.setId_estadocivil(0);
                             estadocivil.setNombre_estadocivil("");
                             
                     }
               } catch (SQLException ex) {
                     System.err.println("Error controlador - buscar id: " + ex);
               }
}
return estadocivil;
}

    public static String buscaNombre(String nombre, int pagina) {
        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()){
            try{
                String sql = "select * from estadociviles where upper(nombre_estadocivil) like '%" + nombre.toUpperCase() + "%'" + "order by id_estadocivil offset " + offset + "limit " + Utiles.REGISTROS_PAGINA;
                System.out.println("--->" + sql);
                try(PreparedStatement ps= Conexion.getConn().prepareStatement(sql)){
                    ResultSet rs = ps.executeQuery();
                    String tabla="";
                    while (rs.next()) {
                        tabla+= "<tr>"
                                + "<td>" + rs.getString("id_estadocivil")+ "</td>"
                                + "<td>" +rs.getString("nombre_estadocivil")+ "</td>"+"</tr>"; 
                               
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

  
  public static boolean modificar(Estadociviles estadocivil) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update estadociviles " + "set nombre_estadocivil='"
                    + estadocivil.getNombre_estadocivil()
                    + "' where id_estadocivil=" + estadocivil.getId_estadocivil();
                    
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
  
  
  public static boolean eliminar (Estadociviles estadocivil) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from estadociviles where id_estadocivil=" + estadocivil.getId_estadocivil();
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            } catch (SQLException ex) {
                System.err.println("Error" + ex);
            }
        }
        return valor;
    }
}
