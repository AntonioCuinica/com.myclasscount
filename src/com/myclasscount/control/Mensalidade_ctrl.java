/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myclasscount.control;

import com.myclasscount.model.Mensalidade;
import com.myclasscount.model.dao.Mensalidade_dao;
import java.util.ArrayList;
import java.util.Date;

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
    
    public static ArrayList<Mensalidade> getMensalidades(int aluno_id){
        return Mensalidade_dao.getMensalidades(aluno_id);
    }
    
    public static ArrayList<Mensalidade> getMensalidades(String ano){
        return Mensalidade_dao.getMensalidades(ano);
    }
    
    public static ArrayList<Mensalidade> getMensalidades(String mes,String ano){
        return Mensalidade_dao.getMensalidades(mes,ano);
    }
    
    public static void updateMensalidade(Mensalidade mensalidade){
        Mensalidade_dao.updateMensalidade(mensalidade);
    }
    
    public static void setMensalidade(int aluno_id,Date dataInicial){
        Mensalidade_dao.setMensalidade(aluno_id,dataInicial);
    }
    
    public static void inserirMensalidades(){
        Mensalidade.actualizarMensalidades();
    }
}
