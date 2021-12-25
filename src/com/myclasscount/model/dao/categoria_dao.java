/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myclasscount.model.dao;

import com.myclasscount.model.Categoria;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author CUINIC4
 */
public class categoria_dao {
    
    public static Categoria getCategoria(String nome){
        Connection con=conexao.getConnection();
        String select="SELECT * FROM myclasscount.categoria_aluno WHERE nome=?";
        Categoria categoria=null;
        try{
            PreparedStatement stmt=con.prepareStatement(select);
            stmt.setString(1,nome);
            ResultSet rs=stmt.executeQuery();
            while(rs.next()){
                categoria=new Categoria();
                categoria.setId(Integer.parseInt(rs.getString("id")));
                categoria.setNome(rs.getString("nome"));
                categoria.setTipoEnsino(rs.getString("tipo_ensino"));
                categoria.setClasse(rs.getString("class"));
                categoria.setPreco(Double.parseDouble(rs.getString("preco")));
                categoria.setDescricao(rs.getString("descricao"));
            }
            stmt.close();
            con.close();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return categoria;
    }
    
    public static ArrayList<Categoria> getCategorias(){
        Connection con=conexao.getConnection();
        String select="SELECT * FROM myclasscount.categoria_aluno";
        ArrayList<Categoria> categoria=new ArrayList();
        try{
            PreparedStatement stmt=con.prepareStatement(select);
            ResultSet rs=stmt.executeQuery();
            while(rs.next()){
                Categoria cat=new Categoria();
                cat.setId(Integer.parseInt(rs.getString("id")));
                cat.setNome(rs.getString("nome"));
                cat.setTipoEnsino(rs.getString("tipo_ensino"));
                cat.setClasse(rs.getString("class"));
                cat.setPreco(Double.parseDouble(rs.getString("preco")));
                cat.setDescricao(rs.getString("descricao"));
                categoria.add(cat);
            }
            stmt.close();
            con.close();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return categoria;
    
    }
    
    public static void setCategoria(Categoria cat){
        Connection con=conexao.getConnection();
        String select="CALL `myclasscount`.`inserir_categoria`(?,?,?,?,?);";
        ArrayList<Categoria> categoria=new ArrayList();
        try{
            PreparedStatement stmt=con.prepareStatement(select);
            stmt.setString(1,cat.getNome());
            stmt.setString(2,cat.getTipoEnsino());
            stmt.setString(3,cat.getClasse());
            stmt.setString(4,""+cat.getPreco());
            stmt.setString(5,cat.getDescricao());
            stmt.execute();
            
            stmt.close();
            con.close();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
