/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myclasscount.control;

import com.myclasscount.model.Categoria;
import com.myclasscount.model.dao.categoria_dao;
import com.myclasscount.model.validar.ValidarCategoria;
import com.myclasscount.view.MyDialogg;
import com.myclasscount.view.Myclasscount;
import java.util.ArrayList;

/**
 *
 * @author CUINIC4
 */
public class categoria_ctrl {
    
    public static boolean inserirCategoria(Categoria cat){
        String resultado=ValidarCategoria.validarCategoria(cat);
        if(resultado.equals("valida")){
            categoria_dao.setCategoria(cat);
            MyDialogg dialog=new MyDialogg(Myclasscount.getFrame(),1,"Categoria Cadastrada!",true);
            return true;
        }else{
            MyDialogg dialog=new MyDialogg(Myclasscount.getFrame(),1,resultado,true);
        }
        return false;
    }
    
    public static Categoria getCategoria(String nome){
        return categoria_dao.getCategoria(nome);
    }
    
    public static ArrayList<Categoria> getCategorias(){
        return categoria_dao.getCategorias();
    }
    
}
