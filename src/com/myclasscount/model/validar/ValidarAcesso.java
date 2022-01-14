/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myclasscount.model.validar;

import com.myclasscount.control.Acesso_ctrl;
import com.myclasscount.model.Acesso;
import com.myclasscount.model.dao.Acesso_dao;
import com.myclasscount.view.MyDialogg;
import com.myclasscount.view.Myclasscount;

/**
 *
 * @author CUINIC4
 */
public class ValidarAcesso {
    
    public static boolean temAcesso(String user, String psw){
        Acesso acesso=Acesso_dao.getAcesso(user,psw);
        return acesso!=null;
    }
    
    public static String setAcesso(Acesso acesso){
        if(acesso.getUsername().isBlank()){
            MyDialogg dialog=new MyDialogg(Myclasscount.getFrame(),1,"Erro, usuario vazio",true);
            return "invalido 1";
        }else if(Acesso_ctrl.getAcesso(acesso.getUsername())!=null){
            MyDialogg dialog=new MyDialogg(Myclasscount.getFrame(),1,"Erro, usuario existente",true);
            return "invalido 1";
        }else if(acesso.getCodigo().isEmpty()){
            MyDialogg dialog=new MyDialogg(Myclasscount.getFrame(),1,"Erro, senha vazia",true);
            return "invalido 2";
        }else if(acesso.getPergunta().isBlank()){
            MyDialogg dialog=new MyDialogg(Myclasscount.getFrame(),1,"Erro, pergunta vazia",true);
            return "invalido 3";
        }else if(acesso.getPergunta().isBlank()){
            MyDialogg dialog=new MyDialogg(Myclasscount.getFrame(),1,"Erro, resposta vazia",true);
            return "invalido 4";
        }
        return "valido";
    }
}
