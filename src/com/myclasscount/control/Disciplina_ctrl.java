/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myclasscount.control;

import com.myclasscount.model.Disciplina;
import com.myclasscount.model.dao.Disciplina_dao;
import com.myclasscount.model.validar.ValidarDisciplina;
import com.myclasscount.view.MyDialogg;
import com.myclasscount.view.Myclasscount;
import java.util.ArrayList;

/**
 *
 * @author CUINIC4
 */
public class Disciplina_ctrl {
    
    public static Disciplina  getDisciplina(String nome){
        return Disciplina_dao.getDisciplina(nome);
    }
    
    public static ArrayList<Disciplina> getDisciplinas(){
        return Disciplina_dao.getDisciplinas();
    }
    
    public static ArrayList<Disciplina> getDisciplinas(int prof_id){
        return Disciplina_dao.getDisciplinas(prof_id);
    }
    
    public static boolean setDisciplina(Disciplina disc){
        String resultado=ValidarDisciplina.validarDisciplina(disc.getNome());
        if(resultado.equals("valida")){
            Disciplina_dao.setDisciplina(disc);
            MyDialogg dialog=new MyDialogg(Myclasscount.getFrame(),1,"Disciplina Cadastrada!",true);
            return true;
        }else{
            MyDialogg dialog=new MyDialogg(Myclasscount.getFrame(),1,resultado,true);
        }
        return false;
        
    }
    
    public static boolean updateDisciplina(Disciplina disc){
        String resultado=ValidarDisciplina.validarDisciplina(disc.getNome());
        if(resultado.equals("valida") || resultado.equals("Erro, a disciplina ja existe")){
            Disciplina_dao.updateDisciplina(disc);
            MyDialogg dialog=new MyDialogg(Myclasscount.getFrame(),1,"Disciplina actualizada!",true);
            return true;
        }else{
            MyDialogg dialog=new MyDialogg(Myclasscount.getFrame(),1,resultado,true);
        }
        return false;
    }
    
    public static boolean delDisciplina(Disciplina disc){
        if(Disciplina_dao.temInscricao(disc.getId())){
            return false;
        }
        return Disciplina_dao.delDisciplina(disc);
    }
        
}
