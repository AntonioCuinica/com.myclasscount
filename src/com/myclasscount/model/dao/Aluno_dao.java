/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myclasscount.model.dao;

import com.myclasscount.model.Aluno;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

/**
 *
 * @author CUINIC4
 */

public class Aluno_dao {
    
    public static Aluno getAluno(final String BI) throws ParseException{
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
                aluno.setApelido(rs.getString("apelido"));
                aluno.setBI(rs.getString("BI"));
                aluno.setNascimento(rs.getString("nascimento"));
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
    
    public static ArrayList<Aluno> getAlunos(){
        Connection con=Conexaoo.getConnection();
        String select="SELECT * FROM myclasscount.aluno;";
        ArrayList<Aluno> alunos=new ArrayList();
        try{
            PreparedStatement stmt=con.prepareStatement(select);
            ResultSet rs=stmt.executeQuery();
            while(rs.next()){
                Aluno aluno=new Aluno();
                aluno.setId(Integer.parseInt(rs.getString("id")));
                aluno.setNome(rs.getString("nome"));
                aluno.setApelido(rs.getString("apelido"));
                aluno.setBI(rs.getString("BI"));
                aluno.setNascimento(rs.getString("nascimento"));
                aluno.setSexo(rs.getString("sexo"));
                aluno.setNivel(rs.getString("nivel"));
                aluno.setMorada(rs.getString("morada"));
                aluno.setTelefone(rs.getString("telefone"));
                aluno.setEmail(rs.getString("email"));
                aluno.setCategoria_id(Integer.parseInt(rs.getString("categoria_id")));
                alunos.add(aluno);
            }
            stmt.close();
            con.close();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return alunos;
    }
    
    public static void updateAluno(Aluno aluno){
        Connection con=Conexaoo.getConnection();
        String select="UPDATE myclasscount.aluno SET nome=?,apelido=?,BI=?,nascimento=?,sexo=?,nivel=?,morada=?,telefone=?,email=? WHERE id=?;";
        try{
            PreparedStatement stmt=con.prepareStatement(select);
            stmt.setString(1,aluno.getNome());
            stmt.setString(2,aluno.getApelido());
            stmt.setString(3,String.valueOf(aluno.getBI()));
            stmt.setString(4,String.valueOf(aluno.getNascimento()));
            stmt.setString(5,aluno.getSexo());
            stmt.setString(6,aluno.getNivel());
            stmt.setString(7,aluno.getMorada());
            stmt.setString(8,aluno.getTelefone());
            stmt.setString(9,aluno.getEmail());
            stmt.execute();
            
            stmt.close();
            con.close();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
    
    public static void setAluno(Aluno aluno){
        Connection con=Conexaoo.getConnection();
        String select="call myclasscount.inserir_aluno(?,?,?,?,?,?,?,?,?,?);";
        try{
            PreparedStatement stmt=con.prepareStatement(select);
            stmt.setString(1,aluno.getNome());
            stmt.setString(2,aluno.getApelido());
            stmt.setString(3,String.valueOf(aluno.getBI()));
            stmt.setString(4,String.valueOf(aluno.getNascimento()));
            stmt.setString(5,aluno.getSexo());
            stmt.setString(6,aluno.getNivel());
            stmt.setString(7,aluno.getMorada());
            stmt.setString(8,aluno.getTelefone());
            stmt.setString(9,aluno.getEmail());
            stmt.setString(10,String.valueOf(aluno.getCategoria_id()));
            stmt.execute(); 
            
            stmt.close();
            con.close();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
    
    public static boolean delCategoria(Aluno aluno){
        Connection con=Conexaoo.getConnection();
        String select="DELETE FROM `myclasscount`.`aluno` WHERE id=?;";
        try{
            PreparedStatement stmt=con.prepareStatement(select);
            stmt.setString(1,String.valueOf(aluno.getId()));
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
