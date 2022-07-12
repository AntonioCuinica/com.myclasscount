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
    
    public static boolean temInscricao(int id){
        Connection con=Conexaoo.getConnection();
        String select="select * from disciplina d join inscricao i on d.id=i.disciplina_id where disciplina_id=?;";
         boolean teste=false;
        try{
            PreparedStatement stmt=con.prepareStatement(select);
            stmt.setInt(1,id);
            ResultSet rs=stmt.executeQuery();
            teste= rs.next();
            stmt.close();
            con.close();
        }catch(SQLException e){
            System.out.println("Erro getDisciplina nome: "+e.getMessage());
        }
        return teste;
    }
    
    
    public static Disciplina getDisciplina(String nome){
        Connection con=Conexaoo.getConnection();
        String select="SELECT * FROM disciplina WHERE nome=?";
        Disciplina disciplina=null;
        try{
            PreparedStatement stmt=con.prepareStatement(select);
            stmt.setString(1,nome);
            ResultSet rs=stmt.executeQuery();
            while(rs.next()){
                disciplina=new Disciplina();
                disciplina.setId(rs.getInt("id"));
                disciplina.setNome(rs.getString("nome"));
                disciplina.setCarga_horaria(rs.getString("carga_horaria"));
                disciplina.setSeccao(rs.getString("seccao"));
            }
            stmt.close();
            con.close();
        }catch(SQLException e){
            System.out.println("Erro getDisciplina nome: "+e.getMessage());
        }
        return disciplina;
    }
    
    public static ArrayList<Disciplina> getDisciplinas(){
        Connection con=Conexaoo.getConnection();
        String select="SELECT * FROM disciplina order by nome";
        ArrayList<Disciplina> disciplina=new ArrayList();
        try{
            PreparedStatement stmt=con.prepareStatement(select);
            ResultSet rs=stmt.executeQuery();
            while(rs.next()){
                Disciplina disc=new Disciplina();
                disc.setId(rs.getInt("id"));
                disc.setNome(rs.getString("nome"));
                disc.setCarga_horaria(rs.getString("carga_horaria"));
                disc.setSeccao(rs.getString("seccao"));
                disciplina.add(disc);
            }
            stmt.close();
            con.close();
        }catch(SQLException e){
            System.out.println("Erro getDisciplinas: "+e.getMessage());
        }
        return disciplina;
    
    }
    
    public static ArrayList<Disciplina> getDisciplinas(int prof_id){
        Connection con=Conexaoo.getConnection();
        String select="SELECT * FROM disciplina d join ensina e on d.id=e.disciplina_id where e.professor_id=?;";
        ArrayList<Disciplina> disciplina=new ArrayList();
        try{
            PreparedStatement stmt=con.prepareStatement(select);
            stmt.setInt(1,prof_id);
            ResultSet rs=stmt.executeQuery();
            while(rs.next()){
                Disciplina disc=new Disciplina();
                disc.setId(rs.getInt("id"));
                disc.setNome(rs.getString("nome"));
                disc.setCarga_horaria(rs.getString("carga_horaria"));
                disc.setSeccao(rs.getString("seccao"));
                disciplina.add(disc);
            }
            stmt.close();
            con.close();
        }catch(SQLException e){
            System.out.println("Erro getDisciplina id: "+e.getMessage());
        }
        return disciplina;
    
    }
    
    public static void updateDisciplina(Disciplina disc){
        Connection con=Conexaoo.getConnection();
        String select="UPDATE disciplina SET nome=?,carga_horaria=?,seccao=? WHERE id=?;";
        try{
            PreparedStatement stmt=con.prepareStatement(select);
            stmt.setString(1,disc.getNome());
            stmt.setString(2,disc.getCarga_horaria());
            stmt.setString(3,disc.getSeccao());
            stmt.setInt(4,disc.getId());
            stmt.execute();
            
            stmt.close();
            con.close();
        }catch(SQLException e){
            System.out.println("Erro updateDisciplina: "+e.getMessage());
        }
    }
    
    public static void setDisciplina(Disciplina disc){
        Connection con=Conexaoo.getConnection();
        String select="insert into disciplina(nome,carga_horaria,seccao) values (?,?,?)";
        try{
            PreparedStatement stmt=con.prepareStatement(select);
            stmt.setString(1,disc.getNome());
            stmt.setString(2,disc.getCarga_horaria());
            stmt.setString(3,disc.getSeccao());
            stmt.execute(); 
            
            stmt.close();
            con.close();
        }catch(SQLException e){
            System.out.println("Erro setDisciplina: "+e.getMessage());
        }
    }
    
    public static boolean delDisciplina(Disciplina disc){
        Connection con=Conexaoo.getConnection();
        String select="DELETE FROM disciplina WHERE id=?;";
        try{
            PreparedStatement stmt=con.prepareStatement(select);
            stmt.setInt(1,disc.getId());
            stmt.execute(); 
            
            stmt.close();
            con.close();
            
            return true;
        }catch(SQLException e){
            System.out.println("Erro delDisciplina nome: "+e.getMessage());
            return false;
        }
    }
}
