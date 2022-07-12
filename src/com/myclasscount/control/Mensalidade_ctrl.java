/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myclasscount.control;

import com.myclasscount.model.Mensalidade;
import com.myclasscount.model.dao.Mensalidade_dao;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author CUINIC4
 */
public class Mensalidade_ctrl {
    
    public static Mensalidade getMensalidade(int id){
        return Mensalidade_dao.getMensalidade(id);
    }
    
    public static ArrayList<Mensalidade> getMensalidades(){
        return Mensalidade_dao.getMensalidades();
    }
    
    public static ArrayList<Mensalidade> getMensalidadesDiferentes(){
        return Mensalidade_dao.getMensalidadesDiferentes();
    }
    
    public static ArrayList<Mensalidade> getMensalidades(int aluno_id){
        return Mensalidade_dao.getMensalidades(aluno_id);
    }
    
    public static ArrayList<Mensalidade> getMensalidades(String ano){
        return Mensalidade_dao.getMensalidades(ano);
    }
    
    
    public static void updateMensalidade(Mensalidade mensalidade){
        Mensalidade_dao.updateMensalidade(mensalidade);
    }
    
    public static void setMensalidade(int aluno_id,Date dataInicial){
        ArrayList<Mensalidade> mensaAl=getMensalidades(aluno_id);
        boolean teste=true;
        for(Mensalidade m:mensaAl){
            Calendar data=Calendar.getInstance();
            data.setTime(dataInicial);
            data.add(Calendar.DAY_OF_MONTH,31);
            String mes1=new SimpleDateFormat("MM/yyyy").format(data.getTime());
            String mes2=new SimpleDateFormat("MM/yyyy").format(m.getDataPagamento());
            System.out.println("Mes1: "+mes1);
            System.out.println("Mes2: "+mes2);
            if(mes1.equals(mes2)){
                teste=false;
                break;
            }
        }
        if(teste)Mensalidade_dao.setMensalidade(aluno_id,dataInicial);
    }
    
    public static void inserirMensalidades(){
        Mensalidade.actualizarMensalidadesTeste();
    }
}
