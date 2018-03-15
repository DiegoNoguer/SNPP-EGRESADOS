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
import modelos.Puestos;
import utiles.Conexion;
import utiles.Utiles;

/**
 *
 * @author Usuario
 */
public class PuestosControlador {
    public static boolean agregar(Puestos puesto){
     boolean valor = false;
     if (Conexion.conectar()){
        String sql = "insert into puestos(nombre_puesto,id_area)"
                    + "values('" +puesto.getNombre_puesto() + "','"                    
                    + puesto.getArea().getId_area() + "')";
         
        try {
           Conexion.getSt().executeUpdate(sql);
           valor=true; 
        } catch (SQLException ex){
           System.err.println("Error:" + ex); 
        }
     }
    return valor;   
  }   
   
    public static Puestos buscarId(Puestos puesto){
            if (Conexion.conectar()){
                String sql ="select * from puestos,areas where puestos.id_area=areas.id_area and id_puesto='" + puesto.getId_puesto() + "'";
                try {
                     ResultSet rs = Conexion.getSt().executeQuery(sql);
                     if (rs.next()){
                            puesto.setNombre_puesto(rs.getString("nombre_puesto"));
                            //AREAS
                    Areas area = new Areas();
                    area.setId_area(rs.getInt("id_area"));
                    area.setNombre_area(rs.getString("nombre_area"));
                    puesto.setArea(area);
                            
                           
                            
                     }else {
                             puesto.setId_puesto(0);
                             puesto.setNombre_puesto("");
                             
                    Areas area = new Areas();
                    area.setId_area(0);
                    area.setNombre_area("");
                    puesto.setArea(area);
                             
                            
                             
                     }
               } catch (SQLException ex) {
                     System.err.println("Error controlador - buscar id: " + ex);
               }
}
return puesto;
}

    public static String buscaNombre(String nombre, int pagina) {
        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()){
            try{
                String sql = "select * from puestos,areas where puestos.id_area=areas.id_area and upper(nombre_puesto) like '%" + nombre.toUpperCase() + "%'" + "order by id_puesto offset " + offset + "limit " + Utiles.REGISTROS_PAGINA;
                System.out.println("--->" + sql);
                try(PreparedStatement ps= Conexion.getConn().prepareStatement(sql)){
                    ResultSet rs = ps.executeQuery();
                    String tabla="";
                    while (rs.next()) {
                        tabla+= "<tr>"
                                + "<td>" + rs.getString("id_puesto")+ "</td>"
                                + "<td>" +rs.getString("nombre_puesto")+ "</td>"
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

  
  public static boolean modificar(Puestos puesto) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update puestos set nombre_puesto='" + puesto.getNombre_puesto()
                    + "',id_area='" + puesto.getArea().getId_area() + ""
                    + "' where id_puesto=" + puesto.getId_puesto();
                    
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
  
  
  public static boolean eliminar (Puestos puesto) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from puestos where id_puesto=" + puesto.getId_puesto();
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

