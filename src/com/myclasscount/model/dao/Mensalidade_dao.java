/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myclasscount.model.dao;

import com.myclasscount.model.Mensalidade;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author CUINIC4
 */
public class Mensalidade_dao {
    public static Mensalidade getMensalidade(int id){
        Connection con=Conexaoo.getConnection();
        String select="SELECT * FROM myclasscount.mensalidade WHERE id=? order by dataP;";
        Mensalidade mensalidade=null;
        try{
            PreparedStatement stmt=con.prepareStatement(select);
            stmt.setInt(1,id);
            ResultSet rs=stmt.executeQuery();
            while(rs.next()){
                mensalidade=new Mensalidade();
                mensalidade.setId(rs.getInt("id"));
                mensalidade.setEstado(rs.getString("estado"));
                mensalidade.setDataPagamento(rs.getDate("dataP"));
                mensalidade.setValor(rs.getDouble("valor"));
                mensalidade.setAluno_id(rs.getInt("aluno_id"));
            }
            stmt.close();
            con.close();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return mensalidade;
    }
    
    public static ArrayList<Mensalidade> getMensalidades(){
        Connection con=Conexaoo.getConnection();
        String select="SELECT * FROM myclasscount.mensalidade order by dataP;";
        ArrayList<Mensalidade> mensalidades=new ArrayList();
        try{
            PreparedStatement stmt=con.prepareStatement(select);
            ResultSet rs=stmt.executeQuery();
            while(rs.next()){
                Mensalidade mensalidade=new Mensalidade();
                mensalidade.setId(rs.getInt("id"));
                mensalidade.setEstado(rs.getString("estado"));
                mensalidade.setDataPagamento(rs.getDate("dataP"));
                mensalidade.setValor(rs.getDouble("valor"));
                mensalidade.setAluno_id(rs.getInt("aluno_id"));
                mensalidades.add(mensalidade);
            }
            stmt.close();
            con.close();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return mensalidades;
    
    }
    
    public static ArrayList<Mensalidade> getMensalidadesDiferentes(){
        Connection con=Conexaoo.getConnection();
        String select="SELECT  * FROM mensalidade group by year(dataP) order by dataP ;";
        ArrayList<Mensalidade> mensalidades=new ArrayList();
        try{
            PreparedStatement stmt=con.prepareStatement(select);
            ResultSet rs=stmt.executeQuery();
            while(rs.next()){
                Mensalidade mensalidade=new Mensalidade();
                mensalidade.setId(rs.getInt("id"));
                mensalidade.setEstado(rs.getString("estado"));
                mensalidade.setDataPagamento(rs.getDate("dataP"));
                mensalidade.setValor(rs.getDouble("valor"));
                mensalidade.setAluno_id(rs.getInt("aluno_id"));
                mensalidades.add(mensalidade);
            }
            stmt.close();
            con.close();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return mensalidades;
    }
    
    public static ArrayList<Mensalidade> getMensalidades(int aluno_id){
        Connection con=Conexaoo.getConnection();
        String select="SELECT * FROM myclasscount.mensalidade WHERE aluno_id=? order by dataP;";
        ArrayList<Mensalidade> mensalidades=new ArrayList();
        try{
            PreparedStatement stmt=con.prepareStatement(select);
            stmt.setInt(1,aluno_id);
            ResultSet rs=stmt.executeQuery();
            while(rs.next()){
                Mensalidade mensalidade=new Mensalidade();
                mensalidade.setId(rs.getInt("id"));
                mensalidade.setEstado(rs.getString("estado"));
                mensalidade.setDataPagamento(rs.getDate("dataP"));
                mensalidade.setValor(rs.getDouble("valor"));
                mensalidade.setAluno_id(rs.getInt("aluno_id"));
                mensalidades.add(mensalidade);
            }
            stmt.close();
            con.close();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return mensalidades;
    }
    
    public static ArrayList<Mensalidade> getMensalidades(String ano){
        Connection con=Conexaoo.getConnection();
        String select="SELECT * FROM myclasscount.mensalidade where year(dataP)=? order by dataP;";
        ArrayList<Mensalidade> mensalidades=new ArrayList();
        try{
            PreparedStatement stmt=con.prepareStatement(select);
            stmt.setString(1,ano);
            ResultSet rs=stmt.executeQuery();
            while(rs.next()){
                Mensalidade mensalidade=new Mensalidade();
                mensalidade.setId(rs.getInt("id"));
                mensalidade.setEstado(rs.getString("estado"));
                mensalidade.setDataPagamento(rs.getDate("dataP"));
                mensalidade.setValor(rs.getDouble("valor"));
                mensalidade.setAluno_id(rs.getInt("aluno_id"));
                mensalidades.add(mensalidade);
            }
            stmt.close();
            con.close();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return mensalidades;
    }
    
    public static ArrayList<Mensalidade> getMensalidades(String mes,String ano){
        Connection con=Conexaoo.getConnection();
        String select="SELECT * FROM myclasscount.mensalidade where year(dataP)=? and month(dataP)=? order by dataP;";
        ArrayList<Mensalidade> mensalidades=new ArrayList();
        try{
            PreparedStatement stmt=con.prepareStatement(select);
            stmt.setString(1,mes);
            stmt.setString(2,ano);
            ResultSet rs=stmt.executeQuery();
            while(rs.next()){
                Mensalidade mensalidade=new Mensalidade();
                mensalidade.setId(rs.getInt("id"));
                mensalidade.setEstado(rs.getString("estado"));
                mensalidade.setDataPagamento(rs.getDate("dataP"));
                mensalidade.setValor(rs.getDouble("valor"));
                mensalidade.setAluno_id(rs.getInt("aluno_id"));
                mensalidades.add(mensalidade);
            }
            stmt.close();
            con.close();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return mensalidades;
    }
    
    public static void updateMensalidade(Mensalidade mensalidade){
        Connection con=Conexaoo.getConnection();
        String select="UPDATE myclasscount.mensalidade SET estado=?,valor=? WHERE id=?;";
        try{
            PreparedStatement stmt=con.prepareStatement(select);
            stmt.setString(1,mensalidade.getEstado());
            stmt.setDouble(2,mensalidade.getValor());
            stmt.setInt(3,mensalidade.getId());
            stmt.execute();
            
            stmt.close();
            con.close();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
    
    public static void setMensalidade(int aluno_id, Date dataInicial){
        Connection con=Conexaoo.getConnection();
        String select="CALL myclasscount.inserir_mensalidade(?,?);";
        try{
            PreparedStatement stmt=con.prepareStatement(select);
            stmt.setInt(1,aluno_id);
            stmt.setString(2,new SimpleDateFormat("yyyy-MM-dd").format(dataInicial));
            stmt.execute(); 
            
            stmt.close();
            con.close();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
    
}
