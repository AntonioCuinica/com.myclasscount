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
public class Turma {
    private int id;
    private String nome;
    private String classe;

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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Turma\nid=").append(id);
        sb.append("\nnome=").append(nome);
        sb.append("\nclasse=").append(classe);
        return sb.toString();
    }
    
    
}
