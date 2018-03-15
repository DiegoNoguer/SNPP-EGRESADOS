/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

/**
 *
 * @author Usuario
 */
public class Empresas {
 private int  id_empresa;
 private String nombre_empresa;
 private String email_empresa;
 private String direccion_empresa;
 private String telefono_empresa;
 private String ruc_empresa;
 private String buscamos_empresa;
 private String turno_empresa;
 private String horario_empresa;
 private String salario_empresa;
 private String vacancia_empresa;
 private String rangoedad_empresa;
 private String tipogenero_empresa;
 private Ciudades ciudad;

    public Empresas() {
    }

    public Empresas(int id_empresa, String nombre_empresa, String email_empresa, String direccion_empresa, String telefono_empresa, String ruc_empresa, String buscamos_empresa, String turno_empresa, String horario_empresa, String salario_empresa, String vacancia_empresa, String rangoedad_empresa, String tipogenero_empresa, Ciudades ciudad) {
        this.id_empresa = id_empresa;
        this.nombre_empresa = nombre_empresa;
        this.email_empresa = email_empresa;
        this.direccion_empresa = direccion_empresa;
        this.telefono_empresa = telefono_empresa;
        this.ruc_empresa = ruc_empresa;
        this.buscamos_empresa = buscamos_empresa;
        this.turno_empresa = turno_empresa;
        this.horario_empresa = horario_empresa;
        this.salario_empresa = salario_empresa;
        this.vacancia_empresa = vacancia_empresa;
        this.rangoedad_empresa = rangoedad_empresa;
        this.tipogenero_empresa = tipogenero_empresa;
        this.ciudad = ciudad;
    }

    public int getId_empresa() {
        return id_empresa;
    }

    public void setId_empresa(int id_empresa) {
        this.id_empresa = id_empresa;
    }

    public String getNombre_empresa() {
        return nombre_empresa;
    }

    public void setNombre_empresa(String nombre_empresa) {
        this.nombre_empresa = nombre_empresa;
    }

    public String getEmail_empresa() {
        return email_empresa;
    }

    public void setEmail_empresa(String email_empresa) {
        this.email_empresa = email_empresa;
    }

    public String getDireccion_empresa() {
        return direccion_empresa;
    }

    public void setDireccion_empresa(String direccion_empresa) {
        this.direccion_empresa = direccion_empresa;
    }

    public String getTelefono_empresa() {
        return telefono_empresa;
    }

    public void setTelefono_empresa(String telefono_empresa) {
        this.telefono_empresa = telefono_empresa;
    }

    public String getRuc_empresa() {
        return ruc_empresa;
    }

    public void setRuc_empresa(String ruc_empresa) {
        this.ruc_empresa = ruc_empresa;
    }

    public String getBuscamos_empresa() {
        return buscamos_empresa;
    }

    public void setBuscamos_empresa(String buscamos_empresa) {
        this.buscamos_empresa = buscamos_empresa;
    }

    public String getTurno_empresa() {
        return turno_empresa;
    }

    public void setTurno_empresa(String turno_empresa) {
        this.turno_empresa = turno_empresa;
    }

    public String getHorario_empresa() {
        return horario_empresa;
    }

    public void setHorario_empresa(String horario_empresa) {
        this.horario_empresa = horario_empresa;
    }

    public String getSalario_empresa() {
        return salario_empresa;
    }

    public void setSalario_empresa(String salario_empresa) {
        this.salario_empresa = salario_empresa;
    }

    public String getVacancia_empresa() {
        return vacancia_empresa;
    }

    public void setVacancia_empresa(String vacancia_empresa) {
        this.vacancia_empresa = vacancia_empresa;
    }

    public String getRangoedad_empresa() {
        return rangoedad_empresa;
    }

    public void setRangoedad_empresa(String rangoedad_empresa) {
        this.rangoedad_empresa = rangoedad_empresa;
    }

    public String getTipogenero_empresa() {
        return tipogenero_empresa;
    }

    public void setTipogenero_empresa(String tipogenero_empresa) {
        this.tipogenero_empresa = tipogenero_empresa;
    }

    public Ciudades getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudades ciudad) {
        this.ciudad = ciudad;
    }


    
}
