/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myclasscount.model.validar;

import com.myclasscount.control.Aluno_ctrl;
import com.myclasscount.control.Professor_ctrl;
import com.myclasscount.model.Professor;
import com.myclasscount.view.MyDialogg;
import com.myclasscount.view.Myclasscount;

/**
 *
 * @author CUINIC4
 */
public class ValidarProfessor {
    
    private static String erro;
    
    public static String getErro(){
        return erro;
    }
    
    public static boolean validarProfessor(Professor professor){
        
        if(!erro(validarTexto(professor.getNome(),"nome")))return false;
        
        if(!erro(validarTexto(professor.getApelido(),"apelido")))return false;
        
        if(!erro(validarBI(professor.getBI(),professor.getId())))return false;
        
        if(!erro(validarNumero(String.valueOf(professor.getSalario()),"salario")))return false;              
        
        if(!erro(validarNUIT(professor.getNUIT(),professor.getId())))return false;
        
        if(!erro(validarTelefone(professor.getTelefone())))return false;
        
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
    
    public static String validarBI(String BI, int prof_id){
        if(BI.isBlank()){
            return "Erro, BI vazio";
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
                Professor prof = Professor_ctrl.getProfessor(BI);
                if(prof!=null && prof.getId()!=prof_id){
                    return "Erro, BI existente";
                }
            }
        }
        return "valido";
    }
    
    public static String validarNumero(String numero,String tipo){
        if(numero.isBlank()){
            return "Erro, "+tipo+" vazio";
        }else{
            for(int i=0;i<numero.length();i++){
                if(numero.substring(i,(i+1)).matches("[a-zA-Z]*")){
                    return "Erro, "+tipo+" contem letras";
                }else if(Double.parseDouble(numero)<0){
                    return "Erro, "+tipo+" invalido";
                }
            }
        }
        return "valido";
    }
    
    public static String validarNUIT(String NUTI, int prof_id){
        if(NUTI.isBlank()){
            return "Erro, NUIT vazio";
        }else if(NUTI.length()!=13){
            return "Erro, NUIT invalido";
        }else{
            boolean valido=false;
            try{
                Long texto=Long.parseLong(NUTI.substring(0,13));
                valido=true;
            }catch(RuntimeException n){
                if(!valido)return "Erro, NUIT invalido";
                Professor prof = Professor_ctrl.getProfessorNUIT(NUTI);
                System.out.println("prof: "+prof.getId());
                System.out.println("professor:"+prof_id);
                if(prof!=null && prof.getId()!=prof_id){
                    return "Erro, NUIT existente";
                }
            }
        }
        return "valido";
    }
    
    
    
    public static String validarTelefone(String telefone){
        if(telefone.isBlank()){
            return "Erro, telefone vazio";
        }else{
            if(telefone.matches("[a-zA-Z]*")){
                return "Erro, telefone invalido";
            }else if(!telefone.matches("[0-9]*") && !telefone.substring(0,1).equals("+")){
                return "Erro, telefone invalido";
            }
        }
        return "valido";
    }
    
}
