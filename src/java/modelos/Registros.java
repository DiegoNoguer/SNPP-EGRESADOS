/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.sql.Date;

/**
 *
 * @author DIEGO LIMA
 */
public class Registros {
    private int id_registro;
    private String nombre_registro;
    private Date fecha_registro;
    private Egresados egresado;
    private Ciudades ciudad;

    public Registros() {
    }

    public Registros(int id_registro, String nombre_registro, Date fecha_registro, Egresados egresado, Ciudades ciudad) {
        this.id_registro = id_registro;
        this.nombre_registro = nombre_registro;
        this.fecha_registro = fecha_registro;
        this.egresado = egresado;
        this.ciudad = ciudad;
    }

    public int getId_registro() {
        return id_registro;
    }

    public void setId_registro(int id_registro) {
        this.id_registro = id_registro;
    }

    public String getNombre_registro() {
        return nombre_registro;
    }

    public void setNombre_registro(String nombre_registro) {
        this.nombre_registro = nombre_registro;
    }

    public Date getFecha_registro() {
        return fecha_registro;
    }

    public void setFecha_registro(Date fecha_registro) {
        this.fecha_registro = fecha_registro;
    }

    public Egresados getEgresado() {
        return egresado;
    }

    public void setEgresado(Egresados egresado) {
        this.egresado = egresado;
    }

    public Ciudades getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudades ciudad) {
        this.ciudad = ciudad;
    }

   

    
    
    
}
