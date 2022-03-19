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
public class Categoriaa_dao {
    
    public static Categoria getCategoria(String nome){
        Connection con=Conexaoo.getConnection();
        String select="SELECT * FROM categoria_aluno WHERE nome=?";
        Categoria categoria=null;
        try{
            PreparedStatement stmt=con.prepareStatement(select);
            stmt.setString(1,nome);
            ResultSet rs=stmt.executeQuery();
            while(rs.next()){
                categoria=new Categoria();
                categoria.setId(rs.getInt("id"));
                categoria.setNome(rs.getString("nome"));
                categoria.setTipoEnsino(rs.getString("tipo_ensino"));
                categoria.setClasse(rs.getString("class"));
                categoria.setPreco(rs.getDouble("preco"));
                categoria.setDescricao(rs.getString("descricao"));
            }
            stmt.close();
            con.close();
        }catch(SQLException e){
            System.out.println("Erro getCategooria nome: "+e.getMessage());
        }
        return categoria;
    }
    
    public static Categoria getCategoria(int id){
        Connection con=Conexaoo.getConnection();
        String select="SELECT * FROM categoria_aluno WHERE id=?";
        Categoria categoria=null;
        try{
            PreparedStatement stmt=con.prepareStatement(select);
            stmt.setString(1,String.valueOf(id));
            ResultSet rs=stmt.executeQuery();
            while(rs.next()){
                categoria=new Categoria();
                categoria.setId(rs.getInt("id"));
                categoria.setNome(rs.getString("nome"));
                categoria.setTipoEnsino(rs.getString("tipo_ensino"));
                categoria.setClasse(rs.getString("class"));
                categoria.setPreco(rs.getDouble("preco"));
                categoria.setDescricao(rs.getString("descricao"));
            }
            stmt.close();
            con.close();
        }catch(SQLException e){
            System.out.println("Erro getCategooria id: "+e.getMessage());
        }
        return categoria;
    }
    
    public static ArrayList<Categoria> getCategorias(){
        Connection con=Conexaoo.getConnection();
        String select="SELECT * FROM categoria_aluno order by nome";
        ArrayList<Categoria> categoria=new ArrayList();
        try{
            PreparedStatement stmt=con.prepareStatement(select);
            ResultSet rs=stmt.executeQuery();
            while(rs.next()){
                Categoria cat=new Categoria();
                cat.setId(rs.getInt("id"));
                cat.setNome(rs.getString("nome"));
                cat.setTipoEnsino(rs.getString("tipo_ensino"));
                cat.setClasse(rs.getString("class"));
                cat.setPreco(rs.getDouble("preco"));
                cat.setDescricao(rs.getString("descricao"));
                categoria.add(cat);
            }
            stmt.close();
            con.close();
        }catch(SQLException e){
            System.out.println("Erro getCategoorias "+e.getMessage());
        }
        return categoria;
    
    }
    
    public static void updateCategoria(Categoria cat){
        Connection con=Conexaoo.getConnection();
        String select="UPDATE categoria_aluno SET nome=?,tipo_ensino=?,class=?,preco=?,descricao=? WHERE id=?;";
        try{
            PreparedStatement stmt=con.prepareStatement(select);
            stmt.setString(1,cat.getNome());
            stmt.setString(2,cat.getTipoEnsino());
            stmt.setString(3,cat.getClasse());
            stmt.setDouble(4,cat.getPreco());
            stmt.setString(5,cat.getDescricao());
            stmt.setInt(6,cat.getId());
            stmt.execute();
            
            stmt.close();
            con.close();
        }catch(SQLException e){
            System.out.println("Erro updateCategooria: "+e.getMessage());
        }
    }
    
    public static void setCategoria(Categoria cat){
        Connection con=Conexaoo.getConnection();
        String select="insert into categoria_aluno(nome,tipo_ensino,class,preco,descricao)  values(?,?,?,?,?)";
        try{
            PreparedStatement stmt=con.prepareStatement(select);
            stmt.setString(1,cat.getNome());
            stmt.setString(2,cat.getTipoEnsino());
            stmt.setString(3,cat.getClasse());
            stmt.setDouble(4,cat.getPreco());
            stmt.setString(5,cat.getDescricao());
            stmt.execute(); 
            
            stmt.close();
            con.close();
        }catch(SQLException e){
            System.out.println("Erro setCategooria: "+e.getMessage());
        }
    }
    
    public static boolean delCategoria(Categoria cat){
        Connection con=Conexaoo.getConnection();
        String select="DELETE FROM categoria_aluno WHERE id=?;";
        try{
            PreparedStatement stmt=con.prepareStatement(select);
            stmt.setInt(1,cat.getId());
            stmt.execute(); 
            
            stmt.close();
            con.close();
            
            return true;
        }catch(SQLException e){
            System.out.println("Erro detCategooria: "+e.getMessage());
            return false;
        }
    }
}
