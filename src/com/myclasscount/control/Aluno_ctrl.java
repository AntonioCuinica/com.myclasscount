/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myclasscount.control;

import com.myclasscount.model.Aluno;
import com.myclasscount.model.dao.Aluno_dao;
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
    
    public static ArrayList<Aluno> getAlunos(){
        return Aluno_dao.getAlunos();
    }
    
    public static String getErro(){
        return ValidarAluno.getErro();
    }
}
