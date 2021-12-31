/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myclasscount.model.dao;

import com.myclasscount.model.Disciplina;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author CUINIC4
 */

public class Disciplina_dao {
    
    public static Disciplina getDisciplina(String nome){
        Connection con=Conexaoo.getConnection();
        String select="SELECT * FROM myclasscount.disciplina WHERE nome=?";
        Disciplina disciplina=null;
        try{
            PreparedStatement stmt=con.prepareStatement(select);
            stmt.setString(1,nome);
            ResultSet rs=stmt.executeQuery();
            while(rs.next()){
                disciplina=new Disciplina();
                disciplina.setId(Integer.parseInt(rs.getString("id")));
                disciplina.setNome(rs.getString("nome"));
                disciplina.setCarga_horaria(rs.getString("carga_horaria"));
                disciplina.setSeccao(rs.getString("seccao"));
            }
            stmt.close();
            con.close();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return disciplina;
    }
    
    public static ArrayList<Disciplina> getDisciplinas(){
        Connection con=Conexaoo.getConnection();
        String select="SELECT * FROM myclasscount.disciplina";
        ArrayList<Disciplina> disciplina=new ArrayList();
        try{
            PreparedStatement stmt=con.prepareStatement(select);
            ResultSet rs=stmt.executeQuery();
            while(rs.next()){
                Disciplina disc=new Disciplina();
                disc.setId(Integer.parseInt(rs.getString("id")));
                disc.setNome(rs.getString("nome"));
                disc.setCarga_horaria(rs.getString("carga_horaria"));
                disc.setSeccao(rs.getString("seccao"));
                disciplina.add(disc);
            }
            stmt.close();
            con.close();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return disciplina;
    
    }
    
    public static void updateDisciplina(Disciplina disc){
        Connection con=Conexaoo.getConnection();
        String select="UPDATE myclasscount.disciplina SET nome=?,carga_horaria=?,seccao=? WHERE id=?;";
        try{
            PreparedStatement stmt=con.prepareStatement(select);
            stmt.setString(1,disc.getNome());
            stmt.setString(2,disc.getCarga_horaria());
            stmt.setString(3,disc.getSeccao());
            stmt.setString(4,String.valueOf(disc.getId()));
            stmt.execute();
            
            stmt.close();
            con.close();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
    
    public static void setDisciplina(Disciplina disc){
        Connection con=Conexaoo.getConnection();
        String select="CALL myclasscount.inserir_disciplina(?,?,?);";
        try{
            PreparedStatement stmt=con.prepareStatement(select);
            stmt.setString(1,disc.getNome());
            stmt.setString(2,disc.getCarga_horaria());
            stmt.setString(3,disc.getSeccao());
            stmt.execute(); 
            
            stmt.close();
            con.close();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
    
    public static boolean delDisciplina(Disciplina disc){
        Connection con=Conexaoo.getConnection();
        String select="DELETE FROM myclasscount.disciplina WHERE id=?;";
        try{
            PreparedStatement stmt=con.prepareStatement(select);
            stmt.setString(1,String.valueOf(disc.getId()));
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
