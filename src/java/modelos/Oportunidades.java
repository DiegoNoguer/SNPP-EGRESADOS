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
public class Oportunidades {
    private int id_oportunidad;
    private String nombre_oportunidad;
    private Date fecha_oportunidad;
    private Empresas empresa;
    private Ciudades ciudad;

    public Oportunidades() {
    }

    public Oportunidades(int id_oportunidad, String nombre_oportunidad, Date fecha_oportunidad, Empresas empresa, Ciudades ciudad) {
        this.id_oportunidad = id_oportunidad;
        this.nombre_oportunidad = nombre_oportunidad;
        this.fecha_oportunidad = fecha_oportunidad;
        this.empresa = empresa;
        this.ciudad = ciudad;
    }

    public int getId_oportunidad() {
        return id_oportunidad;
    }

    public void setId_oportunidad(int id_oportunidad) {
        this.id_oportunidad = id_oportunidad;
    }

    public String getNombre_oportunidad() {
        return nombre_oportunidad;
    }

    public void setNombre_oportunidad(String nombre_oportunidad) {
        this.nombre_oportunidad = nombre_oportunidad;
    }

    public Date getFecha_oportunidad() {
        return fecha_oportunidad;
    }

    public void setFecha_oportunidad(Date fecha_oportunidad) {
        this.fecha_oportunidad = fecha_oportunidad;
    }

    public Empresas getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresas empresa) {
        this.empresa = empresa;
    }

    public Ciudades getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudades ciudad) {
        this.ciudad = ciudad;
    }
    
    
}
