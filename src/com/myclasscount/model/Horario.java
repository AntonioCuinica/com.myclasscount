/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myclasscount.model;

/**
 *
 * @author CUINIC4
 */
public class Horario {
    private int id;
    private String turno;
    private String hora_inicio;
    private String dia_semana;
    private int turma_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public String getHora_inicio() {
        return hora_inicio;
    }

    public void setHora_inicio(String hora_inicio) {
        this.hora_inicio = hora_inicio;
    }

    public String getDia_semana() {
        return dia_semana;
    }

    public void setDia_semana(String dia_semana) {
        this.dia_semana = dia_semana;
    }

    public int getTurma_id() {
        return turma_id;
    }

    public void setTurma_id(int turma_id) {
        this.turma_id = turma_id;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Horario\nid=").append(id);
        sb.append("\nturno=").append(turno);
        sb.append("\nhora_inicio=").append(hora_inicio);
        sb.append("\ndia_semana=").append(dia_semana);
        sb.append("\nturma_id=").append(turma_id);
        return sb.toString();
    }
    
    
}
