/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelos.Cursos;
import modelos.Especialidades;
import utiles.Conexion;
import utiles.Utiles;

/**
 *
 * @author ALUMNO
 */
public class CursosControlador {
    public static boolean agregar(Cursos curso) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "insert into cursos (nombre_curso,tipo_curso,id_especialidad,duracion_curso,año_curso)"               
                + "values('" + curso.getNombre_curso() + "','"
                + curso.getTipo_curso() + "','"
                + curso.getEspecialidad().getId_especialidad() + "','"
                + curso.getDuracion_curso() + "','"
                + curso.getAño_curso() + "')";
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            } catch (SQLException ex) {
                System.err.println("Error:" + ex);
            }
        }
        return valor;
    }
   
    public static Cursos buscarId(Cursos curso){
            if (Conexion.conectar()){
              String sql = "select * from cursos,especialidades where cursos.id_especialidad=especialidades.id_especialidad and id_curso='" + curso.getId_curso() + "'";
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()) {
                    curso.setNombre_curso(rs.getString("nombre_curso"));
                    curso.setTipo_curso(rs.getString("tipo_curso"));
                    curso.setDuracion_curso(rs.getString("duracion_curso"));
                    curso.setAño_curso(rs.getString("año_curso"));
                    
                    Especialidades especialidad = new Especialidades();
                    especialidad.setId_especialidad(rs.getInt("id_especialidad"));
                    especialidad.setNombre_especialidad(rs.getString("nombre_especialidad"));
                    curso.setEspecialidad(especialidad);
                    

                } else {
                    curso.setId_curso(0);
                    curso.setNombre_curso("");
                    curso.setTipo_curso("Tecnico");
                    curso.setDuracion_curso("2 años");
                    curso.setAño_curso("2017");
                    
                    Especialidades especialidad = new Especialidades();
                    especialidad.setId_especialidad(0);
                    especialidad.setNombre_especialidad("");
                   curso.setEspecialidad(especialidad);
                  
                }
            } catch (SQLException ex) {
                System.err.println("Error:" + ex);
            }
}
return curso;
}

    public static String buscaNombre(String nombre, int pagina) {
        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()){
               try {
                String sql = "select * from cursos ,especialidades where cursos.id_especialidad=especialidades.id_especialidad and upper(nombre_curso) like '%" + nombre.toUpperCase() + "%'"
                        + "order by id_curso offset " + offset + "limit " + Utiles.REGISTROS_PAGINA;
                System.out.println("--->" + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_curso") + "</td>"
                                + "<td>" + rs.getString("nombre_curso") + "</td>"
                                + "<td>" + rs.getString("tipo_curso") + "</td>"
                                + "<td>" + rs.getString("duracion_curso") + "</td>"
                                + "<td>" + rs.getString("año_curso") + "</td>"
                                + "<td>" + rs.getString("id_especialidad") + "</td>"                                
                                + "</tr>";
                    }
                    if (tabla.equals("")) {
                        tabla = "<tr><td colspan=2>No existen registros...</td></tr>";
                    }
                    ps.close();
                    valor = tabla;
                } catch (SQLException ex) {
                    System.err.println("Error: " + ex);
                }
                Conexion.cerrar();
            } catch (Exception ex) {
                System.err.println("Error: " + ex);
            }         
        }
       Conexion.cerrar();
       return valor;
    }

  
  public static boolean modificar(Cursos curso) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update cursos set nombre_curso='" + curso.getNombre_curso()
                    + "',tipo_curso='" + curso.getTipo_curso() + ""
                    + "',duracion_curso='" + curso.getDuracion_curso() + ""
                    + "',año_curso='" + curso.getAño_curso() + ""
                    + "',id_especialidad='" + curso.getEspecialidad().getId_especialidad() + ""
                    + "' where id_curso=" + curso.getId_curso();
            System.out.println(sql);
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            } catch (SQLException ex) {
                System.err.println("Error:" + ex);
            }
        }
        return valor;
    }
  
  
  public static boolean eliminar (Cursos curso) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from cursos where id_curso=" + curso.getId_curso();
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

