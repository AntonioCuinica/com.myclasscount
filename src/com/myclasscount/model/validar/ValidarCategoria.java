/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myclasscount.model.validar;
import com.myclasscount.model.Categoria;
import com.myclasscount.model.dao.categoria_dao;


/**
 *
 * @author CUINIC4
 */

public class ValidarCategoria {
    public static String validarCategoria(Categoria cat){
        
        if(cat.getNome().isBlank()){
            return "Erro, nome vazio";
        }else if(categoria_dao.getCategoria(cat.getNome())!=null){
            return "Erro, a categoria ja existe";
        }
        
        if(cat.getPreco()==-1){
            return "Erro, preco Vazio !!";
        }else if(cat.getPreco()==-2){
            return "Erro, Preco invalido !!";
        }else if(cat.getPreco()<0){
            return "Erro, preco negativo !!";
        }
        return "valida";
        
    }
}
