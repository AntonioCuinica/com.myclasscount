/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myclasscount.control;

import java.awt.Component;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author CUINIC4
 */

public class CtrlGeral {
    private static ArrayList<Map>telas=new ArrayList();
    
    public static void setTela(String nome,Object tela){
        Map novaTela=Map.of(nome,tela);
        telas.add(novaTela);
    }
    
    public static Object getTela(String nome){
        for(Map tela:telas){
            if(tela.get(nome)!=null){
               return tela.get(nome);
            }
        }
        return null;
    }

}
