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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author CUINIC4
 */
public class Observacao_dao {
    
    public static ArrayList<Observacao> getObservacoes(){
        Connection con=Conexaoo.getConnection();
        String select="SELECT * FROM observacao;";
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
        String insert="insert into observacao(id,professor_id,aluno_id,titulo,nota,dateO) values(?,?,?,?,?,?);";
        String select="SELECT max(id) as 'id' FROM observacao;";
        try{
            PreparedStatement stmt=con.prepareStatement(select);
            stmt=con.prepareStatement(select);
            ResultSet rs=stmt.executeQuery();
            String id="";
            while(rs.next()){
                id=rs.getString("id");
            }
            stmt=con.prepareStatement(insert);
            if(id==null){
                stmt.setInt(1,1);
            }else {
                stmt.setInt(1,Integer.parseInt(id)+1);
            }
            stmt.setInt(2,observ.getProfessor_id());
            stmt.setInt(3,observ.getAluno_id());
            stmt.setString(4,observ.getTitulo());
            stmt.setString(5,observ.getNota());
            Date data=Calendar.getInstance().getTime();
            String dt=new SimpleDateFormat("yyyy-MM-dd").format(data);
            stmt.setString(6, dt);
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
