/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

/**
 *
 * @author ALUMNO
 */
public class Cursos {
    private int id_curso;
    private String nombre_curso;
    private String tipo_curso;
    private String duracion_curso;
    private String año_curso;
    private  Especialidades especialidad;

    public Cursos() {
    }

    public Cursos(int id_curso, String nombre_curso, String tipo_curso, String duracion_curso, String año_curso, Especialidades especialidad) {
        this.id_curso = id_curso;
        this.nombre_curso = nombre_curso;
        this.tipo_curso = tipo_curso;
        this.duracion_curso = duracion_curso;
        this.año_curso = año_curso;
        this.especialidad = especialidad;
    }

    public int getId_curso() {
        return id_curso;
    }

    public void setId_curso(int id_curso) {
        this.id_curso = id_curso;
    }

    public String getNombre_curso() {
        return nombre_curso;
    }

    public void setNombre_curso(String nombre_curso) {
        this.nombre_curso = nombre_curso;
    }

    public String getTipo_curso() {
        return tipo_curso;
    }

    public void setTipo_curso(String tipo_curso) {
        this.tipo_curso = tipo_curso;
    }

    public String getDuracion_curso() {
        return duracion_curso;
    }

    public void setDuracion_curso(String duracion_curso) {
        this.duracion_curso = duracion_curso;
    }

    public String getAño_curso() {
        return año_curso;
    }

    public void setAño_curso(String año_curso) {
        this.año_curso = año_curso;
    }

    public Especialidades getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(Especialidades especialidad) {
        this.especialidad = especialidad;
    }

   
    
}
