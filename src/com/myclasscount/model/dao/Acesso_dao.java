/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myclasscount.model.dao;

import com.myclasscount.model.Acesso;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author CUINIC4
 */

public class Acesso_dao {
    
    
    public static Acesso getAcesso(String user,String psw){
        Connection con=Conexaoo.getConnection();
        String select="SELECT * FROM myclasscount.acesso WHERE username=? and codigo=?";
        Acesso acesso=null;
        try{
            PreparedStatement stmt=con.prepareStatement(select);
            stmt.setString(1,user);
            stmt.setString(2,psw);
            ResultSet rs=stmt.executeQuery();
            while(rs.next()){
                acesso=new Acesso();
                acesso.setId(Integer.parseInt(rs.getString("id")));
                acesso.setUsername(rs.getString("username"));
                acesso.setCodigo(rs.getString("codigo"));
                acesso.setPergunta(rs.getString("pergunta"));
                acesso.setResposta(rs.getString("rsposta"));
                acesso.setProfessor_id(Integer.parseInt("professor_id"));
            }
            stmt.close();
            rs.close();
            con.close();
        }catch(SQLException e){
        
        }
        return acesso;
    }
    
    public static Acesso getAcesso(String user){
        Connection con=Conexaoo.getConnection();
        String select="SELECT * FROM myclasscount.acesso WHERE username=?";
        Acesso acesso=null;
        try{
            PreparedStatement stmt=con.prepareStatement(select);
            stmt.setString(1,user);
            ResultSet rs=stmt.executeQuery();
            while(rs.next()){
                acesso=new Acesso();
                acesso.setId(Integer.parseInt(rs.getString("id")));
                acesso.setUsername(rs.getString("username"));
                acesso.setCodigo(rs.getString("codigo"));
                acesso.setPergunta(rs.getString("pergunta"));
                acesso.setResposta(rs.getString("resposta"));
                acesso.setProfessor_id(Integer.parseInt(rs.getString("professor_id")));
            }
            stmt.close();
            rs.close();
            con.close();
        }catch(SQLException e){
        
        }
        return acesso;
    }
    
    public static void mudarSenha(String codigo,int id){
        Connection con=Conexaoo.getConnection();
        String select="UPDATE acesso SET codigo=? WHERE id=?";
        try{
            PreparedStatement stmt=con.prepareStatement(select);
            stmt.setString(1,codigo);
            stmt.setString(2,""+id);
            stmt.execute();
            stmt.close();
            con.close();
        }catch(SQLException e){
        
        }
        
    }
    
}
