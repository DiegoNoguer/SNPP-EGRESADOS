/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

/**
 *
 * @author Administrator
 */
public class Ciudades {
    private int id_ciudad;
    private String nombre_ciudad;
    private Departamentos departamento;

    public Ciudades() {
    }

    public Ciudades(int id_ciudad, String nombre_ciudad, Departamentos departamento) {
        this.id_ciudad = id_ciudad;
        this.nombre_ciudad = nombre_ciudad;
        this.departamento = departamento;
    }

    public int getId_ciudad() {
        return id_ciudad;
    }

    public void setId_ciudad(int id_ciudad) {
        this.id_ciudad = id_ciudad;
    }

    public String getNombre_ciudad() {
        return nombre_ciudad;
    }

    public void setNombre_ciudad(String nombre_ciudad) {
        this.nombre_ciudad = nombre_ciudad;
    }

    public Departamentos getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamentos departamento) {
        this.departamento = departamento;
    }

    
    
    

    

    

  
    
    
}
