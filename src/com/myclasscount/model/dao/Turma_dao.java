/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myclasscount.model.dao;

import com.myclasscount.model.Aluno;
import com.myclasscount.model.Horario;
import com.myclasscount.model.Professor;
import com.myclasscount.model.Turma;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author CUINIC4
 */
public class Turma_dao {
    
    public static Turma getTurma(int turma_id){
        Connection con=Conexaoo.getConnection();
        String select="SELECT * FROM myclasscount.turma where id=?;";
        Turma turma=null;
        try{
            PreparedStatement stmt=con.prepareStatement(select);
            stmt.setString(1,String.valueOf(turma_id));
            ResultSet rs=stmt.executeQuery();
            while(rs.next()){
                turma=new Turma();
                turma.setId(Integer.parseInt(rs.getString("id")));
                turma.setNome(rs.getString("nome"));
                turma.setClasse(rs.getString("classe"));
                turma.setHorarios(getHorario(turma.getId()));
                turma.setProfessor(getProfessor_turma(turma.getId()));
                turma.setAlunos(getAluno_turma(turma.getId()));
            }
            stmt.close();
            con.close();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return turma;
    }
    
    public static Turma getTurma(String nome){
        Connection con=Conexaoo.getConnection();
        String select="SELECT * FROM myclasscount.turma where nome=?;";
        Turma turma=null;
        try{
            PreparedStatement stmt=con.prepareStatement(select);
            stmt.setString(1,nome);
            ResultSet rs=stmt.executeQuery();
            while(rs.next()){
                turma=new Turma();
                turma.setId(Integer.parseInt(rs.getString("id")));
                turma.setNome(rs.getString("nome"));
                turma.setClasse(rs.getString("classe"));
                turma.setHorarios(getHorario(turma.getId()));
                turma.setProfessor(getProfessor_turma(turma.getId()));
                turma.setAlunos(getAluno_turma(turma.getId()));
            }
            stmt.close();
            con.close();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return turma;
    }
    
    public static ArrayList<Turma> getTurmas(){
        Connection con=Conexaoo.getConnection();
        String select="SELECT * FROM myclasscount.turma;";
        ArrayList<Turma> turmas=new ArrayList();
        try{
            PreparedStatement stmt=con.prepareStatement(select);
            ResultSet rs=stmt.executeQuery();
            while(rs.next()){
                Turma turma=new Turma();
                turma.setId(Integer.parseInt(rs.getString("id")));
                turma.setNome(rs.getString("nome"));
                turma.setClasse(rs.getString("classe"));
                turma.setHorarios(getHorario(turma.getId()));
                turma.setProfessor(getProfessor_turma(turma.getId()));
                turma.setAlunos(getAluno_turma(turma.getId()));
                turmas.add(turma);
            }
            stmt.close();
            con.close();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return turmas;
    }
    
    public static void setTurma(Turma turma){
        Connection con=Conexaoo.getConnection();
        String select="call myclasscount.inserir_turma_horario(?, ?, ?, ?, ?, ?);";
        try{
            PreparedStatement stmt=con.prepareStatement(select);
            stmt.setString(1,turma.getNome());
            stmt.setString(2,turma.getClasse());
            stmt.setString(3,turma.getHorario().getTurno());
            stmt.setString(4,turma.getHorario().getHora_inicio());
            stmt.setString(5,turma.getHorario().getHora_fim());
            stmt.setString(6,turma.getHorario().getDia_semana());
            stmt.execute(); 
            
            stmt.close();
            con.close();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
    
    public static void setAluno_turma(String nome,int aluno_id){
        Connection con=Conexaoo.getConnection();
        String select="call myclasscount.inserir_aluno_turma(?, ?);";
        Turma turma=getTurma(nome);
        try{
            PreparedStatement stmt=con.prepareStatement(select);
            stmt.setString(1,String.valueOf(aluno_id));
            stmt.setString(2,String.valueOf(turma.getId()));
            stmt.execute(); 
            
            stmt.close();
            con.close();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
    
    public static ArrayList<Aluno> getAluno_turma(int turma_id){
        Connection con=Conexaoo.getConnection();
        String select="SELECT * FROM myclasscount.aluno_turma where turma_id=?;";
        ArrayList<Aluno> alunos=new ArrayList();
        try{
            PreparedStatement stmt=con.prepareStatement(select);
            stmt.setString(1,String.valueOf(turma_id));
            ResultSet rs=stmt.executeQuery();
            while(rs.next()){
                Aluno a=Aluno_dao.getAlunoID(Integer.parseInt(rs.getString("aluno_id")));
                alunos.add(a);
            }
            stmt.close();
            con.close();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return alunos;
    }
    
    public static void delAluno_turma(Turma turma,int aluno_id){
        Connection con=Conexaoo.getConnection();
        String select="delete from aluno_turma where turma_id=? and aluno_id=?";
        try{
            PreparedStatement stmt=con.prepareStatement(select);
            stmt.setString(1,String.valueOf(turma.getId()));
            stmt.setString(2,String.valueOf(aluno_id));
            stmt.execute(); 
            
            stmt.close();
            con.close();
        }catch(SQLException e){
            System.out.println("Erro ao remover aluno da turma: "+e.getMessage());
        }
    }
    
    public static void delHorario(Turma turma,String dia_semana){
        Connection con=Conexaoo.getConnection();
        String select="delete from horario where turma_id=? and dia_semana=?";
        try{
            PreparedStatement stmt=con.prepareStatement(select);
            stmt.setString(1,String.valueOf(turma.getId()));
            stmt.setString(2,dia_semana);
            stmt.execute(); 
            
            stmt.close();
            con.close();
        }catch(SQLException e){
            System.out.println("Erro ao remover horario da turma: "+e.getMessage());
        }
    }
    
    public static ArrayList<Horario> getHorario(int turma_id){
        Connection con=Conexaoo.getConnection();
        String select="SELECT * FROM myclasscount.horario where turma_id=?;";
        ArrayList<Horario> horarios=new ArrayList();
        try{
            PreparedStatement stmt=con.prepareStatement(select);
            stmt.setString(1,String.valueOf(turma_id));
            ResultSet rs=stmt.executeQuery();
            while(rs.next()){
                Horario horario=new Horario();
                horario.setId(Integer.parseInt(rs.getString("id")));
                horario.setTurno(rs.getString("turno"));
                horario.setHora_inicio(rs.getString("hora_inicio"));
                horario.setHora_fim(rs.getString("hora_fim"));
                horario.setDia_semana(rs.getString("dia_semana"));
                horario.setTurma_id(Integer.parseInt(rs.getString("turma_id")));
                horarios.add(horario);
            }
            stmt.close();
            con.close();
        }catch(SQLException e){
            System.out.println("Erro ao retornar horarios: "+e.getMessage());
        }
        return horarios;
    }
    
    public static void setProfessor_turma(int turma_id,int prof_id){
        Connection con=Conexaoo.getConnection();
        String select="call myclasscount.inserir_professor_turma(?, ?);";
        try{
            PreparedStatement stmt=con.prepareStatement(select);
            stmt.setString(1,String.valueOf(prof_id));
            stmt.setString(2,String.valueOf(turma_id));
            stmt.execute(); 
            
            stmt.close();
            con.close();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
     
    public static Professor getProfessor_turma(int turma_id){
        Connection con=Conexaoo.getConnection();
        String select="SELECT * FROM myclasscount.professor_turma where turma_id=?;";
        Professor prof=null;
        try{
            PreparedStatement stmt=con.prepareStatement(select);
            stmt.setString(1,String.valueOf(turma_id));
            ResultSet rs=stmt.executeQuery();
            while(rs.next()){
                prof=Professor_dao.getProfessorID(Integer.parseInt(rs.getString("professor_id")));
            }
            stmt.close();
            con.close();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return prof;
    }
    
    public static void updateTurma(Turma turma){
        Connection con=Conexaoo.getConnection();
        String update1="update turma set nome=? where id=?";
        String update2="update professor_turma set professor_id=? where turma_id=?";
        String update3="insert into horario (turno,hora_inicio,hora_fim,dia_semana,turma_id) values(?,?,?,?,?);";
        String update3_1="update horario set turno=?,hora_inicio=?,hora_fim=?,dia_semana=? where id=?";
        String update4="call myclasscount.inserir_aluno_turma(?, ?);";
        try{
            PreparedStatement stmt=con.prepareStatement(update1);
            stmt.setString(1,turma.getNome());
            stmt.setString(2,String.valueOf(turma.getId()));
            stmt.execute();
            stmt.close();
            
            turma.setId(getTurma(turma.getNome()).getId());
            
            stmt=con.prepareStatement(update2);
            stmt.setString(1,String.valueOf(turma.getProfessor().getId()));
            stmt.setString(2,String.valueOf(turma.getId()));
            stmt.execute();
            stmt.close();
            
            if(turma.getHorario()!=null){
                if(turma.getHorario().getId()<=0){
                stmt=con.prepareStatement(update3);
                stmt.setString(5,String.valueOf(turma.getId()));
                }else{
                    stmt=con.prepareStatement(update3_1);
                    stmt.setString(5,String.valueOf(turma.getHorario().getId()));
                }
                stmt.setString(1,turma.getHorario().getTurno());
                stmt.setString(2,turma.getHorario().getHora_inicio());
                stmt.setString(3,turma.getHorario().getHora_fim());
                stmt.setString(4,turma.getHorario().getDia_semana());
                stmt.execute();
            }
            stmt.close();
            
            for(Aluno a:turma.getAlunos()){
                stmt=con.prepareStatement(update4);
                stmt.setString(1,String.valueOf(a.getId()));
                stmt.setString(2,String.valueOf(turma.getId()));
                stmt.execute();
                stmt.close();
            }
            
            con.close();
        }catch(SQLException e){
            System.out.println("Erro , ao actualizar turma: "+e.getMessage());
        }
    }
}
