/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.sql.Date;

/**
 *
 * @author Usuario
 */
public class Trabajos {
    private int id_trabajo;
    private String nombre_trabajo;
   // private Date fechainicio_trabajo;
  //  private Date fechafin_trabajo;
    private Egresados egresado;

    public Trabajos() {
    }

    public Trabajos(int id_trabajo, String nombre_trabajo, Egresados egresado) {
        this.id_trabajo = id_trabajo;
        this.nombre_trabajo = nombre_trabajo;
        this.egresado = egresado;
    }

    public int getId_trabajo() {
        return id_trabajo;
    }

    public void setId_trabajo(int id_trabajo) {
        this.id_trabajo = id_trabajo;
    }

    public String getNombre_trabajo() {
        return nombre_trabajo;
    }

    public void setNombre_trabajo(String nombre_trabajo) {
        this.nombre_trabajo = nombre_trabajo;
    }

    public Egresados getEgresado() {
        return egresado;
    }

    public void setEgresado(Egresados egresado) {
        this.egresado = egresado;
    }

   
    
}
