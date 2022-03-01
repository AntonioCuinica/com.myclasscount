/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myclasscount.model.dao;

import com.myclasscount.model.Disciplina;
import com.myclasscount.model.Observacao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author CUINIC4
 */
public class Observacao_dao {
    
    public static ArrayList<Observacao> getObservacoes(){
        Connection con=Conexaoo.getConnection();
        String select="SELECT * FROM myclasscount.observacao;";
        ArrayList<Observacao> observacao=new ArrayList();
        try{
            PreparedStatement stmt=con.prepareStatement(select);
            ResultSet rs=stmt.executeQuery();
            while(rs.next()){
                Observacao observ=new Observacao();
                observ.setId(rs.getInt("id"));
                observ.setProfessor_id(rs.getInt("professor_id"));
                observ.setAluno_id(rs.getInt("aluno_id"));
                observ.setTitulo(rs.getString("titulo"));
                observ.setNota(rs.getString("nota"));
                observ.setDataObservacao(rs.getString("dateO"));
                observacao.add(observ);
            }
            stmt.close();
            con.close();
        }catch(SQLException e){
            System.out.println("Erro ao retornar observacoes: "+e.getMessage());
        }
        return observacao;
    }
    
    public static void setObservacao(Observacao observ){
        Connection con=Conexaoo.getConnection();
        String select="CALL myclasscount.observacao(?,?,?,?);";
        try{
            PreparedStatement stmt=con.prepareStatement(select);
            stmt.setInt(1,observ.getProfessor_id());
            stmt.setInt(2,observ.getAluno_id());
            stmt.setString(3,observ.getTitulo());
            stmt.setString(4,observ.getNota());
            stmt.execute(); 
            
            stmt.close();
            con.close();
        }catch(SQLException e){
            System.out.println("Erro ao inserir Observacao: "+e.getMessage());
        }
    }
    
     public static void delObservacao(int observ_id){
        Connection con=Conexaoo.getConnection();
        String select="delete from observacao where id=?";
        try{
            PreparedStatement stmt=con.prepareStatement(select);
            stmt.setInt(1,observ_id);
            stmt.execute(); 
            
            stmt.close();
            con.close();
        }catch(SQLException e){
            System.out.println("Erro ao deletar Observacao: "+e.getMessage());
        }
    }
}
