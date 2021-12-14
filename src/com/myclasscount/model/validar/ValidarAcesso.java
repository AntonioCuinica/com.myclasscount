/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myclasscount.model.validar;

import com.myclasscount.model.Acesso;
import com.myclasscount.model.dao.Acesso_dao;

/**
 *
 * @author CUINIC4
 */
public class ValidarAcesso {
    
    public static boolean temAcesso(String user, String psw){
        Acesso acesso=Acesso_dao.getAcesso(user,psw);
        return !(acesso==null);
    }
}
