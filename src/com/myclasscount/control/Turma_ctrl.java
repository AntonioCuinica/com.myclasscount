/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myclasscount.control;

import com.myclasscount.model.Aluno;
import com.myclasscount.model.Professor;
import com.myclasscount.model.Turma;
import com.myclasscount.model.dao.Turma_dao;
import java.util.ArrayList;

/**
 *
 * @author CUINIC4
 */
public class Turma_ctrl {
    
    public static void setTurma(Turma turma){
        Turma_dao.setTurma(turma);
    }
    
    public static Turma getTurma(int turma_id){
        return Turma_dao.getTurma(turma_id);
    }
    
    public static Turma getTurma(String nome){
        Turma turma = Turma_dao.getTurma(nome);
        return turma;
    }
    
    public static void setAluno_turma(String nome, int aluno_id){
        Turma_dao.setAluno_turma(nome, aluno_id);
    }
    
    public static void setProfessor_turma(int turma_id, int prof_id){
        Turma_dao.setProfessor_turma(turma_id, prof_id);
    }
    
    public static ArrayList<Turma> getTurmas(){
        return Turma_dao.getTurmas();
    }
}
