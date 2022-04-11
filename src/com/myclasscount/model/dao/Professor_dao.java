/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myclasscount.model.dao;

import com.myclasscount.model.Professor;
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

public class Professor_dao {
    
    public static Professor getProfessor(String BI){
        Connection con=Conexaoo.getConnection();
        String select="SELECT * FROM professor WHERE BI=?";
        Professor professor=null;
        try{
            PreparedStatement stmt=con.prepareStatement(select);
            stmt.setString(1,BI);
            ResultSet rs=stmt.executeQuery();
            while(rs.next()){
                professor=new Professor();
                professor.setId(rs.getInt("id"));
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
                professor.setSalario(rs.getDouble("salario"));
            }
            stmt.close();
            con.close();
        }catch(SQLException e){
            System.out.println("Erro getProfessor bi: "+e.getMessage());
        }
        return professor;
    }
    
    
    public static Professor getProfessorID(int prof_id){
        Connection con=Conexaoo.getConnection();
        String select="SELECT * FROM professor WHERE id=?";
        Professor professor=null;
        try{
            PreparedStatement stmt=con.prepareStatement(select);
            stmt.setInt(1,prof_id);
            ResultSet rs=stmt.executeQuery();
            while(rs.next()){
                professor=new Professor();
                professor.setId(rs.getInt("id"));
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
                professor.setSalario(rs.getDouble("salario"));
            }
            stmt.close();
            con.close();
        }catch(SQLException e){
            System.out.println("Erro getProfessor id: "+e.getMessage());
        }
        return professor;
    }
    
    public static Professor getProfessorNUIT(final String NUIT){
        Connection con=Conexaoo.getConnection();
        String select="SELECT * FROM professor WHERE NUIT=?";
        Professor professor=null;
        try{
            PreparedStatement stmt=con.prepareStatement(select);
            stmt.setString(1,NUIT);
            ResultSet rs=stmt.executeQuery();
            while(rs.next()){
                professor=new Professor();
                professor.setId(rs.getInt("id"));
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
                professor.setSalario(rs.getDouble("salario"));
            }
            stmt.close();
            con.close();
        }catch(SQLException e){
            System.out.println("Erro getProfessor nuit: "+e.getMessage());
        }
        return professor;
    }
    
    public static ArrayList<Professor> getProfessores(){
        Connection con=Conexaoo.getConnection();
        String select="SELECT * FROM professor order by nome;";
        ArrayList<Professor> professores=new ArrayList();
        try{
            PreparedStatement stmt=con.prepareStatement(select);
            ResultSet rs=stmt.executeQuery();
            while(rs.next()){
                Professor professor=new Professor();
                professor.setId(rs.getInt("id"));
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
                professor.setSalario(rs.getDouble("salario"));
                professores.add(professor);
            }
            stmt.close();
            con.close();
        }catch(SQLException e){
            System.out.println("Erro getProfessores: "+e.getMessage());
        }
        return professores;
    }
    
    public static void updateProfessor(Professor professor){
        Connection con=Conexaoo.getConnection();
        String select="UPDATE professor SET nome=?,apelido=?,BI=?,nascimento=?,sexo=?,nivel=?,morada=?,telefone=?,email=?,salario=?,NUIT=? WHERE id=?;";
        try{
            PreparedStatement stmt=con.prepareStatement(select);
            stmt.setString(1,professor.getNome());
            stmt.setString(2,professor.getApelido());
            stmt.setString(3,professor.getBI());
            stmt.setString(4,professor.getNascimento());
            stmt.setString(5,professor.getSexo());
            stmt.setString(6,professor.getNivel());
            stmt.setString(7,professor.getMorada());
            stmt.setString(8,professor.getTelefone());
            stmt.setString(9,professor.getEmail());
            stmt.setDouble(10,professor.getSalario());
            stmt.setString(11,professor.getNUIT());
            stmt.setInt(12,professor.getId());
            stmt.execute();
            
            stmt.close();
            con.close();
        }catch(SQLException e){
            System.out.println("Erro updateProfessor: "+e.getMessage());
        }
    }
    
    public static void setProfessor(Professor professor){
        Connection con=Conexaoo.getConnection();
        String select="insert into professor (nome,apelido,BI,nascimento,sexo,nivel,morada,telefone,email,NUIT,salario)"
                + "values (?,?,?,?,?,?,?,?,?,?,?)";
        try{
            PreparedStatement stmt=con.prepareStatement(select);
            stmt.setString(1,professor.getNome());
            stmt.setString(2,professor.getApelido());
            stmt.setString(3,professor.getBI());
            stmt.setString(4,professor.getNascimento());
            stmt.setString(5,professor.getSexo());
            stmt.setString(6,professor.getNivel());
            stmt.setString(7,professor.getMorada());
            stmt.setString(8,professor.getTelefone());
            stmt.setString(9,professor.getEmail());
            stmt.setString(10,professor.getNUIT());
            stmt.setDouble(11,professor.getSalario());
            stmt.execute(); 
            
            stmt.close();
            con.close();
        }catch(SQLException e){
            System.out.println("Erro setProfessor: "+e.getMessage());
        }
    }
    
    public static boolean delProfessor(int professor_id){
        Connection con=Conexaoo.getConnection();
        String delete1="delete from acesso where professor_id=?";
        String delete2="DELETE FROM professor WHERE id=?;";
        try{
            if(getProfessores().size()>1){
                PreparedStatement stmt=con.prepareStatement(delete1);
                stmt.setInt(1,professor_id);
                stmt.execute();

                stmt=con.prepareStatement(delete2);
                stmt.setInt(1,professor_id);
                stmt.execute(); 

                stmt.close();
                con.close();
            }
            return true;
        }catch(SQLException e){
            System.out.println("Erro delProfessor: "+e.getMessage());
            return false;
        }
    }
    
    public static void inscricaoProfessorDisc(Professor professor,int disc_id){
        Connection con=Conexaoo.getConnection();
        String select="insert into ensina values(?,?,?);";
        try{
            PreparedStatement stmt=con.prepareStatement(select);
            Date data=Calendar.getInstance().getTime();
            String dt=new SimpleDateFormat("yyyy-MM-dd").format(data);
            stmt.setString(1,dt);
            stmt.setInt(2,professor.getId());
            stmt.setInt(3,disc_id);
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
            stmt.setInt(1,prof_id);
            stmt.setInt(2,disc_id);
            stmt.execute(); 
            
            stmt.close();
            con.close();
            
            return true;
        }catch(SQLException e){
            System.out.println("Erro delInscricaoProfessor: "+e.getMessage());
            return false;
        }
    }
    
}
