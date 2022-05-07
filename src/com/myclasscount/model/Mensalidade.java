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
    
    public static void actualizarMensalidades(){
        Calendar cal=Calendar.getInstance();
        String mes=new SimpleDateFormat("MM").format(cal.getTime());
        ArrayList<com.myclasscount.model.Mensalidade> mensa=Mensalidade_ctrl.getMensalidades();
        ArrayList<Aluno> alunos=Aluno_ctrl.getAlunosInscritos();
        String ultimoMes=mes;
        if(mensa.isEmpty()){
            alunos.forEach(
                a -> {
                    Mensalidade_ctrl.setMensalidade(a.getId(),cal.getTime());
                }
            );
        }else{
            ultimoMes=new SimpleDateFormat("MM").format(mensa.get(mensa.size()-1).getDataPagamento());
        }
        int x=Integer.parseInt(mes);
        int y=Integer.parseInt(ultimoMes);
        if(!mensa.isEmpty()){
            if(Math.abs(x-y)<=2 || Math.abs(x-y)==0){
                for(int i=0;i<3;i++){
                    Date dataInicial=mensa.get(mensa.size()-1).getDataPagamento();
                    alunos.forEach(
                        a -> {
                            Mensalidade_ctrl.setMensalidade(a.getId(),dataInicial);
                        }
                    );
                    mensa=Mensalidade_ctrl.getMensalidades();
                }
            }else{
                for(Aluno aluno:alunos){
                    ArrayList<Mensalidade> m=Mensalidade_ctrl.getMensalidades(aluno.getId());
                    if(m.size()==0){
                        System.out.println("Aluno sem mensalidade:: "+aluno.getNome()+" "+aluno.getApelido());
                        Date dataInicial=cal.getTime();
                        Mensalidade_ctrl.setMensalidade(aluno.getId(),dataInicial);
                        for(int i=0;i<3;i++){
                            m=Mensalidade_ctrl.getMensalidades(aluno.getId());
                            dataInicial=m.get(m.size()-1).getDataPagamento();
                            System.out.println("Adicionando na data: "+dataInicial);
                            Mensalidade_ctrl.setMensalidade(aluno.getId(),dataInicial);
                        }
                    }
                }
            }
        }
    }
}
