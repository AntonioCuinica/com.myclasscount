/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myclasscount.control;

import com.myclasscount.model.Professor;
import com.myclasscount.model.dao.Professor_dao;
import com.myclasscount.model.validar.ValidarAluno;
import com.myclasscount.model.validar.ValidarProfessor;
import java.util.ArrayList;

/**
 *
 * @author CUINIC4
 */

public class Professor_ctrl {
    
    public static boolean setProfessor(Professor professor){
        if(ValidarProfessor.validarProfessor(professor)){
            Professor_dao.setProfessor(professor);
            return true;
        }
        return false;
    }
    
    public static boolean updateAluno(Professor professor){
        if(ValidarProfessor.validarProfessor(professor)){
            Professor_dao.updateProfessor(professor);
            return true;
        }
        return false;
    }
    
    public static ArrayList<Professor> getProfessores(){
        return Professor_dao.getProfessores();
    }
    
    public static Professor getProfessor(String BI){
        return Professor_dao.getProfessor(BI);
    }
    
    public static Professor getProfessorNUIT(String NUIT){
        return Professor_dao.getProfessorNUIT(NUIT);
    }
    
    public static boolean delProfessor(int prof_id){
        return Professor_dao.delProfessor(prof_id);
    }
    
    public static void inscricaoProfessorDisc(Professor professor, int disc_id){
        Professor_dao.inscricaoProfessorDisc(professor, disc_id);
    }
    
    public static String getErro(){
        return ValidarProfessor.getErro();
    }
    
}
