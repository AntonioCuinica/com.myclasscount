/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myclasscount.model.dao;

import com.myclasscount.model.Aluno;
import com.myclasscount.model.Professor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author CUINIC4
 */

public class Professor_dao {
    
    public static Professor getProfessor(final String BI){
        Connection con=Conexaoo.getConnection();
        String select="SELECT * FROM myclasscount.professor WHERE BI=?";
        Professor professor=null;
        try{
            PreparedStatement stmt=con.prepareStatement(select);
            stmt.setString(1,BI);
            ResultSet rs=stmt.executeQuery();
            while(rs.next()){
                professor=new Professor();
                professor.setId(Integer.parseInt(rs.getString("id")));
                professor.setNome(rs.getString("nome"));
                professor.setApelido(rs.getString("apelido"));
                professor.setBI(rs.getString("BI"));
                professor.setNascimento(rs.getString("nascimento"));
                professor.setSexo(rs.getString("sexo"));
                professor.setNivel(rs.getString("nivel"));
                professor.setMorada(rs.getString("morada"));
                professor.setTelefone(rs.getString("telefone"));
                professor.setEmail(rs.getString("email"));
                professor.setNUIT(rs.getString("NUIT"));
                professor.setSalario(Double.parseDouble(rs.getString("salario")));
            }
            stmt.close();
            con.close();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return professor;
    }
    
    public static Professor getProfessorNUIT(final String NUIT){
        Connection con=Conexaoo.getConnection();
        String select="SELECT * FROM myclasscount.professor WHERE NUIT=?";
        Professor professor=null;
        try{
            PreparedStatement stmt=con.prepareStatement(select);
            stmt.setString(1,NUIT);
            ResultSet rs=stmt.executeQuery();
            while(rs.next()){
                professor=new Professor();
                professor.setId(Integer.parseInt(rs.getString("id")));
                professor.setNome(rs.getString("nome"));
                professor.setApelido(rs.getString("apelido"));
                professor.setBI(rs.getString("BI"));
                professor.setNascimento(rs.getString("nascimento"));
                professor.setSexo(rs.getString("sexo"));
                professor.setNivel(rs.getString("nivel"));
                professor.setMorada(rs.getString("morada"));
                professor.setTelefone(rs.getString("telefone"));
                professor.setEmail(rs.getString("email"));
                professor.setNUIT(rs.getString("NUIT"));
                professor.setSalario(Double.parseDouble(rs.getString("salario")));
            }
            stmt.close();
            con.close();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return professor;
    }
    
    public static ArrayList<Professor> getProfessores(){
        Connection con=Conexaoo.getConnection();
        String select="SELECT * FROM myclasscount.professor;";
        ArrayList<Professor> professores=new ArrayList();
        try{
            PreparedStatement stmt=con.prepareStatement(select);
            ResultSet rs=stmt.executeQuery();
            while(rs.next()){
                Professor professor=new Professor();
                professor.setId(Integer.parseInt(rs.getString("id")));
                professor.setNome(rs.getString("nome"));
                professor.setApelido(rs.getString("apelido"));
                professor.setBI(rs.getString("BI"));
                professor.setNascimento(rs.getString("nascimento"));
                professor.setSexo(rs.getString("sexo"));
                professor.setNivel(rs.getString("nivel"));
                professor.setMorada(rs.getString("morada"));
                professor.setTelefone(rs.getString("telefone"));
                professor.setEmail(rs.getString("email"));
                professor.setNUIT(rs.getString("NUIT"));
                professor.setSalario(Double.parseDouble(rs.getString("salario")));
                professores.add(professor);
            }
            stmt.close();
            con.close();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return professores;
    }
    
    public static void updateProfessor(Professor professor){
        Connection con=Conexaoo.getConnection();
        String select="UPDATE myclasscount.professor SET nome=?,apelido=?,BI=?,nascimento=?,sexo=?,nivel=?,morada=?,telefone=?,email=?,salario=?,NUIT=? WHERE id=?;";
        try{
            PreparedStatement stmt=con.prepareStatement(select);
            stmt.setString(1,professor.getNome());
            stmt.setString(2,professor.getApelido());
            stmt.setString(3,String.valueOf(professor.getBI()));
            stmt.setString(4,String.valueOf(professor.getNascimento()));
            stmt.setString(5,professor.getSexo());
            stmt.setString(6,professor.getNivel());
            stmt.setString(7,professor.getMorada());
            stmt.setString(8,professor.getTelefone());
            stmt.setString(9,professor.getEmail());
            stmt.setString(10,String.valueOf(professor.getSalario()));
            stmt.setString(11,String.valueOf(professor.getNUIT()));
            stmt.setString(12,String.valueOf(professor.getId()));
            stmt.execute();
            
            stmt.close();
            con.close();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
    
    public static void setProfessor(Professor professor){
        Connection con=Conexaoo.getConnection();
        String select="call myclasscount.inserir_professor(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        try{
            PreparedStatement stmt=con.prepareStatement(select);
            stmt.setString(1,professor.getNome());
            stmt.setString(2,professor.getApelido());
            stmt.setString(3,String.valueOf(professor.getBI()));
            stmt.setString(4,String.valueOf(professor.getNascimento()));
            stmt.setString(5,professor.getSexo());
            stmt.setString(6,professor.getNivel());
            stmt.setString(7,professor.getMorada());
            stmt.setString(8,professor.getTelefone());
            stmt.setString(9,professor.getEmail());
            stmt.setString(10,String.valueOf(professor.getNUIT()));
            stmt.setString(11,String.valueOf(professor.getSalario()));
            stmt.execute(); 
            
            stmt.close();
            con.close();
        }catch(SQLException e){
            System.out.println("setProfessor: "+e.getMessage());
        }
    }
    
    public static boolean delProfessor(int professor_id){
        Connection con=Conexaoo.getConnection();
        String select="DELETE FROM `myclasscount`.`professor` WHERE id=?;";
        try{
            PreparedStatement stmt=con.prepareStatement(select);
            stmt.setString(1,String.valueOf(professor_id));
            stmt.execute(); 
            
            stmt.close();
            con.close();
            
            return true;
        }catch(SQLException e){
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    public static void inscricaoProfessorDisc(Professor professor,int disc_id){
        Connection con=Conexaoo.getConnection();
        String select="call myclasscount.inscrever_professor_disciplina(?, ?);";
        try{
            PreparedStatement stmt=con.prepareStatement(select);
            stmt.setString(1,String.valueOf(professor.getId()));
            stmt.setString(2,String.valueOf(disc_id));
            stmt.execute(); 
            
            stmt.close();
            con.close();
            
        }catch(SQLException e){
            System.out.println("inscrever professor: "+e.getMessage());
        }
    }
    
    public static boolean delInscricaoProfDisc(int prof_id, int disc_id){
        Connection con=Conexaoo.getConnection();
        String select="DELETE FROM ensina WHERE professor_id=? and disciplina_id=?;";
        try{
            PreparedStatement stmt=con.prepareStatement(select);
            stmt.setString(1,String.valueOf(prof_id));
            stmt.setString(2,String.valueOf(disc_id));
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
