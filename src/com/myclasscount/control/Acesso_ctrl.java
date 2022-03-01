/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myclasscount.control;

import com.myclasscount.model.Acesso;
import com.myclasscount.model.Professor;
import com.myclasscount.model.dao.Acesso_dao;
import com.myclasscount.model.validar.ValidarAcesso;

/**
 *
 * @author CUINIC4
 */
public class Acesso_ctrl {
    public static Acesso getAcesso(String user){
        return Acesso_dao.getAcesso(user);
    }
    
    public static void mudarSenha(String codigo,int prof_id){
        Acesso_dao.mudarSenha(codigo, prof_id);
    }
    
    public static String setAcesso(Acesso acesso,String prof_BI){
        String resultado=ValidarAcesso.setAcesso(acesso);
        if(resultado.equals("valido")){
            Professor prof=Professor_ctrl.getProfessor(prof_BI);
            Acesso_dao.setAcesso(acesso,prof.getId());
        }
        return resultado;
    }
    
}
