/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myclasscount.control;

import com.myclasscount.model.validar.ValidarAcesso;

/**
 *
 * @author CUINIC4
 */
public class Login_ctrl {
    
    public static boolean login_crtl(String user, String psw){
        return ValidarAcesso.temAcesso(user,psw);
    }
}
