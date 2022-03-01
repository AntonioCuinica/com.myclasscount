/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myclasscount.model;

import com.myclasscount.control.Aluno_ctrl;
import com.myclasscount.control.Mensalidade_ctrl;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
    
    public void mudarEstado(double pag){
        if(getValor()==pag){
            setEstado("paga");
        }else{
            Calendar cal=Calendar.getInstance();
            if(cal.getTime().after(dataPagamento)){
                setEstado("divida");
            }else{
                setEstado("aberta");
            }
        }
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
    
    public static void updateMensalidades(){
        String ano=new SimpleDateFormat("yyyy").format(Calendar.getInstance().getTime());
        String mes=new SimpleDateFormat("MM").format(Calendar.getInstance().getTime());
        ArrayList<com.myclasscount.model.Mensalidade> mensa=Mensalidade_ctrl.getMensalidades(mes,ano);
        String ultimoMes;
        ArrayList<Aluno> alunos=Aluno_ctrl.getAlunosInscritos();
        System.out.println("Ano: "+ano);
        System.out.println("Mes: "+mes);
        System.out.println("mensa: "+mensa.size());
        if(mensa.isEmpty()){
            for(Aluno a:alunos){
                Mensalidade_ctrl.setMensalidade(a.getId(),Calendar.getInstance().getTime());
            }
        }
        else{
            ultimoMes=new SimpleDateFormat("MM").format(mensa.get(mensa.size()-1));
            if((Integer.parseInt(mes)+2)>Integer.parseInt(ultimoMes)){
                for(int i=0;i<3;i++){
                    ArrayList<com.myclasscount.model.Mensalidade> men=Mensalidade_ctrl.getMensalidades(mes,ano);
                    Date dataInicial=men.get(mensa.size()-1).getDataPagamento();
                    for(Aluno a:alunos){
                        Mensalidade_ctrl.setMensalidade(a.getId(),dataInicial);
                    }
                }
            }
        }
        
    }
    
}
