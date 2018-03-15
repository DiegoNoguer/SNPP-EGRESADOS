/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

/**
 *
 * @author DIEGO LIMA
 */
public class DetallesOportunidades {
    private int id_detalleoportunidad;
    private Puestos puesto;
    private Oportunidades oportunidad;
    private int vacancia_detalleoportunidad;
    private String horario_detalleoportunidad;
    private String turno_detalleoportunidad;
    private String rangoedad_detalleoportunidad;

    public DetallesOportunidades() {
    }

    public DetallesOportunidades(int id_detalleoportunidad, Puestos puesto, Oportunidades oportunidad, int vacancia_detalleoportunidad, String horario_detalleoportunidad, String turno_detalleoportunidad, String rangoedad_detalleoportunidad) {
        this.id_detalleoportunidad = id_detalleoportunidad;
        this.puesto = puesto;
        this.oportunidad = oportunidad;
        this.vacancia_detalleoportunidad = vacancia_detalleoportunidad;
        this.horario_detalleoportunidad = horario_detalleoportunidad;
        this.turno_detalleoportunidad = turno_detalleoportunidad;
        this.rangoedad_detalleoportunidad = rangoedad_detalleoportunidad;
    }

    public int getId_detalleoportunidad() {
        return id_detalleoportunidad;
    }

    public void setId_detalleoportunidad(int id_detalleoportunidad) {
        this.id_detalleoportunidad = id_detalleoportunidad;
    }

    public Puestos getPuesto() {
        return puesto;
    }

    public void setPuesto(Puestos puesto) {
        this.puesto = puesto;
    }

    public Oportunidades getOportunidad() {
        return oportunidad;
    }

    public void setOportunidad(Oportunidades oportunidad) {
        this.oportunidad = oportunidad;
    }

    public int getVacancia_detalleoportunidad() {
        return vacancia_detalleoportunidad;
    }

    public void setVacancia_detalleoportunidad(int vacancia_detalleoportunidad) {
        this.vacancia_detalleoportunidad = vacancia_detalleoportunidad;
    }

    public String getHorario_detalleoportunidad() {
        return horario_detalleoportunidad;
    }

    public void setHorario_detalleoportunidad(String horario_detalleoportunidad) {
        this.horario_detalleoportunidad = horario_detalleoportunidad;
    }

    public String getTurno_detalleoportunidad() {
        return turno_detalleoportunidad;
    }

    public void setTurno_detalleoportunidad(String turno_detalleoportunidad) {
        this.turno_detalleoportunidad = turno_detalleoportunidad;
    }

    public String getRangoedad_detalleoportunidad() {
        return rangoedad_detalleoportunidad;
    }

    public void setRangoedad_detalleoportunidad(String rangoedad_detalleoportunidad) {
        this.rangoedad_detalleoportunidad = rangoedad_detalleoportunidad;
    }

    
    
    
}
