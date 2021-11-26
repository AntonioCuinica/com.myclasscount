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
public class Observacao {
    private int professor_id;
    private int aluno_id;
    private String titulo;
    private String nota;
    private String dataObservacao;

    public int getProfessor_id() {
        return professor_id;
    }

    public void setProfessor_id(int professor_id) {
        this.professor_id = professor_id;
    }

    public int getAluno_id() {
        return aluno_id;
    }

    public void setAluno_id(int aluno_id) {
        this.aluno_id = aluno_id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public String getDataObservacao() {
        return dataObservacao;
    }

    public void setDataObservacao(String dataObservacao) {
        this.dataObservacao = dataObservacao;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Observacao\nprofessor_id=").append(professor_id);
        sb.append("\naluno_id=").append(aluno_id);
        sb.append("\ntitulo=").append(titulo);
        sb.append("\nnota=").append(nota);
        sb.append("\ndataObservacao=").append(dataObservacao);
        return sb.toString();
    }
    
    
}
