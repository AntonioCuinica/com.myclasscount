/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myclasscount.control;

import com.myclasscount.model.Aluno;
import com.myclasscount.model.Disciplina;
import com.myclasscount.model.dao.Aluno_dao;
import com.myclasscount.model.dao.Disciplina_dao;
import com.myclasscount.model.validar.ValidarAluno;
import java.util.ArrayList;

/**
 *
 * @author CUINIC4
 */

public class Aluno_ctrl {
    
    public static boolean setAluno(Aluno aluno){
        if(ValidarAluno.validarAluno(aluno)){
            Aluno_dao.setAluno(aluno);
            return true;
        }
        return false;
    }
    
    public static boolean updateAluno(Aluno aluno){
        if(ValidarAluno.validarAluno(aluno)){
            Aluno_dao.updateAluno(aluno);
            return true;
        }
        return false;
    }
    
    public static ArrayList<Aluno> getAlunos(){
        return Aluno_dao.getAlunos();
    }
    
    public static Aluno getAluno(String BI){
        return Aluno_dao.getAluno(BI);
    }
    
    public static boolean delAluno(int aluno_id){
        return Aluno_dao.delAluno(aluno_id);
    }
    
    public static String[] pagamento(int aluno_id){
        return Aluno_dao.pagamento(aluno_id);
    }
    
    public static ArrayList inscricao(int aluno_id){
        return Aluno_dao.inscricoes(aluno_id);
    }
    
    public static void setInscricao(int aluno_id, String disc){
        Disciplina disciplina=Disciplina_dao.getDisciplina(disc);
        Aluno_dao.setInscricao(aluno_id,disciplina.getId());
    }
    
    public static boolean delAlunoInscricao(int aluno_id,String disc){
        int disc_id=0;
        Disciplina disciplina=Disciplina_dao.getDisciplina(disc);
        return Aluno_dao.delAlunoInscricao(aluno_id,disciplina.getId());
    }
    
    public static String getErro(){
        return ValidarAluno.getErro();
    }
    
}
