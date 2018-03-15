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
public class DetallesTrabajos {
    private int id_detalletrabajo;
    private Date fechainicio_detalletrabajo;
    private Date fechafin_detalletrabajo;
    private Trabajos trabajo;
    private Empresas empresa;

    public DetallesTrabajos() {
    }

    public DetallesTrabajos(int id_detalletrabajo, Date fechainicio_detalletrabajo, Date fechafin_detalletrabajo, Trabajos trabajo, Empresas empresa) {
        this.id_detalletrabajo = id_detalletrabajo;
        this.fechainicio_detalletrabajo = fechainicio_detalletrabajo;
        this.fechafin_detalletrabajo = fechafin_detalletrabajo;
        this.trabajo = trabajo;
        this.empresa = empresa;
    }

    public int getId_detalletrabajo() {
        return id_detalletrabajo;
    }

    public void setId_detalletrabajo(int id_detalletrabajo) {
        this.id_detalletrabajo = id_detalletrabajo;
    }

    public Date getFechainicio_detalletrabajo() {
        return fechainicio_detalletrabajo;
    }

    public void setFechainicio_detalletrabajo(Date fechainicio_detalletrabajo) {
        this.fechainicio_detalletrabajo = fechainicio_detalletrabajo;
    }

    public Date getFechafin_detalletrabajo() {
        return fechafin_detalletrabajo;
    }

    public void setFechafin_detalletrabajo(Date fechafin_detalletrabajo) {
        this.fechafin_detalletrabajo = fechafin_detalletrabajo;
    }

    public Trabajos getTrabajo() {
        return trabajo;
    }

    public void setTrabajo(Trabajos trabajo) {
        this.trabajo = trabajo;
    }

    public Empresas getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresas empresa) {
        this.empresa = empresa;
    }

    
    
}
