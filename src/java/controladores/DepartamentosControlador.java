/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelos.Departamentos;
import utiles.Conexion;
import utiles.Utiles;

/**
 *
 * @author Administrator
 */
public class DepartamentosControlador {
    public static boolean agregar(Departamentos departamento){
     boolean valor = false;
     if (Conexion.conectar()){
        String sql = "insert into departamentos (nombre_departamento)" + "values('" + departamento.getNombre_departamento()+"')";  
        
         
        try {
           Conexion.getSt().executeUpdate(sql);
           valor=true; 
        } catch (SQLException ex){
           System.err.println("Error:" + ex); 
        }
     }
    return valor;   
  }   
   
    public static Departamentos buscarId(Departamentos departamento){
            if (Conexion.conectar()){
                String sql ="select * from departamentos where id_departamento='" + departamento.getId_departamento() + "'";
                try {
                     ResultSet rs = Conexion.getSt().executeQuery(sql);
                     if (rs.next()){
                            departamento.setNombre_departamento(rs.getString("nombre_departamento"));
                            
                     }else {
                             departamento.setId_departamento(0);
                             departamento.setNombre_departamento("");
                             
                     }
               } catch (SQLException ex) {
                     System.err.println("Error controlador - buscar id: " + ex);
               }
}
return departamento;
}

    public static String buscaNombre(String nombre, int pagina) {
        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()){
            try{
                String sql = "select * from departamentos where upper(nombre_departamento) like '%" + nombre.toUpperCase() + "%'" + "order by id_departamento offset " + offset + "limit " + Utiles.REGISTROS_PAGINA;
                System.out.println("--->" + sql);
                try(PreparedStatement ps= Conexion.getConn().prepareStatement(sql)){
                    ResultSet rs = ps.executeQuery();
                    String tabla="";
                    while (rs.next()) {
                        tabla+= "<tr>"
                                + "<td>" + rs.getString("id_departamento")+ "</td>"
                                + "<td>" +rs.getString("nombre_departamento")+ "</td>"+"</tr>"; 
                               
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

  
  public static boolean modificar(Departamentos departamento) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update departamentos " + "set nombre_departamento='"
                    + departamento.getNombre_departamento()
                    + "' where id_departamento=" + departamento.getId_departamento();
                    
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
  
  
  public static boolean eliminar (Departamentos departamento) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from departamentos where id_departamento=" + departamento.getId_departamento();
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
