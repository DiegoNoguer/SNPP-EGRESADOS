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
import modelos.Empresas;
import modelos.Oportunidades;
import utiles.Conexion;
import utiles.Utiles;

/**
 *
 * @author Usuario
 */
public class OportunidadesControlador {
    public static boolean agregar(Oportunidades oportunidad){
     boolean valor = false;
     if (Conexion.conectar()){
        String sql = "insert into oportunidades(nombre_oportunidad,fecha_oportunidad,id_empresa) "
                    + "values('" + oportunidad.getNombre_oportunidad() + "','"
                    + oportunidad.getFecha_oportunidad() + "','"
                    + oportunidad.getEmpresa().getId_empresa() + "')";
        
         
     try {
                Conexion.getSt().executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
                ResultSet keyset = Conexion.getSt().getGeneratedKeys();
                if (keyset.next()) {
                    int id_oportunidad = keyset.getInt(1);
                    oportunidad.setId_oportunidad(id_oportunidad);
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
   
    public static Oportunidades buscarId(Oportunidades oportunidad){
            if (Conexion.conectar()){
                String sql ="select * from oportunidades o"
                      
                        + " left join empresas e on e.id_empresa=o.id_empresa "
                          + "left join ciudades c on c.id_ciudad=e.id_ciudad "
                     
                        + "where id_oportunidad=' " + oportunidad.getId_oportunidad() + " '";
                try {
                     ResultSet rs = Conexion.getSt().executeQuery(sql);
                     if (rs.next()){
                       
                         oportunidad.setNombre_oportunidad(rs.getString("nombre_oportunidad"));
                            oportunidad.setFecha_oportunidad(rs.getDate("fecha_oportunidad"));
                            
                            //CIUDADES  
                            Ciudades ciudad= new Ciudades();
                            
                            ciudad.setNombre_ciudad(rs.getString("nombre_ciudad"));
                            
                            
                            //EMPRESAS 
                            Empresas empresa = new Empresas();
                            empresa.setId_empresa(rs.getInt("id_empresa"));
                            empresa.setNombre_empresa(rs.getString("nombre_empresa"));
                            empresa.setEmail_empresa(rs.getString("email_empresa"));
                            empresa.setDireccion_empresa(rs.getString("direccion_empresa"));
                            empresa.setTelefono_empresa(rs.getString("telefono_empresa"));
                            empresa.setRuc_empresa(rs.getString("ruc_empresa"));
                                   empresa.setCiudad(ciudad);
                            oportunidad.setEmpresa(empresa);
                            
                     }else {
                             oportunidad.setId_oportunidad(0);
                            oportunidad.setNombre_oportunidad("");
                             java.sql.Date fecha_oportunidad = new java.sql.Date(new java.util.Date().getTime());
                             oportunidad.setFecha_oportunidad(fecha_oportunidad);
                             
                             //CIUDADES  
                            Ciudades ciudad= new Ciudades();
                            ciudad.setNombre_ciudad("");
                             
                             //EMPRESAS 
                            Empresas empresa = new Empresas();
                            empresa.setId_empresa(0);
                            empresa.setNombre_empresa("");
                            empresa.setEmail_empresa("");
                            empresa.setDireccion_empresa("");
                            empresa.setTelefono_empresa("");
                            empresa.setRuc_empresa("");
                            empresa.setCiudad(ciudad);
                            oportunidad.setEmpresa(empresa);
                     }
               } catch (SQLException ex) {
                     System.err.println("Error controlador - buscar id: " + ex);
               }
}
return oportunidad;
}

    public static String buscaNombre(String nombre, int pagina) {
        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()){
            try{
                String sql = "select * from oportunidades o "
                        + "left join empresas e on o.id_empresa=e.id_empresa "
                        + "left join ciudades c on c.id_ciudad=e.id_ciudad and "
                        
                        + " upper(nombre_oportunidad) like "
                        + "'%" + nombre.toUpperCase() + "%'"  
                        + "order by id_oportunidad offset " + offset + "limit " + Utiles.REGISTROS_PAGINA;
                System.out.println("--->" + sql);
                try(PreparedStatement ps= Conexion.getConn().prepareStatement(sql)){
                    ResultSet rs = ps.executeQuery();
                    String tabla="";
                    while (rs.next()) {
                        tabla+= "<tr>"
                                + "<td>" + rs.getString("id_oportunidad")+ "</td>"
                                + "<td>" +rs.getDate("fecha_oportunidad")+ "</td>"
                                + "<td>" +rs.getString("nombre_empresa")+ "</td>"
                                + "<td>" +rs.getString("email_empresa")+ "</td>"
                                + "<td>" +rs.getString("direccion_empresa")+ "</td>"
                                + "<td>" +rs.getString("telefono_empresa")+ "</td>"
                                + "<td>" +rs.getString("ruc_empresa")+ "</td>"
                                + "<td>" +rs.getString("nombre_ciudad")+ "</td>"+"</tr>"; 
                               
                    }
                    if(tabla.equals("")){
                        tabla = "<tr><td colspan =2>No Existen Oportunidades...</td><tr>";
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

  
  public static boolean modificar(Oportunidades oportunidad) {
        boolean valor = false;
        if (Conexion.conectar()) {
            //String sql="update oportunidades set nombre_oportunidad='"+oportunidad.getNombre_oportunidad()+"'"
                   // +" where id_oportunidad="+oportunidad.getId_oportunidad();
                   String sql = "update oportunidades set nombre_oportunidad='" + oportunidad.getNombre_oportunidad()
                    + "',fecha_oportunidad='" + oportunidad.getFecha_oportunidad() + ""
                    + "',id_empresa='" + oportunidad.getEmpresa().getId_empresa() + ""                    
                    + "' where id_oportunidad=" + oportunidad.getId_oportunidad();
                    
            System.out.println(sql);
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
  
  
  public static boolean eliminar (Oportunidades oportunidad) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from oportunidades where id_oportunidad=" + oportunidad.getId_oportunidad();
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            } catch (SQLException ex) {
                System.err.println("Error" + ex);
            }
        }
        Conexion.cerrar();
        return valor;
    }
  
  
  
   public static String buscarNombrePuesto(String nombre, int pagina) throws SQLException {
        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from detallesoportunidades dv "
                        + "left join oportunidades v on v.id_oportunidad=dv.id_oportunidad "
                        + "left join empresas e on e.id_empresa=v.id_empresa "
                        + "left join puestos p on p.id_puesto=dv.id_puesto "
                       // + "left join especialidades es on p.id_especialidad=es.id_especialidad "
                        + "where upper(p.nombre_puesto) like '%"
                        + nombre.toUpperCase()
                        + "%' "
                        + "order by dv.id_oportunidad "
                        + "offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;
                System.out.println("--> " + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                
                               + "<td>" + rs.getInt("id_oportunidad") + "</td>"
                               // + "<td>" + rs.getInt("id_detalleRegistro") + "</td>"
                                + "<td>" + rs.getInt("id_puesto") + "</td>"                              
                                + "<td>" + rs.getString("nombre_puesto") + "</td>"
                                //+ "<td>" + rs.getInt("id_especialidad") + "</td>"
                               // + "<td>" + rs.getInt("nombre_especialidad") + "</td>"
                                + "<td>" + rs.getString("nombre_empresa") + "</td>"
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

