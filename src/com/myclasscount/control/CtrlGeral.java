/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myclasscount.control;

import com.myclasscount.model.Professor;
import com.myclasscount.model.validar.ValidarProfessor;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author CUINIC4
 */

public class CtrlGeral {
    private static ArrayList<Map>telas=new ArrayList();
    private static Professor professor;
    
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
    
    public static void setProfessor_logado(Professor professor){
        CtrlGeral.professor=professor;
    }
    
    public static Professor getProfessor_logado(){
        return professor;
    }
    
    public static void carregando(){
         /** inserindo mensalidades*/
        Mensalidade_ctrl.inserirMensalidades();
    }
    
    public static String validarNumero(String numero,String tipo){
        return ValidarProfessor.validarNumero(numero,tipo);
    }
    
    
}
