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
public class Egresados {
    private int id_egresado;
    private String nombre_egresado;
    private String apellido_egresado;
    private String telefono_egresado;
    private int cedula_egresado;    
    private Date fechanaci_egresado;
    private int edad_egresado;
    
    private String otroestudio_egresado;
    private String nivelacademico_egresado;
    private String aptitud_egresado;
    private String manejoidioma_egresado;
    private String tipogenero_egresado;
    
    
    
    private Ciudades ciudad;
    private Estadociviles estadocivil;   
    private Departamentos departamento;

    public Egresados() {
    }

    public Egresados(int id_egresado, String nombre_egresado, String apellido_egresado, String telefono_egresado, int cedula_egresado, Date fechanaci_egresado, int edad_egresado, String otroestudio_egresado, String nivelacademico_egresado, String aptitud_egresado, String manejoidioma_egresado, String tipogenero_egresado, Ciudades ciudad, Estadociviles estadocivil, Departamentos departamento) {
        this.id_egresado = id_egresado;
        this.nombre_egresado = nombre_egresado;
        this.apellido_egresado = apellido_egresado;
        this.telefono_egresado = telefono_egresado;
        this.cedula_egresado = cedula_egresado;
        this.fechanaci_egresado = fechanaci_egresado;
        this.edad_egresado = edad_egresado;
        this.otroestudio_egresado = otroestudio_egresado;
        this.nivelacademico_egresado = nivelacademico_egresado;
        this.aptitud_egresado = aptitud_egresado;
        this.manejoidioma_egresado = manejoidioma_egresado;
        this.tipogenero_egresado = tipogenero_egresado;
        this.ciudad = ciudad;
        this.estadocivil = estadocivil;
        this.departamento = departamento;
    }

    public int getId_egresado() {
        return id_egresado;
    }

    public void setId_egresado(int id_egresado) {
        this.id_egresado = id_egresado;
    }

    public String getNombre_egresado() {
        return nombre_egresado;
    }

    public void setNombre_egresado(String nombre_egresado) {
        this.nombre_egresado = nombre_egresado;
    }

    public String getApellido_egresado() {
        return apellido_egresado;
    }

    public void setApellido_egresado(String apellido_egresado) {
        this.apellido_egresado = apellido_egresado;
    }

    public String getTelefono_egresado() {
        return telefono_egresado;
    }

    public void setTelefono_egresado(String telefono_egresado) {
        this.telefono_egresado = telefono_egresado;
    }

    public int getCedula_egresado() {
        return cedula_egresado;
    }

    public void setCedula_egresado(int cedula_egresado) {
        this.cedula_egresado = cedula_egresado;
    }

    public Date getFechanaci_egresado() {
        return fechanaci_egresado;
    }

    public void setFechanaci_egresado(Date fechanaci_egresado) {
        this.fechanaci_egresado = fechanaci_egresado;
    }

    public int getEdad_egresado() {
        return edad_egresado;
    }

    public void setEdad_egresado(int edad_egresado) {
        this.edad_egresado = edad_egresado;
    }

    public String getOtroestudio_egresado() {
        return otroestudio_egresado;
    }

    public void setOtroestudio_egresado(String otroestudio_egresado) {
        this.otroestudio_egresado = otroestudio_egresado;
    }

    public String getNivelacademico_egresado() {
        return nivelacademico_egresado;
    }

    public void setNivelacademico_egresado(String nivelacademico_egresado) {
        this.nivelacademico_egresado = nivelacademico_egresado;
    }

    public String getAptitud_egresado() {
        return aptitud_egresado;
    }

    public void setAptitud_egresado(String aptitud_egresado) {
        this.aptitud_egresado = aptitud_egresado;
    }

    public String getManejoidioma_egresado() {
        return manejoidioma_egresado;
    }

    public void setManejoidioma_egresado(String manejoidioma_egresado) {
        this.manejoidioma_egresado = manejoidioma_egresado;
    }

    public String getTipogenero_egresado() {
        return tipogenero_egresado;
    }

    public void setTipogenero_egresado(String tipogenero_egresado) {
        this.tipogenero_egresado = tipogenero_egresado;
    }

    public Ciudades getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudades ciudad) {
        this.ciudad = ciudad;
    }

    public Estadociviles getEstadocivil() {
        return estadocivil;
    }

    public void setEstadocivil(Estadociviles estadocivil) {
        this.estadocivil = estadocivil;
    }

    public Departamentos getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamentos departamento) {
        this.departamento = departamento;
    }

    
   
}
