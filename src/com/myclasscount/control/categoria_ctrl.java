/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myclasscount.control;

import com.myclasscount.model.Categoria;
import com.myclasscount.model.dao.Categoriaa_dao;
import com.myclasscount.model.validar.ValidarCategoria;
import com.myclasscount.view.MyDialogg;
import com.myclasscount.view.Myclasscount;
import java.util.ArrayList;

/**
 *
 * @author CUINIC4
 */
public class categoria_ctrl {
    private static String erro="";
    
    public static String getErro(){
        return erro;
    }
    
    public static boolean inserirCategoria(Categoria cat){
        String resultado=ValidarCategoria.validarCategoria(cat);
        if(resultado.equals("valida")){
            Categoriaa_dao.setCategoria(cat);
            MyDialogg dialog=new MyDialogg(Myclasscount.getFrame(),1,"Categoria Cadastrada!",true);
            return true;
        }else{
            erro=resultado;
            MyDialogg dialog=new MyDialogg(Myclasscount.getFrame(),1,resultado,true);
        }
        return false;
    }
    
    public static Categoria getCategoria(String nome){
        return Categoriaa_dao.getCategoria(nome);
    }
    
    public static ArrayList<Categoria> getCategorias(){
        return Categoriaa_dao.getCategorias();
    }
    
    public static boolean deletarCategoria(Categoria cat){
        return Categoriaa_dao.delCategoria(cat);
    }
    
    public static boolean updateCategoria(Categoria cat){
        String resultado=ValidarCategoria.validarCategoria(cat);
        if(resultado.equals("valida") || resultado.equals("Erro, a categoria ja existe")){
            Categoriaa_dao.updateCategoria(cat);
            MyDialogg dialog=new MyDialogg(Myclasscount.getFrame(),1,"Categoria Actualizada!",true);
            return true;
        }else{
            erro=resultado;
            MyDialogg dialog=new MyDialogg(Myclasscount.getFrame(),1,resultado,true);
        }
        return false;
    }
}
