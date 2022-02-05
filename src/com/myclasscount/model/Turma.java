/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myclasscount.model;

import java.util.ArrayList;

/**
 *
 * @author CUINIC4
 */
public class Turma {
    private int id;
    private String nome;
    private String classe;
    private Horario horario;
    private Professor professor;
    private ArrayList<Aluno> alunos;
    private ArrayList<Horario> horarios;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }
    
    public Horario getHorario() {
        return horario;
    }

    public void setHorario(Horario horario) {
        this.horario = horario;
    }
    
    public ArrayList<Horario> getHorarios() {
        return horarios;
    }

    public void setHorarios(ArrayList<Horario> horarios) {
        this.horarios = horarios;
    }
    
    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public ArrayList<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(ArrayList<Aluno> alunos) {
        this.alunos = alunos;
    }
    
    
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Turma\nid=").append(id);
        sb.append("\nnome=").append(nome);
        sb.append("\nclasse=").append(classe);
         sb.append("\nAlunos=").append(alunos);
        sb.append("\nProfessor=").append(professor);
        return sb.toString();
    }

    
  
}
