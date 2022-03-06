/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myclasscount.control;

import com.myclasscount.model.Professor;
import com.myclasscount.model.validar.ValidarProfessor;
import com.myclasscount.view.MainFramee;
import com.myclasscount.view.Myclasscount;
import com.myclasscount.view.Ver_Turmas;
import com.myclasscount.view.Ver_Turmas2;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author CUINIC4
 */

public class CtrlGeral {
    private static ArrayList<Map>telas=new ArrayList();
    private static Professor professor;
    private static String nivel_acesso;
    private static boolean iniciar=true;
    
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
    
     public static String getNivel_acesso() {
        return nivel_acesso;
    }

    public static void setNivel_acesso(String aNivel_acesso) {
        nivel_acesso = aNivel_acesso;
    }
    
    public static void carregando(){
         /** inserindo mensalidades*/
        Mensalidade_ctrl.inserirMensalidades();
    }
    
    public static void inicializarTelas(){
        if(iniciar){
            MainFramee mainF=new MainFramee();
            Myclasscount.getFrame().add(mainF,"mainFrame");
            CtrlGeral.setTela("mainFrame",mainF);
            
            Ver_Turmas verT=new Ver_Turmas();
            Myclasscount.getFrame().add(verT,"verTurmas");
            CtrlGeral.setTela("verTurmas",verT);

            Ver_Turmas2 verT2=new Ver_Turmas2();
            Myclasscount.getFrame().add(verT2,"verTurmas2");
            CtrlGeral.setTela("verTurmas2",verT2);
            iniciar=false;
        }
        
    }
    
    public static String validarNumero(String numero,String tipo){
        return ValidarProfessor.validarNumero(numero,tipo);
    }

    
}
