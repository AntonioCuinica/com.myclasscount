/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myclasscount.control;

import com.myclasscount.model.Observacao;
import com.myclasscount.model.dao.Observacao_dao;
import java.util.ArrayList;

/**
 *
 * @author CUINIC4
 */
public class Observacao_ctrl {
    
    public static ArrayList<Observacao> getObservacao(){
        return Observacao_dao.getObservacoes();
    }
    
    public static void setObservacao(Observacao observ){
        Observacao_dao.setObservacao(observ);
    }
    
    public static void delObservacao(int observ_id){
        Observacao_dao.delObservacao(observ_id);
    }
}
