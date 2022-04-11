/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myclasscount.model.validar;

import com.myclasscount.model.dao.Disciplina_dao;

/**
 *
 * @author CUINIC4
 */
public class ValidarDisciplina {
    public static String validarDisciplina(String nome){
        
        if(nome.isEmpty()){
            return "Erro, nome vazio";
        }else if(Disciplina_dao.getDisciplina(nome)!=null){
            return "Erro, a disciplina ja existe";
        }
        
        return "valida";
        
    }
}
