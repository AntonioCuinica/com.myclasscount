/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myclasscount.model;

import java.util.Date;

/**
 *
 * @author CUINIC4
 */
public class Mensalidade {
    private int id;
    private String estado;
    private Date dataPagamento;
    private double valor;
    private int aluno_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getAluno_id() {
        return aluno_id;
    }

    public void setAluno_id(int aluno_id) {
        this.aluno_id = aluno_id;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Mensalidade\nid=").append(id);
        sb.append("\nestado=").append(estado);
        sb.append("\ndataPagamento=").append(dataPagamento);
        sb.append("\nvalor=").append(valor);
        sb.append("\naluno_id=").append(aluno_id);
        return sb.toString();
    }
    
    
}
