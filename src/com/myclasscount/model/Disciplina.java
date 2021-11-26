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
public class Disciplina {
    private int id;
    private String nome;
    private String carga_horaria;
    private String seccao;

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

    public String getCarga_horaria() {
        return carga_horaria;
    }

    public void setCarga_horaria(String carga_horaria) {
        this.carga_horaria = carga_horaria;
    }

    public String getSeccao() {
        return seccao;
    }

    public void setSeccao(String seccao) {
        this.seccao = seccao;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Disciplina\nid=").append(id);
        sb.append("\nnome=").append(nome);
        sb.append("\ncarga_horaria=").append(carga_horaria);
        sb.append("\nseccao=").append(seccao);
        return sb.toString();
    }
    
    
}
