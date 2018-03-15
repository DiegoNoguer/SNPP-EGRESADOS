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
import utiles.Conexion;
import utiles.Utiles;

/**
 *
 * @author Usuario
 */
public class AreasControlador {
    public static boolean agregar(Areas area){
     boolean valor = false;
     if (Conexion.conectar()){
        String sql = "insert into areas (nombre_area)" + "values('" + area.getNombre_area()+"')";  
        
         
        try {
           Conexion.getSt().executeUpdate(sql);
           valor=true; 
        } catch (SQLException ex){
           System.err.println("Error:" + ex); 
        }
     }
    return valor;   
  }   
   
    public static Areas buscarId(Areas area){
            if (Conexion.conectar()){
                String sql ="select * from areas where id_area='" + area.getId_area() + "'";
                try {
                     ResultSet rs = Conexion.getSt().executeQuery(sql);
                     if (rs.next()){
                            area.setNombre_area(rs.getString("nombre_area"));
                            
                     }else {
                             area.setId_area(0);
                             area.setNombre_area("");
                             
                     }
               } catch (SQLException ex) {
                     System.err.println("Error controlador - buscar id: " + ex);
               }
}
return area;
}

    public static String buscaNombre(String nombre, int pagina) {
        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()){
            try{
                String sql = "select * from areas where upper(nombre_area) like '%" + nombre.toUpperCase() + "%'" + "order by id_area offset " + offset + "limit " + Utiles.REGISTROS_PAGINA;
                System.out.println("--->" + sql);
                try(PreparedStatement ps= Conexion.getConn().prepareStatement(sql)){
                    ResultSet rs = ps.executeQuery();
                    String tabla="";
                    while (rs.next()) {
                        tabla+= "<tr>"
                                + "<td>" + rs.getString("id_area")+ "</td>"
                                + "<td>" +rs.getString("nombre_area")+ "</td>"+"</tr>"; 
                               
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

  
  public static boolean modificar(Areas area) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update areas " + "set nombre_area='"
                    + area.getNombre_area()
                    + "' where id_area=" + area.getId_area();
                    
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
  
  
  public static boolean eliminar (Areas area) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from areas where id_area=" + area.getId_area();
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

