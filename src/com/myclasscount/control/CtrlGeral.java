/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myclasscount.control;

import com.myclasscount.model.Licensa;
import com.myclasscount.model.Professor;
import com.myclasscount.model.validar.ValidarLicenca;
import com.myclasscount.model.validar.ValidarProfessor;
import com.myclasscount.view.MainFramee;
import com.myclasscount.view.MyProceduress;
import com.myclasscount.view.Myclasscount;
import com.myclasscount.view.Ver_Turmas;
import com.myclasscount.view.Ver_Turmas2;
import java.util.ArrayList;
import java.util.HashMap;
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
        //Map novaTela=Map.of(nome,tela);
        Map novaTela=new HashMap<String,Object>();
        novaTela.put(nome,tela);
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
         /**inserindo mensalidades*/
        Mensalidade_ctrl.inserirMensalidades();
    }
    
    public static void inicializarTelas(){
        verificarLicensa();
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
    
    public static void verificarLicensa(){
        if(!ValidarLicenca.verificarLicensa()){
            MyProceduress.ActualizarLicensa(Myclasscount.getFrame());
        }
    }

    public static void gerarCodigoGravar(int periodo){
        String cod=ValidarLicenca.gerarCondigoDeLicensa(periodo);
        gravarLicenca(cod);
    }
    
    public static void gravarLicenca(String cod){
        Licensa li=new Licensa();
        li.guardarObjecto(new Object[]{cod,0});
    }
    
    public static Object retornarLicensa(){
        Licensa li=new Licensa();
        return li.retornarObjecto();
    }
    
    public static String[] decryptar(String codigo){
        return ValidarLicenca.decryptar(codigo);
    }
    
}
