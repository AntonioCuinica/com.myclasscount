/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myclasscount.model.dao;

import com.myclasscount.model.Aluno;
import com.myclasscount.model.Categoria;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author CUINIC4
 */
public class Aluno_dao {
    public static Aluno getAluno(final String BI){
        Connection con=Conexaoo.getConnection();
        String select="SELECT * FROM myclasscount.aluno WHERE BI=?";
        Aluno aluno=null;
        try{
            PreparedStatement stmt=con.prepareStatement(select);
            stmt.setString(1,BI);
            ResultSet rs=stmt.executeQuery();
            while(rs.next()){
                aluno=new Aluno();
                aluno.setId(Integer.parseInt(rs.getString("id")));
                aluno.setNome(rs.getString("nome"));
                aluno.setNome(rs.getString("apelido"));
                aluno.setBI((Long.parseLong(rs.getString("BI"))));
                Calendar cal=Calendar.getInstance();
                //cal.setTime(rs.getString("nascimento"));
                aluno.setNascimento(cal.getTime());
                aluno.setSexo(rs.getString("sexo"));
                aluno.setNivel(rs.getString("nivel"));
                aluno.setMorada(rs.getString("morada"));
                aluno.setTelefone(rs.getString("telefone"));
                aluno.setEmail(rs.getString("email"));
                aluno.setCategoria_id(Integer.parseInt(rs.getString("categoria_id")));
            }
            stmt.close();
            con.close();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return aluno;
    }
    
    public static ArrayList<Categoria> getCategorias(){
        Connection con=Conexaoo.getConnection();
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
    
    public static void updateCategoria(Categoria cat){
        Connection con=Conexaoo.getConnection();
        String select="UPDATE myclasscount.categoria_aluno SET nome=?,tipo_ensino=?,class=?,preco=?,descricao=? WHERE id=?;";
        try{
            PreparedStatement stmt=con.prepareStatement(select);
            stmt.setString(1,cat.getNome());
            stmt.setString(2,cat.getTipoEnsino());
            stmt.setString(3,cat.getClasse());
            stmt.setString(4,String.valueOf(cat.getPreco()));
            stmt.setString(5,cat.getDescricao());
            stmt.setString(6,String.valueOf(cat.getId()));
            stmt.execute();
            
            stmt.close();
            con.close();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
    
    public static void setCategoria(Categoria cat){
        Connection con=Conexaoo.getConnection();
        String select="CALL myclasscount.inserir_categoria(?,?,?,?,?);";
        try{
            PreparedStatement stmt=con.prepareStatement(select);
            stmt.setString(1,cat.getNome());
            stmt.setString(2,cat.getTipoEnsino());
            stmt.setString(3,cat.getClasse());
            stmt.setString(4,String.valueOf(cat.getPreco()));
            stmt.setString(5,cat.getDescricao());
            stmt.execute(); 
            
            stmt.close();
            con.close();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
    
    public static boolean delCategoria(Categoria cat){
        Connection con=Conexaoo.getConnection();
        String select="DELETE FROM `myclasscount`.`categoria_aluno` WHERE id=?;";
        try{
            PreparedStatement stmt=con.prepareStatement(select);
            stmt.setString(1,String.valueOf(cat.getId()));
            stmt.execute(); 
            
            stmt.close();
            con.close();
            
            return true;
        }catch(SQLException e){
            System.out.println(e.getMessage());
            return false;
        }
    }
}
