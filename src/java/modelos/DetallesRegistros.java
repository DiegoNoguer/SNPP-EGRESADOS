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
public class DetallesRegistros {
    private int id_detalleregistro;
    private Cursos curso;
    private Registros registro;

    public DetallesRegistros() {
    }

    public DetallesRegistros(int id_detalleregistro, Cursos curso, Registros registro) {
        this.id_detalleregistro = id_detalleregistro;
        this.curso = curso;
        this.registro = registro;
    }

    public int getId_detalleregistro() {
        return id_detalleregistro;
    }

    public void setId_detalleregistro(int id_detalleregistro) {
        this.id_detalleregistro = id_detalleregistro;
    }

    public Cursos getCurso() {
        return curso;
    }

    public void setCurso(Cursos curso) {
        this.curso = curso;
    }

    public Registros getRegistro() {
        return registro;
    }

    public void setRegistro(Registros registro) {
        this.registro = registro;
    }

    
    
}
