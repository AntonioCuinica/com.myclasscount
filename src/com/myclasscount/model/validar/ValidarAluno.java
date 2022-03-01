/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myclasscount.model.validar;

import com.myclasscount.control.Aluno_ctrl;
import com.myclasscount.model.Aluno;
import com.myclasscount.view.MyDialogg;
import com.myclasscount.view.Myclasscount;
import java.util.Calendar;

/**
 *
 * @author CUINIC4
 */
public class ValidarAluno {
    
    private static String erro;
    
    public static String getErro(){
        return erro;
    }
    
    public static boolean validarAluno(Aluno aluno){
        
        if(!erro(validarTexto(aluno.getNome(),"nome")))return false;
        
        if(!erro(validarTexto(aluno.getApelido(),"apelido")))return false;
        
        if(!erro(validarBI(aluno.getBI(),aluno.getId())))return false;
        
        if(!erro(validarTelefone(aluno.getTelefone())))return false;
        
        return true;
    }
    
    public static boolean erro(String resultado){
        
        erro=resultado;
        
        if(!resultado.equals("valido")){
            MyDialogg dialog=new MyDialogg(Myclasscount.getFrame(),1, resultado, true);
            return false;
        }
        return true;
    }
    
    
    public static String validarTexto(String texto,String tipo){
        if(texto.isBlank()){
            return "Erro , "+tipo+" vazio";
        }else{
            for(int i=0;i<texto.length();i++){
                if(texto.substring(i,(i+1)).matches("[0-9]*")){
                    return "Erro, O "+tipo+" contem numero";
                }
            }
        } 
        return "valido";
    }
    
    public static String validarBI(String BI, int aluno_id){
        if(BI.isBlank()){
            return "BI, será aleatorio, continuar ?";
        }else if(BI.length()!=13){
            return "Erro, BI invalido";
        }else{
            boolean valido=false;
            try{
                Long texto=Long.parseLong(BI.substring(0,12));
                valido=true;
                System.out.println("12 valido: "+texto);
                int numero=Integer.parseInt(BI.substring(12,13));
                System.out.println("13 invalido: "+numero);
                return "Erro, BI invalido";
            }catch(RuntimeException n){
                if(!valido)return "Erro, BI invalido";
                Aluno a = Aluno_ctrl.getAluno(BI);
                if(a!=null && a.getId()!=aluno_id){
                    return "Erro, BI existente";
                }
            }
        }
        return "valido";
    }
    
    public static String validarTelefone(String telefone){
        if(!telefone.isBlank()){
            if(telefone.matches("[a-zA-Z]*")){
                return "Erro, telefone invalido";
            }else if(!telefone.matches("[0-9]*") && !telefone.substring(0,1).equals("+")){
                return "Erro, telefone invalido";
            }
        }
        
        return "valido";
    }
    
}
